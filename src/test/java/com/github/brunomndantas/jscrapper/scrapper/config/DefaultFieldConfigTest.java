package com.github.brunomndantas.jscrapper.scrapper.config;

import com.github.brunomndantas.jscrapper.core.config.FieldConfig;
import com.github.brunomndantas.jscrapper.core.parser.IParser;
import com.github.brunomndantas.jscrapper.core.property.IProperty;
import com.github.brunomndantas.jscrapper.core.selector.ISelector;
import com.github.brunomndantas.jscrapper.support.parser.array.ArrayWebElementParser;
import com.github.brunomndantas.jscrapper.support.parser.array.text.primitive.*;
import com.github.brunomndantas.jscrapper.support.parser.array.text.reference.*;
import com.github.brunomndantas.jscrapper.support.parser.collection.CollectionWebElementParser;
import com.github.brunomndantas.jscrapper.support.parser.collection.text.reference.*;
import com.github.brunomndantas.jscrapper.support.parser.single.SingleWebDriverParser;
import com.github.brunomndantas.jscrapper.support.parser.single.SingleWebElementParser;
import com.github.brunomndantas.jscrapper.support.parser.single.text.primitive.*;
import com.github.brunomndantas.jscrapper.support.parser.single.text.reference.*;
import com.github.brunomndantas.jscrapper.support.property.ComposedProperty;
import com.github.brunomndantas.jscrapper.support.property.FieldProperty;
import com.github.brunomndantas.jscrapper.support.property.MethodProperty;
import com.github.brunomndantas.jscrapper.support.selector.IdSelector;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Collection;
import java.util.Date;

import static org.junit.Assert.*;

public class DefaultFieldConfigTest {

    private static class PersonFieldGetterFieldSetter {
        public String name;
    }

    private static class PersonFieldGetterMethodSetter {
        public String name;
        public void setName(String name) { this.name = name; }
    }

    private static class PersonMethodGetterFieldSetter {
        public String name;
        public String getName() { return this.name; }
    }

    private static class PersonMethodGetterMethodSetter {
        public String name;
        public String getName() { return this.name; }
        public void setName(String name) { this.name = name; }
    }

    private static class PersonSingleParser {
        public boolean _boolean;
        public byte _byte;
        public char _char;
        public double _double;
        public float _float;
        public int _int;
        public long _long;
        public short _short;

        public Boolean _Boolean;
        public Byte _Byte;
        public Character _Character;
        public Date _Date;
        public Double _Double;
        public Float _Float;
        public Integer _Integer;
        public Long _Long;
        public Short _Short;
        public String _String;

        public WebElement _WebElement;
        public WebDriver _WebDriver;

        public Object _Object;
    }

    private static class PersonArrayParser {
        public boolean[] _boolean;
        public byte[] _byte;
        public char[] _char;
        public double[] _double;
        public float[] _float;
        public int[] _int;
        public long[] _long;
        public short[] _short;

        public Boolean[] _Boolean;
        public Byte[] _Byte;
        public Character[] _Character;
        public Date[] _Date;
        public Double[] _Double;
        public Float[] _Float;
        public Integer[] _Integer;
        public Long[] _Long;
        public Short[] _Short;
        public String[] _String;

        public WebElement[] _WebElement;

        public Object[] _Object;
    }

    private static class PersonCollectionParser {
        public Collection<Boolean> _Boolean;
        public Collection<Byte> _Byte;
        public Collection<Character> _Character;
        public Collection<Date> _Date;
        public Collection<Double> _Double;
        public Collection<Float> _Float;
        public Collection<Integer> _Integer;
        public Collection<Long> _Long;
        public Collection<Short> _Short;
        public Collection<String> _String;

        public Collection<WebElement> _WebElement;

        public Collection<Object> _Object;
    }



    @Test
    public void getFieldConfigTest() throws Exception {
        FieldConfig config = DefaultFieldConfig.getFieldConfig(PersonSingleParser.class, PersonSingleParser.class.getDeclaredField("_boolean"));

        assertEquals(PersonSingleParser.class.getDeclaredField("_boolean"), config.getField());
        assertNotNull(config.getDriverLoader());
        assertNotNull(config.getSelector());
        assertNotNull(config.getElementLoader());
        assertNotNull(config.getParser());
        assertNotNull(config.getProperty());
    }

