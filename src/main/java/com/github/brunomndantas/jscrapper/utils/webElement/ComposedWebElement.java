package com.github.brunomndantas.jscrapper.utils.webElement;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.remote.FileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;

import java.util.List;
import java.util.Map;

public class ComposedWebElement extends RemoteWebElement {

    protected WebElement element;
    public WebElement getElement() { return this.element; }



    public ComposedWebElement(WebElement element) {
        this.element = element;
    }



    @Override
    public void click() {
        this.element.click();
    }

    @Override
    public void submit() {
        this.element.submit();
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        this.element.sendKeys(keysToSend);
    }

    @Override
    public void clear() {
        this.element.clear();
    }

    @Override
    public String getTagName() {
        return this.element.getTagName();
    }

    @Override
    public String getAttribute(String name) {
        return this.element.getAttribute(name);
    }

    @Override
    public boolean isSelected() {
        return this.element.isSelected();
    }

    @Override
    public boolean isEnabled() {
        return this.element.isEnabled();
    }

    @Override
    public String getText() {
        return this.element.getText();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return this.element.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return this.element.findElement(by);
    }

    @Override
    public boolean isDisplayed() {
        return this.element.isDisplayed();
    }

    @Override
    public Point getLocation() {
        return this.element.getLocation();
    }

    @Override
    public Dimension getSize() {
        return this.element.getSize();
    }

    @Override
    public Rectangle getRect() {
        return this.element.getRect();
    }

    @Override
    public String getCssValue(String propertyName) {
        return this.element.getCssValue(propertyName);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return this.element.getScreenshotAs(target);
    }

    @Override
    public void setParent(RemoteWebDriver parent) {
        ((RemoteWebElement) this.element).setParent(parent);
    }

    @Override
    public String getId() {
        return ((RemoteWebElement) this.element).getId();
    }

    @Override
    public void setId(String id) {
        ((RemoteWebElement) this.element).setId(id);
    }

    @Override
    public void setFileDetector(FileDetector detector) {
        ((RemoteWebElement) this.element).setFileDetector(detector);
    }

    @Override
    public WebElement findElementById(String using) {
        return ((RemoteWebElement) this.element).findElementById(using);
    }

    @Override
    public List<WebElement> findElementsById(String using) {
        return ((RemoteWebElement) this.element).findElementsById(using);
    }

    @Override
    public WebElement findElementByLinkText(String using) {
        return ((RemoteWebElement) this.element).findElementByLinkText(using);
    }

    @Override
    public List<WebElement> findElementsByLinkText(String using) {
        return ((RemoteWebElement) this.element).findElementsByLinkText(using);
    }

    @Override
    public WebElement findElementByName(String using) {
        return ((RemoteWebElement) this.element).findElementByName(using);
    }

    @Override
    public List<WebElement> findElementsByName(String using) {
        return ((RemoteWebElement) this.element).findElementsByName(using);
    }

    @Override
    public WebElement findElementByClassName(String using) {
        return ((RemoteWebElement) this.element).findElementByClassName(using);
    }

    @Override
    public List<WebElement> findElementsByClassName(String using) {
        return ((RemoteWebElement) this.element).findElementsByClassName(using);
    }

    @Override
    public WebElement findElementByCssSelector(String using) {
        return ((RemoteWebElement) this.element).findElementByCssSelector(using);
    }

    @Override
    public List<WebElement> findElementsByCssSelector(String using) {
        return ((RemoteWebElement) this.element).findElementsByCssSelector(using);
    }

    @Override
    public WebElement findElementByXPath(String using) {
        return ((RemoteWebElement) this.element).findElementByXPath(using);
    }

    @Override
    public List<WebElement> findElementsByXPath(String using) {
        return ((RemoteWebElement) this.element).findElementsByXPath(using);
    }

    @Override
    public WebElement findElementByPartialLinkText(String using) {
        return ((RemoteWebElement) this.element).findElementByPartialLinkText(using);
    }

    @Override
    public List<WebElement> findElementsByPartialLinkText(String using) {
        return ((RemoteWebElement) this.element).findElementsByPartialLinkText(using);
    }

    @Override
    public WebElement findElementByTagName(String using) {
        return ((RemoteWebElement) this.element).findElementByTagName(using);
    }

    @Override
    public List<WebElement> findElementsByTagName(String using) {
        return ((RemoteWebElement) this.element).findElementsByTagName(using);
    }

    @Override
    public boolean equals(Object obj) {
        return this.element.equals(obj);
    }

    @Override
    public int hashCode() {
        return this.element.hashCode();
    }

    @Override
    public WebDriver getWrappedDriver() {
        return ((RemoteWebElement) this.element).getWrappedDriver();
    }

    @Override
    public Coordinates getCoordinates() {
        return ((RemoteWebElement) this.element).getCoordinates();
    }

    @Override
    public String toString() {
        return this.element.toString();
    }

    @Override
    public Map<String, Object> toJson() {
        return ((RemoteWebElement) this.element).toJson();
    }

}
