package MKK.base;

import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected static WebDriver driver;
    DesiredCapabilities capabilities;
    ChromeOptions chromeOptions;

    @BeforeScenario
    public void setUp() {
        driver = new ChromeDriver(chromeOptions());
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.get("https://amazon.com.tr");
    }

    @AfterScenario
    public void tearDown() {
        //driver.quit();
    }

    public ChromeOptions chromeOptions() {
        chromeOptions = new ChromeOptions();
        capabilities = DesiredCapabilities.chrome();
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--start-maximized");
        System.setProperty("webdriver.chrome.driver", "properties/driver/chromedriver.exe");
        return chromeOptions;
    }


}