package com.example.upitog.Controller;

import com.example.upitog.Model.Post;
import com.example.upitog.Model.Product;
import com.example.upitog.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/post")
public class PostController {
    @Autowired
    PostRepository repository;

    @GetMapping("")
    public String postMain(Model model) {
        Iterable<Post> posts = repository.findAll();
        model.addAttribute("posts", posts);
        return "post/index";
    }

    @GetMapping("/add")
    public String postAddView(Post post){
        return "post/add";
    }

    @PostMapping("/add")
    public String postAdd(@Valid Post post, BindingResult result){
        if(result.hasErrors())
            return "post/add";

        repository.save(post);

        return "redirect:/post";
    }

    @GetMapping("/filter")
    public String postFilter(@RequestParam String searchName,
                                Model model){
        List<Post> posts = repository.findByTitleContaining(searchName);
        model.addAttribute("posts", posts);
        return "post/index";
    }

    @GetMapping("/details/{id}")
    public String postDetails(Model model,
                                 @PathVariable long id) {
        Post post = repository.findById(id).orElseThrow();
        model.addAttribute("post", post);
        return ("/post/details");
    }

    @GetMapping("/edit/{id}")
    public String postEdit(Model model,
                              @PathVariable long id) {

        Post post = repository.findById(id).orElseThrow();
        model.addAttribute("post", post);
        return("/post/edit");
    }

    @PostMapping("/edit/{id}")
    public String postEdit(@Valid Post post, BindingResult result) {
        if(result.hasErrors()) return "/post/edit";

        repository.save(post);

        return("redirect:/post/details/" + post.getId());
    }

    @GetMapping("/delete/{id}")
    public String postDelete(@PathVariable long id) {
        repository.deleteById(id);
        return("redirect:/post");
    }
}
