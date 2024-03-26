package ru.mashinis;

import ru.mashinis.annotation.AfterSuite;
import ru.mashinis.annotation.BeforeSuite;
import ru.mashinis.annotation.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class MyTestFramework {
    public static void runTests(Class<?> testClass) {
        Method beforeSuiteMethod = null;
        Method afterSuiteMethod = null;
        List<Method> testMethods = new ArrayList<>();

        // Получаем все методы класса и отбираем методы с нужными аннотациями
        Method[] methods = testClass.getDeclaredMethods();
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

        // Сортируем тестовые методы по приоритету
        testMethods.sort(Comparator.comparingInt(method -> method.getAnnotation(Test.class).priority()));

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
                    System.out.println("Test failed: " + testMethod.getName());
                    Throwable originalException = e.getCause(); // Получаем оригинальное исключение
                    if (originalException != null) {
                        System.out.println("Original exception: " + originalException.getMessage());
                        originalException.printStackTrace();
                    } else {
                        e.printStackTrace();
                    }
                }
            }

            if (afterSuiteMethod != null) {
                afterSuiteMethod.invoke(null);
            }

            System.out.println("Tests summary:");
            System.out.println("Successful: " + successfulTests);
            System.out.println("Failed: " + failedTests);
            System.out.println("Total: " + (successfulTests + failedTests));
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

