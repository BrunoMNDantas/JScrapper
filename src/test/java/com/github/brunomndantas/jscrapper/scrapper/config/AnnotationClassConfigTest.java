package com.github.brunomndantas.jscrapper.scrapper.config;

import com.github.brunomndantas.jscrapper.core.ScrapperException;
import com.github.brunomndantas.jscrapper.core.config.ClassConfig;
import com.github.brunomndantas.jscrapper.core.driverLoader.DriverLoaderException;
import com.github.brunomndantas.jscrapper.core.driverLoader.IDriverLoader;
import com.github.brunomndantas.jscrapper.core.driverSupplier.DriverSupplierException;
import com.github.brunomndantas.jscrapper.core.driverSupplier.IDriverSupplier;
import com.github.brunomndantas.jscrapper.core.instanceFactory.IInstanceFactory;
import com.github.brunomndantas.jscrapper.core.instanceFactory.InstanceFactoryException;
import com.github.brunomndantas.jscrapper.scrapper.annotation.SelectorType;
import com.github.brunomndantas.jscrapper.scrapper.annotation.page.DriverLoader;
import com.github.brunomndantas.jscrapper.scrapper.annotation.page.DriverSupplier;
import com.github.brunomndantas.jscrapper.scrapper.annotation.page.InstanceFactory;
import com.github.brunomndantas.jscrapper.scrapper.annotation.page.Page;
import com.github.brunomndantas.jscrapper.support.driverLoader.*;
import com.github.brunomndantas.jscrapper.support.driverSupplier.ChromeDriverSupplier;
import com.github.brunomndantas.jscrapper.support.driverSupplier.FirefoxDriverSupplier;
import com.github.brunomndantas.jscrapper.support.driverSupplier.PhantomDriverSupplier;
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

    private static class MyDriverSupplier implements IDriverSupplier {
        @Override public WebDriver get() throws DriverSupplierException { return null; }
    }

    private static class MyDriverLoader implements IDriverLoader {
        @Override public void load(WebDriver driver) throws DriverLoaderException { }
    }

    @Page(
        instanceFactory = @InstanceFactory(MyInstanceFactory.class),
        driverSupplier = @DriverSupplier(MyDriverSupplier.class),
        driverLoader = @DriverLoader(MyDriverLoader.class)
    )
    private static class AnnotationConfigEntity { }

    @Page()
    private static class NoAnnotationConfigEntity { }

    private static class NoAnnotationEntity { }

    @Page(driverSupplier = @DriverSupplier(driverLocation = "chrome", driverType = DriverSupplier.DriverType.CHROME))
    private static class ChromeDriverSupplierConfigEntity { }

    @Page(driverSupplier = @DriverSupplier(driverLocation = "firefox", driverType = DriverSupplier.DriverType.FIREFOX))
    private static class FirefoxDriverSupplierConfigEntity { }

    @Page(driverSupplier = @DriverSupplier(driverLocation = "phantom", driverType = DriverSupplier.DriverType.PHANTOM))
    private static class PhantomDriverSupplierConfigEntity { }

    @Page(driverLoader = @DriverLoader(
        actions = {
            @DriverLoader.Action(clear = @DriverLoader.Clear(selector = "elementId", selectorType = SelectorType.ID)),
            @DriverLoader.Action(click = @DriverLoader.Click(selector = "elementId", selectorType = SelectorType.ID)),
            @DriverLoader.Action(doubleClick = @DriverLoader.DoubleClick(selector = "elementId", selectorType = SelectorType.ID)),
            @DriverLoader.Action(sendKeys = @DriverLoader.SendKeys(value = "text", selector = "elementId", selectorType = SelectorType.ID)),
            @DriverLoader.Action(submit = @DriverLoader.Submit(selector = "elementId", selectorType = SelectorType.ID)),
            @DriverLoader.Action(waitFor = @DriverLoader.Wait(value = 1000, unit = TimeUnit.DAYS)),
            @DriverLoader.Action(waitVisible = @DriverLoader.WaitVisible(value = 1000, unit = TimeUnit.DAYS, selector = "elementId", selectorType = SelectorType.ID))
        }
    ))
    private static class DriverLoaderConfigEntity { }

    @Page(driverLoader = @DriverLoader(actions = {
        @DriverLoader.Action()
    }))
    private static class UnknownDriverLoaderConfigEntity { }



    @Test
    public void getClassConfigWithoutAnnotationTest() throws Exception {
        ClassConfig config = AnnotationClassConfig.getClassConfig(NoAnnotationEntity.class);

        assertSame(NoAnnotationEntity.class, config.getKlass());
        assertNull(config.getInstanceFactory());
        assertNull(config.getDriverSupplier());
        assertNull(config.getDriverLoader());
    }

    @Test
    public void getClassConfigTest() throws Exception {
        ClassConfig config = AnnotationClassConfig.getClassConfig(AnnotationConfigEntity.class);

        assertSame(AnnotationConfigEntity.class, config.getKlass());
        assertNotNull(config.getInstanceFactory());
        assertTrue(config.getInstanceFactory() instanceof MyInstanceFactory);
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
    public void getClassConfigAnnotationTest() throws Exception {
        Page annotation = AnnotationConfigEntity.class.getDeclaredAnnotation(Page.class);

        ClassConfig config = AnnotationClassConfig.getClassConfig(AnnotationConfigEntity.class, annotation);

        assertSame(AnnotationConfigEntity.class, config.getKlass());
        assertNotNull(config.getInstanceFactory());
        assertTrue(config.getInstanceFactory() instanceof MyInstanceFactory);
        assertNotNull(config.getDriverSupplier());
        assertTrue(config.getDriverSupplier() instanceof MyDriverSupplier);
        assertNotNull(config.getDriverLoader());
        assertTrue(config.getDriverLoader() instanceof MyDriverLoader);
    }

    @Test
    public void getInstanceFactoryWithoutAnnotationTest() throws Exception {
        InstanceFactory annotation = NoAnnotationConfigEntity.class.getDeclaredAnnotation(Page.class).instanceFactory();
        assertNull(AnnotationClassConfig.getInstanceFactory(NoAnnotationConfigEntity.class, annotation));
    }

    @Test
    public void getMyImplementationInstanceFactoryAnnotationTest() throws Exception {
        InstanceFactory annotation = AnnotationConfigEntity.class.getDeclaredAnnotation(Page.class).instanceFactory();

        IInstanceFactory factory = AnnotationClassConfig.getInstanceFactory(AnnotationConfigEntity.class, annotation);

        assertTrue(factory instanceof MyInstanceFactory);
     }

    @Test
    public void getDriverSupplierWithoutAnnotationTest() throws Exception {
        DriverSupplier annotation = NoAnnotationConfigEntity.class.getDeclaredAnnotation(Page.class).driverSupplier();
        assertNull(AnnotationClassConfig.getDriverSupplier(NoAnnotationConfigEntity.class, annotation));
    }

    @Test
    public void getMyImplementationDriverSupplierAnnotationTest() throws Exception {
        DriverSupplier annotation = AnnotationConfigEntity.class.getDeclaredAnnotation(Page.class).driverSupplier();

        IDriverSupplier supplier = AnnotationClassConfig.getDriverSupplier(AnnotationConfigEntity.class, annotation);

        assertTrue(supplier instanceof MyDriverSupplier);
    }

    @Test
    public void getChromeDriverSupplierAnnotationTest() throws Exception {
        DriverSupplier annotation = ChromeDriverSupplierConfigEntity.class.getDeclaredAnnotation(Page.class).driverSupplier();

        IDriverSupplier supplier = AnnotationClassConfig.getDriverSupplier(ChromeDriverSupplierConfigEntity.class, annotation);

        assertTrue(supplier instanceof ChromeDriverSupplier);
        assertEquals("chrome", ((ChromeDriverSupplier)supplier).getDriverPath());
    }

    @Test
    public void getFirefoxDriverSupplierAnnotationTest() throws Exception {
        DriverSupplier annotation = FirefoxDriverSupplierConfigEntity.class.getDeclaredAnnotation(Page.class).driverSupplier();

        IDriverSupplier supplier = AnnotationClassConfig.getDriverSupplier(FirefoxDriverSupplierConfigEntity.class, annotation);

        assertTrue(supplier instanceof FirefoxDriverSupplier);
        assertEquals("firefox", ((FirefoxDriverSupplier)supplier).getDriverPath());
    }

    @Test
    public void getPhantomDriverSupplierAnnotationTest() throws Exception {
        DriverSupplier annotation = PhantomDriverSupplierConfigEntity.class.getDeclaredAnnotation(Page.class).driverSupplier();

        IDriverSupplier supplier = AnnotationClassConfig.getDriverSupplier(PhantomDriverSupplierConfigEntity.class, annotation);

        assertTrue(supplier instanceof PhantomDriverSupplier);
        assertEquals("phantom", ((PhantomDriverSupplier)supplier).getDriverPath());
    }

    @Test
    public void getDriverLoaderWithoutAnnotationTest() throws Exception {
        DriverLoader annotation = NoAnnotationConfigEntity.class.getDeclaredAnnotation(Page.class).driverLoader();
        assertNull(AnnotationClassConfig.getDriverLoader(NoAnnotationConfigEntity.class, annotation));
    }

    @Test
    public void getMyImplementationDriverLoaderAnnotationTest() throws Exception {
        DriverLoader annotation = AnnotationConfigEntity.class.getDeclaredAnnotation(Page.class).driverLoader();

        IDriverLoader loader = AnnotationClassConfig.getDriverLoader(AnnotationConfigEntity.class, annotation);

        assertTrue(loader instanceof MyDriverLoader);
    }

    @Test
    public void getComposedDriverLoaderAnnotationTest() throws Exception {
        DriverLoader.Action[] actions = DriverLoaderConfigEntity.class.getDeclaredAnnotation(Page.class).driverLoader().actions();
        IDriverLoader loader = AnnotationClassConfig.getDriverLoader(DriverLoaderConfigEntity.class, actions);

        assertTrue(loader instanceof ComposedDriverLoader);

        List<IDriverLoader> loaders = new LinkedList<>(((ComposedDriverLoader)loader).getLoaders());

        assertEquals(actions.length, loaders.size());
        assertTrue(loaders.get(0) instanceof ClearDriverLoader);
        assertTrue(loaders.get(1) instanceof ClickDriverLoader);
        assertTrue(loaders.get(2) instanceof DoubleClickDriverLoader);
        assertTrue(loaders.get(3) instanceof SendKeysDriverLoader);
        assertTrue(loaders.get(4) instanceof SubmitDriverLoader);
        assertTrue(loaders.get(5) instanceof WaitDriverLoader);
        assertTrue(loaders.get(6) instanceof WaitVisibleDriverLoader);
    }

    @Test
    public void getDriverLoaderAnnotationTest() throws Exception {
        DriverLoader.Action[] actions = DriverLoaderConfigEntity.class.getDeclaredAnnotation(Page.class).driverLoader().actions();
        DriverLoader.Action action;

        action = actions[0];
        IDriverLoader loader = AnnotationClassConfig.getDriverLoader(DriverLoaderConfigEntity.class, action);
        assertTrue(loader instanceof ClearDriverLoader);

        action = actions[1];
        loader = AnnotationClassConfig.getDriverLoader(DriverLoaderConfigEntity.class, action);
        assertTrue(loader instanceof ClickDriverLoader);

        action = actions[2];
        loader = AnnotationClassConfig.getDriverLoader(DriverLoaderConfigEntity.class, action);
        assertTrue(loader instanceof DoubleClickDriverLoader);

        action = actions[3];
        loader = AnnotationClassConfig.getDriverLoader(DriverLoaderConfigEntity.class, action);
        assertTrue(loader instanceof SendKeysDriverLoader);

        action = actions[4];
        loader = AnnotationClassConfig.getDriverLoader(DriverLoaderConfigEntity.class, action);
        assertTrue(loader instanceof SubmitDriverLoader);

        action = actions[5];
        loader = AnnotationClassConfig.getDriverLoader(DriverLoaderConfigEntity.class, action);
        assertTrue(loader instanceof WaitDriverLoader);

        action = actions[6];
        loader = AnnotationClassConfig.getDriverLoader(DriverLoaderConfigEntity.class, action);
        assertTrue(loader instanceof WaitVisibleDriverLoader);
    }

    @Test
    public void getUnknownDriverLoaderAnnotationTest() throws Exception {
        DriverLoader.Action[] actions = UnknownDriverLoaderConfigEntity.class.getDeclaredAnnotation(Page.class).driverLoader().actions();
        DriverLoader.Action action = actions[0];

        try {
            AnnotationClassConfig.getDriverLoader(UnknownDriverLoaderConfigEntity.class, action);
            fail("Exception should be thrown!");
        } catch (ScrapperException e) {
            assertTrue(e.getMessage().toLowerCase().contains("unknown"));
            assertTrue(e.getMessage().toLowerCase().contains("action"));
        }
    }

    @Test
    public void getDriverLoaderClearAnnotationTest() throws Exception {
        DriverLoader.Action[] actions = DriverLoaderConfigEntity.class.getDeclaredAnnotation(Page.class).driverLoader().actions();
        DriverLoader.Action action = actions[0];

        ClearDriverLoader loader = AnnotationClassConfig.getDriverLoader(DriverLoaderConfigEntity.class, action.clear());

        assertNotNull(loader);
    }

    @Test
    public void getDriverLoaderClickAnnotationTest() throws Exception {
        DriverLoader.Action[] actions = DriverLoaderConfigEntity.class.getDeclaredAnnotation(Page.class).driverLoader().actions();
        DriverLoader.Action action = actions[1];

        ClickDriverLoader loader = AnnotationClassConfig.getDriverLoader(DriverLoaderConfigEntity.class, action.click());

        assertNotNull(loader);
    }

    @Test
    public void getDriverLoaderDoubleClickAnnotationTest() throws Exception {
        DriverLoader.Action[] actions = DriverLoaderConfigEntity.class.getDeclaredAnnotation(Page.class).driverLoader().actions();
        DriverLoader.Action action = actions[2];

        DoubleClickDriverLoader loader = AnnotationClassConfig.getDriverLoader(DriverLoaderConfigEntity.class, action.doubleClick());

        assertNotNull(loader);
    }

    @Test
    public void getDriverLoaderSendKeysAnnotationTest() throws Exception {
        DriverLoader.Action[] actions = DriverLoaderConfigEntity.class.getDeclaredAnnotation(Page.class).driverLoader().actions();
        DriverLoader.Action action = actions[3];

        SendKeysDriverLoader loader = AnnotationClassConfig.getDriverLoader(DriverLoaderConfigEntity.class, action.sendKeys());

        assertEquals("text", loader.getKeys());
    }

    @Test
    public void getDriverLoaderSubmitAnnotationTest() throws Exception {
        DriverLoader.Action[] actions = DriverLoaderConfigEntity.class.getDeclaredAnnotation(Page.class).driverLoader().actions();
        DriverLoader.Action action = actions[4];

        SubmitDriverLoader loader = AnnotationClassConfig.getDriverLoader(DriverLoaderConfigEntity.class, action.submit());

        assertNotNull(loader);
    }

    @Test
    public void getDriverLoaderWaitAnnotationTest() throws Exception {
        DriverLoader.Action[] actions = DriverLoaderConfigEntity.class.getDeclaredAnnotation(Page.class).driverLoader().actions();
        DriverLoader.Action action = actions[5];

        WaitDriverLoader loader = AnnotationClassConfig.getDriverLoader(DriverLoaderConfigEntity.class, action.waitFor());

        assertEquals(1000, loader.getTime());
        assertEquals(TimeUnit.DAYS, loader.getTimeUnit());
    }

    @Test
    public void getDriverLoaderWaitVisibleAnnotationTest() throws Exception {
        DriverLoader.Action[] actions = DriverLoaderConfigEntity.class.getDeclaredAnnotation(Page.class).driverLoader().actions();
        DriverLoader.Action action = actions[6];

        WaitVisibleDriverLoader loader = AnnotationClassConfig.getDriverLoader(DriverLoaderConfigEntity.class, action.waitVisible());

        assertEquals(1000, loader.getTime());
        assertEquals(TimeUnit.DAYS, loader.getTimeUnit());
        assertTrue(loader.getBy() instanceof By.ById);
        assertTrue(loader.getBy().toString().contains("elementId"));
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
