package org.example;

import org.example.service.TestRunnerService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("/spring-context.xml");
        TestRunnerService testRunnerService = context.getBean(TestRunnerService.class);
        testRunnerService.run();
        context.close();
    }
}
