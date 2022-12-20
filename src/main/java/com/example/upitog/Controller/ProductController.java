package com.example.upitog.Controller;

import com.example.upitog.Model.Postavshik;
import com.example.upitog.Model.Product;
import com.example.upitog.Model.Store;
import com.example.upitog.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductRepository repository;

    @GetMapping("")
    public String productMain(Model model) {
        Iterable<Product> products = repository.findAll();
        model.addAttribute("products", products);
        return "product/index";
    }

    @GetMapping("/add")
    public String productAddView(Product product){
        return "product/add";
    }

    @PostMapping("/add")
    public String productAdd(@Valid Product product, BindingResult result){
        if(result.hasErrors())
            return "product/add";

        repository.save(product);

        return "redirect:/product";
    }

    @GetMapping("/filter")
    public String productFilter(@RequestParam String searchName,
                                   Model model){
        List<Product> products = repository.findByNameContaining(searchName);
        model.addAttribute("products", products);
        return "product/index";
    }

    @GetMapping("/details/{id}")
    public String productDetails(Model model,
                               @PathVariable long id) {
        Product product = repository.findById(id).orElseThrow();
        model.addAttribute("product", product);
        return ("/product/details");
    }

    @GetMapping("/edit/{id}")
    public String productEdit(Model model,
                            @PathVariable long id) {

        Product product = repository.findById(id).orElseThrow();
        model.addAttribute("product", product);
        return("/product/edit");
    }

    @PostMapping("/edit/{id}")
    public String productEdit(@Valid Product product, BindingResult result) {
        if(result.hasErrors()) return "/product/edit";

        repository.save(product);

        return("redirect:/product/details/" + product.getId());
    }

    @GetMapping("/delete/{id}")
    public String productDelete(@PathVariable long id) {
        repository.deleteById(id);
        return("redirect:/product");
    }

}