    @Test
    public void getDriverLoaderTest() throws Exception {
        assertNotNull(DefaultFieldConfig.getDriverLoader(PersonSingleParser.class, PersonSingleParser.class.getDeclaredField("_boolean")));
    }

    @Test
    public void getSelectorTest() throws Exception {
        ISelector selector = DefaultFieldConfig.getSelector(PersonSingleParser.class, PersonSingleParser.class.getDeclaredField("_boolean"));

        assertNotNull(selector);
        assertTrue(selector instanceof IdSelector);
        assertEquals("_boolean", ((IdSelector)selector).getSelector());
    }

    @Test
    public void getElementLoaderTest() throws Exception {
        assertNotNull(DefaultFieldConfig.getElementLoader(PersonSingleParser.class, PersonSingleParser.class.getDeclaredField("_boolean")));
    }

    @Test
    public void getParserTest() throws Exception {
        IParser parser;

        parser = DefaultFieldConfig.getParser(PersonArrayParser.class, PersonArrayParser.class.getDeclaredField("_Boolean"));
        assertTrue(parser instanceof ArrayReferenceBooleanTextParser);

        parser = DefaultFieldConfig.getParser(PersonCollectionParser.class, PersonCollectionParser.class.getDeclaredField("_Boolean"));
        assertTrue(parser instanceof CollectionReferenceBooleanTextParser);

        parser = DefaultFieldConfig.getParser(PersonSingleParser.class, PersonSingleParser.class.getDeclaredField("_Boolean"));
        assertTrue(parser instanceof SingleReferenceBooleanTextParser);
    }

