package com.example.upitog.Controller;

import com.example.upitog.Model.Employee;
import com.example.upitog.Model.Post;
import com.example.upitog.Model.Role;
import com.example.upitog.Repository.EmployeeRepository;
import com.example.upitog.Repository.PostRepository;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/employee")

public class EmployeeController {
    @Autowired
    EmployeeRepository repository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

//    public BCryptPasswordEncoder getPasswordEncoder() {return new BCryptPasswordEncoder(8);}


    @GetMapping("")
    public String employeeMain(Model model){
        Iterable<Employee> employees = repository.findAll();
        model.addAttribute("employees", employees);
        return "employee/index";
    }

    @GetMapping("/add")
    public String employeeAddView(Employee employee, Model model){
        Iterable<Role> roles = List.of(Role.values());
        model.addAttribute("roleName", roles);
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "/employee/add";
    }

    @PostMapping("/add")
    public String employeeAdd(@Valid Employee employee,  BindingResult result, Model model,
    @RequestParam(name = "post_name") String postname,
                              @RequestParam(name = "roles_name") String rolesname){
        if(result.hasErrors()) {
            model.addAttribute("post_name", postRepository.findAll());
            model.addAttribute("roles_name", Set.of(Role.values()));
            if (!result.hasFieldErrors("surname") &&
                    !result.hasFieldErrors("name") &&
                    !result.hasFieldErrors("midlename") &&
                    !result.hasFieldErrors("pasportNumber") &&
                    !result.hasFieldErrors("username") &&
                    !result.hasFieldErrors("password")) {
                employee.setAddress("---");
                employee.setEmail("---");
                employee.setPassword(passwordEncoder.encode(employee.getPassword()));
                Post post =  postRepository.findById(Long.parseLong(postname)).orElseThrow();
                employee.setPost(post);
                employee.setRoles(Collections.singleton(Role.valueOf(rolesname)));
                repository.save(employee);
                return "redirect:/employee";
            }
            return "employee/add";
        }
        return "redirect:/employee";
    }

    @GetMapping("/filter")
    public String employeeFilter(@RequestParam String searchName,
                                 Model model){
        List<Employee> employee =repository.findBySurnameContaining(searchName);
        model.addAttribute("listPeople", employee);
        return "employee/index";
    }

    @GetMapping("/details/{id}")
    public String employeeDetails(Model model,
                                  @PathVariable long id) {
        Employee employee = repository.findById(id).orElseThrow();
        model.addAttribute("employee", employee);
        return ("/employee/details");
    }

    @GetMapping("/edit/{id}")
    public String employeeEdit(Model model,
                               @PathVariable long id) {

        Employee employee = repository.findById(id).orElseThrow();
        model.addAttribute("employee", employee);
        Iterable<Role> roles = List.of(Role.values());
        model.addAttribute("roleName", roles);
        return("/employee/edit");
    }

    @PostMapping("/edit/{id}")
    public String employeeEdit(@Valid Employee employee, BindingResult result) {
        if(result.hasErrors()) return "/employee/edit";

        repository.save(employee);

        return("redirect:/employee/details/" + employee.getId());
    }

    @GetMapping("/delete/{id}")
    public String employeeDelete(@PathVariable long id) {
        repository.deleteById(id);
        return("redirect:/employee");
    }
}
