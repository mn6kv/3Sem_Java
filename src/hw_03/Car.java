package hw_03;

public class Car {
    private Long id;
    private String model;
    private String color;
    private Driver driver;

    public Car(long id, String model, String color) {
        this.id = id;
        this.model = model;
        this.color = color;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
