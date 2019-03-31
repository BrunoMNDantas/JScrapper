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
import com.github.brunomndantas.jscrapper.scrapper.annotation.element.ElementLoader;
import com.github.brunomndantas.jscrapper.scrapper.annotation.element.Parser;
import com.github.brunomndantas.jscrapper.scrapper.annotation.element.Property;
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
        @DriverLoader(MyDriverLoader.class)
        @Selector(MySelector.class)
        @ElementLoader(MyElementLoader.class)
        @Parser(MyParser.class)
        @Property(MyProperty.class)
        public String name;
    }

    private static class NoAnnotationEntity {
        public String name;
    }

    private static class ElementLoaderConfigEntity {
        @ElementLoader(actions = {
            @ElementLoader.Action(clear = @ElementLoader.Clear()),
            @ElementLoader.Action(click = @ElementLoader.Click()),
            @ElementLoader.Action(doubleClick = @ElementLoader.DoubleClick()),
            @ElementLoader.Action(sendKeys = @ElementLoader.SendKeys("text")),
            @ElementLoader.Action(submit = @ElementLoader.Submit()),
            @ElementLoader.Action(waitFor = @ElementLoader.Wait(value = 1000, unit = TimeUnit.DAYS)),
            @ElementLoader.Action(waitVisible = @ElementLoader.WaitVisible(value = 1000, unit = TimeUnit.DAYS))
        })
        public String name;
    }

    private static class UnknownElementLoaderConfigEntity {
        @ElementLoader(actions = {@ElementLoader.Action(clear = @ElementLoader.Clear(isUserDefined = false))})
        public String name;
    }

    private static class SelectorConfigEntity {
        @Selector(selector = "id", selectorType = SelectorType.ID)
        public String id;

        @Selector(selector = "name", selectorType = SelectorType.NAME)
        public String name;

        @Selector(selector = "class", selectorType = SelectorType.CLASS)
        public String klass;

        @Selector(selector = "tag", selectorType = SelectorType.TAG)
        public String tag;

        @Selector(selector = "linkText", selectorType = SelectorType.LINK_TEXT)
        public String linkText;

        @Selector(selector = "partialLinkText", selectorType = SelectorType.PARTIAL_LINK_TEXT)
        public String partialLinkText;

        @Selector(selector = "css", selectorType = SelectorType.CSS)
        public String css;

        @Selector(selector = "xpath", selectorType = SelectorType.XPATH)
        public String xpath;
    }

    private static class PersonSingleParser {
        @Parser(attribute = "att")
        public boolean _boolean;
        @Parser(attribute = "att")
        public byte _byte;
        @Parser(attribute = "att")
        public char _char;
        @Parser(attribute = "att")
        public double _double;
        @Parser(attribute = "att")
        public float _float;
        @Parser(attribute = "att")
        public int _int;
        @Parser(attribute = "att")
        public long _long;
        @Parser(attribute = "att")
        public short _short;

        @Parser(attribute = "att")
        public Boolean _Boolean;
        @Parser(attribute = "att")
        public Byte _Byte;
        @Parser(attribute = "att")
        public Character _Character;
        @Parser(attribute = "att", dateFormat = "date")
        public Date _Date;
        @Parser(attribute = "att")
        public Double _Double;
        @Parser(attribute = "att")
        public Float _Float;
        @Parser(attribute = "att")
        public Integer _Integer;
        @Parser(attribute = "att")
        public Long _Long;
        @Parser(attribute = "att")
        public Short _Short;
        @Parser(attribute = "att")
        public String _String;
        @Parser(attribute = "att")
        public Object _Object;
    }

    private static class PersonArrayParser {
        @Parser(attribute = "att")
        public boolean[] _boolean;
        @Parser(attribute = "att")
        public byte[] _byte;
        @Parser(attribute = "att")
        public char[] _char;
        @Parser(attribute = "att")
        public double[] _double;
        @Parser(attribute = "att")
        public float[] _float;
        @Parser(attribute = "att")
        public int[] _int;
        @Parser(attribute = "att")
        public long[] _long;
        @Parser(attribute = "att")
        public short[] _short;

        @Parser(attribute = "att")
        public Boolean[] _Boolean;
        @Parser(attribute = "att")
        public Byte[] _Byte;
        @Parser(attribute = "att")
        public Character[] _Character;
        @Parser(attribute = "att", dateFormat = "date")
        public Date[] _Date;
        @Parser(attribute = "att")
        public Double[] _Double;
        @Parser(attribute = "att")
        public Float[] _Float;
        @Parser(attribute = "att")
        public Integer[] _Integer;
        @Parser(attribute = "att")
        public Long[] _Long;
        @Parser(attribute = "att")
        public Short[] _Short;
        @Parser(attribute = "att")
        public String[] _String;
        @Parser(attribute = "att")
        public Object[] _Object;
    }

    private static class PersonCollectionParser {
        @Parser(attribute = "att")
        public Collection<Boolean> _Boolean;
        @Parser(attribute = "att")
        public Collection<Byte> _Byte;
        @Parser(attribute = "att")
        public Collection<Character> _Character;
        @Parser(attribute = "att", dateFormat = "date")
        public Collection<Date> _Date;
        @Parser(attribute = "att")
        public Collection<Double> _Double;
        @Parser(attribute = "att")
        public Collection<Float> _Float;
        @Parser(attribute = "att")
        public Collection<Integer> _Integer;
        @Parser(attribute = "att")
        public Collection<Long> _Long;
        @Parser(attribute = "att")
        public Collection<Short> _Short;
        @Parser(attribute = "att")
        public Collection<String> _String;
        @Parser(attribute = "att")
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
    public void getDriverLoaderWithoutAnnotationTest() throws Exception {
        Field field = NoAnnotationEntity.class.getDeclaredField("name");
        assertNull(AnnotationFieldConfig.getDriverLoader(field));
    }

    @Test
    public void getDriverLoaderTest() throws Exception {
        Field field = AnnotationConfigEntity.class.getDeclaredField("name");

        IDriverLoader loader = AnnotationFieldConfig.getDriverLoader(field);

        assertTrue(loader instanceof MyDriverLoader);
    }

    @Test
    public void getSelectorWithoutAnnotationTest() throws Exception {
        Field field = NoAnnotationEntity.class.getDeclaredField("name");
        assertNull(AnnotationFieldConfig.getSelector(field));
    }

    @Test
    public void getMyImplementationSelectorAnnotationTest() throws Exception {
        Field field = AnnotationConfigEntity.class.getDeclaredField("name");

        ISelector selector = AnnotationFieldConfig.getSelector(field);

        assertTrue(selector instanceof MySelector);
    }

    @Test
    public void getSelectorIdAnnotationTest() throws Exception {
        Field field = SelectorConfigEntity.class.getDeclaredField("id");

        ISelector selector = AnnotationFieldConfig.getSelector(field);

        assertTrue(selector instanceof IdSelector);
        assertEquals("id", ((IdSelector)selector).getSelector());
    }

    @Test
    public void getSelectorNameAnnotationTest() throws Exception {
        Field field = SelectorConfigEntity.class.getDeclaredField("name");

        ISelector selector = AnnotationFieldConfig.getSelector(field);

        assertTrue(selector instanceof NameSelector);
        assertEquals("name", ((NameSelector)selector).getSelector());
    }

    @Test
    public void getSelectorClassAnnotationTest() throws Exception {
        Field field = SelectorConfigEntity.class.getDeclaredField("klass");

        ISelector selector = AnnotationFieldConfig.getSelector(field);

        assertTrue(selector instanceof ClassNameSelector);
        assertEquals("class", ((ClassNameSelector)selector).getSelector());
    }

    @Test
    public void getSelectorTagAnnotationTest() throws Exception {
        Field field = SelectorConfigEntity.class.getDeclaredField("tag");

        ISelector selector = AnnotationFieldConfig.getSelector(field);

        assertTrue(selector instanceof TagNameSelector);
        assertEquals("tag", ((TagNameSelector)selector).getSelector());
    }

    @Test
    public void getSelectorLinkTextAnnotationTest() throws Exception {
        Field field = SelectorConfigEntity.class.getDeclaredField("linkText");

        ISelector selector = AnnotationFieldConfig.getSelector(field);

        assertTrue(selector instanceof LinkTextSelector);
        assertEquals("linkText", ((LinkTextSelector)selector).getSelector());
    }

    @Test
    public void getSelectorPartialLinkTextAnnotationTest() throws Exception {
        Field field = SelectorConfigEntity.class.getDeclaredField("partialLinkText");

        ISelector selector = AnnotationFieldConfig.getSelector(field);

        assertTrue(selector instanceof PartialLinkTextSelector);
        assertEquals("partialLinkText", ((PartialLinkTextSelector)selector).getSelector());
    }

    @Test
    public void getSelectorCSSAnnotationTest() throws Exception {
        Field field = SelectorConfigEntity.class.getDeclaredField("css");

        ISelector selector = AnnotationFieldConfig.getSelector(field);

        assertTrue(selector instanceof CSSSelector);
        assertEquals("css", ((CSSSelector)selector).getSelector());
    }

    @Test
    public void getSelectorXPathAnnotationTest() throws Exception {
        Field field = SelectorConfigEntity.class.getDeclaredField("xpath");

        ISelector selector = AnnotationFieldConfig.getSelector(field);

        assertTrue(selector instanceof XPathSelector);
        assertEquals("xpath", ((XPathSelector)selector).getSelector());
    }

    @Test
    public void getElementLoaderWithoutAnnotationTest() throws Exception {
        Field field = NoAnnotationEntity.class.getDeclaredField("name");
        assertNull(AnnotationFieldConfig.getElementLoader(field));
    }

    @Test
    public void getMyImplementationElementLoaderAnnotationTest() throws Exception {
        Field field = AnnotationConfigEntity.class.getDeclaredField("name");

        IElementLoader loader = AnnotationFieldConfig.getElementLoader(field);

        assertTrue(loader instanceof MyElementLoader);
    }

    @Test
    public void getComposedElementLoaderAnnotationTest() throws Exception {
        Field field = ElementLoaderConfigEntity.class.getDeclaredField("name");
        ElementLoader.Action[] actions = field.getDeclaredAnnotation(ElementLoader.class).actions();
        IElementLoader loader = AnnotationFieldConfig.getElementLoader(field);

        assertTrue(loader instanceof ComposedElementLoader);

        List<IElementLoader> loaders = new LinkedList<>(((ComposedElementLoader)loader).getLoaders());

        assertEquals(actions.length, loaders.size());
        assertTrue(loaders.get(0) instanceof ClearElementLoader);
        assertTrue(loaders.get(1) instanceof ClickElementLoader);
        assertTrue(loaders.get(2) instanceof DoubleClickElementLoader);
        assertTrue(loaders.get(3) instanceof SendKeysElementLoader);
        assertEquals("text", ((SendKeysElementLoader)loaders.get(3)).getKeys());
        assertTrue(loaders.get(4) instanceof SubmitElementLoader);
        assertTrue(loaders.get(5) instanceof WaitElementLoader);
        assertEquals(1000, ((WaitElementLoader)loaders.get(5)).getTime());
        assertEquals(TimeUnit.DAYS, ((WaitElementLoader)loaders.get(5)).getTimeUnit());
        assertTrue(loaders.get(6) instanceof WaitVisibleElementLoader);
        assertEquals(1000, ((WaitVisibleElementLoader)loaders.get(6)).getTime());
        assertEquals(TimeUnit.DAYS, ((WaitVisibleElementLoader)loaders.get(6)).getTimeUnit());
    }

    @Test
    public void getUnknownElementLoaderAnnotationTest() throws Exception {
        Field field = UnknownElementLoaderConfigEntity.class.getDeclaredField("name");
        try {
            AnnotationFieldConfig.getElementLoader(field);
            fail("Exception should be thrown!");
        } catch (ScrapperException e) {
            assertTrue(e.getMessage().toLowerCase().contains("unknown"));
            assertTrue(e.getMessage().toLowerCase().contains("action"));
        }
    }

    @Test
    public void getMyImplementationParserAnnotationTest() throws Exception {
        Field field = AnnotationConfigEntity.class.getDeclaredField("name");

        IParser parser = AnnotationFieldConfig.getParser(field);

        assertTrue(parser instanceof MyParser);
    }

    @Test
    public void getParserWithoutAnnotationTest() throws Exception {
        Field field = NoAnnotationEntity.class.getDeclaredField("name");
        assertNull(AnnotationFieldConfig.getParser(field));
    }

    @Test
    public void getParserTest() throws Exception {
        Field field = AnnotationConfigEntity.class.getDeclaredField("name");
        IParser parser = AnnotationFieldConfig.getParser(field);
        assertTrue(parser instanceof MyParser);

        field = PersonArrayParser.class.getDeclaredField("_boolean");
        parser = AnnotationFieldConfig.getParser(field);
        assertTrue(parser instanceof ArrayPrimitiveBooleanAttributeParser);

        field = PersonCollectionParser.class.getDeclaredField("_Boolean");
        parser = AnnotationFieldConfig.getParser(field);
        assertTrue(parser instanceof CollectionReferenceBooleanAttributeParser);

        field = PersonSingleParser.class.getDeclaredField("_boolean");
        parser = AnnotationFieldConfig.getParser(field);
        assertTrue(parser instanceof SinglePrimitiveBooleanAttributeParser);
    }

    @Test
    public void getArrayParserTest() throws Exception {
        IParser parser;
        Field field;

        field = PersonArrayParser.class.getDeclaredField("_boolean");
        parser = AnnotationFieldConfig.getParser(field);
        assertTrue(parser instanceof ArrayPrimitiveBooleanAttributeParser);
        assertEquals("att",((ArrayAttributeParser)parser).getAttribute());

        field = PersonArrayParser.class.getDeclaredField("_byte");
        parser = AnnotationFieldConfig.getParser(field);
        assertTrue(parser instanceof ArrayPrimitiveByteAttributeParser);
        assertEquals("att",((ArrayAttributeParser)parser).getAttribute());

        field = PersonArrayParser.class.getDeclaredField("_char");
        parser = AnnotationFieldConfig.getParser(field);
        assertTrue(parser instanceof ArrayPrimitiveCharacterAttributeParser);
        assertEquals("att",((ArrayAttributeParser)parser).getAttribute());

        field = PersonArrayParser.class.getDeclaredField("_double");
        parser = AnnotationFieldConfig.getParser(field);
        assertTrue(parser instanceof ArrayPrimitiveDoubleAttributeParser);
        assertEquals("att",((ArrayAttributeParser)parser).getAttribute());

        field = PersonArrayParser.class.getDeclaredField("_float");
        parser = AnnotationFieldConfig.getParser(field);
        assertTrue(parser instanceof ArrayPrimitiveFloatAttributeParser);
        assertEquals("att",((ArrayAttributeParser)parser).getAttribute());

        field = PersonArrayParser.class.getDeclaredField("_int");
        parser = AnnotationFieldConfig.getParser(field);
        assertTrue(parser instanceof ArrayPrimitiveIntegerAttributeParser);
        assertEquals("att",((ArrayAttributeParser)parser).getAttribute());

        field = PersonArrayParser.class.getDeclaredField("_long");
        parser = AnnotationFieldConfig.getParser(field);
        assertTrue(parser instanceof ArrayPrimitiveLongAttributeParser);
        assertEquals("att",((ArrayAttributeParser)parser).getAttribute());

        field = PersonArrayParser.class.getDeclaredField("_short");
        parser = AnnotationFieldConfig.getParser(field);
        assertTrue(parser instanceof ArrayPrimitiveShortAttributeParser);
        assertEquals("att",((ArrayAttributeParser)parser).getAttribute());

        field = PersonArrayParser.class.getDeclaredField("_Boolean");
        parser = AnnotationFieldConfig.getParser(field);
        assertTrue(parser instanceof ArrayReferenceBooleanAttributeParser);
        assertEquals("att",((ArrayAttributeParser)parser).getAttribute());

        field = PersonArrayParser.class.getDeclaredField("_Byte");
        parser = AnnotationFieldConfig.getParser(field);
        assertTrue(parser instanceof ArrayReferenceByteAttributeParser);
        assertEquals("att",((ArrayAttributeParser)parser).getAttribute());

        field = PersonArrayParser.class.getDeclaredField("_Character");
        parser = AnnotationFieldConfig.getParser(field);
        assertTrue(parser instanceof ArrayReferenceCharacterAttributeParser);
        assertEquals("att",((ArrayAttributeParser)parser).getAttribute());

        field = PersonArrayParser.class.getDeclaredField("_Date");
        parser = AnnotationFieldConfig.getParser(field);
        assertTrue(parser instanceof ArrayReferenceDateAttributeParser);
        assertEquals("att",((ArrayAttributeParser)parser).getAttribute());
        assertEquals("date", ((ArrayReferenceDateAttributeParser)parser).getFormat());

        field = PersonArrayParser.class.getDeclaredField("_Double");
        parser = AnnotationFieldConfig.getParser(field);
        assertTrue(parser instanceof ArrayReferenceDoubleAttributeParser);
        assertEquals("att",((ArrayAttributeParser)parser).getAttribute());

        field = PersonArrayParser.class.getDeclaredField("_Float");
        parser = AnnotationFieldConfig.getParser(field);
        assertTrue(parser instanceof ArrayReferenceFloatAttributeParser);
        assertEquals("att",((ArrayAttributeParser)parser).getAttribute());

        field = PersonArrayParser.class.getDeclaredField("_Integer");
        parser = AnnotationFieldConfig.getParser(field);
        assertTrue(parser instanceof ArrayReferenceIntegerAttributeParser);
        assertEquals("att",((ArrayAttributeParser)parser).getAttribute());

        field = PersonArrayParser.class.getDeclaredField("_Long");
        parser = AnnotationFieldConfig.getParser(field);
        assertTrue(parser instanceof ArrayReferenceLongAttributeParser);
        assertEquals("att",((ArrayAttributeParser)parser).getAttribute());

        field = PersonArrayParser.class.getDeclaredField("_Short");
        parser = AnnotationFieldConfig.getParser(field);
        assertTrue(parser instanceof ArrayReferenceShortAttributeParser);
        assertEquals("att",((ArrayAttributeParser)parser).getAttribute());

        field = PersonArrayParser.class.getDeclaredField("_String");
        parser = AnnotationFieldConfig.getParser(field);
        assertTrue(parser instanceof ArrayReferenceStringAttributeParser);
        assertEquals("att",((ArrayAttributeParser)parser).getAttribute());

        field = PersonArrayParser.class.getDeclaredField("_Object");
        parser = AnnotationFieldConfig.getParser(field);
        assertNull(parser);
    }

    @Test
    public void getCollectionParserTest() throws Exception {
        IParser parser;
        Field field;

        field = PersonCollectionParser.class.getDeclaredField("_Boolean");
        parser = AnnotationFieldConfig.getParser(field);
        assertTrue(parser instanceof CollectionReferenceBooleanAttributeParser);
        assertEquals("att",((CollectionAttributeParser)parser).getAttribute());

        field = PersonCollectionParser.class.getDeclaredField("_Byte");
        parser = AnnotationFieldConfig.getParser(field);
        assertTrue(parser instanceof CollectionReferenceByteAttributeParser);
        assertEquals("att",((CollectionAttributeParser)parser).getAttribute());

        field = PersonCollectionParser.class.getDeclaredField("_Character");
        parser = AnnotationFieldConfig.getParser(field);
        assertTrue(parser instanceof CollectionReferenceCharacterAttributeParser);
        assertEquals("att",((CollectionAttributeParser)parser).getAttribute());

        field = PersonCollectionParser.class.getDeclaredField("_Date");
        parser = AnnotationFieldConfig.getParser(field);
        assertTrue(parser instanceof CollectionReferenceDateAttributeParser);
        assertEquals("att",((CollectionAttributeParser)parser).getAttribute());
        assertEquals("date", ((CollectionReferenceDateAttributeParser)parser).getFormat());

        field = PersonCollectionParser.class.getDeclaredField("_Double");
        parser = AnnotationFieldConfig.getParser(field);
        assertTrue(parser instanceof CollectionReferenceDoubleAttributeParser);
        assertEquals("att",((CollectionAttributeParser)parser).getAttribute());

        field = PersonCollectionParser.class.getDeclaredField("_Float");
        parser = AnnotationFieldConfig.getParser(field);
        assertTrue(parser instanceof CollectionReferenceFloatAttributeParser);
        assertEquals("att",((CollectionAttributeParser)parser).getAttribute());

        field = PersonCollectionParser.class.getDeclaredField("_Integer");
        parser = AnnotationFieldConfig.getParser(field);
        assertTrue(parser instanceof CollectionReferenceIntegerAttributeParser);
        assertEquals("att",((CollectionAttributeParser)parser).getAttribute());

        field = PersonCollectionParser.class.getDeclaredField("_Long");
        parser = AnnotationFieldConfig.getParser(field);
        assertTrue(parser instanceof CollectionReferenceLongAttributeParser);
        assertEquals("att",((CollectionAttributeParser)parser).getAttribute());

        field = PersonCollectionParser.class.getDeclaredField("_Short");
        parser = AnnotationFieldConfig.getParser(field);
        assertTrue(parser instanceof CollectionReferenceShortAttributeParser);
        assertEquals("att",((CollectionAttributeParser)parser).getAttribute());

        field = PersonCollectionParser.class.getDeclaredField("_String");
        parser = AnnotationFieldConfig.getParser(field);
        assertTrue(parser instanceof CollectionReferenceStringAttributeParser);
        assertEquals("att",((CollectionAttributeParser)parser).getAttribute());

        field = PersonCollectionParser.class.getDeclaredField("_Object");
        parser = AnnotationFieldConfig.getParser(field);
        assertNull(parser);
    }

    @Test
    public void getSingleParserTest() throws Exception {
        IParser parser;
        Field field;

        field = PersonSingleParser.class.getDeclaredField("_boolean");
        parser = AnnotationFieldConfig.getParser(field);
        assertTrue(parser instanceof SinglePrimitiveBooleanAttributeParser);
        assertEquals("att",((SingleAttributeParser)parser).getAttribute());

        field = PersonSingleParser.class.getDeclaredField("_byte");
        parser = AnnotationFieldConfig.getParser(field);
        assertTrue(parser instanceof SinglePrimitiveByteAttributeParser);
        assertEquals("att",((SingleAttributeParser)parser).getAttribute());

        field = PersonSingleParser.class.getDeclaredField("_char");
        parser = AnnotationFieldConfig.getParser(field);
        assertTrue(parser instanceof SinglePrimitiveCharacterAttributeParser);
        assertEquals("att",((SingleAttributeParser)parser).getAttribute());

        field = PersonSingleParser.class.getDeclaredField("_double");
        parser = AnnotationFieldConfig.getParser(field);
        assertTrue(parser instanceof SinglePrimitiveDoubleAttributeParser);
        assertEquals("att",((SingleAttributeParser)parser).getAttribute());

        field = PersonSingleParser.class.getDeclaredField("_float");
        parser = AnnotationFieldConfig.getParser(field);
        assertTrue(parser instanceof SinglePrimitiveFloatAttributeParser);
        assertEquals("att",((SingleAttributeParser)parser).getAttribute());

        field = PersonSingleParser.class.getDeclaredField("_int");
        parser = AnnotationFieldConfig.getParser(field);
        assertTrue(parser instanceof SinglePrimitiveIntegerAttributeParser);
        assertEquals("att",((SingleAttributeParser)parser).getAttribute());

        field = PersonSingleParser.class.getDeclaredField("_long");
        parser = AnnotationFieldConfig.getParser(field);
        assertTrue(parser instanceof SinglePrimitiveLongAttributeParser);
        assertEquals("att",((SingleAttributeParser)parser).getAttribute());

        field = PersonSingleParser.class.getDeclaredField("_short");
        parser = AnnotationFieldConfig.getParser(field);
        assertTrue(parser instanceof SinglePrimitiveShortAttributeParser);
        assertEquals("att",((SingleAttributeParser)parser).getAttribute());

        field = PersonSingleParser.class.getDeclaredField("_Boolean");
        parser = AnnotationFieldConfig.getParser(field);
        assertTrue(parser instanceof SingleReferenceBooleanAttributeParser);
        assertEquals("att",((SingleAttributeParser)parser).getAttribute());

        field = PersonSingleParser.class.getDeclaredField("_Byte");
        parser = AnnotationFieldConfig.getParser(field);
        assertTrue(parser instanceof SingleReferenceByteAttributeParser);
        assertEquals("att",((SingleAttributeParser)parser).getAttribute());

        field = PersonSingleParser.class.getDeclaredField("_Character");
        parser = AnnotationFieldConfig.getParser(field);
        assertTrue(parser instanceof SingleReferenceCharacterAttributeParser);
        assertEquals("att",((SingleAttributeParser)parser).getAttribute());

        field = PersonSingleParser.class.getDeclaredField("_Date");
        parser = AnnotationFieldConfig.getParser(field);
        assertTrue(parser instanceof SingleReferenceDateAttributeParser);
        assertEquals("att",((SingleAttributeParser)parser).getAttribute());
        assertEquals("date", ((SingleReferenceDateAttributeParser)parser).getFormat());

        field = PersonSingleParser.class.getDeclaredField("_Double");
        parser = AnnotationFieldConfig.getParser(field);
        assertTrue(parser instanceof SingleReferenceDoubleAttributeParser);
        assertEquals("att",((SingleAttributeParser)parser).getAttribute());

        field = PersonSingleParser.class.getDeclaredField("_Float");
        parser = AnnotationFieldConfig.getParser(field);
        assertTrue(parser instanceof SingleReferenceFloatAttributeParser);
        assertEquals("att",((SingleAttributeParser)parser).getAttribute());

        field = PersonSingleParser.class.getDeclaredField("_Integer");
        parser = AnnotationFieldConfig.getParser(field);
        assertTrue(parser instanceof SingleReferenceIntegerAttributeParser);
        assertEquals("att",((SingleAttributeParser)parser).getAttribute());

        field = PersonSingleParser.class.getDeclaredField("_Long");
        parser = AnnotationFieldConfig.getParser(field);
        assertTrue(parser instanceof SingleReferenceLongAttributeParser);
        assertEquals("att",((SingleAttributeParser)parser).getAttribute());

        field = PersonSingleParser.class.getDeclaredField("_Short");
        parser = AnnotationFieldConfig.getParser(field);
        assertTrue(parser instanceof SingleReferenceShortAttributeParser);
        assertEquals("att",((SingleAttributeParser)parser).getAttribute());

        field = PersonSingleParser.class.getDeclaredField("_String");
        parser = AnnotationFieldConfig.getParser(field);
        assertTrue(parser instanceof SingleReferenceStringAttributeParser);
        assertEquals("att",((SingleAttributeParser)parser).getAttribute());

        field = PersonSingleParser.class.getDeclaredField("_Object");
        parser = AnnotationFieldConfig.getParser(field);
        assertNull(parser);
    }

    @Test
    public void getMyImplementationPropertyAnnotationTest() throws Exception {
        Field field = AnnotationConfigEntity.class.getDeclaredField("name");

        IProperty property = AnnotationFieldConfig.getProperty(field);

        assertTrue(property instanceof MyProperty);
    }

    @Test
    public void getPropertyWithoutAnnotationTest() throws Exception {
        Field field = NoAnnotationEntity.class.getDeclaredField("name");
        assertNull(AnnotationFieldConfig.getProperty(field));
    }

}