    @Test
    public void getArrayParserTest() throws Exception {
        IParser parser;

        parser = DefaultFieldConfig.getArrayParser(PersonArrayParser.class, PersonArrayParser.class.getDeclaredField("_boolean"));
        assertTrue(parser instanceof ArrayPrimitiveBooleanTextParser);

        parser = DefaultFieldConfig.getArrayParser(PersonArrayParser.class, PersonArrayParser.class.getDeclaredField("_byte"));
        assertTrue(parser instanceof ArrayPrimitiveByteTextParser);

        parser = DefaultFieldConfig.getArrayParser(PersonArrayParser.class, PersonArrayParser.class.getDeclaredField("_char"));
        assertTrue(parser instanceof ArrayPrimitiveCharacterTextParser);

        parser = DefaultFieldConfig.getArrayParser(PersonArrayParser.class, PersonArrayParser.class.getDeclaredField("_double"));
        assertTrue(parser instanceof ArrayPrimitiveDoubleTextParser);

        parser = DefaultFieldConfig.getArrayParser(PersonArrayParser.class, PersonArrayParser.class.getDeclaredField("_float"));
        assertTrue(parser instanceof ArrayPrimitiveFloatTextParser);

        parser = DefaultFieldConfig.getArrayParser(PersonArrayParser.class, PersonArrayParser.class.getDeclaredField("_int"));
        assertTrue(parser instanceof ArrayPrimitiveIntegerTextParser);

        parser = DefaultFieldConfig.getArrayParser(PersonArrayParser.class, PersonArrayParser.class.getDeclaredField("_long"));
        assertTrue(parser instanceof ArrayPrimitiveLongTextParser);

        parser = DefaultFieldConfig.getArrayParser(PersonArrayParser.class, PersonArrayParser.class.getDeclaredField("_short"));
        assertTrue(parser instanceof ArrayPrimitiveShortTextParser);

        parser = DefaultFieldConfig.getArrayParser(PersonArrayParser.class, PersonArrayParser.class.getDeclaredField("_Boolean"));
        assertTrue(parser instanceof ArrayReferenceBooleanTextParser);

        parser = DefaultFieldConfig.getArrayParser(PersonArrayParser.class, PersonArrayParser.class.getDeclaredField("_Byte"));
        assertTrue(parser instanceof ArrayReferenceByteTextParser);

        parser = DefaultFieldConfig.getArrayParser(PersonArrayParser.class, PersonArrayParser.class.getDeclaredField("_Character"));
        assertTrue(parser instanceof ArrayReferenceCharacterTextParser);

        parser = DefaultFieldConfig.getArrayParser(PersonArrayParser.class, PersonArrayParser.class.getDeclaredField("_Date"));
        assertTrue(parser instanceof ArrayReferenceDateTextParser);
        assertEquals(DefaultFieldConfig.DEFAULT_DATE_FORMAT, ((ArrayReferenceDateTextParser)parser).getFormat());

        parser = DefaultFieldConfig.getArrayParser(PersonArrayParser.class, PersonArrayParser.class.getDeclaredField("_Double"));
        assertTrue(parser instanceof ArrayReferenceDoubleTextParser);

        parser = DefaultFieldConfig.getArrayParser(PersonArrayParser.class, PersonArrayParser.class.getDeclaredField("_Float"));
        assertTrue(parser instanceof ArrayReferenceFloatTextParser);

        parser = DefaultFieldConfig.getArrayParser(PersonArrayParser.class, PersonArrayParser.class.getDeclaredField("_Integer"));
        assertTrue(parser instanceof ArrayReferenceIntegerTextParser);

        parser = DefaultFieldConfig.getArrayParser(PersonArrayParser.class, PersonArrayParser.class.getDeclaredField("_Long"));
        assertTrue(parser instanceof ArrayReferenceLongTextParser);

        parser = DefaultFieldConfig.getArrayParser(PersonArrayParser.class, PersonArrayParser.class.getDeclaredField("_Short"));
        assertTrue(parser instanceof ArrayReferenceShortTextParser);

        parser = DefaultFieldConfig.getArrayParser(PersonArrayParser.class, PersonArrayParser.class.getDeclaredField("_String"));
        assertTrue(parser instanceof ArrayReferenceStringTextParser);

        parser = DefaultFieldConfig.getArrayParser(PersonArrayParser.class, PersonArrayParser.class.getDeclaredField("_WebElement"));
        assertTrue(parser instanceof ArrayWebElementParser);

        parser = DefaultFieldConfig.getArrayParser(PersonArrayParser.class, PersonArrayParser.class.getDeclaredField("_Object"));
        assertNull(parser);
    }

    @Test
    public void getCollectionParserTest() throws Exception {
        IParser parser;

        parser = DefaultFieldConfig.getCollectionParser(PersonCollectionParser.class, PersonCollectionParser.class.getDeclaredField("_Boolean"));
        assertTrue(parser instanceof CollectionReferenceBooleanTextParser);

        parser = DefaultFieldConfig.getCollectionParser(PersonCollectionParser.class, PersonCollectionParser.class.getDeclaredField("_Byte"));
        assertTrue(parser instanceof CollectionReferenceByteTextParser);

        parser = DefaultFieldConfig.getCollectionParser(PersonCollectionParser.class, PersonCollectionParser.class.getDeclaredField("_Character"));
        assertTrue(parser instanceof CollectionReferenceCharacterTextParser);

        parser = DefaultFieldConfig.getCollectionParser(PersonCollectionParser.class, PersonCollectionParser.class.getDeclaredField("_Date"));
        assertTrue(parser instanceof CollectionReferenceDateTextParser);
        assertEquals(DefaultFieldConfig.DEFAULT_DATE_FORMAT, ((CollectionReferenceDateTextParser)parser).getFormat());

        parser = DefaultFieldConfig.getCollectionParser(PersonCollectionParser.class, PersonCollectionParser.class.getDeclaredField("_Double"));
        assertTrue(parser instanceof CollectionReferenceDoubleTextParser);

        parser = DefaultFieldConfig.getCollectionParser(PersonCollectionParser.class, PersonCollectionParser.class.getDeclaredField("_Float"));
        assertTrue(parser instanceof CollectionReferenceFloatTextParser);

        parser = DefaultFieldConfig.getCollectionParser(PersonCollectionParser.class, PersonCollectionParser.class.getDeclaredField("_Integer"));
        assertTrue(parser instanceof CollectionReferenceIntegerTextParser);

        parser = DefaultFieldConfig.getCollectionParser(PersonCollectionParser.class, PersonCollectionParser.class.getDeclaredField("_Long"));
        assertTrue(parser instanceof CollectionReferenceLongTextParser);

        parser = DefaultFieldConfig.getCollectionParser(PersonCollectionParser.class, PersonCollectionParser.class.getDeclaredField("_Short"));
        assertTrue(parser instanceof CollectionReferenceShortTextParser);

        parser = DefaultFieldConfig.getCollectionParser(PersonCollectionParser.class, PersonCollectionParser.class.getDeclaredField("_String"));
        assertTrue(parser instanceof CollectionReferenceStringTextParser);

        parser = DefaultFieldConfig.getCollectionParser(PersonCollectionParser.class, PersonCollectionParser.class.getDeclaredField("_WebElement"));
        assertTrue(parser instanceof CollectionWebElementParser);

        parser = DefaultFieldConfig.getCollectionParser(PersonCollectionParser.class, PersonCollectionParser.class.getDeclaredField("_Object"));
        assertNull(parser);
    }

