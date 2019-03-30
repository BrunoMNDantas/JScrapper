package com.github.brunomndantas.jscrapper.support.urlSupplier;

import org.junit.Test;

import static org.junit.Assert.assertSame;

public class FixedURLSupplierTest {

    @Test
    public void constructorTest() {
        String url = "";
        FixedURLSupplier urlSupplier = new FixedURLSupplier(url);

        assertSame(url, urlSupplier.getURL());
    }

}
