package config;

import repository.RepositoryDb;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseSetup {

    public void setUp() {
        String createTableSqlLibrarians = "CREATE TABLE IF NOT EXISTS librarians(" +
                "id int PRIMARY KEY AUTO_INCREMENT,"+"" +
                "firstname varchar(30),"+
                "lastname varchar(30)," +
                "phoneNumber varchar(30)," +
                "salary float"
                +")";
        String createTableSqlReaders = "CREATE TABLE IF NOT EXISTS readers(" +
                "id int PRIMARY KEY AUTO_INCREMENT,"+"" +
                "firstname varchar(30),"+
                "lastname varchar(30)," +
                "phoneNumber varchar(30)"
                +")";
        String createTableSqlCities = "CREATE TABLE IF NOT EXISTS cities(" +
                "id int PRIMARY KEY AUTO_INCREMENT,"+"" +
                "name varchar(30),"+
                "country varchar(30)"
                +")";
        String createTableSqlPublishers ="CREATE TABLE IF NOT EXISTS publishers(" +
                "id int PRIMARY KEY AUTO_INCREMENT,"+"" +
                "name varchar(30),"+
                "idCity int,"+
                "constraint fk_city FOREIGN KEY (idCity) references cities(id)"+
                "on delete cascade"
                +")";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        RepositoryDb repository = RepositoryDb.getRepositoryDb();

        try {
            repository.executeSql(databaseConnection, createTableSqlLibrarians);
            repository.executeSql(databaseConnection, createTableSqlReaders);
            repository.executeSql(databaseConnection, createTableSqlCities);
            repository.executeSql(databaseConnection, createTableSqlPublishers);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
