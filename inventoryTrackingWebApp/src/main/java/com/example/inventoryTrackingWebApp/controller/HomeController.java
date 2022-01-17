package com.example.inventoryTrackingWebApp.controller;

import com.example.inventoryTrackingWebApp.model.Product;
import com.example.inventoryTrackingWebApp.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {

    private ProductService productService;
    private int editID = -1;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/home")
    public String gotoHome(@ModelAttribute("product") Product product, Model model) {

        List<Product> productList = productService.getProductList();
        model.addAttribute("productList", productList);
        model.addAttribute("editID", editID);

        return "home";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute("product") Product product) {
        Product newProduct = new Product();
        newProduct.setProductName(product.getProductName());
        newProduct.setQuantity(product.getQuantity());
        productService.insertProduct(newProduct);
        return "redirect:/home";
    }


    @GetMapping("/delete")
    public String deleteProduct(Integer id){
        productService.deleteProduct(id);
        return "redirect:/home";
    }

}
