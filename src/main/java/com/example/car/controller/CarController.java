package com.example.car.controller;

import com.example.car.domain.Car;
import com.example.car.repository.CommonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class CarController {

    CommonRepository<Car> repository;

    @Autowired
    public CarController(CommonRepository<Car> repo){
        this.repository = repo;
    }

    // for all user and admin
    @GetMapping("/index")
    public String getHome(Model model){
        return "index";
    }

    @GetMapping("/showcar")
    public String showCar(Model model){
        List<Car> allCar = (List<Car>) repository.findAll();
        model.addAttribute("cars", allCar);
        return "showcar";
    }

    // for user controller
    @GetMapping("/user/home")
    public String getHomeUser(Model model){
        return "homeuser";
    }

    @GetMapping("/user/form-add")
    public String addCarUserForm(Model model){
        Car car = new Car();
        // object car yang belum diisi nilai akan dipass ke thymeleaf pada html form
        // yang kemudian akan diberikan nilai pada html form
        model.addAttribute("car", car);
        // diatas merupakana baris untuk mengirim object kosong agar dapat diisi oleh data pada html form
        return "formuser"; // baris ini untuk mereturn form yang akan dilewatkan oleh objet car dan diisi data
    }

    @RequestMapping(value = "/user/add-car", method = {RequestMethod.POST, RequestMethod.GET})
    public String addCarUser(@ModelAttribute Car car, Model model){
        Car carAdded = repository.save(car);
        model.addAttribute("car", carAdded);
        return "uploadformuser";
    }

    // use to get user car by id
    @GetMapping(value = "/user/form-get-car-id")
    public String getCarIdForm(Model model){
        return "usergetcarid";
    }

    @RequestMapping(value = "/user/get-car-id", method = {RequestMethod.POST, RequestMethod.GET})
    public String getCarId(@RequestParam(value = "id", required = false) String id, Model model){
        Car carGet = repository.findById(id);
        model.addAttribute("car", carGet);
        return "getcaruser";
    }

    // use to get user car by brand
    @GetMapping("/user/form-get-car-brand")
    public String getCarBrandForm(Model model){
        return "usergetcarbrand";
    }

    @RequestMapping(value = "/user/get-car-brand", method = {RequestMethod.POST, RequestMethod.GET})
    public String getCarBrand(@RequestParam(value = "brand", required = false) String brand, Model model){
        Car carGet = repository.findByBrand(brand);
        model.addAttribute("car", carGet);
        return "getcaruser";
    }

    // use to get user car by type
    @GetMapping("/user/form-get-car-type")
    public String getCarTypeForm(Model model){
        return "usergetcartype";
    }

    @RequestMapping(value = "/user/get-car-type", method = {RequestMethod.POST, RequestMethod.GET})
    public String getCarType(@RequestParam(value = "type", required = false) String type, Model model){
        Car carGet = repository.findByType(type);
        model.addAttribute("car", carGet);
        return "getcaruser";
    }

    // use to get user car by color
    @GetMapping("/user/form-get-car-color")
    public String getCarColorForm(Model model){
        return "usergetcarcolor";
    }

    @RequestMapping(value = "/user/get-car-color", method = {RequestMethod.POST, RequestMethod.GET})
    public String getCarColor(@RequestParam(value = "color", required = false) String color, Model model){
        Car carGet = repository.findByColor(color);
        model.addAttribute("car", carGet);
        return "getcaruser";
    }

    // use to get user car by code
    @GetMapping("/user/form-get-car-code")
    public String getCarCodeForm(Model model){
        return "usergetcarcode";
    }

    @RequestMapping(value = "/user/get-car-code", method = {RequestMethod.POST, RequestMethod.GET})
    public String getCarCode(@RequestParam(value = "code", required = false) String code, Model model){
        Car carGet = repository.findByCode(code);
        model.addAttribute("car", carGet);
        return "getcaruser";
    }

    // forAdmin controller
    @GetMapping("/admin/home")
    public String getHomeAdmin(Model model) {
        return "homeadmin";
    }

    @GetMapping("/admin/form-add-car")
    public String formAddCar(Model model){
        Car carAdded = new Car();
        model.addAttribute("car", carAdded);
        return "formadmin";
    }

    @RequestMapping(value = "/admin/addcar", method = {RequestMethod.POST, RequestMethod.GET})
    public String addCarAdmin(@ModelAttribute Car car, Model model){
        Car carAdd = repository.save(car);
        model.addAttribute("car", carAdd);
        return "getcaruser";
    }

    @GetMapping("/admin/see-all-car")
    public String getCarAdmin(Model model){
        List<Car> carList = (List<Car>) repository.findAll();
        model.addAttribute("cars", carList);
        return "showcar";
    }

    // use to delete car by id
    @GetMapping("/admin/form-delete-car-id")
    public String deleteCarId(Model model){
        return "admindeletecarid";
    }

    @RequestMapping(value = "/admin/delete-car-id", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String adminDeleteCarId(@RequestParam(value = "id", required = true) String id, Model model){
        Car carDelete = repository.findById(id);
        Car carSave = new Car();
        carSave.setType(carDelete.getType());
        carSave.setYear(carDelete.getYear());
        carSave.setPass(carDelete.getPass());
        carSave.setCode(carDelete.getCode());
        carSave.setColor(carDelete.getColor());
        carSave.setId(carDelete.getId());
        carSave.setBrand(carDelete.getBrand());
        repository.delete(carDelete);
        model.addAttribute("car", carSave);
        return "getcaradmin";
    }

    // use to delete car by brand
    @GetMapping("/admin/form-delete-car-brand")
    public String deleteCarBrand(Model model){
        return "admindeletecarbrand";
    }

    @RequestMapping(value = "/admin/delete-car-brand", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String adminDeleteCarBrand(@RequestParam(value = "brand", required = false) String brand, Model model){
        Car carDelete = repository.findByBrand(brand);
        Car carSave = new Car();
        carSave.setType(carDelete.getType());
        carSave.setYear(carDelete.getYear());
        carSave.setPass(carDelete.getPass());
        carSave.setCode(carDelete.getCode());
        carSave.setColor(carDelete.getColor());
        carSave.setId(carDelete.getId());
        carSave.setBrand(carDelete.getBrand());
        repository.delete(carDelete);
        model.addAttribute("car", carSave);
        return "getcaradmin";
    }

    // use to delete car by type
    @GetMapping("/admin/form-delete-car-type")
    public String deleteCarType(Model model){
        return "admindeletecartype";
    }

    @RequestMapping(value = "/admin/delete-car-type", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String adminDeleteCarType(@RequestParam(value = "brand", required = false) String type, Model model){
        Car carDelete = repository.findByType(type);
        Car carSave = new Car();
        carSave.setType(carDelete.getType());
        carSave.setYear(carDelete.getYear());
        carSave.setPass(carDelete.getPass());
        carSave.setCode(carDelete.getCode());
        carSave.setColor(carDelete.getColor());
        carSave.setId(carDelete.getId());
        carSave.setBrand(carDelete.getBrand());
        repository.delete(carDelete);
        model.addAttribute("car", carSave);
        return "getcaradmin";
    }

    // use to delete car color
    @GetMapping("/admin/form-delete-car-color")
    public String deleteCarColor(Model model){
        return "admindeletecarcolor";
    }

    @RequestMapping(value = "/admin/delete-car-color", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String adminDeleteCarColor(@RequestParam(value = "color", required = false) String color, Model model){
        Car carDelete = repository.findByColor(color);
        Car carSave = new Car();
        carSave.setType(carDelete.getType());
        carSave.setYear(carDelete.getYear());
        carSave.setPass(carDelete.getPass());
        carSave.setCode(carDelete.getCode());
        carSave.setColor(carDelete.getColor());
        carSave.setId(carDelete.getId());
        carSave.setBrand(carDelete.getBrand());
        repository.delete(carDelete);
        model.addAttribute("car", carSave);
        return "getcaradmin";
    }

    // use to delete car by code
    @GetMapping("/admin/form-delete-car-code")
    public String deleteCarCode(Model model){
        return "admindeletecarcode";
    }

    @RequestMapping(value = "/admin/delete-car-code", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String adminDeleteCarCode(@RequestParam(value = "code", required = false) String code, Model model){
        Car carDelete = repository.findByCode(code);
        Car carSave = new Car();
        carSave.setType(carDelete.getType());
        carSave.setYear(carDelete.getYear());
        carSave.setPass(carDelete.getPass());
        carSave.setCode(carDelete.getCode());
        carSave.setColor(carDelete.getColor());
        carSave.setId(carDelete.getId());
        carSave.setBrand(carDelete.getBrand());
        repository.delete(carDelete);
        model.addAttribute("car", carSave);
        return "getcaradmin";
    }

}
