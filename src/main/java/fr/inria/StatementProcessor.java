package fr.inria;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtStatement;

/**
 * An statement processor uses for decorating other processors with the output of each analyzed statement
 */
public class StatementProcessor extends AbstractProcessor<CtStatement> {

    private AbstractProcessor decoratedAbstractProcessor;

    public StatementProcessor(AbstractProcessor decoratedAbstractProcessor){
        this.decoratedAbstractProcessor = decoratedAbstractProcessor;
    }

    int i = 0;
    public void process(CtStatement element) {
        i++;
        System.out.println("Statement: "+ i);
        System.out.println(element);
        this.decoratedAbstractProcessor.process(element);
        System.out.println("-----------------------------------");
    }
}
