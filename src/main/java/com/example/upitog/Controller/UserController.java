package com.example.upitog.Controller;

import com.example.upitog.Model.*;
import com.example.upitog.Repository.BagRepository;
import com.example.upitog.Repository.DishInCheckRepository;
import com.example.upitog.Repository.DishRepository;
import com.example.upitog.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/ui")
public class UserController {
    @Autowired
    DishRepository dishRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    BagRepository bagRepository;
    @Autowired
    DishInCheckRepository dishInCheckRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/index")
    public String mainLoad(Model model) {
        Iterable<Dish> dishes = dishRepository.findAll();
        model.addAttribute("dishes", dishes);
        return ("/ui/index");
    }

    @GetMapping("/about-us")
    public String aboutUsLoad() {

        return "/ui/about-us";
    }

    @GetMapping("/sign-up")
    public String signUpLoad(Model model) {
        Employee user = new Employee();
        model.addAttribute("user", user);
        return "/ui/sign-up";
    }

    @PostMapping("/sign-up")
    public String signUp(@Valid Employee user, BindingResult result, Model model) {

        model.addAttribute("user", user);
        user.setRoles(Set.of(Role.USER));
        if (result.hasErrors()) {
            if (!result.hasFieldErrors("username") &&
                    !result.hasFieldErrors("name") &&
                    !result.hasFieldErrors("password") &&
                    !result.hasFieldErrors("address")) {
                user.setMidlename("---");
                user.setSurname("---");
                user.setPasportNumber("1234 123456");
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                employeeRepository.save(user);
                return "redirect:/login";
            }
            return "/login";
        }
        return "";
    }

    @PostMapping("add-to-bag")
    public String addToBag(Model model, @RequestParam long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.isAuthenticated()) {
            Dish dish =  dishRepository.findById(id).orElseThrow();
            Employee user = employeeRepository.findByUsername(auth.getName());
            Bag bag = new Bag();
            bag.setBagUser(user);
            bag.setBagDish(dish);
            bagRepository.save(bag);
        }
        model.addAttribute("dishes", dishRepository.findAll());
        return "/ui/index";
    }

    @GetMapping("/bag")
    public String order (Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Employee user = employeeRepository.findByUsername(auth.getName());
        List<Dish> dishes = new ArrayList<>();
        List<Bag> bagList = bagRepository.findByBagEmployee(user);
//        if (bagList.isEmpty()) {
//            model.addAttribute("Кажется ваша корзина пуста", "oops");
//            return "/ui/bag";
//        }
        for (Bag bag : bagList) {
            dishes.add(bag.getBagDish());
        }
        model.addAttribute("dishes", dishes);
        return "/ui/bag";
    }

    @PostMapping("/order")
    public String orderConfirm() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Employee user = employeeRepository.findByUsername(auth.getName());
        Iterable<Bag> bagList = bagRepository.findByBagEmployee(user);
        for (Bag bag : bagList) {
            DishInCheck dishInCheck = new DishInCheck();
            dishInCheck.setDishInCheckEmployee(bag.getBagUser());
            dishInCheck.setDish(bag.getBagDish());
            dishInCheckRepository.save(dishInCheck);
            bagRepository.deleteById(bag.getId());
        }
        return "redirect:/ui/index";
    }
}
