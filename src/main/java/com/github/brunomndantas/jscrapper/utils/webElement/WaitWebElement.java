package com.github.brunomndantas.jscrapper.utils.webElement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class WaitWebElement extends ComposedWebElement {

    private long sleepTime;
    public long getSleepTime() { return this.sleepTime; }



    public WaitWebElement(WebElement element, long sleepTime) {
        super(element);
        this.sleepTime = sleepTime;
    }



    @Override
    public void click() {
        if(!isEnabled() || !isDisplayed())
            sleep();

        super.click();

        sleep();
    }

    @Override
    public void submit() {
        if(!isEnabled() || !isDisplayed())
            sleep();

        super.submit();

        sleep();
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        if(!isEnabled() || !isDisplayed())
            sleep();

        super.sendKeys(keysToSend);

        sleep();
    }

    @Override
    public void clear() {
        if(!isEnabled() || !isDisplayed())
            sleep();

        super.clear();

        sleep();
    }

    @Override
    public List<WebElement> findElements(By by) {
        List<WebElement> elements = super.findElements(by);

        if(elements.isEmpty()) {
            sleep();
            elements = super.findElements(by);
        }

        return elements.stream().map(e -> new WaitWebElement(e, sleepTime)).collect(Collectors.toList());
    }

    @Override
    public WebElement findElement(By by) {
        WebElement element = super.findElement(by);

        if(element == null) {
            sleep();
            element = super.findElement(by);
        }

        return new WaitWebElement(element, sleepTime);
    }

    @Override
    public WebElement findElementById(String using) {
        WebElement element = super.findElementById(using);

        if(element == null) {
            sleep();
            element = super.findElementById(using);
        }

        return new WaitWebElement(element, sleepTime);
    }

    @Override
    public List<WebElement> findElementsById(String using) {
        List<WebElement> elements = super.findElementsById(using);

        if(elements.isEmpty()) {
            sleep();
            elements = super.findElementsById(using);
        }

        return elements.stream().map(e -> new WaitWebElement(e, sleepTime)).collect(Collectors.toList());
    }

    @Override
    public WebElement findElementByLinkText(String using) {
        WebElement element = super.findElementByLinkText(using);

        if(element == null) {
            sleep();
            element = super.findElementByLinkText(using);
        }

        return new WaitWebElement(element, sleepTime);
    }

    @Override
    public List<WebElement> findElementsByLinkText(String using) {
        List<WebElement> elements = super.findElementsByLinkText(using);

        if(elements.isEmpty()) {
            sleep();
            elements = super.findElementsByLinkText(using);
        }

        return elements.stream().map(e -> new WaitWebElement(e, sleepTime)).collect(Collectors.toList());
    }

    @Override
    public WebElement findElementByName(String using) {
        WebElement element = super.findElementByName(using);

        if(element == null) {
            sleep();
            element = super.findElementByName(using);
        }

        return new WaitWebElement(element, sleepTime);
    }

    @Override
    public List<WebElement> findElementsByName(String using) {
        List<WebElement> elements = super.findElementsByName(using);

        if(elements.isEmpty()) {
            sleep();
            elements = super.findElementsByName(using);
        }

        return elements.stream().map(e -> new WaitWebElement(e, sleepTime)).collect(Collectors.toList());
    }

    @Override
    public WebElement findElementByClassName(String using) {
        WebElement element = super.findElementByClassName(using);

        if(element == null) {
            sleep();
            element = super.findElementByClassName(using);
        }

        return new WaitWebElement(element, sleepTime);
    }

    @Override
    public List<WebElement> findElementsByClassName(String using) {
        List<WebElement> elements = super.findElementsByClassName(using);

        if(elements.isEmpty()) {
            sleep();
            elements = super.findElementsByClassName(using);
        }

        return elements.stream().map(e -> new WaitWebElement(e, sleepTime)).collect(Collectors.toList());
    }

    @Override
    public WebElement findElementByCssSelector(String using) {
        WebElement element = super.findElementByCssSelector(using);

        if(element == null) {
            sleep();
            element = super.findElementByCssSelector(using);
        }

        return new WaitWebElement(element, sleepTime);
    }

    @Override
    public List<WebElement> findElementsByCssSelector(String using) {
        List<WebElement> elements = super.findElementsByCssSelector(using);

        if(elements.isEmpty()) {
            sleep();
            elements = super.findElementsByCssSelector(using);
        }

        return elements.stream().map(e -> new WaitWebElement(e, sleepTime)).collect(Collectors.toList());
    }

    @Override
    public WebElement findElementByXPath(String using) {
        WebElement element = super.findElementByXPath(using);

        if(element == null) {
            sleep();
            element = super.findElementByXPath(using);
        }

        return new WaitWebElement(element, sleepTime);
    }

    @Override
    public List<WebElement> findElementsByXPath(String using) {
        List<WebElement> elements = super.findElementsByXPath(using);

        if(elements.isEmpty()) {
            sleep();
            elements = super.findElementsByXPath(using);
        }

        return elements.stream().map(e -> new WaitWebElement(e, sleepTime)).collect(Collectors.toList());
    }

    @Override
    public WebElement findElementByPartialLinkText(String using) {
        WebElement element = super.findElementByPartialLinkText(using);

        if(element == null) {
            sleep();
            element = super.findElementByPartialLinkText(using);
        }

        return new WaitWebElement(element, sleepTime);
    }

    @Override
    public List<WebElement> findElementsByPartialLinkText(String using) {
        List<WebElement> elements = super.findElementsByPartialLinkText(using);

        if(elements.isEmpty()) {
            sleep();
            elements = super.findElementsByPartialLinkText(using);
        }

        return elements.stream().map(e -> new WaitWebElement(e, sleepTime)).collect(Collectors.toList());
    }

    @Override
    public WebElement findElementByTagName(String using) {
        WebElement element = super.findElementByTagName(using);

        if(element == null) {
            sleep();
            element = super.findElementByTagName(using);
        }

        return new WaitWebElement(element, sleepTime);
    }

    @Override
    public List<WebElement> findElementsByTagName(String using) {
        List<WebElement> elements = super.findElementsByTagName(using);

        if(elements.isEmpty()) {
            sleep();
            elements = super.findElementsByTagName(using);
        }

        return elements.stream().map(e -> new WaitWebElement(e, sleepTime)).collect(Collectors.toList());
    }

    private void sleep() {
        try {
            Thread.sleep(this.sleepTime);
        } catch (InterruptedException e) { }
    }

}
