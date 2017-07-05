package ru.sbt.webdriver.stepdefs;

import cucumber.api.java.en.And;
import cucumber.api.java.ru.Когда;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import ru.sbt.webdriver.elements.*;
import ru.sbtqa.tag.pagefactory.PageFactory;
import ru.sbtqa.tag.pagefactory.exceptions.PageException;
import ru.sbtqa.tag.pagefactory.exceptions.PageInitializationException;
import ru.yandex.qatools.htmlelements.element.*;
import ru.yandex.qatools.htmlelements.element.Button;


import static ru.sbtqa.tag.pagefactory.PageFactory.*;

/**
 * Created by Kasyanenko Konstantin
 * on 03.07.2017.
 */
public class CommonStepDef {

    @Когда("сверяет дату \"(.*)\" в календаре \"(.*)\"")
    public void checkDate(String date, String title) {
        System.out.println("hi");
        try {
            TypifiedElement element = (TypifiedElement) getInstance().getCurrentPage().getElementByTitle(title);
            System.out.println(((DateBox) element).getDate());
            Assert.assertEquals("Даты не совпадают", ((DateBox) element).getDate(), date);

        } catch (PageInitializationException e) {
            e.printStackTrace();
        } catch (PageException e) {
            e.printStackTrace();
        }
    }

    @And("пользователь в блоке \"([^\"]*)\" находит (дату|кнопку|список|) \"([^\"]*)\"$")
    public void findElementInBlock(String block, String elementType, String elementTitle) throws PageException {
        Class<? extends WebElement> clazz;
        switch (elementType) {
            case "datebox":
            case "поле даты":
                clazz = DateBox.class;
                break;
            case "button":
            case "кнопку":
                clazz = ru.sbt.webdriver.elements.Button.class;
                break;
            case "dropdown":
            case "выпадающий список":
                clazz = Radio.class;
                break;
            default:
                clazz = WebElement.class;
        }
        PageFactory.getInstance().getCurrentPage().findElementInBlockByTitle(block, elementTitle, clazz);
    }

}
