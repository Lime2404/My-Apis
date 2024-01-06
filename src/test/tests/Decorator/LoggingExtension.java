package Decorator;

import org.apache.commons.lang3.exception.ExceptionContext;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class LoggingExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

    @Override
    public void beforeTestExecution(ExtensionContext context){
        System.out.println("Началось " + context.getDisplayName());
    }

    @Override
    public void afterTestExecution(ExtensionContext context){
        System.out.println("Всё " + context.getDisplayName());
    }

}
