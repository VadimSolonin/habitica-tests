package utils;

import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.title;
import static com.codeborne.selenide.WebDriverConditions.url;

public class Helpers {

    public static void verifyPageUrl(String pageUrl){
        webdriver().shouldHave(url(pageUrl));
    }
    public static void verifyPageTitle(String pageTitle){
        webdriver().shouldHave(title(pageTitle));
    }
}