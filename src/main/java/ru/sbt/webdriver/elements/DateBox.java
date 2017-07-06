package ru.sbt.webdriver.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;

import java.util.List;

/**
 * Created by Kasyanenko Konstantin
 * on 03.07.2017.
 */
public class DateBox extends TypifiedElement {

    @FindBy(xpath = ".//div[contains(@class,'kit-input')]/input")
    private WebElement input;

    public DateBox(WebElement wrappedElement) {
        super(wrappedElement);
    }

    public String getDate() {
        return input.getAttribute("value");
    }
}
