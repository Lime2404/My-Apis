package Decorator;

import org.apache.commons.lang3.exception.ExceptionContext;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class LoggingExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {
// ниже мы реализуем аннотации которые помогают нам добавить подпись при логировании ошибок
    @Override
    public void beforeTestExecution(ExtensionContext context){
        System.out.println("Началось " + context.getDisplayName());
    }

    @Override
    public void afterTestExecution(ExtensionContext context){
        System.out.println("Всё " + context.getDisplayName());
    }
// добавим теперь анотацию @ExtendWith в класс ReqresNoPogoTest
}
