package com.luxoft.training.java.spring.intro.lab.aop;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * This annotation is for polite aspect specified in
 * {@link Politeness#sellSquishee}
 */
@SuppressWarnings("JavadocReference")
@Target(METHOD)
@Retention(RUNTIME)
public @interface Polite {
}
