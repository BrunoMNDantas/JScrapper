package com.github.brunomndantas.jscrapper.scrapper.annotation.page;

import com.github.brunomndantas.jscrapper.core.driverLoader.IDriverLoader;
import com.github.brunomndantas.jscrapper.scrapper.annotation.SelectorType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.TimeUnit;

@Retention(RetentionPolicy.RUNTIME)
public @interface DriverLoader {

    @Retention(RetentionPolicy.RUNTIME)
    @interface Clear {
        String selector() default "";
        SelectorType selectorType() default SelectorType.ID;
        /*Ignore this property. This property it is just to distinguish between userDefined annotation or default value annotation*/
        @Deprecated boolean isUserDefined() default true;
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface Click {
        String selector() default "";
        SelectorType selectorType() default SelectorType.ID;
        /*Ignore this property. This property it is just to distinguish between userDefined annotation or default value annotation*/
        @Deprecated boolean isUserDefined() default true;
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface DoubleClick {
        String selector() default "";
        SelectorType selectorType() default SelectorType.ID;
        /*Ignore this property. This property it is just to distinguish between userDefined annotation or default value annotation*/
        @Deprecated boolean isUserDefined() default true;
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface SendKeys {
        String value() default "";
        String selector() default "";
        SelectorType selectorType() default SelectorType.ID;
        /*Ignore this property. This property it is just to distinguish between userDefined annotation or default value annotation*/
        @Deprecated boolean isUserDefined() default true;
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface Submit {
        String selector() default "";
        SelectorType selectorType() default SelectorType.ID;
        /*Ignore this property. This property it is just to distinguish between userDefined annotation or default value annotation*/
        @Deprecated boolean isUserDefined() default true;
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface Wait {
        long value() default 1;
        TimeUnit unit() default TimeUnit.SECONDS;
        /*Ignore this property. This property it is just to distinguish between userDefined annotation or default value annotation*/
        boolean isUserDefined() default true;
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface WaitVisible {
        long value() default 1;
        TimeUnit unit() default TimeUnit.SECONDS;
        String selector() default "";
        SelectorType selectorType() default SelectorType.ID;
        /*Ignore this property. This property it is just to distinguish between userDefined annotation or default value annotation*/
        boolean isUserDefined() default true;
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface Action {
        Clear clear() default @Clear(isUserDefined = false);
        Click click() default @Click(isUserDefined = false);
        DoubleClick doubleClick() default @DoubleClick(isUserDefined = false);
        SendKeys sendKeys() default @SendKeys(isUserDefined = false);
        Submit submit() default @Submit(isUserDefined = false);
        Wait waitFor() default @Wait(isUserDefined = false);
        WaitVisible waitVisible() default @WaitVisible(isUserDefined = false);
    }



    Action[] actions() default {};

    Class<? extends IDriverLoader> value() default IDriverLoader.class;

}
