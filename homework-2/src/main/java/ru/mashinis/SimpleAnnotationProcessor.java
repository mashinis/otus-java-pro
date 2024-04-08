package ru.mashinis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.mashinis.annotation.AfterSuite;
import ru.mashinis.annotation.BeforeSuite;
import ru.mashinis.annotation.Test;

public class SimpleAnnotationProcessor {
    private static final Logger logger = LogManager.getLogger(MyTestFramework.class);

    @BeforeSuite
    public static void beforeSuite() {
        logger.info("BeforeSuite method");
    }

    @Test(priority = 1)
    public static void test1() {
        logger.info("Test 1 with priority 1");
    }

    @Test(priority = 8)
    public static void test2() {
        logger.info("Test 2 with priority 8");
    }

    @Test(priority = 3)
    public static void test3() {
        logger.info("Test 3 with priority 3");
        throw new RuntimeException("Test 3 failed");
    }

    @Test(priority = 10)
    public static void test4() {
        logger.info("Test 4 with priority 10");
    }

    @AfterSuite
    public static void afterSuite() {
        logger.info("AfterSuite method");
    }
}
