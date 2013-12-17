package exo.scanner.test;

import fr.inria.LocalVariableProcessor;
import fr.inria.StatementProcessor;


/**
 * Class used to test local variable scanner
 */
public class LocalVariableScannerTestClass {

  public void testMethod(String parameter) {
      int firstDeclaration = 0;
      firstDeclaration++;
      LocalVariableProcessor secondDeclaration = new LocalVariableProcessor();
      secondDeclaration.getScanner();
      StatementProcessor  thirdDeclaration = new StatementProcessor(secondDeclaration);
      System.out.println(parameter);
  }
}
