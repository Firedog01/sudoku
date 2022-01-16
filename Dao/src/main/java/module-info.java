module Dao {
    requires ModelProject;
    requires org.slf4j;
    requires mysql.connector.java;

    exports pl.comp.dao;
}