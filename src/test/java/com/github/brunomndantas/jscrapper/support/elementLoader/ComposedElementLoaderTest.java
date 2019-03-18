package com.github.brunomndantas.jscrapper.support.elementLoader;

import com.github.brunomndantas.jscrapper.DummyDriver;
import com.github.brunomndantas.jscrapper.DummyElement;
import com.github.brunomndantas.jscrapper.core.elementLoader.IElementLoader;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class ComposedElementLoaderTest {

    @Test
    public void getLoadersTest() {
        Collection<IElementLoader> loaders = new LinkedList<>();
        ComposedElementLoader loader = new ComposedElementLoader(loaders);

        assertSame(loaders, loader.getLoaders());
    }

    @Test
    public void constructorTest() {
        Collection<IElementLoader> loaders = new LinkedList<>();
        ComposedElementLoader loader = new ComposedElementLoader(loaders);

        assertSame(loaders, loader.getLoaders());
    }

    @Test
    public void loadTest() throws Exception {
        boolean[] passedA = new boolean[1];
        boolean[] passedB = new boolean[1];
        DummyDriver driver = new DummyDriver();
        Collection<WebElement> elements = Arrays.asList(new DummyElement());
        Collection<IElementLoader> loaders = Arrays.asList(
            (d, e) -> passedA [0] = d==driver && e==elements,
            (d, e) -> passedB [0] = d==driver && e==elements
        );
        ComposedElementLoader loader = new ComposedElementLoader(loaders);

        loader.load(driver, elements);
        assertTrue(passedA[0]);
        assertTrue(passedB[0]);
    }

}
