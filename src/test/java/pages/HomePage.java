package pages;

import static com.codeborne.selenide.Selenide.open;

public class HomePage {

        public HomePage openPage(String pageAddress) {
        open(pageAddress);
        return this;
    }
}
