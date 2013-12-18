package exo.scanner.array_test;


/**
 *  Class used to basic_test local variable scanner
 */
public class LocalVariableScannerTestClass {

    public int arrayTest(int unused, Object[] parameter0, Object parameter1, Object unusedTwo, long parameter2, String unusedThree) {
        parameter0[0] = parameter1;
        Object firstDeclaration = null;
        Object secondDeclarationWithExpression = parameter1;
        firstDeclaration.equals(secondDeclarationWithExpression);
        try {
            parameter0.wait(parameter2);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return (int) parameter2;
    }


}
