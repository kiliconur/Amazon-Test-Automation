package MKK.base;

import com.thoughtworks.gauge.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BasePage extends BaseTest{


    protected WebDriver driver;
    protected Logger logger = LoggerFactory.getLogger(getClass());
    WebDriverWait wait;
    private HashMap<String, By> map;
    private List<String> stringList = new ArrayList<>();

    Locators locators = new Locators();

    public BasePage() {
        this.driver = BaseTest.driver;
        this.wait = new WebDriverWait(driver, 30);
        initLocators();
    }

    public void initLocators() {
        map = locators.getMap();
    }
    public By getElementFromMap(String key) {
        return map.get(key);
    }
    public WebElement findElement(String key) {
        return driver.findElement(getElementFromMap(key));
    }

    public List<WebElement> findElements(String key) {
        return driver.findElements(getElementFromMap(key));
    }

    @Step("<key> elementine tikla")
    public void clickElement(String key) {
        findElement(key).click();
    }

    @Step("<key> alanina <text> yaz")
    public void clickElement(String key,String text) {
        findElement(key).sendKeys(text);
    }

    @Step("<key> select listesinden <item> text secimi yapilir")
    public void selectElement(String key, String item) {
        Select select = new Select(findElement(key));
        select.selectByVisibleText(item);
    }

    @Step("<time> saniye bekle")
    public void waitBySeconds(int time) throws InterruptedException {
        Thread.sleep(1000L *time);
    }

    @Step("<key> elementlerinden ilk <count> tanesini listeye kaydet")
    public void saveElementsToList(String key, String count) {
        List<WebElement> elements = findElements(key);
        int elementCount;
        if (count.equals("tüm")) {
            elementCount = elements.size();
        }
        else {
            elementCount = Integer.parseInt(count);
        }
        for (int i = 0; i < elementCount; i++) {
            stringList.add(elements.get(i).getText());
            logger.info(stringList.get(i)+" texti listeye kaydedildi");
        }
    }

    @Step("listedeki elemanlari <fileName> dosyasina yazdir ve listeyi temizle")
    public void saveElementsToList(String fileName) {
        try {
            FileWriter myWriter = new FileWriter(fileName);
            for (String str:stringList) {
                myWriter.write(str + System.lineSeparator());
            }
            stringList.clear();
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
