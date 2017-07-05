package ru.sbt.webdriver.elements;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by Kasyanenko Konstantin
 * on 03.07.2017.
 */
public class Button extends ru.yandex.qatools.htmlelements.element.Button {

    public static final String BUTTON_TEXT = "//div[@class = 'kit-button__text']";

    public Button(WebElement wrappedElement) {
        super(wrappedElement);
    }

    @Override
    public String getText() {
        return findElement(By.xpath(BUTTON_TEXT)).getText();
    }
}
