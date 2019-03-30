package com.github.brunomndantas.jscrapper.support.urlSupplier;

import com.github.brunomndantas.jscrapper.core.urlSupplier.URLSupplierException;
import org.junit.Test;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

public class URLSupplierTest {

    @Test
    public void getSuccessTest() throws URLSupplierException {
        String url = "";
        URLSupplier supplier = new URLSupplier() {@Override protected String getURL() { return url; }};

        assertSame(url, supplier.get());
    }

    @Test
    public void getWrapsException() {
        Exception exception = new Exception();
        URLSupplier supplier = new URLSupplier() {@Override protected String getURL() throws Exception { throw exception; }};

        try {
            supplier.get();
            fail("Exception should be thrown!");
        } catch(Exception e) {
            assertSame(exception, e.getCause());
        }
    }

}
