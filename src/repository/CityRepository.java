package repository;

import models.City;
import config.DatabaseConfiguration;

import java.sql.*;
import java.util.ArrayList;

public class CityRepository {
    private static CityRepository cityRepository = null;
    private CityRepository (){}
    public static CityRepository getInstance() {
        if( cityRepository == null) {
            cityRepository = new CityRepository();
        }
        return cityRepository;
    }

    //-------- insert
    public void insertCity (City city){
        String preparedSql= "{call insertcity(?,?,?)}";
        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try{
            CallableStatement cstmt = databaseConnection.prepareCall(preparedSql);
            cstmt.setString(2, city.getName());
            cstmt.setString(3,city.getCountry());


            cstmt.registerOutParameter(1, Types.INTEGER);
            cstmt.execute();
            System.out.println("Added city with id:" + cstmt.getInt(1));


        }
        catch( SQLException e){
            e.printStackTrace();
        }
    }

    public City getCityById(int id){
        String selectSql= "SELECT * FROM cities where id=?";
        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(selectSql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToCity(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private City mapToCity(ResultSet resultSet) throws SQLException {
        if(resultSet.next()){
            return new City( resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3) );
        }
        return null;
    }
    // ------ update
    public void updateCityName(String name, int id) {
        String updateNameSql = "UPDATE cities SET name=? WHERE id=?";

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
    public ArrayList<City> getCities() {
        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        ArrayList<City> cities = new ArrayList<>();

        String sqlSelect = "SELECT * FROM cities";
        try {
            PreparedStatement preparedStatement =  databaseConnection.prepareStatement(sqlSelect);
            ResultSet resultSet = null;
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next())    {
                City c = new City( resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3));
                cities.add(c);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return cities;

    }
    //delete
    public void removeCity(int  id) {
        String deleteSql = "DELETE FROM cities WHERE id=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(deleteSql);
            preparedStatement.setInt(1, id);
            int resultSet = preparedStatement.executeUpdate();
            System.out.println("The city: " + id+ " was removed!");


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