    @Test
    public void getSingleParserTest() throws Exception {
        IParser parser;

        parser = DefaultFieldConfig.getSingleParser(PersonSingleParser.class, PersonSingleParser.class.getDeclaredField("_boolean"));
        assertTrue(parser instanceof SinglePrimitiveBooleanTextParser);

        parser = DefaultFieldConfig.getSingleParser(PersonSingleParser.class, PersonSingleParser.class.getDeclaredField("_byte"));
        assertTrue(parser instanceof SinglePrimitiveByteTextParser);

        parser = DefaultFieldConfig.getSingleParser(PersonSingleParser.class, PersonSingleParser.class.getDeclaredField("_char"));
        assertTrue(parser instanceof SinglePrimitiveCharacterTextParser);

        parser = DefaultFieldConfig.getSingleParser(PersonSingleParser.class, PersonSingleParser.class.getDeclaredField("_double"));
        assertTrue(parser instanceof SinglePrimitiveDoubleTextParser);

        parser = DefaultFieldConfig.getSingleParser(PersonSingleParser.class, PersonSingleParser.class.getDeclaredField("_float"));
        assertTrue(parser instanceof SinglePrimitiveFloatTextParser);

        parser = DefaultFieldConfig.getSingleParser(PersonSingleParser.class, PersonSingleParser.class.getDeclaredField("_int"));
        assertTrue(parser instanceof SinglePrimitiveIntegerTextParser);

        parser = DefaultFieldConfig.getSingleParser(PersonSingleParser.class, PersonSingleParser.class.getDeclaredField("_long"));
        assertTrue(parser instanceof SinglePrimitiveLongTextParser);

        parser = DefaultFieldConfig.getSingleParser(PersonSingleParser.class, PersonSingleParser.class.getDeclaredField("_short"));
        assertTrue(parser instanceof SinglePrimitiveShortTextParser);

        parser = DefaultFieldConfig.getSingleParser(PersonSingleParser.class, PersonSingleParser.class.getDeclaredField("_Boolean"));
        assertTrue(parser instanceof SingleReferenceBooleanTextParser);

        parser = DefaultFieldConfig.getSingleParser(PersonSingleParser.class, PersonSingleParser.class.getDeclaredField("_Byte"));
        assertTrue(parser instanceof SingleReferenceByteTextParser);

        parser = DefaultFieldConfig.getSingleParser(PersonSingleParser.class, PersonSingleParser.class.getDeclaredField("_Character"));
        assertTrue(parser instanceof SingleReferenceCharacterTextParser);

        parser = DefaultFieldConfig.getSingleParser(PersonSingleParser.class, PersonSingleParser.class.getDeclaredField("_Date"));
        assertTrue(parser instanceof SingleReferenceDateTextParser);
        assertEquals(DefaultFieldConfig.DEFAULT_DATE_FORMAT, ((SingleReferenceDateTextParser)parser).getFormat());

        parser = DefaultFieldConfig.getSingleParser(PersonSingleParser.class, PersonSingleParser.class.getDeclaredField("_Double"));
        assertTrue(parser instanceof SingleReferenceDoubleTextParser);

        parser = DefaultFieldConfig.getSingleParser(PersonSingleParser.class, PersonSingleParser.class.getDeclaredField("_Float"));
        assertTrue(parser instanceof SingleReferenceFloatTextParser);

        parser = DefaultFieldConfig.getSingleParser(PersonSingleParser.class, PersonSingleParser.class.getDeclaredField("_Integer"));
        assertTrue(parser instanceof SingleReferenceIntegerTextParser);

        parser = DefaultFieldConfig.getSingleParser(PersonSingleParser.class, PersonSingleParser.class.getDeclaredField("_Long"));
        assertTrue(parser instanceof SingleReferenceLongTextParser);

        parser = DefaultFieldConfig.getSingleParser(PersonSingleParser.class, PersonSingleParser.class.getDeclaredField("_Short"));
        assertTrue(parser instanceof SingleReferenceShortTextParser);

        parser = DefaultFieldConfig.getSingleParser(PersonSingleParser.class, PersonSingleParser.class.getDeclaredField("_String"));
        assertTrue(parser instanceof SingleReferenceStringTextParser);

        parser = DefaultFieldConfig.getSingleParser(PersonSingleParser.class, PersonSingleParser.class.getDeclaredField("_WebElement"));
        assertTrue(parser instanceof SingleWebElementParser);

        parser = DefaultFieldConfig.getSingleParser(PersonSingleParser.class, PersonSingleParser.class.getDeclaredField("_WebDriver"));
        assertTrue(parser instanceof SingleWebDriverParser);

        parser = DefaultFieldConfig.getSingleParser(PersonSingleParser.class, PersonSingleParser.class.getDeclaredField("_Object"));
        assertNull(parser);
    }

