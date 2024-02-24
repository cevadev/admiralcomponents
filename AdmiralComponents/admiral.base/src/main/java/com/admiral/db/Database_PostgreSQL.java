package com.admiral.db;

import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Database_PostgreSQL implements AdmiralDatabase{
    private static Logger logger = LoggerFactory.getLogger(Database_PostgreSQL.class);
    public Database_PostgreSQL(){}

    /** reference to Driver objeto For Postgresql Database */
    private org.postgresql.Driver s_Driver = null;

    /** Point to Driver class */
    private static final String DRIVER = "org.postgresql.Driver";

    /** Postgresql Database default port */
    private static final int DEFAULT_PORT = 5432;

    /** DS */
    private volatile HikariDataSource dataSource;

    /** Database name */
    private String  databaseName = null;

    /** User name */
    private String userName = null;
}
