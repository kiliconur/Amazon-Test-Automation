package MKK.pages;

import MKK.base.BasePage;
import com.thoughtworks.gauge.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;


public class SearchPage extends BasePage {

    @Step("<key> elementlerinin <key2> puan elementlerinden en yükseklerinin <key3> fiyat elementlerinin en ucuzuna tikla")
    public void clickHighestScoreAndCheapestElement (String key, String key2, String key3) {
        List<WebElement> elementList = findElements(key);
        List<WebElement> pointList = findElements(key2);
        List<WebElement> priceList = findElements(key3);
        List<Integer> highestRankElementIndexes = findHighestNumbersInCommentElements(pointList);
        int cheapestElementIndex = findCheapestElementFromPredeterminedList(priceList,highestRankElementIndexes);
        elementList.get(cheapestElementIndex).click();
    }

    public List<Integer> findHighestNumbersInCommentElements(List<WebElement> pointList) {
        List<Double> doubleList = new ArrayList<>();
        for (WebElement element:pointList) {
            doubleList.add(splitWordAndGetRightMostNumberFromText(element.getAttribute("innerText"),"üzerinden "));
        }
        return findHighestNumberIndexesFromDoubleList(doubleList);
    }

    public double splitWordAndGetRightMostNumberFromText(String text, String splitWord) {
        text = text.replaceAll(",",".");
        String[] newString = text.split(splitWord);
        return Double.parseDouble(newString[newString.length-1]);
    }

    public List<Integer> findHighestNumberIndexesFromDoubleList (List<Double> list) {
        double max = 0;
        List<Integer> integerList = new ArrayList<>();
        for (Double number:list) {
            if (number > max) {
                max = number;
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(max)) {
                integerList.add(i);
            }
        }
        return integerList;
    }

    public int findCheapestElementFromPredeterminedList(List<WebElement> priceList,List<Integer> highestRankElementIndexes) {
        int minPriceIndex = 0;
        int minPrice;
        int currentPrice;
        for (int i = 0; i < highestRankElementIndexes.size(); i++) {
            minPrice = convertStringToNumber(priceList.get(minPriceIndex).getText());
            if (priceList.get(highestRankElementIndexes.get(i)).getText().equals("")) {
                currentPrice = convertStringToNumber(priceList.get(highestRankElementIndexes.get(i)).findElement(By.xpath("//span[@class='a-price-whole']")).getText()+priceList.get(highestRankElementIndexes.get(i)).findElement(By.xpath("//span[@class='a-price-fraction']")).getText());
            }
            else {
                currentPrice = convertStringToNumber(priceList.get(highestRankElementIndexes.get(i)).getText());
            }
            if (currentPrice < minPrice) {
                minPriceIndex = highestRankElementIndexes.get(i);
            }
        }
        return minPriceIndex;
    }

    public int convertStringToNumber(String text) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            int currentIndex = text.charAt(i);
            if (currentIndex >= 48 && currentIndex <= 57) {
                stringBuilder.append(text.charAt(i));
            }
        }
        return Integer.parseInt(stringBuilder.toString());
    }
}
