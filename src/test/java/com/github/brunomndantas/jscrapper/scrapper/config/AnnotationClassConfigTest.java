package com.github.brunomndantas.jscrapper.scrapper.config;

import com.github.brunomndantas.jscrapper.core.ScrapperException;
import com.github.brunomndantas.jscrapper.core.config.ClassConfig;
import com.github.brunomndantas.jscrapper.core.driverLoader.DriverLoaderException;
import com.github.brunomndantas.jscrapper.core.driverLoader.IDriverLoader;
import com.github.brunomndantas.jscrapper.core.driverSupplier.DriverSupplierException;
import com.github.brunomndantas.jscrapper.core.driverSupplier.IDriverSupplier;
import com.github.brunomndantas.jscrapper.core.instanceFactory.IInstanceFactory;
import com.github.brunomndantas.jscrapper.core.instanceFactory.InstanceFactoryException;
import com.github.brunomndantas.jscrapper.core.urlSupplier.IURLSupplier;
import com.github.brunomndantas.jscrapper.core.urlSupplier.URLSupplierException;
import com.github.brunomndantas.jscrapper.scrapper.annotation.SelectorType;
import com.github.brunomndantas.jscrapper.scrapper.annotation.page.DriverLoader;
import com.github.brunomndantas.jscrapper.scrapper.annotation.page.DriverSupplier;
import com.github.brunomndantas.jscrapper.scrapper.annotation.page.InstanceFactory;
import com.github.brunomndantas.jscrapper.scrapper.annotation.page.URLSupplier;
import com.github.brunomndantas.jscrapper.support.driverLoader.*;
import com.github.brunomndantas.jscrapper.support.driverSupplier.ChromeDriverSupplier;
import com.github.brunomndantas.jscrapper.support.driverSupplier.FirefoxDriverSupplier;
import com.github.brunomndantas.jscrapper.support.driverSupplier.PhantomDriverSupplier;
import com.github.brunomndantas.jscrapper.support.urlSupplier.FixedURLSupplier;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class AnnotationClassConfigTest {

    private static class MyInstanceFactory implements IInstanceFactory {
        @Override public Object create() throws InstanceFactoryException { return null; }
    }

    private static class MyURLSupplier implements IURLSupplier {
        @Override public String get() throws URLSupplierException { return null; }
    }

    private static class MyDriverSupplier implements IDriverSupplier {
        @Override public WebDriver get() throws DriverSupplierException { return null; }
    }

    private static class MyDriverLoader implements IDriverLoader {
        @Override public void load(WebDriver driver) throws DriverLoaderException { }
    }

    @InstanceFactory(MyInstanceFactory.class)
    @URLSupplier(MyURLSupplier.class)
    @DriverSupplier(MyDriverSupplier.class)
    @DriverLoader(MyDriverLoader.class)
    private static class AnnotationConfigEntity { }

    private static class NoAnnotationEntity { }

    @URLSupplier(url = "url")
    private static class FixedURLEntity { }

    @DriverSupplier(driverLocation = "chrome", driverType = DriverSupplier.DriverType.CHROME)
    private static class ChromeDriverSupplierConfigEntity { }

    @DriverSupplier(driverLocation = "firefox", driverType = DriverSupplier.DriverType.FIREFOX)
    private static class FirefoxDriverSupplierConfigEntity { }

    @DriverSupplier(driverLocation = "phantom", driverType = DriverSupplier.DriverType.PHANTOM)
    private static class PhantomDriverSupplierConfigEntity { }

    @DriverLoader(actions = {
        @DriverLoader.Action(clear = @DriverLoader.Clear(selector = "elementId", selectorType = SelectorType.ID)),
        @DriverLoader.Action(click = @DriverLoader.Click(selector = "elementId", selectorType = SelectorType.ID)),
        @DriverLoader.Action(doubleClick = @DriverLoader.DoubleClick(selector = "elementId", selectorType = SelectorType.ID)),
        @DriverLoader.Action(sendKeys = @DriverLoader.SendKeys(value = "text", selector = "elementId", selectorType = SelectorType.ID)),
        @DriverLoader.Action(submit = @DriverLoader.Submit(selector = "elementId", selectorType = SelectorType.ID)),
        @DriverLoader.Action(waitFor = @DriverLoader.Wait(value = 1000, unit = TimeUnit.DAYS)),
        @DriverLoader.Action(waitVisible = @DriverLoader.WaitVisible(value = 1000, unit = TimeUnit.DAYS, selector = "elementId", selectorType = SelectorType.ID))
    })
    private static class DriverLoaderConfigEntity { }

    @DriverLoader(actions = {@DriverLoader.Action()})
    private static class UnknownDriverLoaderConfigEntity { }



    @Test
    public void getClassConfigWithoutAnnotationTest() throws Exception {
        ClassConfig config = AnnotationClassConfig.getClassConfig(NoAnnotationEntity.class);

        assertSame(NoAnnotationEntity.class, config.getKlass());
        assertNull(config.getInstanceFactory());
        assertNull(config.getURLSupplier());
        assertNull(config.getDriverSupplier());
        assertNull(config.getDriverLoader());
    }

    @Test
    public void getClassConfigTest() throws Exception {
        ClassConfig config = AnnotationClassConfig.getClassConfig(AnnotationConfigEntity.class);

        assertSame(AnnotationConfigEntity.class, config.getKlass());
        assertNotNull(config.getInstanceFactory());
        assertTrue(config.getInstanceFactory() instanceof MyInstanceFactory);
        assertNotNull(config.getURLSupplier());
        assertTrue(config.getURLSupplier() instanceof MyURLSupplier);
        assertNotNull(config.getDriverSupplier());
        assertTrue(config.getDriverSupplier() instanceof MyDriverSupplier);
        assertNotNull(config.getDriverLoader());
        assertTrue(config.getDriverLoader() instanceof MyDriverLoader);
    }

    @Test
    public void getClassConfigWrapsExceptionTest() throws Exception {
        try {
            AnnotationClassConfig.getClassConfig(UnknownDriverLoaderConfigEntity.class);
            fail("Exception should be thrown!");
        } catch (ScrapperException e) {
            assertTrue(e.getMessage().contains(UnknownDriverLoaderConfigEntity.class.getName()));
        }
    }

    @Test
    public void getInstanceFactoryWithoutAnnotationTest() throws Exception {
        assertNull(AnnotationClassConfig.getInstanceFactory(NoAnnotationEntity.class));
    }

    @Test
    public void getMyImplementationInstanceFactoryAnnotationTest() throws Exception {
        IInstanceFactory factory = AnnotationClassConfig.getInstanceFactory(AnnotationConfigEntity.class);
        assertTrue(factory instanceof MyInstanceFactory);
     }

    @Test
    public void getURLSupplierWithoutAnnotationTest() throws Exception {
        assertNull(AnnotationClassConfig.getURLSupplier(NoAnnotationEntity.class));
    }

    @Test
    public void getMyImplementationURLSupplierAnnotationTest() throws Exception {
        IURLSupplier supplier =  AnnotationClassConfig.getURLSupplier(AnnotationConfigEntity.class);
        assertTrue(supplier instanceof MyURLSupplier);
    }

    @Test
    public void getFixedURLSupplierAnnotationTest() throws Exception {
        IURLSupplier supplier =  AnnotationClassConfig.getURLSupplier(FixedURLEntity.class);
        assertTrue(supplier instanceof FixedURLSupplier);
        assertEquals("url", ((FixedURLSupplier)supplier).get());
    }

    @Test
    public void getDriverSupplierWithoutAnnotationTest() throws Exception {
        assertNull(AnnotationClassConfig.getDriverSupplier(NoAnnotationEntity.class));
    }

    @Test
    public void getMyImplementationDriverSupplierAnnotationTest() throws Exception {
        IDriverSupplier supplier = AnnotationClassConfig.getDriverSupplier(AnnotationConfigEntity.class);
        assertTrue(supplier instanceof MyDriverSupplier);
    }

    @Test
    public void getChromeDriverSupplierAnnotationTest() throws Exception {
        IDriverSupplier supplier = AnnotationClassConfig.getDriverSupplier(ChromeDriverSupplierConfigEntity.class);

        assertTrue(supplier instanceof ChromeDriverSupplier);
        assertEquals("chrome", ((ChromeDriverSupplier)supplier).getDriverPath());
    }

    @Test
    public void getFirefoxDriverSupplierAnnotationTest() throws Exception {
        IDriverSupplier supplier = AnnotationClassConfig.getDriverSupplier(FirefoxDriverSupplierConfigEntity.class);

        assertTrue(supplier instanceof FirefoxDriverSupplier);
        assertEquals("firefox", ((FirefoxDriverSupplier)supplier).getDriverPath());
    }

    @Test
    public void getPhantomDriverSupplierAnnotationTest() throws Exception {
        IDriverSupplier supplier = AnnotationClassConfig.getDriverSupplier(PhantomDriverSupplierConfigEntity.class);

        assertTrue(supplier instanceof PhantomDriverSupplier);
        assertEquals("phantom", ((PhantomDriverSupplier)supplier).getDriverPath());
    }

    @Test
    public void getDriverLoaderWithoutAnnotationTest() throws Exception {
        assertNull(AnnotationClassConfig.getDriverLoader(NoAnnotationEntity.class));
    }

    @Test
    public void getMyImplementationDriverLoaderAnnotationTest() throws Exception {
        IDriverLoader loader = AnnotationClassConfig.getDriverLoader(AnnotationConfigEntity.class);
        assertTrue(loader instanceof MyDriverLoader);
    }

    @Test
    public void getComposedDriverLoaderAnnotationTest() throws Exception {
        DriverLoader.Action[] actions = DriverLoaderConfigEntity.class.getDeclaredAnnotation(DriverLoader.class).actions();
        IDriverLoader loader = AnnotationClassConfig.getDriverLoader(DriverLoaderConfigEntity.class);

        assertTrue(loader instanceof ComposedDriverLoader);

        List<IDriverLoader> loaders = new LinkedList<>(((ComposedDriverLoader)loader).getLoaders());

        assertEquals(actions.length, loaders.size());
        assertTrue(loaders.get(0) instanceof ClearDriverLoader);
        assertTrue(loaders.get(1) instanceof ClickDriverLoader);
        assertTrue(loaders.get(2) instanceof DoubleClickDriverLoader);
        assertTrue(loaders.get(3) instanceof SendKeysDriverLoader);
        assertEquals("text",((SendKeysDriverLoader)loaders.get(3)).getKeys());
        assertTrue(loaders.get(4) instanceof SubmitDriverLoader);
        assertTrue(loaders.get(5) instanceof WaitDriverLoader);
        assertEquals(1000, ((WaitDriverLoader)loaders.get(5)).getTime());
        assertEquals(TimeUnit.DAYS, ((WaitDriverLoader)loaders.get(5)).getTimeUnit());
        assertTrue(loaders.get(6) instanceof WaitVisibleDriverLoader);
        assertEquals(1000, ((WaitVisibleDriverLoader)loaders.get(6)).getTime());
        assertEquals(TimeUnit.DAYS, ((WaitVisibleDriverLoader)loaders.get(6)).getTimeUnit());
        assertTrue(((WaitVisibleDriverLoader)loaders.get(6)).getBy() instanceof By.ById);
        assertTrue(((WaitVisibleDriverLoader)loaders.get(6)).getBy().toString().contains("elementId"));
    }

    @Test
    public void getUnknownDriverLoaderAnnotationTest() throws Exception {
        try {
            AnnotationClassConfig.getDriverLoader(UnknownDriverLoaderConfigEntity.class);
            fail("Exception should be thrown!");
        } catch (ScrapperException e) {
            assertTrue(e.getMessage().toLowerCase().contains("unknown"));
            assertTrue(e.getMessage().toLowerCase().contains("action"));
        }
    }

    @Test
    public void getBySelectorTest() throws Exception {
        By by;
        String selector = "selectorValue";

        by = AnnotationClassConfig.getBySelector(SelectorType.ID, selector);
        assertTrue(by instanceof By.ById);
        assertTrue(by.toString().contains(selector));

        by = AnnotationClassConfig.getBySelector(SelectorType.NAME, selector);
        assertTrue(by instanceof By.ByName);
        assertTrue(by.toString().contains(selector));

        by = AnnotationClassConfig.getBySelector(SelectorType.CLASS, selector);
        assertTrue(by instanceof By.ByClassName);
        assertTrue(by.toString().contains(selector));

        by = AnnotationClassConfig.getBySelector(SelectorType.TAG, selector);
        assertTrue(by instanceof By.ByTagName);
        assertTrue(by.toString().contains(selector));

        by = AnnotationClassConfig.getBySelector(SelectorType.LINK_TEXT, selector);
        assertTrue(by instanceof By.ByLinkText);
        assertTrue(by.toString().contains(selector));

        by = AnnotationClassConfig.getBySelector(SelectorType.PARTIAL_LINK_TEXT, selector);
        assertTrue(by instanceof By.ByPartialLinkText);
        assertTrue(by.toString().contains(selector));

        by = AnnotationClassConfig.getBySelector(SelectorType.CSS, selector);
        assertTrue(by instanceof By.ByCssSelector);
        assertTrue(by.toString().contains(selector));

        by = AnnotationClassConfig.getBySelector(SelectorType.XPATH, selector);
        assertTrue(by instanceof By.ByXPath);
        assertTrue(by.toString().contains(selector));
    }

}
