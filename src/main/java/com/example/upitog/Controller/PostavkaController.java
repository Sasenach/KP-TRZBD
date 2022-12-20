package com.example.upitog.Controller;

import com.example.upitog.Model.*;
import com.example.upitog.Repository.PostavkaRepository;
import com.example.upitog.Repository.PostavshikRepository;
import com.example.upitog.Repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/postavka")
public class PostavkaController {
    @Autowired
    PostavkaRepository repository;
    @Autowired
    PostavshikRepository postavshikRepository;
    @Autowired
    StoreRepository storeRepository;

    @GetMapping("")
    public String postavkaMain(Model model) {
        Iterable<Postavka> postavkas = repository.findAll();
        model.addAttribute("postavkas", postavkas);
        return "postavka/index";
    }

    @GetMapping("/add")
    public String postavkaAddView(Postavka postavka, Model model){
        Iterable<Postavshik> postavshiks = postavshikRepository.findAll();
        model.addAttribute("postavshiks", postavshiks);
        Iterable<Store> stores = storeRepository.findAll();
        model.addAttribute("stores", stores);
        return "/postavka/add";
    }

    @PostMapping("/add")
    public String postavkaAdd(@Valid Postavka postavka, BindingResult result,
                              @RequestParam int store,
                              @RequestParam(name = "postavshik_name") String postavshik,
                              @RequestParam int numberPostavki,
                              @RequestParam String dataPostavki){
        if(result.hasErrors()) return "postavka/add";
        Postavshik postavshik1 = postavshikRepository.findPostavshikByOrgName(postavshik);
        Store store1 = storeRepository.findBySkladNumber(store);
        Postavka postavka1 = new Postavka(numberPostavki, dataPostavki, postavshik1, store1);

        repository.save(postavka1);
        return "redirect:/postavka";
    }

    @GetMapping("/details/{id}")
    public String postavkaDetails(Model model,
                                    @PathVariable long id) {
        Postavka postavka = repository.findById(id).orElseThrow();
        model.addAttribute("postavka", postavka);
        return ("/postavka/details");
    }

    @GetMapping("/edit/{id}")
    public String postavkaEdit(Model model,
                               @PathVariable long id) {

        Postavka postavka = repository.findById(id).orElseThrow();
        model.addAttribute("postavka", postavka);
        Iterable<Postavshik> postavshiks = postavshikRepository.findAll();
        model.addAttribute("postavshik", postavshiks);
        Iterable<Store> stores = storeRepository.findAll();
        model.addAttribute("store", stores);
        return("/postavka/edit");
    }

    @PostMapping("/edit/{id}")
    public String postavkaEdit(@Valid Postavka postavka, BindingResult result,
                               @RequestParam int store,
                               @RequestParam(name = "postavshik_name") String postavshik,
                               @RequestParam int numberPostavki,
                               @RequestParam String dataPostavki) {

        if(result.hasErrors()) return "/postavka/edit";

        Postavshik postavshik1 = postavshikRepository.findPostavshikByOrgName(postavshik);
        Store store1 = storeRepository.findBySkladNumber(store);
        Postavka postavka1 = new Postavka(numberPostavki, dataPostavki, postavshik1, store1);

        repository.save(postavka1);

        return("redirect:/postavka/details/" + postavka.getId());
    }

    @GetMapping("/delete/{id}")
    public String postavkaDelete(@PathVariable long id) {
        repository.deleteById(id);
        return("redirect:/postavka");
    }
}
