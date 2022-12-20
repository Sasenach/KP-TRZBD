package com.example.upitog.Controller;

import com.example.upitog.Model.Postavshik;
import com.example.upitog.Repository.PostavshikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/postavshik")
public class PostavshikController {
    @Autowired
    PostavshikRepository repository;

    @GetMapping("")
    public String postavshikMain(Model model) {
        Iterable<Postavshik> postavshiks = repository.findAll();
        model.addAttribute("postavshiks", postavshiks);
        return "postavshik/index";
    }

    @GetMapping("/add")
    public String postavshikAddView(Postavshik postavshik){
        return "postavshik/add";
    }

    @PostMapping("/add")
    public String postavshikAdd(@Valid Postavshik postavshik, BindingResult result){
        if(result.hasErrors())
            return "postavshik/add";

        repository.save(postavshik);

        return "redirect:/postavshik";
    }

    @GetMapping("/filter")
    public String postavshikFilter(@RequestParam String searchName,
                            Model model){
        List<Postavshik> postavshiks = repository.findPostavshikByOrgNameContaining(searchName);
        model.addAttribute("postavshiks", postavshiks);
        return "postavshik/index";
    }

    @GetMapping("/details/{id}")
    public String postavshikDetails(Model model,
                             @PathVariable long id) {
        Postavshik postavshiks = repository.findById(id).orElseThrow();
        model.addAttribute("postavshiks", postavshiks);
        return ("/postavshik/details");
    }

    @GetMapping("/edit/{id}")
    public String postavshikEdit(Model model,
                          @PathVariable long id) {

        Postavshik postavshik = repository.findById(id).orElseThrow();
        model.addAttribute("postavshik", postavshik);
        return("/postavshik/edit");
    }

    @PostMapping("/edit/{id}")
    public String postavshikEdit(@Valid Postavshik postavshik, BindingResult result) {
        if(result.hasErrors()) return "/postavshik/edit";

        repository.save(postavshik);

        return("redirect:/postavshik/details/" + postavshik.getId());
    }

    @GetMapping("/delete/{id}")
    public String postavshikDelete(@PathVariable long id) {
        repository.deleteById(id);
        return("redirect:/postavshik");
    }

}
