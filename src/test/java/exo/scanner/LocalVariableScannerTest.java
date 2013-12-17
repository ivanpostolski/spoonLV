package exo.scanner;

import exo.scanner.test.LocalVariableScannerTestClass;
import fr.inria.LocalVariableProcessor;
import fr.inria.StatementProcessor;
import org.junit.Test;
import spoon.processing.ProcessingManager;
import spoon.reflect.Factory;
import spoon.support.DefaultCoreFactory;
import spoon.support.QueueProcessingManager;
import spoon.support.StandardEnvironment;
import spoon.support.builder.SpoonBuildingManager;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Unit test for local variable scanner
 */
public class LocalVariableScannerTest  {

    /**
     * Test for declaration, references and parameters from method testMethod at {@link LocalVariableScannerTestClass}
     */
    @Test
    public void testUnOrderedSize() {
        LocalVariableProcessor localVariableProcessor = new LocalVariableProcessor();
        runSpoon("src/test/java/exo/scanner/test",localVariableProcessor);

        Set declarations = new HashSet(localVariableProcessor.getScanner().getLocalDeclarations());
        assertEquals(3,declarations.size());

        Set references = new HashSet(localVariableProcessor.getScanner().getLocalReferences());
        assertEquals(2,references.size());

        Set parameters = new HashSet(localVariableProcessor.getScanner().getLocalParameters());
        assertEquals(1,parameters.size());
    }


    /**
     * Test for order in declaration, references and parameters from method testMethod at {@link LocalVariableScannerTestClass}
     */
    @Test
    public void testWithOrder(){
        LocalVariableProcessor localVariableProcessor = new LocalVariableProcessor();
        runSpoon("src/test/java/exo/scanner/test", localVariableProcessor);

        //Check declarations

        assertEquals("firstDeclaration", localVariableProcessor.getScanner().getLocalDeclarations().get(0).getSimpleName());
        assertEquals("secondDeclaration",localVariableProcessor.getScanner().getLocalDeclarations().get(1).getSimpleName());
        assertEquals("thirdDeclaration",localVariableProcessor.getScanner().getLocalDeclarations().get(2).getSimpleName());
        assertEquals("firstDeclaration",localVariableProcessor.getScanner().getLocalDeclarations().get(3).getSimpleName());
        assertEquals("secondDeclaration",localVariableProcessor.getScanner().getLocalDeclarations().get(4).getSimpleName());
        assertEquals("thirdDeclaration",localVariableProcessor.getScanner().getLocalDeclarations().get(5).getSimpleName());
        assertEquals(6, localVariableProcessor.getScanner().getLocalDeclarations().size());

        //Check References

        assertEquals("firstDeclaration",localVariableProcessor.getScanner().getLocalReferences().get(0).getSimpleName());
        assertEquals("secondDeclaration",localVariableProcessor.getScanner().getLocalReferences().get(1).getSimpleName());
        assertEquals("secondDeclaration",localVariableProcessor.getScanner().getLocalReferences().get(2).getSimpleName());
        assertEquals("firstDeclaration",localVariableProcessor.getScanner().getLocalReferences().get(3).getSimpleName());
        assertEquals("secondDeclaration",localVariableProcessor.getScanner().getLocalReferences().get(4).getSimpleName());
        assertEquals("secondDeclaration",localVariableProcessor.getScanner().getLocalReferences().get(5).getSimpleName());
        assertEquals("secondDeclaration",localVariableProcessor.getScanner().getLocalReferences().get(6).getSimpleName());
        assertEquals(7,localVariableProcessor.getScanner().getLocalReferences().size());

        //Check parameters

        assertEquals("parameter", localVariableProcessor.getScanner().getLocalParameters().get(0).getSimpleName());
        assertEquals("parameter",localVariableProcessor.getScanner().getLocalParameters().get(1).getSimpleName());
        assertEquals(2,localVariableProcessor.getScanner().getLocalParameters().size());

    }



    private void runSpoon(String sourceDirectory, LocalVariableProcessor localVariableProcessor) {
        StandardEnvironment env = new StandardEnvironment();
        env.setComplianceLevel(6);
        env.setVerbose(true);
        env.setDebug(true);

        DefaultCoreFactory f = new DefaultCoreFactory();
        Factory factory = new Factory(f, env);
        SpoonBuildingManager builder = new SpoonBuildingManager(factory);
        for(String dir : sourceDirectory.split(System.getProperty("path.separator")))
            try {
                builder.addInputSource(new File(dir));
                builder.build();
            } catch (Exception e) {
                e.printStackTrace();
            }
        ProcessingManager pm = new QueueProcessingManager(factory);
        StatementProcessor processor = new StatementProcessor(localVariableProcessor);
        pm.addProcessor(processor);
        pm.process();
    }
}
