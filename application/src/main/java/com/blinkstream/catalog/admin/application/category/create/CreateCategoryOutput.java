package com.blinkstream.catalog.admin.application.category.create;

import com.blinkstream.catalog.admin.domain.category.Category;
import com.blinkstream.catalog.admin.domain.category.CategoryID;

public record CreateCategoryOutput(
        CategoryID id
){

    public static CreateCategoryOutput from(final Category category) {
        return new CreateCategoryOutput(category.getId());
    }
}
