package com.blinkstream.catalog.admin.domain.validation.handler;

import com.blinkstream.catalog.admin.domain.exceptions.DomainException;
import com.blinkstream.catalog.admin.domain.validation.Error;
import com.blinkstream.catalog.admin.domain.validation.ValidationHandler;

import java.util.List;

public class ThrowsValidationHandler implements ValidationHandler {


    @Override
    public ValidationHandler append(Error anError) {
        throw DomainException.with(List.of(anError));
    }

    @Override
    public ValidationHandler append(ValidationHandler anHandler) {
        throw DomainException.with(anHandler.getErrors());
    }

    @Override
    public ValidationHandler validate(Validation aValidation) {
        try{
            aValidation.validate();
        }catch (Exception e) {
            throw DomainException.with(List.of(new Error(e.getMessage())));
        }
        return this;
    }

    @Override
    public List<Error> getErrors() {
        return List.of();
    }
}
