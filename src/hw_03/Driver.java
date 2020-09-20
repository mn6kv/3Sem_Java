package hw_03;

import java.util.List;

public class Driver {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private List<Car> cars;

    public Driver(Long id, String firstName, String lastName, Integer age, List<Car> cars) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.cars = cars;
    }
}
