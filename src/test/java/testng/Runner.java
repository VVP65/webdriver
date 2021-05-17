package testng;

import org.testng.TestNG;
import org.testng.xml.XmlSuite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Runner {
    public static void main(String[] args) {
        TestNG testNg = new TestNG();
        XmlSuite suite = new XmlSuite();
        suite.setSuiteFiles(Collections.singletonList("C:\\Users\\Volodymyr_Protsiv\\Desktop\\JavaMentoringProgram\\M6_Webdriver_and_Locators\\hello-webdriver\\src\\test\\resources\\testng.xml"));
        List<XmlSuite> suites = new ArrayList<>();
        suites.add(suite);
        testNg.setXmlSuites(suites);
        testNg.run();
    }
}


