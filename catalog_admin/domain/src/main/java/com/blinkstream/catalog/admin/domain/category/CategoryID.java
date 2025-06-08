package com.blinkstream.catalog.admin.domain.category;

import com.blinkstream.catalog.admin.domain.Identifier;

import java.util.Objects;
import java.util.UUID;

public class CategoryID extends Identifier {

    private final String value;

    private CategoryID(String value) {
        this.value = value;
    }

    public static CategoryID unique(){
        return CategoryID.from(UUID.randomUUID().toString().toLowerCase());
    }

    public static CategoryID from (final String anId){
        return new CategoryID(anId);
    }

    public static CategoryID from (final UUID anId){
        return new CategoryID(anId.toString());
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        final CategoryID that = (CategoryID) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
