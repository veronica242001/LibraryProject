package repository;

import classes.Librarian;
import config.DatabaseConfiguration;

import java.sql.*;
import java.util.ArrayList;

public class LibrarianRepository {
    private static LibrarianRepository librarianRepository = null;
    private LibrarianRepository (){}
    public static LibrarianRepository getInstance() {
        if( librarianRepository == null) {
            librarianRepository = new LibrarianRepository();
        }
        return librarianRepository;
    }

    //-------- insert
     public void insertLibrarian (Librarian librarian){
         String preparedSql= "{call insertLibrarian(?,?,?,?,?)}";
         Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
         try{
             CallableStatement cstmt = databaseConnection.prepareCall(preparedSql);
             cstmt.setString(2, librarian.getFirstName());
             cstmt.setString(3,librarian.getLastName());
             cstmt.setString(4, librarian.getPhoneNumber());
             cstmt.setFloat (5, librarian.getSalary());

             cstmt.registerOutParameter(1, Types.INTEGER);
             cstmt.execute();
             System.out.println("Added librarian with id:" + cstmt.getInt(1));


         }
         catch( SQLException e){
             e.printStackTrace();
         }
     }

     public Librarian getLibrarianById(int id){
         String selectSql= "SELECT * FROM librarians where id=?";
         Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
         try {
             PreparedStatement preparedStatement = databaseConnection.prepareStatement(selectSql);
             preparedStatement.setInt(1, id);

             ResultSet resultSet = preparedStatement.executeQuery();
             return mapToLibrarian(resultSet);
         } catch (SQLException e) {
             e.printStackTrace();
         }
         return null;
     }

    private Librarian mapToLibrarian(ResultSet resultSet) throws SQLException {
         if(resultSet.next()){
             return new Librarian( resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4), resultSet.getFloat(5) );
         }
         return null;
    }
    // ------ update
    public void updateLibrarianSalary(Float salary, int id) {
        String updateNameSql = "UPDATE librarians SET salary=? WHERE id=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(updateNameSql);
            preparedStatement.setFloat(1, salary);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //------ read
    public ArrayList<Librarian> getLibrarians() {
        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        ArrayList<Librarian> librarians = new ArrayList<>();

        String sqlSelect = "SELECT * FROM librarians";
        try {
        PreparedStatement preparedStatement =  databaseConnection.prepareStatement(sqlSelect);
        ResultSet resultSet = null;
        resultSet = preparedStatement.executeQuery();
            while (resultSet.next())    {
                Librarian c = new Librarian( resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4), resultSet.getFloat(5) );
                librarians.add(c);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return librarians;

    }
    //delete
    public void removeLibrarian(int  id) {
        String deleteSql = "DELETE FROM librarians WHERE id=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
                PreparedStatement preparedStatement = databaseConnection.prepareStatement(deleteSql);
                preparedStatement.setInt(1, id);
                int resultSet = preparedStatement.executeUpdate();
                System.out.println("The librarian: " + id+ " was removed!");


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
