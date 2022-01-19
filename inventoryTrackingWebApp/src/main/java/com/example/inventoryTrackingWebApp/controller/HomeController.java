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
    private boolean showSuccessMessage = false;
    private boolean showFailureMessage = false;
    private String successMessage = "This is a success message";
    private String failureMessage = "This is a failure message";


    public HomeController(ProductService productService, ReportService reportService) {
        this.productService = productService;
        this.reportService = reportService;
    }

    @GetMapping("/home")
    public String gotoHome(@ModelAttribute("product") Product product, Model model) {

        List<Product> productList = productService.getProductList();
        model.addAttribute("productList", productList);
        model.addAttribute("showSuccessMessage", showSuccessMessage);
        model.addAttribute("showFailureMessage", showFailureMessage);
        model.addAttribute("successMessage", successMessage);
        model.addAttribute("failureMessage", failureMessage);

        showSuccessMessage = false;
        showFailureMessage = false;
        successMessage = "";
        failureMessage = "";

        return "home";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute("product") Product product) {
        Product newProduct = new Product();
        newProduct.setProductName(product.getProductName());
        newProduct.setQuantity(product.getQuantity());
        int result = productService.insertProduct(newProduct);

        if (result == 1) {
            prepareResponse(true, "Success! " + product.getProductName() + " has been added to the system!");
        } else {
            prepareResponse(false, "Unable to add the product. Please try again later.");
        }

        return "redirect:/home";
    }


    @GetMapping("/delete")
    public String deleteProduct(Integer id){
        int result = productService.deleteProduct(id);
        return "redirect:/home";
    }

    @PostMapping("/edit")
    public String editProduct(Product product) {
        int result = productService.updateProduct(product);
        return "redirect:/home";
    }

    @GetMapping("/report")
    public String generateReport() {
        int result = reportService.generateReport();

        return "redirect:/home";
    }

    public void prepareResponse(boolean isSuccess, String message) {

        if (isSuccess) {
            showSuccessMessage = true;
            successMessage = message;
        } else {
            showFailureMessage = true;
            failureMessage = message;
        }

    }

}
