package com.blinkstream.catalog.admin.application.category.create;

public record CreateCategoryCommand(
        String name,
        String description,
        boolean isActive
){
    public static CreateCategoryCommand with(String name, String description, boolean isActive) {
        return new CreateCategoryCommand(name, description, isActive);
    }
}
