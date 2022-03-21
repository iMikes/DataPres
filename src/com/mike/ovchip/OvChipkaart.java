package com.mike.ovchip;

import java.sql.*;

public class OvChipkaart {

    public static void main(String[] args) {
        String jdbcURL = "jdbc:postgresql://localhost:5432/ovchip";
        String username = "postgres";
        String password = "root";

        try
        {
            Connection connection = DriverManager.getConnection(jdbcURL, username, password);
            System.out.println("Database is connected successfully.");

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM reiziger");

            System.out.println("Alle reizigers:");
            while (resultSet.next())
            {
                String tussenvoegsel = (resultSet.getString("tussenvoegsel") != null ? resultSet.getString("tussenvoegsel") + " " : "" );
                System.out.println(
                        "#"+resultSet.getString("reiziger_id")+ " " +
                                resultSet.getString("voorletters") + ". " + tussenvoegsel +
                                resultSet.getString("achternaam") + " ("+ resultSet.getString("geboortedatum") +")"
                );
            }
            resultSet.close();
            statement.close();
        }
        catch (SQLException e)
        {
            System.out.println("ERROR: Failed to connect to the Database");
            e.printStackTrace();
        }
    }
}
