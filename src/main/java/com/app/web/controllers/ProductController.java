package com.app.web.controllers;


import com.app.web.entity.Product;
import com.app.web.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {

    private ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping({"/products","/"})
    public String listProducts(Model model){
        model.addAttribute("products",service.listAllProducts());
        return"products";
    }

    @GetMapping("/products/new")
    public  String showRegistrationForm (Model model){
        Product product=new Product();
        model.addAttribute("product",product);
        return "create_product";
    }
    @PostMapping("/products")
    public String saveProduct(@ModelAttribute("product") Product product){
        service.saveProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/products/edit/{id}")
    public String showRegistrationFormEdit(@PathVariable Long id ,Model model){
        model.addAttribute("product",service.findById(id));
        return "edit_product";
    }
    @PostMapping("/products/{id}")
    public String updateProduct(@PathVariable Long id,@ModelAttribute("product") Product product,Model model){
        Product existingProduct=service.findById(id);
        existingProduct.setId(id);
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setStock(product.getStock());
        service.updateProduct(existingProduct);
        return "redirect:/products";
    }
    @GetMapping("/products/{id}")
    public String deleteProduct(@PathVariable Long id){
        service.deleteProduct(id);
        return "redirect:/products";
    }
}
