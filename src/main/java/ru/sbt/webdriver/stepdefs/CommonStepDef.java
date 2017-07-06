package ru.sbt.webdriver.stepdefs;

import cucumber.api.java.en.And;
import cucumber.api.java.ru.Когда;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import ru.sbt.webdriver.elements.*;
import ru.sbt.webdriver.elements.Button;
import ru.sbtqa.tag.pagefactory.PageFactory;
import ru.sbtqa.tag.pagefactory.exceptions.PageException;
import ru.sbtqa.tag.pagefactory.exceptions.PageInitializationException;
import ru.sbtqa.tag.qautils.errors.AutotestError;
import ru.yandex.qatools.htmlelements.element.*;


import java.util.List;

import static ru.sbtqa.tag.pagefactory.PageFactory.*;

/**
 * Created by Kasyanenko Konstantin
 * on 03.07.2017.
 */
public class CommonStepDef {

    public static final String WRONG_TYPE_ARG = "Не верный тип элемента";
    public static final String WRONG_NAME = "Названия не совпали";

    @And("^пользователь на элементе \"(.*?)\" (?:сверяет название кнопки|сверяет текст выпадающего списка|сверяет дату в календаре) \"(.*?)\"$")
    public void checkText(String nameElement, String text) {
        try {
            TypifiedElement element = (TypifiedElement) getInstance().getCurrentPage().getElementByTitle(nameElement);
            if (element.getClass().equals(Button.class)) {
                Assert.assertEquals(WRONG_NAME, ((Button) element).getText(), text);
            } else if (element.getClass().equals(DateBox.class)) {
                Assert.assertEquals("Даты не совпали", ((DateBox) element).getDate(), text);
            } else if (element.getClass().equals(DropDown.class)) {
                // TODO: 05.07.2017
            } else {
                throw new AutotestError(WRONG_TYPE_ARG + nameElement);
            }
        } catch (PageException e) {
            throw new AutotestError(String.format("Не удалось найти элемент '%s', либо поле имеет не корректный тип", nameElement), e);
        }
    }

    @And("^пользователь на элементе \"(.*?)\" сверяет текст в выпадающем списка$")
    public void CheckListText(String nameElement, List<String> listtext) {
        try {
            TypifiedElement element = (TypifiedElement) getInstance().getCurrentPage().getElementByTitle(nameElement);
            if (element.getClass().equals(DropDown.class)) {
                List<String> listDropDown = ((DropDown) element).getListOptions();
                for (int i = 0; i < listtext.size(); i++) {
                    Assert.assertEquals(WRONG_NAME, listDropDown.get(i), listtext.get(i));
                }
            } else {
                throw new AutotestError(WRONG_TYPE_ARG + nameElement);
            }
        } catch (PageException e) {
            throw new AutotestError(String.format("Не удалось найти элемент '%s', либо поле имеет не корректный тип", nameElement), e);
        }
    }
}
