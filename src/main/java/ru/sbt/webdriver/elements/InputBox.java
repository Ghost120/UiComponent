package ru.sbt.webdriver.elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;

/**
 * Created by Kasyanenko Konstantin
 * on 05.07.2017.
 */
public class InputBox extends TypifiedElement {

    @FindBy(xpath = ".//input[@class='kit-input_control']")
    private WebElement input;

    public String getInput() {
        return input.getAttribute("value");
    }

    public void setInput(String input) {
        this.input.sendKeys(input);
    }

    public InputBox(WebElement wrappedElement) {
        super(wrappedElement);
    }
}
