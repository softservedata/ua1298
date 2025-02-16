package com.softserve.edu.framework.tests;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class TestResultExtension implements AfterTestExecutionCallback {
    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        boolean testResult = context.getExecutionException().isPresent();
        TestRunner.isTestSuccessful = !testResult;
    }
}
