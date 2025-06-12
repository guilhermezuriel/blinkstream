package com.blinkstream.catalog.admin.domain.category;

import com.blinkstream.catalog.admin.domain.pagination.Pagination;

import java.util.Optional;

public interface CategoryGateway {

    Category create(Category aCategory);

    void deleteById(Long aCategoryId);

    Optional<Category> findById(Long aCategoryId);

    Category update(Category aCategory);

    Pagination<Category> findAll(CategorySearchQuery aQuery);
}
