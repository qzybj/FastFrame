package earlll.com.testdemoall.module.annotationdemo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface AnnotationTest {
    enum Color{ BULE,RED,GREEN}
    enum Type{TYPEA,TYPEB,TYPEC}
    /**
     * @return the desired name of the field when it is serialized or deserialized
     */
    Type[] value() default Type.TYPEA;

    /**
     * @return the alternative names of the field when it is deserialized
     */
    String[] alternate() default {};

    Color bgColor() default Color.GREEN;
}