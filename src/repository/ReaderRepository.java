package repository;

import models.Reader;
import config.DatabaseConfiguration;

import java.sql.*;
import java.util.ArrayList;

public class ReaderRepository {
    private static ReaderRepository readerRepository = null;
    private ReaderRepository (){}
    public static ReaderRepository getInstance() {
        if( readerRepository == null) {
            readerRepository = new ReaderRepository();
        }
        return readerRepository;
    }

    //-------- insert
    public void insertReader (Reader reader){
        String preparedSql= "{call insertReader(?,?,?,?)}";
        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try{
            CallableStatement cstmt = databaseConnection.prepareCall(preparedSql);
            cstmt.setString(2, reader.getFirstName());
            cstmt.setString(3,reader.getLastName());
            cstmt.setString(4, reader.getPhoneNumber());

            cstmt.registerOutParameter(1, Types.INTEGER);
            cstmt.execute();
            System.out.println("Added reader with id:" + cstmt.getInt(1));


        }
        catch( SQLException e){
            e.printStackTrace();
        }
    }

    public Reader getReaderById(int id){
        String selectSql= "SELECT * FROM readers where id=?";
        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(selectSql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToReader(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Reader mapToReader(ResultSet resultSet) throws SQLException {
        if(resultSet.next()){
            return new Reader( resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4) );
        }
        return null;
    }
    // ------ update
    public void updateReaderFisrtName(String FirstName, int id) {
        String updateNameSql = "UPDATE readers SET firstname=? WHERE id=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(updateNameSql);
            preparedStatement.setString(1, FirstName);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //------ read
    public ArrayList<Reader> getReaders() {
        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        ArrayList<Reader> readers = new ArrayList<>();

        String sqlSelect = "SELECT * FROM readers";
        try {
            PreparedStatement preparedStatement =  databaseConnection.prepareStatement(sqlSelect);
            ResultSet resultSet = null;
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next())    {
                Reader c = new Reader( resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4));
                readers.add(c);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return readers;

    }
    //delete
    public void removeReader(int  id) {
        String deleteSql = "DELETE FROM readers WHERE id=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(deleteSql);
            preparedStatement.setInt(1, id);
            int resultSet = preparedStatement.executeUpdate();
            System.out.println("The reader: " + id+ " was removed!");


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
