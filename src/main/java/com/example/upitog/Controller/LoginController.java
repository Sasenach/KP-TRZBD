package com.example.upitog.Controller;

import com.example.upitog.Model.Employee;
import com.example.upitog.Model.Role;
import com.example.upitog.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class LoginController {
    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/")
    public String main(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Employee user = employeeRepository.findByUsername(auth.getName());
        if (user.getRoles().contains(Role.USER))
            return "redirect:/ui/index";
        else return "/employee/index";
    }
}
