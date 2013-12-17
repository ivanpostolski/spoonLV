package fr.inria;

import exo.scanner.LocalVariableScanner;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtStatement;

/**
 * Processor used for print local variables declaration, access and received parameters.
 */
public class LocalVariableProcessor extends AbstractProcessor<CtStatement> {

    private LocalVariableScanner scanner = new LocalVariableScanner();

    @Override
    public void process(CtStatement element) {
        element.accept(scanner);
    }

    public LocalVariableScanner getScanner(){
        return scanner;
    }
}
