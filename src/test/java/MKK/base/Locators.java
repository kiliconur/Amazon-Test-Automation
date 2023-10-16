package MKK.base;

import org.openqa.selenium.By;

import java.util.HashMap;

public class Locators
{
    private HashMap<String,By> map = new HashMap<String, By>();
    public Locators(){
        initMap();
    }

    public void initMap() {
        map.put("Login Register Butonu",By.cssSelector("a[id='nav-link-accountList']"));
        map.put("Mail Input Alanı",By.cssSelector("input[type='email']"));
        map.put("Login Devam Butonu",By.cssSelector("input[id='continue']"));
        map.put("Password Input Alanı",By.cssSelector("input[type='email']"));
        map.put("Login Giriş Yap Butonu",By.cssSelector("input[id='signInSubmit']"));
        map.put("Arama Alanı",By.cssSelector("input[type='text']"));
        map.put("Arama Alanı Ara Butonu",By.id("nav-search-submit-button"));
        map.put("Çerezleri Kabul Et",By.id("sp-cc-accept"));
        map.put("Kulaklık Kategorisi Filtresi",By.xpath("//div[@id='departments']//span[text()='Kulaküstü ve Kulakiçi Kulaklıklar']/.."));
        map.put("Min Tutar Filtresi",By.id("low-price"));
        map.put("Max Tutar Filtresi",By.id("high-price"));
        map.put("Tutar Filtresi Git Butonu",By.xpath("//span[contains(text(),'Git')]/preceding-sibling::input"));
        map.put("Sıralama Filtresi",By.id("s-result-sort-select"));
        map.put("Listelenen Ürünlerin Texti",By.xpath("//div[@data-component-id]//div[@class='a-section a-spacing-none a-spacing-top-small s-title-instructions-style']//span"));
        map.put("Listelenen Ürünlerin Puanı",By.xpath("//div[@data-component-id]//span[contains(text(),'5 yıldız üzerinden')]"));
        map.put("Listelenen Ürünlerin Fiyatları",By.xpath("//div[@data-component-id]//span[contains(text(),'5 yıldız üzerinden')]/following::span[contains(text(),'TL')][1]"));
        map.put("Listelenen Ürünler",By.xpath("//div[@data-component-id]//div[@class='a-section a-spacing-none a-spacing-top-small s-title-instructions-style']//span/.."));
        map.put("Sepete Ekle Butonu",By.id("add-to-cart-button"));
    }

    protected HashMap<String,By> getMap() {
        return map;
    }
}
