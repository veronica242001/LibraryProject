package repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RepositoryDb {
    private static RepositoryDb repository = new RepositoryDb();

    private RepositoryDb() {
    }

    public static RepositoryDb getRepositoryDb() {
        return repository;
    }

    public void executeSql(Connection connection, String sql) throws SQLException {
        Statement stmt = connection.createStatement();
        // execute() for updating (INSERT, UPDATE, DELETE) and SELECT instructions
        stmt.execute(sql);
        //ResultSet resultSet = stmt.getResultSet();
    }

    public void executeUpdateSql(Connection connection, String sql) throws SQLException {
        Statement stmt = connection.createStatement();
        // executeUpdate() for updating the data (INSERT, UPDATE, DELETE) or the database structure
        int i = stmt.executeUpdate(sql); // no of altered lines
    }

    public ResultSet executeQuerySql(Connection connection, String sql) throws SQLException {
        Statement stmt = connection.createStatement();
        // executeQuery() for SELECT instructions
        return stmt.executeQuery(sql);
    }

}
