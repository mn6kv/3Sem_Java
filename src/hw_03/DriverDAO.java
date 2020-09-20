package hw_03;

import hw_03.Car;
import hw_03.Driver;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DriverDAO {

    private Connection connection;

    public DriverDAO(Connection connection) {
        this.connection = connection;
    }


    public Optional<Driver> findById(Long id) throws SQLException {
        Statement statement = connection.createStatement();
        String firstName = null;
        String lastName = null;
        Integer age = null;
        List<Car> carList = new ArrayList<>();
        ResultSet result = statement.executeQuery("select * from driver");
        boolean flag = true;

        while ((result.next()) && flag) {
            if (result.getInt("id") == id) {
                flag = false;
                firstName = result.getString("Firstname");
                lastName = result.getString("Lastname");
                age = result.getInt("Age");
                ResultSet carRes = statement.executeQuery("select * from car");

                while ((carRes.next())) {
                    if (carRes.getInt("driver_id") == id) {
                        int idC = carRes.getInt("id");
                        String modelC = carRes.getString("model");
                        String colorC = carRes.getString("color");
                        carList.add(new Car((long) idC, modelC, colorC));
                    }
                }

            }
        }
        Optional<Driver> optionalDriver = Optional.ofNullable(new Driver(id, firstName, lastName, age, carList));
        for (Car car: carList) {
            car.setDriver(optionalDriver.get());
        }
        return optionalDriver;
    }

}