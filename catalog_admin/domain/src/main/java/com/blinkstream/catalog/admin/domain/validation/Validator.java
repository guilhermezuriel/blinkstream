package com.blinkstream.catalog.admin.domain.validation;

public abstract class Validator {

    private final ValidationHandler handler;

    protected Validator(ValidationHandler handler) {
        this.handler = handler;
    }

    protected ValidationHandler validationHandler() {
        return handler;
    }

    public abstract void validate();
}