    @Test
    public void getPropertyTest() throws Exception {
        IProperty property;

        property = DefaultFieldConfig.getProperty(PersonFieldGetterFieldSetter.class, PersonFieldGetterFieldSetter.class.getDeclaredField("name"));
        assertTrue(property instanceof FieldProperty);
        assertEquals(PersonFieldGetterFieldSetter.class.getDeclaredField("name"), ((FieldProperty)property).getField());

        property = DefaultFieldConfig.getProperty(PersonFieldGetterMethodSetter.class, PersonFieldGetterMethodSetter.class.getDeclaredField("name"));
        assertTrue(property instanceof ComposedProperty);
        assertTrue(((ComposedProperty)property).getGetter() instanceof FieldProperty);
        assertEquals(PersonFieldGetterMethodSetter.class.getDeclaredField("name"), ((FieldProperty)((ComposedProperty)property).getGetter()).getField());
        assertTrue(((ComposedProperty)property).getSetter() instanceof MethodProperty);
        assertEquals(PersonFieldGetterMethodSetter.class.getDeclaredField("name"), ((MethodProperty)((ComposedProperty)property).getSetter()).getField());

        property = DefaultFieldConfig.getProperty(PersonMethodGetterFieldSetter.class, PersonMethodGetterFieldSetter.class.getDeclaredField("name"));
        assertTrue(property instanceof ComposedProperty);
        assertTrue(((ComposedProperty)property).getGetter() instanceof MethodProperty);
        assertEquals(PersonMethodGetterFieldSetter.class.getDeclaredField("name"), ((MethodProperty)((ComposedProperty)property).getGetter()).getField());
        assertTrue(((ComposedProperty)property).getSetter() instanceof FieldProperty);
        assertEquals(PersonMethodGetterFieldSetter.class.getDeclaredField("name"), ((FieldProperty)((ComposedProperty)property).getSetter()).getField());

        property = DefaultFieldConfig.getProperty(PersonMethodGetterMethodSetter.class, PersonMethodGetterMethodSetter.class.getDeclaredField("name"));
        assertTrue(property instanceof MethodProperty);
        assertEquals(PersonMethodGetterMethodSetter.class.getDeclaredField("name"), ((MethodProperty)property).getField());
    }

}
