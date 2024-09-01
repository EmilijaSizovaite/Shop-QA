import org.example.Main;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    public void onTestFailure(ITestResult result) {
        Main.takeScreenshot();
    }
}
