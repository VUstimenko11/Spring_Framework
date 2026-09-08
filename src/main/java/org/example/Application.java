package org.example;

import org.example.config.AppConfig;
import org.example.service.TestRunnerService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(AppConfig.class)) {
            TestRunnerService testRunnerService = context.getBean(TestRunnerService.class);
            testRunnerService.run();
        }
    }
}
