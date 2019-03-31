package com.github.brunomndantas.jscrapper.scrapper.annotation.element;

import com.github.brunomndantas.jscrapper.core.elementLoader.IElementLoader;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ElementLoader {

    @Retention(RetentionPolicy.RUNTIME)
    @interface Clear {
        /*Ignore this property. This property it is just to distinguish between userDefined annotation or default value annotation*/
        @Deprecated boolean isUserDefined() default true;
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface Click {
        /*Ignore this property. This property it is just to distinguish between userDefined annotation or default value annotation*/
        @Deprecated boolean isUserDefined() default true;
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface DoubleClick {
        /*Ignore this property. This property it is just to distinguish between userDefined annotation or default value annotation*/
        @Deprecated boolean isUserDefined() default true;
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface SendKeys {
        String value() default "";
        /*Ignore this property. This property it is just to distinguish between userDefined annotation or default value annotation*/
        @Deprecated boolean isUserDefined() default true;
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface Submit {
        /*Ignore this property. This property it is just to distinguish between userDefined annotation or default value annotation*/
        @Deprecated boolean isUserDefined() default true;
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface Wait {
        long value() default 1;
        TimeUnit unit() default TimeUnit.SECONDS;
        /*Ignore this property. This property it is just to distinguish between userDefined annotation or default value annotation*/
        @Deprecated boolean isUserDefined() default true;
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface WaitVisible {
        long value() default 1;
        TimeUnit unit() default TimeUnit.SECONDS;
        /*Ignore this property. This property it is just to distinguish between userDefined annotation or default value annotation*/
        @Deprecated boolean isUserDefined() default true;
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

    Class<? extends IElementLoader> value() default IElementLoader.class;

}
