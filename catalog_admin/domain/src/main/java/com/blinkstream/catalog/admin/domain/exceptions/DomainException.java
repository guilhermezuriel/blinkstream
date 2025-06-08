package com.blinkstream.catalog.admin.domain.exceptions;

import com.blinkstream.catalog.admin.domain.validation.Error;

import java.util.List;

public class DomainException extends NoStackTraceException {

    private final List<Error> errors;

    private DomainException(final String aMessage, final List<Error> anErrors){
        super(aMessage);
        this.errors = anErrors;
    }

    public static DomainException with(List<Error> anErrors){
        return new DomainException("", anErrors);
    }

    public static DomainException with(Error anError){
        return new DomainException("", List.of(anError));
    }

    public List<Error> getErrors() {
        return errors;
    }
}
