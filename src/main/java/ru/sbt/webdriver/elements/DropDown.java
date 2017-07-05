package ru.sbt.webdriver.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Kasyanenko Konstantin
 * on 03.07.2017.
 */
public class DropDown extends TypifiedElement {

    private static final String DROP_OPTIONS = ".//div[contains(@class, 'kit-dropdown_options')]";

    @FindBy(xpath = ".//div[@class='kit-dropdown__button']")
    private WebElement buttonDropDown;

    @FindBy(xpath = ".//div[@class='kit-dropdown__arrow-box']")
    private WebElement iconDown;

    public DropDown(WebElement wrappedElement) {
        super(wrappedElement);
    }

    public List<String> getListOptions(){
        buttonDropDown.click();
        List<WebElement> elements = findElements(By.xpath(DROP_OPTIONS));
        return elements.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void selectByValue(String value) {
        buttonDropDown.click();
        findElement(By.xpath(".//div[contains(text(), '" + value + "')")).click();
    }
}
