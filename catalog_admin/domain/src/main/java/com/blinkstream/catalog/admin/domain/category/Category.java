package com.blinkstream.catalog.admin.domain.category;

import com.blinkstream.catalog.admin.domain.AggregateRoot;
import com.blinkstream.catalog.admin.domain.validation.ValidationHandler;

import java.time.Instant;

public class Category extends AggregateRoot<CategoryID> {

    private String aName;
    private String aDescription;
    private boolean isActive;
    private Instant aCreationDate;
    private Instant aUpdateDate;
    private Instant aDeletionDate;


    public String getName() {
        return aName;
    }

    public String getDescription() {
        return aDescription;
    }

    public boolean isActive() {
        return isActive;
    }

    public Instant getCreationDate() {
        return aCreationDate;
    }
    public Instant getUpdateDate() {
        return aUpdateDate;
    }
    public Instant getDeletionDate() {
        return aDeletionDate;
    }

    public Category update(String aName, String aDescription, boolean active){
        if(active){
            this.activate();
        }else {
            this.deactivate();
        }
        this.aName = aName;
        this.aDescription = aDescription;
        this.aUpdateDate = Instant.now();
        return this;
    }


    public void validate(final ValidationHandler validationHandler){
        new CategoryValidator(this, validationHandler).validate();
    }

    private Category(final CategoryID anId,
                     final String aName,
                     final String description,
                     final boolean active,
                     final Instant createdAt,
                     final Instant updatedAt,
                     final Instant deletedAt) {
        super(anId);
        this.aName = aName;
        this.aDescription = description;
        this.isActive = active;
        this.aUpdateDate = updatedAt;
        this.aCreationDate = createdAt;
        this.aDeletionDate = deletedAt;
    }

    public static Category newCategory(final String name, final String description, final boolean active) {
        final CategoryID id = CategoryID.unique();
        final Instant now = Instant.now();
        final Instant deletedAt = active ? null : now;
        return new Category(id, name, description, active, Instant.now(), Instant.now(), deletedAt);
    }

    public Category deactivate(){
        if(getDeletionDate() == null){
            this.aDeletionDate = Instant.now();
        }
        this.isActive = false;
        this.aUpdateDate = Instant.now();
        return this;
    }

    public Category activate(){
        if(getDeletionDate() != null){
            this.aDeletionDate = null;
        }
        this.isActive = true;
        this.aUpdateDate = Instant.now();
        return this;
    }
}
