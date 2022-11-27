package testcases.test;

import org.testng.annotations.*;

public class demo2 {
    // test case 1
    @Test
    public void testCase1() {
        System.out.println("Testcase in DEMO2");
    }
    // test case 2
    @Test
    public void testCase2() {
        System.out.println("Test2 in demo 2");
    }
    @BeforeMethod
    public void beforeMethod() {
        System.out.println("DEMO2 in beforeMethod");
    }
    @AfterMethod
    public void afterMethod() {
        System.out.println("DEMO2 in afterMethod");
    }
    @BeforeClass
    public void beforeClass() {
        System.out.println("DEMO2 in beforeClass");
    }
    @AfterClass
    public void afterClass() {
        System.out.println("DEMO2 in afterClass");
    }
    @BeforeTest
    public void beforeTest() {
        System.out.println("DEMO2 in beforeTest");
    }
    @AfterTest
    public void afterTest() {
        System.out.println("DEMO2 in afterTest");
    }
    @BeforeSuite
    public void beforeSuite() {
        System.out.println("DEMO2 in beforeSuite");
    }
    @AfterSuite
    public void afterSuite() {
        System.out.println("DEMO2 in afterSuite");
    }
}
