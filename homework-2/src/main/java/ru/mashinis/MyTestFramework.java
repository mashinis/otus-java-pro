package ru.mashinis;

import ru.mashinis.annotation.AfterSuite;
import ru.mashinis.annotation.BeforeSuite;
import ru.mashinis.annotation.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class MyTestFramework {
    private static Method beforeSuiteMethod = null;
    private static Method afterSuiteMethod = null;
    private static List<Method> testMethods = null;
    private static final Logger logger = LogManager.getLogger(MyTestFramework.class);

    public static void runTests(Class<?> testClass) {

        testMethods = methodMyAnnotation(testClass.getDeclaredMethods());
        testMethods.sort(Comparator.comparingInt(method -> method.getAnnotation(Test.class).priority()));
        runTesting();
    }

    // Получаем все методы класса и отбираем методы с нужными аннотациями
    private static List<Method> methodMyAnnotation(Method[] methods) {
        List<Method> testMethods = new ArrayList<>();
        for (Method method : methods) {
            if (method.isAnnotationPresent(BeforeSuite.class)) {
                if (beforeSuiteMethod == null) {
                    beforeSuiteMethod = method;
                } else {
                    throw new RuntimeException("More than one method annotated with @BeforeSuite");
                }
            } else if (method.isAnnotationPresent(AfterSuite.class)) {
                if (afterSuiteMethod == null) {
                    afterSuiteMethod = method;
                } else {
                    throw new RuntimeException("More than one method annotated with @AfterSuite");
                }
            } else if (method.isAnnotationPresent(Test.class)) {
                testMethods.add(method);
            }
        }
        return testMethods;
    }

    private static void runTesting() {
        // Выполняем тесты
        try {
            if (beforeSuiteMethod != null) {
                beforeSuiteMethod.invoke(null);
            }

            int successfulTests = 0;
            int failedTests = 0;

            for (Method testMethod : testMethods) {
                try {
                    testMethod.invoke(null);
                    successfulTests++;
                } catch (InvocationTargetException e) {
                    failedTests++;
                    logger.info(String.format("Test failed: %s", testMethod.getName()));
                    Throwable originalException = e.getCause(); // Получаем оригинальное исключение
                    if (originalException != null) {
                        logger.info(String.format("Original exception: %s", originalException.getMessage()));
                        originalException.printStackTrace();
                    } else {
                        logger.error(e);
                        e.printStackTrace();
                    }
                }
            }

            if (afterSuiteMethod != null) {
                afterSuiteMethod.invoke(null);
            }

            logger.info("Tests summary:");
            logger.info(String.format("Successful: %s", successfulTests));
            logger.info(String.format("Failed: %s", failedTests));
            logger.info(String.format("Total: %s", (successfulTests + failedTests)));
        } catch (IllegalAccessException | InvocationTargetException e) {
            logger.error(e);
            e.printStackTrace();
        }
    }
}

