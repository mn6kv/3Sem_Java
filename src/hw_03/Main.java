package hw_03;

import java.sql.*;
import java.util.Optional;

public class Main {

    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "1815144981Misha!";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/db-infa-2k";

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from driver");

//        while (resultSet.next())
//            System.out.println(resultSet.getInt("Id") + " " + resultSet.getString("Firstname"));

        DriverDAO driversDao = new DriverDAO(connection);
        Optional<Driver> driver = driversDao.findById(1L);

        if (driver.isPresent())
            System.out.println(driver.get().toString());
        else
            System.out.println("not find");
    }
}
