package com.github.brunomndantas.jscrapper.support.urlSupplier;

import com.github.brunomndantas.jscrapper.core.urlSupplier.IURLSupplier;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class ParameterizedURLSupplierTest {

    @Test
    public void getSourceTest() {
        IURLSupplier source = () -> null;
        ParameterizedURLSupplier supplier = new ParameterizedURLSupplier(source, null);
        assertSame(source, supplier.getSource());
    }

    @Test
    public void getParametersTest() {
        Map<String, String> parameters = new HashMap<>();
        ParameterizedURLSupplier supplier = new ParameterizedURLSupplier(null, parameters);
        assertSame(parameters, supplier.getParameters());
    }

    @Test
    public void constructorTest() {
        IURLSupplier source = () -> null;
        Map<String, String> parameters = new HashMap<>();

        ParameterizedURLSupplier supplier = new ParameterizedURLSupplier(source, parameters);

        assertSame(source, supplier.getSource());
        assertSame(parameters, supplier.getParameters());
    }

    @Test
    public void getTest() throws Exception {
        IURLSupplier source = () -> "www.google.pt/{name}/{color}";
        Map<String, String> parameters = new HashMap<>();
        parameters.put("name", "paul");
        parameters.put("color", "red");

        ParameterizedURLSupplier supplier = new ParameterizedURLSupplier(source, parameters);

        assertEquals("www.google.pt/paul/red", supplier.get());
    }

}
