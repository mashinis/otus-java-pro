package ru.mashinis;

import ru.mashinis.annotation.AfterSuite;
import ru.mashinis.annotation.BeforeSuite;
import ru.mashinis.annotation.Test;

public class SimpleAnnotationProcessor {

    @BeforeSuite
    public static void beforeSuite() {
        System.out.println("BeforeSuite method");
    }

    @Test(priority = 1)
    public static void test1() {
        System.out.println("Test 1 with priority 1");
    }

    @Test(priority = 8)
    public static void test2() {
        System.out.println("Test 2 with priority 8");
    }

    @Test(priority = 3)
    public static void test3() {
        System.out.println("Test 3 with priority 3");
        throw new RuntimeException("Test 3 failed");
    }

    @Test(priority = 10)
    public static void test4() {
        System.out.println("Test 4 with priority 10");
    }

    @AfterSuite
    public static void afterSuite() {
        System.out.println("AfterSuite method");
    }
}
