package com.example.inventoryTrackingWebApp.controller;

import com.example.inventoryTrackingWebApp.model.Product;
import com.example.inventoryTrackingWebApp.service.ProductService;
import com.example.inventoryTrackingWebApp.service.ReportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {

    private ProductService productService;
    private ReportService reportService;

    public HomeController(ProductService productService, ReportService reportService) {
        this.productService = productService;
        this.reportService = reportService;
    }

    @GetMapping("/home")
    public String gotoHome(@ModelAttribute("product") Product product, Model model) {

        List<Product> productList = productService.getProductList();
        model.addAttribute("productList", productList);

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

    @PostMapping("/edit")
    public String showEditFields(Product product) {
        productService.updateProduct(product);
        return "redirect:/home";
    }

    @GetMapping("/report")
    public String generateReport() {
        reportService.generateReport();
        return "redirect:/home";
    }

}
