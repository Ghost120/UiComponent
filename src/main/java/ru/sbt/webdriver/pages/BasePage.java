package ru.sbt.webdriver.pages;

import org.openqa.selenium.WebDriver;
import ru.sbt.webdriver.decorator.CustomHtmlElementDecorator;
import ru.sbtqa.tag.pagefactory.Page;
import ru.sbtqa.tag.pagefactory.PageFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

/**
 * Created by Kasyanenko Konstantin
 * on 03.07.2017.
 * savkk@bk.ru
 */
public class BasePage extends Page {
    public BasePage() {
        WebDriver driver = PageFactory.getWebDriver();
        PageFactory.initElements(new CustomHtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
    }
}
