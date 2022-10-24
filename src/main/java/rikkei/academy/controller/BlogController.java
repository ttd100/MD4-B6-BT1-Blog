package rikkei.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import rikkei.academy.model.Blog;
import rikkei.academy.service.blog.IBlogService;

@Controller
public class BlogController {

    @Autowired
    private IBlogService blogService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("blogList", blogService.findAll());
        model.addAttribute("blogForm", new Blog());
        return "/index";
    }

    @PostMapping("/create")
    public String create(Blog blog) {
        blogService.save(blog);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("blogEdit", blogService.findById(id));
        return "/edit";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        model.addAttribute("blogDelete", blogService.findById(id));
        return "/delete";
    }

    @PostMapping("/update")
    public String update(Blog blog) {
        blogService.save(blog);
        return "redirect:/";
    }

    @PostMapping("/remove")
    public String remove(Blog blog) {
        blogService.remove(blog.getId());
        return "redirect:/";
    }
}