module Dao {
    requires ModelProject;
    requires org.slf4j;
    requires mysql.connector.java;
    requires java.sql;

    exports pl.comp.dao;
}