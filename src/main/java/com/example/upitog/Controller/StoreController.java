package com.example.upitog.Controller;

import com.example.upitog.Model.Postavshik;
import com.example.upitog.Model.Store;
import com.example.upitog.Repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/store")
@PreAuthorize("hasAuthority('ADMIN')")
public class StoreController {
    @Autowired
    StoreRepository repository;

    @GetMapping("")
    public String storeMain(Model model) {
        Iterable<Store> stores = repository.findAll();
        model.addAttribute("stores", stores);
        return "store/index";
    }

    @GetMapping("/add")
    public String storeAddView(Store store){
        return "store/add";
    }

    @PostMapping("/add")
    public String storeAdd(@Valid Store store, BindingResult result){
        if(result.hasErrors())
            return "store/add";

        repository.save(store);

        return "redirect:/store";
    }

    @GetMapping("/details/{id}")
    public String storeDetails(Model model,
                                    @PathVariable long id) {
        Store store = repository.findById(id).orElseThrow();
        model.addAttribute("store", store);
        return ("/store/details");
    }

    @GetMapping("/edit/{id}")
    public String storeEdit(Model model,
                                 @PathVariable long id) {

        Store store = repository.findById(id).orElseThrow();
        model.addAttribute("store", store);
        return("/store/edit");
    }

    @PostMapping("/edit/{id}")
    public String storeEdit(@Valid Store store, BindingResult result) {
        if(result.hasErrors()) return "/store/edit";

        repository.save(store);

        return("redirect:/store/details/" + store.getId());
    }

    @GetMapping("/delete/{id}")
    public String storeDelete(@PathVariable long id) {
        repository.deleteById(id);
        return("redirect:/store");
    }

}
