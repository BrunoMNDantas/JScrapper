package com.github.brunomndantas.jscrapper.scrapper.config;

import com.github.brunomndantas.jscrapper.core.ScrapperException;
import com.github.brunomndantas.jscrapper.core.config.FieldConfig;
import com.github.brunomndantas.jscrapper.core.driverLoader.DriverLoaderException;
import com.github.brunomndantas.jscrapper.core.driverLoader.IDriverLoader;
import com.github.brunomndantas.jscrapper.core.elementLoader.ElementLoaderException;
import com.github.brunomndantas.jscrapper.core.elementLoader.IElementLoader;
import com.github.brunomndantas.jscrapper.core.parser.IParser;
import com.github.brunomndantas.jscrapper.core.parser.ParserException;
import com.github.brunomndantas.jscrapper.core.property.IProperty;
import com.github.brunomndantas.jscrapper.core.property.PropertyException;
import com.github.brunomndantas.jscrapper.core.selector.ISelector;
import com.github.brunomndantas.jscrapper.core.selector.SelectorException;
import com.github.brunomndantas.jscrapper.scrapper.annotation.SelectorType;
import com.github.brunomndantas.jscrapper.scrapper.annotation.element.*;
import com.github.brunomndantas.jscrapper.scrapper.annotation.element.ElementLoader;
import com.github.brunomndantas.jscrapper.scrapper.annotation.element.Selector;
import com.github.brunomndantas.jscrapper.scrapper.annotation.page.DriverLoader;
import com.github.brunomndantas.jscrapper.support.elementLoader.*;
import com.github.brunomndantas.jscrapper.support.parser.array.attribute.ArrayAttributeParser;
import com.github.brunomndantas.jscrapper.support.parser.array.attribute.primitive.*;
import com.github.brunomndantas.jscrapper.support.parser.array.attribute.reference.*;
import com.github.brunomndantas.jscrapper.support.parser.collection.attribute.CollectionAttributeParser;
import com.github.brunomndantas.jscrapper.support.parser.collection.attribute.reference.*;
import com.github.brunomndantas.jscrapper.support.parser.single.attribute.SingleAttributeParser;
import com.github.brunomndantas.jscrapper.support.parser.single.attribute.primitive.*;
import com.github.brunomndantas.jscrapper.support.parser.single.attribute.reference.*;
import com.github.brunomndantas.jscrapper.support.selector.*;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class AnnotationFieldConfigTest {

    private static class MyDriverLoader implements IDriverLoader {
        @Override public void load(WebDriver driver) throws DriverLoaderException { }
    }

    private static class MySelector implements ISelector {
        @Override public Collection<WebElement> select(WebDriver driver) throws SelectorException { return null; }
    }

    private static class MyElementLoader implements IElementLoader {
        @Override public void load(WebDriver driver, Collection<WebElement> elements) throws ElementLoaderException { }
    }

    private static class MyParser implements IParser {
        @Override public Object parse(WebDriver driver, Collection<WebElement> elements) throws ParserException { return null; }
    }

    private static class MyProperty implements IProperty {
        @Override public Object get(Object instance) throws PropertyException { return null; }
        @Override public void set(Object instance, Object value) throws PropertyException { }
    }

    private static class AnnotationConfigEntity {
        @Element(
            driverLoader = @DriverLoader(MyDriverLoader.class),
            selector =  @Selector(MySelector.class),
            elementLoader = @ElementLoader(MyElementLoader.class),
            parser = @Parser(MyParser.class),
            property = @Property(MyProperty.class)
        )
        public String name;
    }

    private static class NoAnnotationConfigEntity {
        @Element
        public String name;
    }

    private static class NoAnnotationEntity {
        public String name;
    }

    private static class ElementLoaderConfigEntity {
        @Element(elementLoader = @ElementLoader(
            actions = {
                @ElementLoader.Action(clear = @ElementLoader.Clear()),
                @ElementLoader.Action(click = @ElementLoader.Click()),
                @ElementLoader.Action(doubleClick = @ElementLoader.DoubleClick()),
                @ElementLoader.Action(sendKeys = @ElementLoader.SendKeys("text")),
                @ElementLoader.Action(submit = @ElementLoader.Submit()),
                @ElementLoader.Action(waitFor = @ElementLoader.Wait(value = 1000, unit = TimeUnit.DAYS)),
                @ElementLoader.Action(waitVisible = @ElementLoader.WaitVisible(value = 1000, unit = TimeUnit.DAYS))
            }
        ))
        public String name;
    }

    private static class UnknownElementLoaderConfigEntity {
        @Element(elementLoader = @ElementLoader(
            actions = {
                @ElementLoader.Action(clear = @ElementLoader.Clear(isUserDefined = false)),
            }
        ))
        public String name;
    }

    private static class SelectorConfigEntity {
        @Element(selector = @Selector(selector = "id", selectorType = SelectorType.ID))
        public String id;

        @Element(selector = @Selector(selector = "name", selectorType = SelectorType.NAME))
        public String name;

        @Element(selector = @Selector(selector = "class", selectorType = SelectorType.CLASS))
        public String klass;

        @Element(selector = @Selector(selector = "tag", selectorType = SelectorType.TAG))
        public String tag;

        @Element(selector = @Selector(selector = "linkText", selectorType = SelectorType.LINK_TEXT))
        public String linkText;

        @Element(selector = @Selector(selector = "partialLinkText", selectorType = SelectorType.PARTIAL_LINK_TEXT))
        public String partialLinkText;

        @Element(selector = @Selector(selector = "css", selectorType = SelectorType.CSS))
        public String css;

        @Element(selector = @Selector(selector = "xpath", selectorType = SelectorType.XPATH))
        public String xpath;
    }

    private static class PersonSingleParser {
        @Element(parser = @Parser(attribute = "att"))
        public boolean _boolean;
        @Element(parser = @Parser(attribute = "att"))
        public byte _byte;
        @Element(parser = @Parser(attribute = "att"))
        public char _char;
        @Element(parser = @Parser(attribute = "att"))
        public double _double;
        @Element(parser = @Parser(attribute = "att"))
        public float _float;
        @Element(parser = @Parser(attribute = "att"))
        public int _int;
        @Element(parser = @Parser(attribute = "att"))
        public long _long;
        @Element(parser = @Parser(attribute = "att"))
        public short _short;

        @Element(parser = @Parser(attribute = "att"))
        public Boolean _Boolean;
        @Element(parser = @Parser(attribute = "att"))
        public Byte _Byte;
        @Element(parser = @Parser(attribute = "att"))
        public Character _Character;
        @Element(parser = @Parser(attribute = "att", dateFormat = "date"))
        public Date _Date;
        @Element(parser = @Parser(attribute = "att"))
        public Double _Double;
        @Element(parser = @Parser(attribute = "att"))
        public Float _Float;
        @Element(parser = @Parser(attribute = "att"))
        public Integer _Integer;
        @Element(parser = @Parser(attribute = "att"))
        public Long _Long;
        @Element(parser = @Parser(attribute = "att"))
        public Short _Short;
        @Element(parser = @Parser(attribute = "att"))
        public String _String;
        @Element(parser = @Parser(attribute = "att"))
        public Object _Object;
    }

    private static class PersonArrayParser {
        @Element(parser = @Parser(attribute = "att"))
        public boolean[] _boolean;
        @Element(parser = @Parser(attribute = "att"))
        public byte[] _byte;
        @Element(parser = @Parser(attribute = "att"))
        public char[] _char;
        @Element(parser = @Parser(attribute = "att"))
        public double[] _double;
        @Element(parser = @Parser(attribute = "att"))
        public float[] _float;
        @Element(parser = @Parser(attribute = "att"))
        public int[] _int;
        @Element(parser = @Parser(attribute = "att"))
        public long[] _long;
        @Element(parser = @Parser(attribute = "att"))
        public short[] _short;

        @Element(parser = @Parser(attribute = "att"))
        public Boolean[] _Boolean;
        @Element(parser = @Parser(attribute = "att"))
        public Byte[] _Byte;
        @Element(parser = @Parser(attribute = "att"))
        public Character[] _Character;
        @Element(parser = @Parser(attribute = "att", dateFormat = "date"))
        public Date[] _Date;
        @Element(parser = @Parser(attribute = "att"))
        public Double[] _Double;
        @Element(parser = @Parser(attribute = "att"))
        public Float[] _Float;
        @Element(parser = @Parser(attribute = "att"))
        public Integer[] _Integer;
        @Element(parser = @Parser(attribute = "att"))
        public Long[] _Long;
        @Element(parser = @Parser(attribute = "att"))
        public Short[] _Short;
        @Element(parser = @Parser(attribute = "att"))
        public String[] _String;
        @Element(parser = @Parser(attribute = "att"))
        public Object[] _Object;
    }

    private static class PersonCollectionParser {
        @Element(parser = @Parser(attribute = "att"))
        public Collection<Boolean> _Boolean;
        @Element(parser = @Parser(attribute = "att"))
        public Collection<Byte> _Byte;
        @Element(parser = @Parser(attribute = "att"))
        public Collection<Character> _Character;
        @Element(parser = @Parser(attribute = "att", dateFormat = "date"))
        public Collection<Date> _Date;
        @Element(parser = @Parser(attribute = "att"))
        public Collection<Double> _Double;
        @Element(parser = @Parser(attribute = "att"))
        public Collection<Float> _Float;
        @Element(parser = @Parser(attribute = "att"))
        public Collection<Integer> _Integer;
        @Element(parser = @Parser(attribute = "att"))
        public Collection<Long> _Long;
        @Element(parser = @Parser(attribute = "att"))
        public Collection<Short> _Short;
        @Element(parser = @Parser(attribute = "att"))
        public Collection<String> _String;
        @Element(parser = @Parser(attribute = "att"))
        public Collection<Object> _Object;
    }



    @Test
    public void getFieldConfigWithoutAnnotationTest() throws Exception {
        FieldConfig config = AnnotationFieldConfig.getFieldConfig(NoAnnotationEntity.class.getDeclaredField("name"));

        assertEquals(NoAnnotationEntity.class.getDeclaredField("name"), config.getField());
        assertNull(config.getDriverLoader());
        assertNull(config.getSelector());
        assertNull(config.getElementLoader());
        assertNull(config.getParser());
        assertNull(config.getProperty());
    }

    @Test
    public void getFieldConfigTest() throws Exception {
        FieldConfig config = AnnotationFieldConfig.getFieldConfig(AnnotationConfigEntity.class.getDeclaredField("name"));

        assertEquals(AnnotationConfigEntity.class.getDeclaredField("name"), config.getField());
        assertNotNull(config.getDriverLoader());
        assertTrue(config.getDriverLoader() instanceof MyDriverLoader);
        assertNotNull(config.getSelector());
        assertTrue(config.getSelector() instanceof MySelector);
        assertNotNull(config.getElementLoader());
        assertTrue(config.getElementLoader() instanceof MyElementLoader);
        assertNotNull(config.getParser());
        assertTrue(config.getParser() instanceof MyParser);
        assertNotNull(config.getProperty());
        assertTrue(config.getProperty() instanceof MyProperty);
    }

    @Test
    public void getFieldConfigWrapsExceptionTest() throws Exception {
        try {
            AnnotationFieldConfig.getFieldConfig(UnknownElementLoaderConfigEntity.class.getDeclaredField("name"));
            fail("Exception should be thrown!");
        } catch (ScrapperException e) {
            assertTrue(e.getMessage().contains(UnknownElementLoaderConfigEntity.class.getDeclaredField("name").getName()));
        }
    }

    @Test
    public void getFieldConfigAnnotationTest() throws Exception {
        Element annotation = AnnotationConfigEntity.class.getDeclaredField("name").getDeclaredAnnotation(Element.class);

        FieldConfig config = AnnotationFieldConfig.getFieldConfig(AnnotationConfigEntity.class.getDeclaredField("name"), annotation);

        assertEquals(AnnotationConfigEntity.class.getDeclaredField("name"), config.getField());
        assertNotNull(config.getDriverLoader());
        assertTrue(config.getDriverLoader() instanceof MyDriverLoader);
        assertNotNull(config.getSelector());
        assertTrue(config.getSelector() instanceof MySelector);
        assertNotNull(config.getElementLoader());
        assertTrue(config.getElementLoader() instanceof MyElementLoader);
        assertNotNull(config.getParser());
        assertTrue(config.getParser() instanceof MyParser);
        assertNotNull(config.getProperty());
        assertTrue(config.getProperty() instanceof MyProperty);
    }


    @Test
    public void getDriverLoaderTest() throws Exception {
        Field field = AnnotationConfigEntity.class.getDeclaredField("name");
        DriverLoader annotation = field.getDeclaredAnnotation(Element.class).driverLoader();

        IDriverLoader loader = AnnotationFieldConfig.getDriverLoader(field, annotation);

        assertTrue(loader instanceof MyDriverLoader);
    }

    @Test
    public void getSelectorWithoutAnnotationTest() throws Exception {
        Field field = NoAnnotationConfigEntity.class.getDeclaredField("name");
        Selector annotation = field.getDeclaredAnnotation(Element.class).selector();
        assertNull(AnnotationFieldConfig.getSelector(field, annotation));
    }

    @Test
    public void getMyImplementationSelectorAnnotationTest() throws Exception {
        Field field = AnnotationConfigEntity.class.getDeclaredField("name");
        Selector annotation = field.getDeclaredAnnotation(Element.class).selector();

        ISelector selector = AnnotationFieldConfig.getSelector(field, annotation);

        assertTrue(selector instanceof MySelector);
    }

    @Test
    public void getSelectorIdAnnotationTest() throws Exception {
        Field field = SelectorConfigEntity.class.getDeclaredField("id");
        Selector annotation = field.getDeclaredAnnotation(Element.class).selector();

        ISelector selector = AnnotationFieldConfig.getSelector(field, annotation);

        assertTrue(selector instanceof IdSelector);
        assertEquals("id", ((IdSelector)selector).getSelector());
    }

    @Test
    public void getSelectorNameAnnotationTest() throws Exception {
        Field field = SelectorConfigEntity.class.getDeclaredField("name");
        Selector annotation = field.getDeclaredAnnotation(Element.class).selector();

        ISelector selector = AnnotationFieldConfig.getSelector(field, annotation);

        assertTrue(selector instanceof NameSelector);
        assertEquals("name", ((NameSelector)selector).getSelector());
    }

    @Test
    public void getSelectorClassAnnotationTest() throws Exception {
        Field field = SelectorConfigEntity.class.getDeclaredField("klass");
        Selector annotation = field.getDeclaredAnnotation(Element.class).selector();

        ISelector selector = AnnotationFieldConfig.getSelector(field, annotation);

        assertTrue(selector instanceof ClassNameSelector);
        assertEquals("class", ((ClassNameSelector)selector).getSelector());
    }

    @Test
    public void getSelectorTagAnnotationTest() throws Exception {
        Field field = SelectorConfigEntity.class.getDeclaredField("tag");
        Selector annotation = field.getDeclaredAnnotation(Element.class).selector();

        ISelector selector = AnnotationFieldConfig.getSelector(field, annotation);

        assertTrue(selector instanceof TagNameSelector);
        assertEquals("tag", ((TagNameSelector)selector).getSelector());
    }

    @Test
    public void getSelectorLinkTextAnnotationTest() throws Exception {
        Field field = SelectorConfigEntity.class.getDeclaredField("linkText");
        Selector annotation = field.getDeclaredAnnotation(Element.class).selector();

        ISelector selector = AnnotationFieldConfig.getSelector(field, annotation);

        assertTrue(selector instanceof LinkTextSelector);
        assertEquals("linkText", ((LinkTextSelector)selector).getSelector());
    }

    @Test
    public void getSelectorPartialLinkTextAnnotationTest() throws Exception {
        Field field = SelectorConfigEntity.class.getDeclaredField("partialLinkText");
        Selector annotation = field.getDeclaredAnnotation(Element.class).selector();

        ISelector selector = AnnotationFieldConfig.getSelector(field, annotation);

        assertTrue(selector instanceof PartialLinkTextSelector);
        assertEquals("partialLinkText", ((PartialLinkTextSelector)selector).getSelector());
    }

    @Test
    public void getSelectorCSSAnnotationTest() throws Exception {
        Field field = SelectorConfigEntity.class.getDeclaredField("css");
        Selector annotation = field.getDeclaredAnnotation(Element.class).selector();

        ISelector selector = AnnotationFieldConfig.getSelector(field, annotation);

        assertTrue(selector instanceof CSSSelector);
        assertEquals("css", ((CSSSelector)selector).getSelector());
    }

    @Test
    public void getSelectorXPathAnnotationTest() throws Exception {
        Field field = SelectorConfigEntity.class.getDeclaredField("xpath");
        Selector annotation = field.getDeclaredAnnotation(Element.class).selector();

        ISelector selector = AnnotationFieldConfig.getSelector(field, annotation);

        assertTrue(selector instanceof XPathSelector);
        assertEquals("xpath", ((XPathSelector)selector).getSelector());
    }

    @Test
    public void getElementLoaderWithoutAnnotationTest() throws Exception {
        Field field = NoAnnotationConfigEntity.class.getDeclaredField("name");
        ElementLoader annotation = field.getDeclaredAnnotation(Element.class).elementLoader();
        assertNull(AnnotationFieldConfig.getElementLoader(field, annotation));
    }

    @Test
    public void getMyImplementationElementLoaderAnnotationTest() throws Exception {
        Field field = AnnotationConfigEntity.class.getDeclaredField("name");
        ElementLoader annotation = field.getDeclaredAnnotation(Element.class).elementLoader();

        IElementLoader loader = AnnotationFieldConfig.getElementLoader(field, annotation);

        assertTrue(loader instanceof MyElementLoader);
    }

    @Test
    public void getComposedElementLoaderAnnotationTest() throws Exception {
        Field field = ElementLoaderConfigEntity.class.getDeclaredField("name");
        ElementLoader.Action[] actions = field.getDeclaredAnnotation(Element.class).elementLoader().actions();
        IElementLoader loader = AnnotationFieldConfig.getElementLoader(field, actions);

        assertTrue(loader instanceof ComposedElementLoader);

        List<IElementLoader> loaders = new LinkedList<>(((ComposedElementLoader)loader).getLoaders());

        assertEquals(actions.length, loaders.size());
        assertTrue(loaders.get(0) instanceof ClearElementLoader);
        assertTrue(loaders.get(1) instanceof ClickElementLoader);
        assertTrue(loaders.get(2) instanceof DoubleClickElementLoader);
        assertTrue(loaders.get(3) instanceof SendKeysElementLoader);
        assertTrue(loaders.get(4) instanceof SubmitElementLoader);
        assertTrue(loaders.get(5) instanceof WaitElementLoader);
        assertTrue(loaders.get(6) instanceof WaitVisibleElementLoader);
    }

    @Test
    public void getElementLoaderAnnotationTest() throws Exception {
        Field field = ElementLoaderConfigEntity.class.getDeclaredField("name");
        ElementLoader.Action[] actions = field.getDeclaredAnnotation(Element.class).elementLoader().actions();
        ElementLoader.Action action = actions[0];

        IElementLoader loader = AnnotationFieldConfig.getElementLoader(field, action);
        assertTrue(loader instanceof ClearElementLoader);

        action = actions[1];
        loader = AnnotationFieldConfig.getElementLoader(field, action);
        assertTrue(loader instanceof ClickElementLoader);

        action = actions[2];
        loader = AnnotationFieldConfig.getElementLoader(field, action);
        assertTrue(loader instanceof DoubleClickElementLoader);

        action = actions[3];
        loader = AnnotationFieldConfig.getElementLoader(field, action);
        assertTrue(loader instanceof SendKeysElementLoader);

        action = actions[4];
        loader = AnnotationFieldConfig.getElementLoader(field, action);
        assertTrue(loader instanceof SubmitElementLoader);

        action = actions[5];
        loader = AnnotationFieldConfig.getElementLoader(field, action);
        assertTrue(loader instanceof WaitElementLoader);

        action = actions[6];
        loader = AnnotationFieldConfig.getElementLoader(field, action);
        assertTrue(loader instanceof WaitVisibleElementLoader);
    }

    @Test
    public void getUnknownElementLoaderAnnotationTest() throws Exception {
        Field field = UnknownElementLoaderConfigEntity.class.getDeclaredField("name");
        ElementLoader.Action[] actions = field.getDeclaredAnnotation(Element.class).elementLoader().actions();
        ElementLoader.Action action = actions[0];

        try {
            AnnotationFieldConfig.getElementLoader(field, action);
            fail("Exception should be thrown!");
        } catch (ScrapperException e) {
            assertTrue(e.getMessage().toLowerCase().contains("unknown"));
            assertTrue(e.getMessage().toLowerCase().contains("action"));
        }
    }

    @Test
    public void getElementLoaderClearAnnotationTest() throws Exception {
        Field field = ElementLoaderConfigEntity.class.getDeclaredField("name");
        ElementLoader.Action[] actions = field.getDeclaredAnnotation(Element.class).elementLoader().actions();
        ElementLoader.Action action = actions[0];

        ClearElementLoader loader = AnnotationFieldConfig.getElementLoader(field, action.clear());

        assertNotNull(loader);
    }

    @Test
    public void getElementLoaderClickAnnotationTest() throws Exception {
        Field field = ElementLoaderConfigEntity.class.getDeclaredField("name");
        ElementLoader.Action[] actions = field.getDeclaredAnnotation(Element.class).elementLoader().actions();
        ElementLoader.Action action = actions[1];

        ClickElementLoader loader = AnnotationFieldConfig.getElementLoader(field, action.click());

        assertNotNull(loader);
    }

    @Test
    public void getElementLoaderDoubleClickAnnotationTest() throws Exception {
        Field field = ElementLoaderConfigEntity.class.getDeclaredField("name");
        ElementLoader.Action[] actions = field.getDeclaredAnnotation(Element.class).elementLoader().actions();
        ElementLoader.Action action = actions[2];

        DoubleClickElementLoader loader = AnnotationFieldConfig.getElementLoader(field, action.doubleClick());

        assertNotNull(loader);
    }

    @Test
    public void getElementLoaderSendKeysAnnotationTest() throws Exception {
        Field field  = ElementLoaderConfigEntity.class.getDeclaredField("name");
        ElementLoader.Action[] actions = field.getDeclaredAnnotation(Element.class).elementLoader().actions();
        ElementLoader.Action action = actions[3];

        SendKeysElementLoader loader = AnnotationFieldConfig.getElementLoader(field, action.sendKeys());

        assertNotNull(loader);
        assertEquals("text", loader.getKeys());
    }

    @Test
    public void getElementLoaderSubmitAnnotationTest() throws Exception {
        Field field = ElementLoaderConfigEntity.class.getDeclaredField("name");
        ElementLoader.Action[] actions = field.getDeclaredAnnotation(Element.class).elementLoader().actions();
        ElementLoader.Action action = actions[4];

        SubmitElementLoader loader = AnnotationFieldConfig.getElementLoader(field, action.submit());

        assertNotNull(loader);
    }

    @Test
    public void getElementLoaderWaitAnnotationTest() throws Exception {
        Field field = ElementLoaderConfigEntity.class.getDeclaredField("name");
        ElementLoader.Action[] actions = field.getDeclaredAnnotation(Element.class).elementLoader().actions();
        ElementLoader.Action action = actions[5];

        WaitElementLoader loader = AnnotationFieldConfig.getElementLoader(field, action.waitFor());

        assertNotNull(loader);
        assertEquals(1000, loader.getTime());
        assertEquals(TimeUnit.DAYS, loader.getTimeUnit());
    }

    @Test
    public void getElementLoaderWaitVisibleAnnotationTest() throws Exception {
        Field field = ElementLoaderConfigEntity.class.getDeclaredField("name");
        ElementLoader.Action[] actions = field.getDeclaredAnnotation(Element.class).elementLoader().actions();
        ElementLoader.Action action = actions[6];

        WaitVisibleElementLoader loader = AnnotationFieldConfig.getElementLoader(field, action.waitVisible());

        assertNotNull(loader);
        assertEquals(1000, loader.getTime());
        assertEquals(TimeUnit.DAYS, loader.getTimeUnit());
    }

    @Test
    public void getMyImplementationParserAnnotationTest() throws Exception {
        Field field = AnnotationConfigEntity.class.getDeclaredField("name");
        Parser annotation = field.getDeclaredAnnotation(Element.class).parser();

        IParser parser = AnnotationFieldConfig.getParser(field, annotation);

        assertTrue(parser instanceof MyParser);
    }

    @Test
    public void getParserWithoutAnnotationTest() throws Exception {
        Field field = NoAnnotationConfigEntity.class.getDeclaredField("name");
        Parser annotation = field.getDeclaredAnnotation(Element.class).parser();
        assertNull(AnnotationFieldConfig.getParser(field, annotation));
    }

    @Test
    public void getParserTest() throws Exception {
        Field field = AnnotationConfigEntity.class.getDeclaredField("name");
        Parser annotation = field.getDeclaredAnnotation(Element.class).parser();
        IParser parser = AnnotationFieldConfig.getParser(field, annotation);
        assertTrue(parser instanceof MyParser);

        field = PersonArrayParser.class.getDeclaredField("_boolean");
        annotation = field.getDeclaredAnnotation(Element.class).parser();
        parser = AnnotationFieldConfig.getParser(field, annotation);

        assertTrue(parser instanceof ArrayPrimitiveBooleanAttributeParser);

        field = PersonCollectionParser.class.getDeclaredField("_Boolean");
        annotation = field.getDeclaredAnnotation(Element.class).parser();
        parser = AnnotationFieldConfig.getParser(field, annotation);

        assertTrue(parser instanceof CollectionReferenceBooleanAttributeParser);

        field = PersonSingleParser.class.getDeclaredField("_boolean");
        annotation = field.getDeclaredAnnotation(Element.class).parser();
        parser = AnnotationFieldConfig.getParser(field, annotation);

        assertTrue(parser instanceof SinglePrimitiveBooleanAttributeParser);
    }

    @Test
    public void getArrayParserTest() throws Exception {
        IParser parser;
        Field field;

        field = PersonArrayParser.class.getDeclaredField("_boolean");
        parser = AnnotationFieldConfig.getArrayParser(field, field.getDeclaredAnnotation(Element.class).parser());
        assertTrue(parser instanceof ArrayPrimitiveBooleanAttributeParser);
        assertEquals("att",((ArrayAttributeParser)parser).getAttribute());

        field = PersonArrayParser.class.getDeclaredField("_byte");
        parser = AnnotationFieldConfig.getArrayParser(field, field.getDeclaredAnnotation(Element.class).parser());
        assertTrue(parser instanceof ArrayPrimitiveByteAttributeParser);
        assertEquals("att",((ArrayAttributeParser)parser).getAttribute());

        field = PersonArrayParser.class.getDeclaredField("_char");
        parser = AnnotationFieldConfig.getArrayParser(field, field.getDeclaredAnnotation(Element.class).parser());
        assertTrue(parser instanceof ArrayPrimitiveCharacterAttributeParser);
        assertEquals("att",((ArrayAttributeParser)parser).getAttribute());

        field = PersonArrayParser.class.getDeclaredField("_double");
        parser = AnnotationFieldConfig.getArrayParser(field, field.getDeclaredAnnotation(Element.class).parser());
        assertTrue(parser instanceof ArrayPrimitiveDoubleAttributeParser);
        assertEquals("att",((ArrayAttributeParser)parser).getAttribute());

        field = PersonArrayParser.class.getDeclaredField("_float");
        parser = AnnotationFieldConfig.getArrayParser(field, field.getDeclaredAnnotation(Element.class).parser());
        assertTrue(parser instanceof ArrayPrimitiveFloatAttributeParser);
        assertEquals("att",((ArrayAttributeParser)parser).getAttribute());

        field = PersonArrayParser.class.getDeclaredField("_int");
        parser = AnnotationFieldConfig.getArrayParser(field, field.getDeclaredAnnotation(Element.class).parser());
        assertTrue(parser instanceof ArrayPrimitiveIntegerAttributeParser);
        assertEquals("att",((ArrayAttributeParser)parser).getAttribute());

        field = PersonArrayParser.class.getDeclaredField("_long");
        parser = AnnotationFieldConfig.getArrayParser(field, field.getDeclaredAnnotation(Element.class).parser());
        assertTrue(parser instanceof ArrayPrimitiveLongAttributeParser);
        assertEquals("att",((ArrayAttributeParser)parser).getAttribute());

        field = PersonArrayParser.class.getDeclaredField("_short");
        parser = AnnotationFieldConfig.getArrayParser(field, field.getDeclaredAnnotation(Element.class).parser());
        assertTrue(parser instanceof ArrayPrimitiveShortAttributeParser);
        assertEquals("att",((ArrayAttributeParser)parser).getAttribute());

        field = PersonArrayParser.class.getDeclaredField("_Boolean");
        parser = AnnotationFieldConfig.getArrayParser(field, field.getDeclaredAnnotation(Element.class).parser());
        assertTrue(parser instanceof ArrayReferenceBooleanAttributeParser);
        assertEquals("att",((ArrayAttributeParser)parser).getAttribute());

        field = PersonArrayParser.class.getDeclaredField("_Byte");
        parser = AnnotationFieldConfig.getArrayParser(field, field.getDeclaredAnnotation(Element.class).parser());
        assertTrue(parser instanceof ArrayReferenceByteAttributeParser);
        assertEquals("att",((ArrayAttributeParser)parser).getAttribute());

        field = PersonArrayParser.class.getDeclaredField("_Character");
        parser = AnnotationFieldConfig.getArrayParser(field, field.getDeclaredAnnotation(Element.class).parser());
        assertTrue(parser instanceof ArrayReferenceCharacterAttributeParser);
        assertEquals("att",((ArrayAttributeParser)parser).getAttribute());

        field = PersonArrayParser.class.getDeclaredField("_Date");
        parser = AnnotationFieldConfig.getArrayParser(field, field.getDeclaredAnnotation(Element.class).parser());
        assertTrue(parser instanceof ArrayReferenceDateAttributeParser);
        assertEquals("att",((ArrayAttributeParser)parser).getAttribute());
        assertEquals("date", ((ArrayReferenceDateAttributeParser)parser).getFormat());

        field = PersonArrayParser.class.getDeclaredField("_Double");
        parser = AnnotationFieldConfig.getArrayParser(field, field.getDeclaredAnnotation(Element.class).parser());
        assertTrue(parser instanceof ArrayReferenceDoubleAttributeParser);
        assertEquals("att",((ArrayAttributeParser)parser).getAttribute());

        field = PersonArrayParser.class.getDeclaredField("_Float");
        parser = AnnotationFieldConfig.getArrayParser(field, field.getDeclaredAnnotation(Element.class).parser());
        assertTrue(parser instanceof ArrayReferenceFloatAttributeParser);
        assertEquals("att",((ArrayAttributeParser)parser).getAttribute());

        field = PersonArrayParser.class.getDeclaredField("_Integer");
        parser = AnnotationFieldConfig.getArrayParser(field, field.getDeclaredAnnotation(Element.class).parser());
        assertTrue(parser instanceof ArrayReferenceIntegerAttributeParser);
        assertEquals("att",((ArrayAttributeParser)parser).getAttribute());

        field = PersonArrayParser.class.getDeclaredField("_Long");
        parser = AnnotationFieldConfig.getArrayParser(field, field.getDeclaredAnnotation(Element.class).parser());
        assertTrue(parser instanceof ArrayReferenceLongAttributeParser);
        assertEquals("att",((ArrayAttributeParser)parser).getAttribute());

        field = PersonArrayParser.class.getDeclaredField("_Short");
        parser = AnnotationFieldConfig.getArrayParser(field, field.getDeclaredAnnotation(Element.class).parser());
        assertTrue(parser instanceof ArrayReferenceShortAttributeParser);
        assertEquals("att",((ArrayAttributeParser)parser).getAttribute());

        field = PersonArrayParser.class.getDeclaredField("_String");
        parser = AnnotationFieldConfig.getArrayParser(field, field.getDeclaredAnnotation(Element.class).parser());
        assertTrue(parser instanceof ArrayReferenceStringAttributeParser);
        assertEquals("att",((ArrayAttributeParser)parser).getAttribute());

        field = PersonArrayParser.class.getDeclaredField("_Object");
        parser = AnnotationFieldConfig.getArrayParser(field, field.getDeclaredAnnotation(Element.class).parser());
        assertNull(parser);
    }

    @Test
    public void getCollectionParserTest() throws Exception {
        IParser parser;
        Field field;

        field = PersonCollectionParser.class.getDeclaredField("_Boolean");
        parser = AnnotationFieldConfig.getCollectionParser(field, field.getDeclaredAnnotation(Element.class).parser());
        assertTrue(parser instanceof CollectionReferenceBooleanAttributeParser);
        assertEquals("att",((CollectionAttributeParser)parser).getAttribute());

        field = PersonCollectionParser.class.getDeclaredField("_Byte");
        parser = AnnotationFieldConfig.getCollectionParser(field, field.getDeclaredAnnotation(Element.class).parser());
        assertTrue(parser instanceof CollectionReferenceByteAttributeParser);
        assertEquals("att",((CollectionAttributeParser)parser).getAttribute());

        field = PersonCollectionParser.class.getDeclaredField("_Character");
        parser = AnnotationFieldConfig.getCollectionParser(field, field.getDeclaredAnnotation(Element.class).parser());
        assertTrue(parser instanceof CollectionReferenceCharacterAttributeParser);
        assertEquals("att",((CollectionAttributeParser)parser).getAttribute());

        field = PersonCollectionParser.class.getDeclaredField("_Date");
        parser = AnnotationFieldConfig.getCollectionParser(field, field.getDeclaredAnnotation(Element.class).parser());
        assertTrue(parser instanceof CollectionReferenceDateAttributeParser);
        assertEquals("att",((CollectionAttributeParser)parser).getAttribute());
        assertEquals("date", ((CollectionReferenceDateAttributeParser)parser).getFormat());

        field = PersonCollectionParser.class.getDeclaredField("_Double");
        parser = AnnotationFieldConfig.getCollectionParser(field, field.getDeclaredAnnotation(Element.class).parser());
        assertTrue(parser instanceof CollectionReferenceDoubleAttributeParser);
        assertEquals("att",((CollectionAttributeParser)parser).getAttribute());

        field = PersonCollectionParser.class.getDeclaredField("_Float");
        parser = AnnotationFieldConfig.getCollectionParser(field, field.getDeclaredAnnotation(Element.class).parser());
        assertTrue(parser instanceof CollectionReferenceFloatAttributeParser);
        assertEquals("att",((CollectionAttributeParser)parser).getAttribute());

        field = PersonCollectionParser.class.getDeclaredField("_Integer");
        parser = AnnotationFieldConfig.getCollectionParser(field, field.getDeclaredAnnotation(Element.class).parser());
        assertTrue(parser instanceof CollectionReferenceIntegerAttributeParser);
        assertEquals("att",((CollectionAttributeParser)parser).getAttribute());

        field = PersonCollectionParser.class.getDeclaredField("_Long");
        parser = AnnotationFieldConfig.getCollectionParser(field, field.getDeclaredAnnotation(Element.class).parser());
        assertTrue(parser instanceof CollectionReferenceLongAttributeParser);
        assertEquals("att",((CollectionAttributeParser)parser).getAttribute());

        field = PersonCollectionParser.class.getDeclaredField("_Short");
        parser = AnnotationFieldConfig.getCollectionParser(field, field.getDeclaredAnnotation(Element.class).parser());
        assertTrue(parser instanceof CollectionReferenceShortAttributeParser);
        assertEquals("att",((CollectionAttributeParser)parser).getAttribute());

        field = PersonCollectionParser.class.getDeclaredField("_String");
        parser = AnnotationFieldConfig.getCollectionParser(field, field.getDeclaredAnnotation(Element.class).parser());
        assertTrue(parser instanceof CollectionReferenceStringAttributeParser);
        assertEquals("att",((CollectionAttributeParser)parser).getAttribute());

        field = PersonCollectionParser.class.getDeclaredField("_Object");
        parser = AnnotationFieldConfig.getCollectionParser(field, field.getDeclaredAnnotation(Element.class).parser());
        assertNull(parser);
    }

    @Test
    public void getSingleParserTest() throws Exception {
        IParser parser;
        Field field;

        field = PersonSingleParser.class.getDeclaredField("_boolean");
        parser = AnnotationFieldConfig.getSingleParser(field, field.getDeclaredAnnotation(Element.class).parser());
        assertTrue(parser instanceof SinglePrimitiveBooleanAttributeParser);
        assertEquals("att",((SingleAttributeParser)parser).getAttribute());

        field = PersonSingleParser.class.getDeclaredField("_byte");
        parser = AnnotationFieldConfig.getSingleParser(field, field.getDeclaredAnnotation(Element.class).parser());
        assertTrue(parser instanceof SinglePrimitiveByteAttributeParser);
        assertEquals("att",((SingleAttributeParser)parser).getAttribute());

        field = PersonSingleParser.class.getDeclaredField("_char");
        parser = AnnotationFieldConfig.getSingleParser(field, field.getDeclaredAnnotation(Element.class).parser());
        assertTrue(parser instanceof SinglePrimitiveCharacterAttributeParser);
        assertEquals("att",((SingleAttributeParser)parser).getAttribute());

        field = PersonSingleParser.class.getDeclaredField("_double");
        parser = AnnotationFieldConfig.getSingleParser(field, field.getDeclaredAnnotation(Element.class).parser());
        assertTrue(parser instanceof SinglePrimitiveDoubleAttributeParser);
        assertEquals("att",((SingleAttributeParser)parser).getAttribute());

        field = PersonSingleParser.class.getDeclaredField("_float");
        parser = AnnotationFieldConfig.getSingleParser(field, field.getDeclaredAnnotation(Element.class).parser());
        assertTrue(parser instanceof SinglePrimitiveFloatAttributeParser);
        assertEquals("att",((SingleAttributeParser)parser).getAttribute());

        field = PersonSingleParser.class.getDeclaredField("_int");
        parser = AnnotationFieldConfig.getSingleParser(field, field.getDeclaredAnnotation(Element.class).parser());
        assertTrue(parser instanceof SinglePrimitiveIntegerAttributeParser);
        assertEquals("att",((SingleAttributeParser)parser).getAttribute());

        field = PersonSingleParser.class.getDeclaredField("_long");
        parser = AnnotationFieldConfig.getSingleParser(field, field.getDeclaredAnnotation(Element.class).parser());
        assertTrue(parser instanceof SinglePrimitiveLongAttributeParser);
        assertEquals("att",((SingleAttributeParser)parser).getAttribute());

        field = PersonSingleParser.class.getDeclaredField("_short");
        parser = AnnotationFieldConfig.getSingleParser(field, field.getDeclaredAnnotation(Element.class).parser());
        assertTrue(parser instanceof SinglePrimitiveShortAttributeParser);
        assertEquals("att",((SingleAttributeParser)parser).getAttribute());

        field = PersonSingleParser.class.getDeclaredField("_Boolean");
        parser = AnnotationFieldConfig.getSingleParser(field, field.getDeclaredAnnotation(Element.class).parser());
        assertTrue(parser instanceof SingleReferenceBooleanAttributeParser);
        assertEquals("att",((SingleAttributeParser)parser).getAttribute());

        field = PersonSingleParser.class.getDeclaredField("_Byte");
        parser = AnnotationFieldConfig.getSingleParser(field, field.getDeclaredAnnotation(Element.class).parser());
        assertTrue(parser instanceof SingleReferenceByteAttributeParser);
        assertEquals("att",((SingleAttributeParser)parser).getAttribute());

        field = PersonSingleParser.class.getDeclaredField("_Character");
        parser = AnnotationFieldConfig.getSingleParser(field, field.getDeclaredAnnotation(Element.class).parser());
        assertTrue(parser instanceof SingleReferenceCharacterAttributeParser);
        assertEquals("att",((SingleAttributeParser)parser).getAttribute());

        field = PersonSingleParser.class.getDeclaredField("_Date");
        parser = AnnotationFieldConfig.getSingleParser(field, field.getDeclaredAnnotation(Element.class).parser());
        assertTrue(parser instanceof SingleReferenceDateAttributeParser);
        assertEquals("att",((SingleAttributeParser)parser).getAttribute());
        assertEquals("date", ((SingleReferenceDateAttributeParser)parser).getFormat());

        field = PersonSingleParser.class.getDeclaredField("_Double");
        parser = AnnotationFieldConfig.getSingleParser(field, field.getDeclaredAnnotation(Element.class).parser());
        assertTrue(parser instanceof SingleReferenceDoubleAttributeParser);
        assertEquals("att",((SingleAttributeParser)parser).getAttribute());

        field = PersonSingleParser.class.getDeclaredField("_Float");
        parser = AnnotationFieldConfig.getSingleParser(field, field.getDeclaredAnnotation(Element.class).parser());
        assertTrue(parser instanceof SingleReferenceFloatAttributeParser);
        assertEquals("att",((SingleAttributeParser)parser).getAttribute());

        field = PersonSingleParser.class.getDeclaredField("_Integer");
        parser = AnnotationFieldConfig.getSingleParser(field, field.getDeclaredAnnotation(Element.class).parser());
        assertTrue(parser instanceof SingleReferenceIntegerAttributeParser);
        assertEquals("att",((SingleAttributeParser)parser).getAttribute());

        field = PersonSingleParser.class.getDeclaredField("_Long");
        parser = AnnotationFieldConfig.getSingleParser(field, field.getDeclaredAnnotation(Element.class).parser());
        assertTrue(parser instanceof SingleReferenceLongAttributeParser);
        assertEquals("att",((SingleAttributeParser)parser).getAttribute());

        field = PersonSingleParser.class.getDeclaredField("_Short");
        parser = AnnotationFieldConfig.getSingleParser(field, field.getDeclaredAnnotation(Element.class).parser());
        assertTrue(parser instanceof SingleReferenceShortAttributeParser);
        assertEquals("att",((SingleAttributeParser)parser).getAttribute());

        field = PersonSingleParser.class.getDeclaredField("_String");
        parser = AnnotationFieldConfig.getSingleParser(field, field.getDeclaredAnnotation(Element.class).parser());
        assertTrue(parser instanceof SingleReferenceStringAttributeParser);
        assertEquals("att",((SingleAttributeParser)parser).getAttribute());

        field = PersonSingleParser.class.getDeclaredField("_Object");
        parser = AnnotationFieldConfig.getSingleParser(field, field.getDeclaredAnnotation(Element.class).parser());
        assertNull(parser);
    }

    @Test
    public void getMyImplementationPropertyAnnotationTest() throws Exception {
        Field field = AnnotationConfigEntity.class.getDeclaredField("name");
        Property annotation = field.getDeclaredAnnotation(Element.class).property();

        IProperty property = AnnotationFieldConfig.getProperty(field, annotation);

        assertTrue(property instanceof MyProperty);
    }

    @Test
    public void getPropertyWithoutAnnotationTest() throws Exception {
        Field field = NoAnnotationConfigEntity.class.getDeclaredField("name");
        Property annotation = field.getDeclaredAnnotation(Element.class).property();
        assertNull(AnnotationFieldConfig.getProperty(field, annotation));
    }

}
