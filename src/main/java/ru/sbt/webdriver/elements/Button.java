package ru.sbt.webdriver.elements;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Kasyanenko Konstantin
 * on 03.07.2017.
 */
public class Button extends ru.yandex.qatools.htmlelements.element.Button {

    @FindBy(xpath = ".//div[@class = 'kit-button__text']")
    private WebElement text;

    public Button(WebElement wrappedElement) {
        super(wrappedElement);
    }

    @Override
    public String getText() {
        return text.getText();
    }
}
