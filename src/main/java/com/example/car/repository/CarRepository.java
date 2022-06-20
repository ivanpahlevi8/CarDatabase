package com.example.car.repository;

import com.example.car.domain.Car;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class CarRepository implements CommonRepository<Car> {

    Map<String, Car> cars = new HashMap<>();

    @Override
    public Iterable<Car> save(Collection<Car> domain) {
        domain.forEach(this::save);
        return findAll();
    }

    @Override
    public Iterable<Car> findAll() {
        Iterable<Car> res = cars.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
        return res;
    }

    @Override
    public Car findById(String id) {
        Car carFind;
        carFind = cars.get(id);
        return carFind;
    }

    @Override
    public Car findByBrand(String brand) {
        Car carFind = null;
        List<Car> carList = new ArrayList<>(cars.values());
        int numCar = carList.size();
        for(int i = 0; i < numCar; i++){
            if(brand.equals(carList.get(i).getBrand())){
                carFind = carList.get(i);
                break;
            }
        }
        return carFind;
    }

    @Override
    public Car findByType(String type) {
        Car carFind = null;
        List<Car> carList = new ArrayList<>(cars.values());
        int numCar = carList.size();
        for(int i = 0; i < numCar; i++){
            if(type.equals(carList.get(i).getType())){
                carFind = carList.get(i);
                break;
            }
        }
        return carFind;
    }

    @Override
    public Car findByCode(String code) {
        Car carFind = null;
        List<Car> carList = new ArrayList<>(cars.values());
        int numCar = carList.size();
        for(int i = 0; i < numCar; i++){
            if(code.equals(carList.get(i).getCode())){
                carFind = carList.get(i);
                break;
            }
        }
        return carFind;
    }

    @Override
    public Car findByColor(String color) {
        Car carFind = null;
        List<Car> carList = new ArrayList<>(cars.values());
        int numCar = carList.size();
        for(int i = 0; i < numCar; i++){
            if(color.equals(carList.get(i).getColor())){
                carFind = carList.get(i);
                break;
            }
        }
        return carFind;
    }

    public Car findByPass(String pass){
        Car carFind = null;
        List<Car> carList = new ArrayList<>(cars.values());
        int numCar = carList.size();
        for(int i = 0; i < numCar; i++){
            if(pass.equals(carList.get(i).getPass())){
                carFind = carList.get(i);
                break;
            }
        }
        return carFind;
    }

    @Override
    public Car delete(Car domain) {
        String carId = domain.getId();;
        Car carDeleted = cars.get(carId);
        cars.remove(carId);
        return carDeleted;
    }

    @Override
    public Car save(Car domain) {
        String carId = domain.getId();
        Car getCar = cars.get(carId);
        if(getCar != null){
            getCar.setBrand(domain.getBrand());
            getCar.setCode(domain.getCode());
            getCar.setColor(domain.getColor());
            getCar.setId(domain.getId());
            getCar.setPass(domain.getPass());
            getCar.setYear(domain.getYear());
            getCar.setType(domain.getType());
            domain = getCar;
        }
        cars.put(domain.getId(), domain);
        return domain;
    }

}
