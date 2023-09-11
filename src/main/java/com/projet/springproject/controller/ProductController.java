package com.projet.springproject.controller;

import com.projet.springproject.entity.Category;
import com.projet.springproject.entity.Product;
import com.projet.springproject.service.CategoryService;
import com.projet.springproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
//@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/products")
    public String products(Model model){

        List<Product> products =  productService.getAll();
        model.addAttribute("products", products);
        model.addAttribute("categoryService", categoryService);

        return "product/products";
    }

    @GetMapping("products/add")
    public String addProducts(Model model){

        List<Category> categories =  categoryService.getAll();
        model.addAttribute("categories", categories);

        return "product/productsAdd";
    }

    @GetMapping("products/{id}")
    public String modifyProducts(@PathVariable("id") String id, Model model){
        Product product = null;
        if (productService.getById(Long.valueOf(id)).isPresent()) {
            product = productService.getById(Long.valueOf(id)).get();
        }
        model.addAttribute("id", id);
        model.addAttribute("name", product.getName());
        model.addAttribute("price", product.getPrice());
        List<Category> categories =  categoryService.getAll();
        model.addAttribute("categories", categories);
        model.addAttribute("categoryValue", product.getCategory_id().getId());

        return "product/productsModify";
    }

    @PostMapping("/products/add")
    public RedirectView add(
        @RequestParam("name") String name,
        @RequestParam("price") String price,
        @RequestParam("category") String category,
        RedirectAttributes redirectAttrs
    ){
        System.out.println("oui c'est bon !");
        System.out.println("title = " + name);
        System.out.println("price = " + price);
        System.out.println("category = " + category);
        redirectAttrs.addFlashAttribute("name", name);
        redirectAttrs.addFlashAttribute("price", price);
        List<Category> categories =  categoryService.getAll();
        redirectAttrs.addFlashAttribute("categories", categories);
        redirectAttrs.addFlashAttribute("categoryValue", category);

        if (name.isEmpty()) {
            redirectAttrs.addFlashAttribute("error", "*fields must be completed");
            return new RedirectView("add", false);
        }

        if (price.isEmpty()) {
            redirectAttrs.addFlashAttribute("error", "*fields must be completed");
            return new RedirectView("add", false);
        }

        if (category.isEmpty()) {
            redirectAttrs.addFlashAttribute("error", "*fields must be completed");
            return new RedirectView("add", false);
        }

        if (categoryService.getById(Long.valueOf(category)).isPresent()) {
            productService.create(new Product(name, Double.parseDouble(price), categoryService.getById(Long.valueOf(category)).get()));
            return new RedirectView("/products");
        }
        return new RedirectView("add", false);
    }


    @PostMapping("/products/modify")
    public RedirectView modify(
        @RequestParam("id") String id,
        @RequestParam("name") String name,
        @RequestParam("price") String price,
        @RequestParam("category") String category,
        RedirectAttributes redirectAttrs
    ){
        System.out.println("oui c'est bon !");
        System.out.println("title = " + name);
        System.out.println("price = " + price);
        System.out.println("category = " + category);
        redirectAttrs.addFlashAttribute("name", name);
        redirectAttrs.addFlashAttribute("price", price);
        List<Category> categories =  categoryService.getAll();
        redirectAttrs.addFlashAttribute("categories", categories);
        redirectAttrs.addFlashAttribute("categoryValue", category);

        if (name.isEmpty()) {
            redirectAttrs.addFlashAttribute("error", "*fields must be completed");
            return new RedirectView(id, false);
        }

        if (price.isEmpty()) {
            redirectAttrs.addFlashAttribute("error", "*fields must be completed");
            return new RedirectView(id, false);
        }

        if (category.isEmpty()) {
            redirectAttrs.addFlashAttribute("error", "*fields must be completed");
            return new RedirectView(id, false);
        }

        if (categoryService.getById(Long.valueOf(category)).isPresent()) {
            productService.modify(Long.valueOf(id), new Product(name, Double.parseDouble(price), categoryService.getById(Long.valueOf(category)).get()));
            return new RedirectView("/products");
        }
        return new RedirectView(id, false);
    }



    @PostMapping("/products/delete")
    public RedirectView delete(
        @RequestParam("id") String id
    ){
        productService.delete(Long.valueOf(id));
        return new RedirectView("/products", false);
    }
}