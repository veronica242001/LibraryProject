package repository;

import models.City;
import models.Publisher;
import config.DatabaseConfiguration;

import java.sql.*;
import java.util.ArrayList;

public class PublisherRepository {
    private CityRepository cityRepository = CityRepository.getInstance();
    private static PublisherRepository publisherRepository = null;
    private PublisherRepository (){}
    public static PublisherRepository getInstance() {
        if( publisherRepository == null) {
            publisherRepository = new PublisherRepository();
        }
        return publisherRepository;
    }

    //-------- insert
    public void insertPublisher (Publisher publisher){
        String preparedSql= "{call insertpublisher(?,?,?)}";
        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try{
            CallableStatement cstmt = databaseConnection.prepareCall(preparedSql);
            cstmt.setString(2, publisher.getName());
            cstmt.setInt(3, publisher.getCity().getId());


            cstmt.registerOutParameter(1, Types.INTEGER);
            cstmt.execute();
            System.out.println("Added publisher with id:" + cstmt.getInt(1));


        }
        catch( SQLException e){
            e.printStackTrace();
        }
    }

    public Publisher getPublisherById(int id){
        String selectSql= "SELECT * FROM publishers where id=?";
        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(selectSql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToPublisher(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Publisher mapToPublisher(ResultSet resultSet) throws SQLException {
        if(resultSet.next()){
            City city = cityRepository.getCityById( resultSet.getInt(3));
            return new Publisher( resultSet.getInt(1),resultSet.getString(2),city );
        }
        return null;
    }
    // ------ update
    public void updatePublisherName(String name, int id) {
        String updateNameSql = "UPDATE publishers SET name=? WHERE id=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(updateNameSql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //------ read
    public ArrayList<Publisher> getPublishers() {
        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        ArrayList<Publisher> publishers = new ArrayList<>();

        String sqlSelect = "SELECT * FROM publishers";
        try {
            PreparedStatement preparedStatement =  databaseConnection.prepareStatement(sqlSelect);
            ResultSet resultSet = null;
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next())    {
                City city = cityRepository.getCityById( resultSet.getInt(3));
                Publisher c = new Publisher( resultSet.getInt(1),resultSet.getString(2),city);
                publishers.add(c);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return publishers;

    }
    //delete
    public void removePublisher(int  id) {
        String deleteSql = "DELETE FROM publishers WHERE id=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(deleteSql);
            preparedStatement.setInt(1, id);
            int resultSet = preparedStatement.executeUpdate();
            System.out.println("The publisher: " + id+ " was removed!");


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
