package com.ExcelToMysqlTransfer.ExcelMysql;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class justForTesting {

    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/spring_p";
        String username = "root";
        String password = "Password@1234";

        String csvFilePath = "/home/deeksha/Downloads/Excel-Mysql/src/main/java/com/ExcelToMysqlTransfer/ExcelMysql/testFor.csv";

        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password)) {
            String sql = "SELECT * FROM employee";

            Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery(sql);

            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(csvFilePath));

            // write header line containing column names
            fileWriter.write("id,email,first_name,last_name,address");

            while (result.next()) {
                int Id = result.getInt("id");
                String Email = result.getString("email");
                String First_name = result.getString("first_name");
                String Last_name = result.getString("last_name");
                String Address = result.getString("address");

                String line = String.format("%d\"%s\",%s,%s,%s",
                       Id,Email, First_name, Last_name , Address);

                fileWriter.newLine();
                fileWriter.write(line);
            }

            statement.close();
            fileWriter.close();

        } catch (SQLException e) {
            System.out.println("Datababse error:");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("File IO error:");
            e.printStackTrace();
        }

    }

}