package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.service.CarService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping(value = "/cars")
    public String printCars (@RequestParam(required = false) Integer count, ModelMap model) {
        if (count == null || count > 5) {
            count = 5;
        }
        List<String> messages = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            messages.add(carService.getCars().get(i).toString());
        }
        model.addAttribute("messages", messages);
        return "cars";
    }
}
