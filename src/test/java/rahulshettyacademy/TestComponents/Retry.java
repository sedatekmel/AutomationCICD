package rahulshettyacademy.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

    int count = 0;
    int maxRetry = 1;

    //Bu method true dönerse retry yapar. False dönerse yapmaz.
    @Override
    public boolean retry(ITestResult result) {
        if (count < maxRetry) {
            count++; 
            return true;
        }
        return false;
    }
}
