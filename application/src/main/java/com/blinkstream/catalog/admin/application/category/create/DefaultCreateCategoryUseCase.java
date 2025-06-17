package com.blinkstream.catalog.admin.application.category.create;

import com.blinkstream.catalog.admin.domain.category.Category;
import com.blinkstream.catalog.admin.domain.category.CategoryGateway;
import com.blinkstream.catalog.admin.domain.validation.handler.ThrowsValidationHandler;

public class DefaultCreateCategoryUseCase extends CreateCategoryUseCase {

    private final CategoryGateway categoryGateway;

    public DefaultCreateCategoryUseCase(CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    @Override
    public CreateCategoryOutput execute(CreateCategoryCommand anInput) {
        final var aName = anInput.name();
        final var aDescription = anInput.description();
        final var isActive = anInput.isActive();

        final var aCategory = Category.newCategory(aName, aDescription, isActive);
        aCategory.validate(new ThrowsValidationHandler());

        return CreateCategoryOutput.from(this.categoryGateway.create(aCategory));
    }
}
