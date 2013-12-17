package exo.scanner;

import spoon.reflect.code.CtLocalVariable;
import spoon.reflect.reference.CtLocalVariableReference;
import spoon.reflect.reference.CtParameterReference;
import spoon.reflect.visitor.CtScanner;

import java.util.ArrayList;
import java.util.List;

/**
 * Scanner made for analyzing local variable declarations and access
 */
public class LocalVariableScanner extends CtScanner {

    private List<CtLocalVariable> localDeclarations;
    private List<CtLocalVariableReference> localReferences;
    private List<CtParameterReference> localParameters;

    public LocalVariableScanner(){
        this.localDeclarations = new ArrayList<CtLocalVariable>();
        this.localParameters = new ArrayList<CtParameterReference>();
        this.localReferences = new ArrayList<CtLocalVariableReference>();
    }

    public List<CtLocalVariable> getLocalDeclarations() {
        return localDeclarations;
    }

    public List<CtParameterReference> getLocalParameters() {
        return localParameters;
    }

    public List<CtLocalVariableReference> getLocalReferences() {
        return localReferences;
    }

    @Override
    public <T> void visitCtLocalVariable(CtLocalVariable<T> localVariable) {
        System.out.println("variable: " + localVariable.getSimpleName()
                + " declared in file " + localVariable.getPosition().getFile() + ":" + localVariable.getPosition().getLine());
        localDeclarations.add(localVariable);
        super.visitCtLocalVariable(localVariable);
    }

    @Override
    public <T> void visitCtLocalVariableReference(CtLocalVariableReference<T> localVariableReference) {
        System.out.println("variable access : " + localVariableReference.getSimpleName());
        localReferences.add(localVariableReference);
    }

    @Override
    public <T> void visitCtParameterReference(CtParameterReference<T> parameterReference) {
        System.out.println("parameter access: " + parameterReference.getSimpleName());
        localParameters.add(parameterReference);
    }

}
