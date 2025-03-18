package com.rightprice.auth.config;

import org.hibernate.dialect.SQLServer2012Dialect;
import org.hibernate.type.StringType;

import static java.sql.Types.NVARCHAR;

/**
 * @author Akash
 */
public class CustomMsSqlDialect extends SQLServer2012Dialect {

    public CustomMsSqlDialect() {
        super();
        registerHibernateType(NVARCHAR, StringType.INSTANCE.getName());
    }

}
