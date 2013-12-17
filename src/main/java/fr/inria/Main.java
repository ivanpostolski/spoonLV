package fr.inria;

import spoon.processing.ProcessingManager;
import spoon.reflect.Factory;
import spoon.support.DefaultCoreFactory;
import spoon.support.QueueProcessingManager;
import spoon.support.StandardEnvironment;
import spoon.support.builder.SpoonBuildingManager;

import java.io.File;

/**
 * User: Simon
 * Date: 5/29/13
 * Time: 5:44 PM
 */
public class Main {

    public static void main(String [ ] args) throws Exception {
        Main main = new Main();
        main.runSpoon(args[0]);
    }


    protected void runSpoon(String sourceDirectory) {
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
        StatementProcessor processor = new StatementProcessor(new LocalVariableProcessor());
        pm.addProcessor(processor);
        pm.process();
    }
}
