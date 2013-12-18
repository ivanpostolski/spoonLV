package fr.inria;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtStatement;

/**
 * Base processor used for decorating.
 * This can be used for just displaying basic information about statements with {@link StatementProcessor}.
 */
public class BaseProcessor extends AbstractProcessor<CtStatement> {

    @Override
    public void process(CtStatement element) {
    }
}
