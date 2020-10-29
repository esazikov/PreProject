package web.service;

import org.springframework.stereotype.Component;
import web.model.Car;

import java.util.ArrayList;
import java.util.List;

@Component
public class CarServiceImpl implements CarService{

    private List<Car> cars = new ArrayList<>();

    public CarServiceImpl() {
        cars.add(new Car("Skoda", "Kodiaq", 1_634_000));
        cars.add(new Car("Skoda", "Karoq", 1_359_000));
        cars.add(new Car("Skoda", "Octavia", 1_409_000));
        cars.add(new Car("Skoda", "Rapid", 819_000));
        cars.add(new Car("Skoda", "Superb", 1_993_000));
    }

    @Override
    public List<Car> getCars() {
        return cars;
    }
}
