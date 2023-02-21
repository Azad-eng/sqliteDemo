package com.azad.samples.mybatisDemo;

import java.sql.*;

/**
 * @author: EFL-ryl
 */
public class SQLiteDemo {
    private static String url = "jdbc:sqlite:src/main/resources/db/data_efl.db";
    private static Connection connection;
    private static Statement statement;
    private static String tableName = "intensity_record";

    public static void main(String[] args) {
        connection = createNewDatabase();

        statement = createNewTable();
        //System.out.println("Table intensity_record Created Successfully!!!");
        //System.out.println("table Opened...");

        appendData("4444", "3333", 222.2f, 11.1);
        //updateData(1, "4444", "3333", 222.2f, 11.1 );
        //selectAll();
        //selectIntensityGreaterThan(22);
    }

    public static void appendData(String opentime, String closetime, float time, double intensity) {
        String sql = "INSERT INTO " + tableName + "(opentime,closetime,time,intensity) VALUES(?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, opentime);
            preparedStatement.setString(2, closetime);
            preparedStatement.setFloat(3, time);
            preparedStatement.setDouble(4, intensity);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void updateData(int id,String opentime, String closetime, float time, double intensity) {
        String sql = "UPDATE "+ tableName + " SET opentime = ? , "
                + "closetime = ?, "
                + "time = ?, "
                + "intensity = ? "
                + "WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, opentime);
            preparedStatement.setString(2, closetime);
            preparedStatement.setFloat(3, time);
            preparedStatement.setDouble(4, intensity);
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void selectAll(){
        String sql = "SELECT id, opentime, closetime, time, intensity FROM "+ tableName;
        try (ResultSet resultSet = statement.executeQuery(sql)){
            // loop through the result set
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id") +  "\t" +
                        resultSet.getString("opentime") + "\t" +
                        resultSet.getString("closetime") + "\t" +
                        resultSet.getFloat("time") + "\t" +
                        resultSet.getDouble("intensity"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void selectIntensityGreaterThan(double intensity){
        String sql = "SELECT id, opentime, intensity "
                + "FROM "+ tableName + " WHERE intensity > ?";
        ResultSet resultSet;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setDouble(1, intensity);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                System.out.println(resultSet.getInt("id") + "\t" +
                        resultSet.getString("opentime") + "\t" +
                        resultSet.getDouble("intensity"));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    private static Connection createNewDatabase() {
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(url);
            if (connection != null) {
                DatabaseMetaData meta = connection.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    private static Statement createNewTable() {
        Statement statement = null;
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS "+ tableName + " " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "opentime TEXT NOT NULL," +
                "closetime TEXT NOT NULL," +
                "time REAL NOT NULL," +
                "intensity REAL NOT NULL)";
        try{
            statement = connection.createStatement();
            // create a new table
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return statement;
    }
}
