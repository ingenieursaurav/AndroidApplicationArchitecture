package com.greenzhabs.arc.injection.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/** Saurav on 13-01-2018. */
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Qualifier
public @interface ScopeContext {}
