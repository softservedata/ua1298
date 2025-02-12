package com.softserve.hw14;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class RunnerExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
        System.out.println("Test has been started: " + context.getDisplayName());
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        System.out.println("Test has been finished: " + context.getDisplayName());
    }
}