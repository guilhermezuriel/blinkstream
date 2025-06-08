package com.blinkstream.catalog.admin.domain.category;

import com.blinkstream.catalog.admin.domain.validation.Error;
import com.blinkstream.catalog.admin.domain.validation.ValidationHandler;
import com.blinkstream.catalog.admin.domain.validation.Validator;

public class CategoryValidator extends Validator {

    private final Category category;

    public CategoryValidator(final Category category, ValidationHandler handler) {
        super(handler);
        this.category = category;
    }

    @Override
    public void validate() {
        if(this.category.getName() == null || this.category.getName().isEmpty()) {
           this.validationHandler().append(new Error("'Name' should not be null"));
        }
    }
}
