package com.projet.springproject.controller;

import com.projet.springproject.entity.Category;
import com.projet.springproject.entity.Product;
import com.projet.springproject.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public String products(Model model){

        List<Category> categories =  categoryService.getAll();
        model.addAttribute("categories", categories);

        return "category/categories";
    }

    @GetMapping("categories/add")
    public String addProducts(Model model){
        return "category/categoriesAdd";
    }

    @GetMapping("categories/{id}")
    public String modifyCategory(@PathVariable("id") String id, Model model){
        Category category = null;
        if (categoryService.getById(Long.valueOf(id)).isPresent()) {
            category = categoryService.getById(Long.valueOf(id)).get();
        }
        model.addAttribute("id", id);
        model.addAttribute("name", category.getName());

        return "category/categoriesModify";
    }


    @PostMapping("/categories/add")
    public RedirectView add(
        @RequestParam("name") String name,
        RedirectAttributes redirectAttrs
    ){

        System.out.println("title = " + name);
        redirectAttrs.addFlashAttribute("name", name);

        if (name.isEmpty()) {
            redirectAttrs.addFlashAttribute("error", "*fields must be completed");
            return new RedirectView("add", false);
        }

        categoryService.create(new Category(name));
        return new RedirectView("/categories");
    }


    @PostMapping("/categories/modify")
    public RedirectView modify(
        @RequestParam("id") String id,
        @RequestParam("name") String name,
        RedirectAttributes redirectAttrs
    ){
        System.out.println("oui c'est bon !");
        System.out.println("title = " + name);
        redirectAttrs.addFlashAttribute("name", name);

        if (name.isEmpty()) {
            redirectAttrs.addFlashAttribute("error", "*fields must be completed");
            return new RedirectView(id, false);
        }

        categoryService.modify(Long.valueOf(id), new Category(name));
        return new RedirectView("/categories");
    }



    @PostMapping("/categories/delete")
    public RedirectView delete(
        @RequestParam("id") String id
    ){
        categoryService.delete(Long.valueOf(id));
        return new RedirectView("/categories", false);
    }
}
