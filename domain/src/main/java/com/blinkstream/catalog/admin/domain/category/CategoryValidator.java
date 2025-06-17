package com.blinkstream.catalog.admin.domain.category;

import com.blinkstream.catalog.admin.domain.validation.Error;
import com.blinkstream.catalog.admin.domain.validation.ValidationHandler;
import com.blinkstream.catalog.admin.domain.validation.Validator;

public class CategoryValidator extends Validator {

    private final Category category;

    public static int NAME_MIN_LENGTH = 3;
    public static int NAME_MAX_LENGTH = 255;

    public CategoryValidator(final Category category, ValidationHandler handler) {
        super(handler);
        this.category = category;
    }

    @Override
    public void validate() {
        if(this.category.getName() == null) {
           this.validationHandler().append(new Error("'Name' should not be null"));
        }
        if(this.category.getName().isBlank()){
            this.validationHandler().append(new Error("'Name' should not be blank"));
        }
        int size = this.category.getName().trim().length();
        if(size < NAME_MIN_LENGTH || size > NAME_MAX_LENGTH) {
            this.validationHandler().append(new Error("'Name' must be between 3 and 255 characters"));
        }
    }
}
