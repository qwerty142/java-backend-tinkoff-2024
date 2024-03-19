/*
 * This file is generated by jOOQ.
 */
package edu.java.domain.jooq.jooqGen.tables.records;


import edu.java.domain.jooq.jooqGen.tables.Databasechangeloglock;

import jakarta.validation.constraints.Size;

import java.beans.ConstructorProperties;
import java.time.LocalDateTime;

import javax.annotation.processing.Generated;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "https://www.jooq.org",
        "jOOQ version:3.19.6"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DatabasechangeloglockRecord extends UpdatableRecordImpl<DatabasechangeloglockRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.databasechangeloglock.id</code>.
     */
    public void setId(@NotNull Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.databasechangeloglock.id</code>.
     */
    @jakarta.validation.constraints.NotNull
    @NotNull
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.databasechangeloglock.locked</code>.
     */
    public void setLocked(@NotNull Boolean value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.databasechangeloglock.locked</code>.
     */
    @jakarta.validation.constraints.NotNull
    @NotNull
    public Boolean getLocked() {
        return (Boolean) get(1);
    }

    /**
     * Setter for <code>public.databasechangeloglock.lockgranted</code>.
     */
    public void setLockgranted(@Nullable LocalDateTime value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.databasechangeloglock.lockgranted</code>.
     */
    @Nullable
    public LocalDateTime getLockgranted() {
        return (LocalDateTime) get(2);
    }

    /**
     * Setter for <code>public.databasechangeloglock.lockedby</code>.
     */
    public void setLockedby(@Nullable String value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.databasechangeloglock.lockedby</code>.
     */
    @Size(max = 255)
    @Nullable
    public String getLockedby() {
        return (String) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    @NotNull
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached DatabasechangeloglockRecord
     */
    public DatabasechangeloglockRecord() {
        super(Databasechangeloglock.DATABASECHANGELOGLOCK);
    }

    /**
     * Create a detached, initialised DatabasechangeloglockRecord
     */
    @ConstructorProperties({ "id", "locked", "lockgranted", "lockedby" })
    public DatabasechangeloglockRecord(@NotNull Integer id, @NotNull Boolean locked, @Nullable LocalDateTime lockgranted, @Nullable String lockedby) {
        super(Databasechangeloglock.DATABASECHANGELOGLOCK);

        setId(id);
        setLocked(locked);
        setLockgranted(lockgranted);
        setLockedby(lockedby);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised DatabasechangeloglockRecord
     */
    public DatabasechangeloglockRecord(edu.java.domain.jooq.jooqGen.tables.pojos.Databasechangeloglock value) {
        super(Databasechangeloglock.DATABASECHANGELOGLOCK);

        if (value != null) {
            setId(value.getId());
            setLocked(value.getLocked());
            setLockgranted(value.getLockgranted());
            setLockedby(value.getLockedby());
            resetChangedOnNotNull();
        }
    }
}
