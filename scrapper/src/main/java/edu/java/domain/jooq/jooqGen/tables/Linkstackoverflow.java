/*
 * This file is generated by jOOQ.
 */
package edu.java.domain.jooq.jooqGen.tables;


import edu.java.domain.jooq.jooqGen.Keys;
import edu.java.domain.jooq.jooqGen.Public;
import edu.java.domain.jooq.jooqGen.tables.Link.LinkPath;
import edu.java.domain.jooq.jooqGen.tables.records.LinkstackoverflowRecord;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.annotation.processing.Generated;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.InverseForeignKey;
import org.jooq.Name;
import org.jooq.Path;
import org.jooq.PlainSQL;
import org.jooq.QueryPart;
import org.jooq.Record;
import org.jooq.SQL;
import org.jooq.Schema;
import org.jooq.Select;
import org.jooq.Stringly;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


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
public class Linkstackoverflow extends TableImpl<LinkstackoverflowRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.linkstackoverflow</code>
     */
    public static final Linkstackoverflow LINKSTACKOVERFLOW = new Linkstackoverflow();

    /**
     * The class holding records for this type
     */
    @Override
    @NotNull
    public Class<LinkstackoverflowRecord> getRecordType() {
        return LinkstackoverflowRecord.class;
    }

    /**
     * The column <code>public.linkstackoverflow.linkid</code>.
     */
    public final TableField<LinkstackoverflowRecord, Long> LINKID = createField(DSL.name("linkid"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.linkstackoverflow.commentsamount</code>.
     */
    public final TableField<LinkstackoverflowRecord, Integer> COMMENTSAMOUNT = createField(DSL.name("commentsamount"), SQLDataType.INTEGER.defaultValue(DSL.field(DSL.raw("0"), SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>public.linkstackoverflow.answersamount</code>.
     */
    public final TableField<LinkstackoverflowRecord, Integer> ANSWERSAMOUNT = createField(DSL.name("answersamount"), SQLDataType.INTEGER.defaultValue(DSL.field(DSL.raw("0"), SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>public.linkstackoverflow.answered</code>.
     */
    public final TableField<LinkstackoverflowRecord, Boolean> ANSWERED = createField(DSL.name("answered"), SQLDataType.BOOLEAN.defaultValue(DSL.field(DSL.raw("false"), SQLDataType.BOOLEAN)), this, "");

    private Linkstackoverflow(Name alias, Table<LinkstackoverflowRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private Linkstackoverflow(Name alias, Table<LinkstackoverflowRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>public.linkstackoverflow</code> table reference
     */
    public Linkstackoverflow(String alias) {
        this(DSL.name(alias), LINKSTACKOVERFLOW);
    }

    /**
     * Create an aliased <code>public.linkstackoverflow</code> table reference
     */
    public Linkstackoverflow(Name alias) {
        this(alias, LINKSTACKOVERFLOW);
    }

    /**
     * Create a <code>public.linkstackoverflow</code> table reference
     */
    public Linkstackoverflow() {
        this(DSL.name("linkstackoverflow"), null);
    }

    public <O extends Record> Linkstackoverflow(Table<O> path, ForeignKey<O, LinkstackoverflowRecord> childPath, InverseForeignKey<O, LinkstackoverflowRecord> parentPath) {
        super(path, childPath, parentPath, LINKSTACKOVERFLOW);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class LinkstackoverflowPath extends Linkstackoverflow implements Path<LinkstackoverflowRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> LinkstackoverflowPath(Table<O> path, ForeignKey<O, LinkstackoverflowRecord> childPath, InverseForeignKey<O, LinkstackoverflowRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private LinkstackoverflowPath(Name alias, Table<LinkstackoverflowRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public LinkstackoverflowPath as(String alias) {
            return new LinkstackoverflowPath(DSL.name(alias), this);
        }

        @Override
        public LinkstackoverflowPath as(Name alias) {
            return new LinkstackoverflowPath(alias, this);
        }

        @Override
        public LinkstackoverflowPath as(Table<?> alias) {
            return new LinkstackoverflowPath(alias.getQualifiedName(), this);
        }
    }

    @Override
    @Nullable
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    @NotNull
    public UniqueKey<LinkstackoverflowRecord> getPrimaryKey() {
        return Keys.LINKSTACKOVERFLOW_PKEY;
    }

    @Override
    @NotNull
    public List<ForeignKey<LinkstackoverflowRecord, ?>> getReferences() {
        return Arrays.asList(Keys.LINKSTACKOVERFLOW__LINKSTACKOVERFLOW_LINKID_FKEY);
    }

    private transient LinkPath _link;

    /**
     * Get the implicit join path to the <code>public.link</code> table.
     */
    public LinkPath link() {
        if (_link == null)
            _link = new LinkPath(this, Keys.LINKSTACKOVERFLOW__LINKSTACKOVERFLOW_LINKID_FKEY, null);

        return _link;
    }

    @Override
    @NotNull
    public Linkstackoverflow as(String alias) {
        return new Linkstackoverflow(DSL.name(alias), this);
    }

    @Override
    @NotNull
    public Linkstackoverflow as(Name alias) {
        return new Linkstackoverflow(alias, this);
    }

    @Override
    @NotNull
    public Linkstackoverflow as(Table<?> alias) {
        return new Linkstackoverflow(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    @NotNull
    public Linkstackoverflow rename(String name) {
        return new Linkstackoverflow(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    @NotNull
    public Linkstackoverflow rename(Name name) {
        return new Linkstackoverflow(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    @NotNull
    public Linkstackoverflow rename(Table<?> name) {
        return new Linkstackoverflow(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @NotNull
    public Linkstackoverflow where(Condition condition) {
        return new Linkstackoverflow(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @NotNull
    public Linkstackoverflow where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @NotNull
    public Linkstackoverflow where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @NotNull
    public Linkstackoverflow where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @NotNull
    @PlainSQL
    public Linkstackoverflow where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @NotNull
    @PlainSQL
    public Linkstackoverflow where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @NotNull
    @PlainSQL
    public Linkstackoverflow where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @NotNull
    @PlainSQL
    public Linkstackoverflow where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @NotNull
    public Linkstackoverflow whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @NotNull
    public Linkstackoverflow whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
