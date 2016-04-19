package com.kiev.java;

public interface Validator<T> {

    boolean isValid(T result);
}
