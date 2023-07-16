package com.peaksoft;

import java.sql.*;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws SQLException {
//        DB.connection().close();
    createTable();
//        System.out.println(getCount());

        addStudents("Uson", 23);
        addStudents("Asan", 24);
        addStudents("Aibek", 20);
        addStudents("Nurisa", 21);
    }

    public static void createTable() {
        String SQL = "CREATE TABLE students(" +
                "id SERIAL PRIMARY KEY," +
                "name VARCHAR(50) NOT NULL," +
                "age INTEGER);";
        try (Connection connection = DB.connection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getCount() {
        String SQL = "Select count(*) FROM students";
        int count = 0;
        try (Connection con = DB.connection();
             Statement statement = con.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL);
            resultSet.next();
            count = resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public static void addStudents(String name, int age) {
        String SQL = "INSERT INTO students (name,age)" +
                "VALUES(?,?)";
        try (Connection con = DB.connection();
             PreparedStatement statement = con.prepareStatement(SQL)) {
            statement.setString(1, name);
            statement.setInt(2, age);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}
