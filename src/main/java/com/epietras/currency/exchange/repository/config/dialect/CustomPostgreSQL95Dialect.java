package com.epietras.currency.exchange.repository.config.dialect;

import org.hibernate.dialect.PostgreSQL95Dialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.StandardBasicTypes;

public class CustomPostgreSQL95Dialect extends PostgreSQL95Dialect {

    public CustomPostgreSQL95Dialect() {
        super();
        registerFunction("string_agg", new SQLFunctionTemplate(StandardBasicTypes.STRING, "string_agg(?1, ?2)"));
    }
}