package ru.pirozhkov.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.pirozhkov.springcourse.DAO.ProductDAO;
import ru.pirozhkov.springcourse.models.Product;

@Controller
@RequestMapping("/product")
public class ProductController {
    private ProductDAO productDAO;

    @Autowired
    public ProductController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @GetMapping()
    public String index (Model model) {
        model.addAttribute("products", productDAO.getAllProducts());
        return "products";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("product", productDAO.getProductById(id));
        return "show";
    }

    @GetMapping("/add")
    public String newProduct(@ModelAttribute("product") Product product) {
        return "add";
    }

    @PostMapping()
    public String create(@ModelAttribute("product") Product product) {
        productDAO.addNewProduct(product);
        return "redirect:/product";
    }

    @GetMapping ("/{id}/edit")
    public String edit (@PathVariable("id") int id, Model model) {
        model.addAttribute("product", productDAO.getProductById(id));
        return "edit";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("product") Product product) {
        productDAO.edit(id, product);
        return "redirect:/product";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        productDAO.delete(id);
        return "redirect:/product";
    }



}
