package com.github.brunomndantas.jscrapper.support.driverLoader;

import com.github.brunomndantas.jscrapper.DummyDriver;
import com.github.brunomndantas.jscrapper.core.driverLoader.IDriverLoader;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class ComposedDriverLoaderTest {

    @Test
    public void getLoadersTest() {
        Collection<IDriverLoader> loaders = new LinkedList<>();
        ComposedDriverLoader loader = new ComposedDriverLoader(loaders);

        assertSame(loaders, loader.getLoaders());
    }

    @Test
    public void constructorTest() {
        Collection<IDriverLoader> loaders = new LinkedList<>();
        ComposedDriverLoader loader = new ComposedDriverLoader(loaders);

        assertSame(loaders, loader.getLoaders());
    }

    @Test
    public void loadTest() throws Exception {
        boolean[] passedA = new boolean[1];
        boolean[] passedB = new boolean[1];
        DummyDriver driver = new DummyDriver();
        Collection<IDriverLoader> loaders = Arrays.asList(
            (d) -> passedA [0] = d==driver,
            (d) -> passedB [0] = d==driver
        );
        ComposedDriverLoader loader = new ComposedDriverLoader(loaders);

        loader.load(driver);
        assertTrue(passedA[0]);
        assertTrue(passedB[0]);
    }

}
