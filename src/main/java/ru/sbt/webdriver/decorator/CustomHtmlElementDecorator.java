package ru.sbt.webdriver.decorator;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;
import ru.yandex.qatools.htmlelements.exceptions.HtmlElementsException;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;
import ru.yandex.qatools.htmlelements.pagefactory.CustomElementLocatorFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import static ru.yandex.qatools.htmlelements.utils.HtmlElementUtils.getElementName;
import static ru.yandex.qatools.htmlelements.utils.HtmlElementUtils.newInstance;

/**
 * Created by Kasyanenko Konstantin
 * on 03.07.2017.
 */
public class CustomHtmlElementDecorator extends HtmlElementDecorator {

    public CustomHtmlElementDecorator(CustomElementLocatorFactory factory) {
        super(factory);
    }

    @Override
    protected <T extends TypifiedElement> T decorateTypifiedElement(ClassLoader loader, Field field) {
        WebElement elementToWrap = decorateWebElement(loader, field);

        //noinspection unchecked
        return createTypifiedElement((Class<T>) field.getType(), elementToWrap, getElementName(field));
    }

    private static <T extends TypifiedElement> T createTypifiedElement(Class<T> elementClass, WebElement elementToWrap, String name) {
        try {
            T instance = newInstance(elementClass, elementToWrap);
            instance.setName(name);
            populatePageObject(instance, elementToWrap);// this is the patch to Yandex typified elements. With this string it walks via nested elements and initializes them too
            return instance;
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException
                | InvocationTargetException e) {
            throw new HtmlElementsException(e);
        }
    }

    private static void populatePageObject(Object page, SearchContext searchContext) {
        populatePageObject(page, new HtmlElementLocatorFactory(searchContext));
    }

    private static void populatePageObject(Object page, CustomElementLocatorFactory locatorFactory) {
        PageFactory.initElements(new HtmlElementDecorator(locatorFactory), page);
    }
}