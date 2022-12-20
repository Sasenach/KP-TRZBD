package com.example.upitog.Controller;

import com.example.upitog.Model.*;
import com.example.upitog.Repository.EmployeeRepository;
import com.example.upitog.Repository.PostavkaRepository;
import com.example.upitog.Repository.ProductRepository;
import com.example.upitog.Repository.VedomostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/vedomost")
public class VedomostController {
    @Autowired
    VedomostRepository repository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    PostavkaRepository postavkaRepository;
    @Autowired
    ProductRepository productRepository;

    @GetMapping("")
    public String vedomostMain(Model model) {
        Iterable<Vedomost> vedomosts = repository.findAll();
        model.addAttribute("vedomosts", vedomosts);
        return "vedomost/index";
    }

    @GetMapping("/add")
    public String vedomostAddView(Vedomost vedomost, Model model){
        Iterable<Employee> employees = employeeRepository.findAll();
        model.addAttribute("employees", employees);
        Iterable<Postavka> postavkas = postavkaRepository.findAll();
        model.addAttribute("postavkas", postavkas);
        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "vedomost/add";
    }

    @PostMapping("/add")
    public String vedomostAdd(@Valid Vedomost vedomost, BindingResult result){
        if(result.hasErrors())
            return "vedomost/add";

        repository.save(vedomost);

        return "redirect:/vedomost";
    }

    @GetMapping("/details/{id}")
    public String vedomostDetails(Model model,
                               @PathVariable long id) {
        Vedomost vedomost = repository.findById(id).orElseThrow();
        model.addAttribute("vedomost", vedomost);
        return ("/vedomost/details");
    }


    @GetMapping("/delete/{id}")
    public String vedomostDelete(@PathVariable long id) {
        repository.deleteById(id);
        return("redirect:/vedomost");
    }
}
