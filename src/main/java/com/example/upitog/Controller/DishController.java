package com.example.upitog.Controller;

import com.example.upitog.Model.*;
import com.example.upitog.Repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/dish")
@PreAuthorize("hasAuthority('ADMIN')")
public class DishController {
    @Autowired
    DishRepository repository;

    @GetMapping("")
    public String dishMain(Model model){
        Iterable<Dish> dishes = repository.findAll();
        model.addAttribute("dishes", dishes);
        return "dish/index";
    }

    @GetMapping("/add")
    public String dishAddView(Model model, Dish dish){
        model.addAttribute("tipes", DishType.values());
        return "dish/add";
    }

    @PostMapping("/add")
    public String dishAdd(@Valid Dish dish, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("tipes", DishType.values());
            return "dish/add";
        }

        repository.save(dish);

        return "redirect:/dish";
    }

    @GetMapping("/filter")
    public String dishFilter(@RequestParam String searchName,
                                 Model model){
        List<Dish> dishes = repository.findByNameContaining(searchName);
        model.addAttribute("dishes", dishes);
        return "dish/index";
    }


    @GetMapping("/delete/{id}")
    public String dishDelete(@PathVariable long id) {
        repository.deleteById(id);
        return("redirect:/dish");
    }
}
