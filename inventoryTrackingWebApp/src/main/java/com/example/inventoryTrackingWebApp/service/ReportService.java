package com.example.inventoryTrackingWebApp.service;

import com.example.inventoryTrackingWebApp.model.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ReportService {

    private ProductService productService;

    @Value("${report_path}")
    private String reportPath;

    public static final String DELIMITER = ",";

    public ReportService(ProductService productService) {
        this.productService = productService;
    }

    private String marshallProduct(Product product) {

        String productAsText = product.getProductId() + DELIMITER;
        productAsText += product.getProductName() + DELIMITER;
        productAsText += product.getQuantity();

        return productAsText;
    }


    public int generateReport() {

        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String formatDateTime = time.format(formatter);
        String filePath = reportPath + "Inventory_Report" + "_" + formatDateTime + ".csv";

        PrintWriter out = null;

        try {
            out = new PrintWriter(new FileWriter(filePath));
        } catch (Exception e) {
            System.out.println(e);
            return -1;
        }

        String header = "product_id,product_name,quantity";
        out.println(header);
        out.flush();

        String productAsText;
        List<Product> productList = productService.getProductList();
        for(Product product : productList) {
            productAsText = marshallProduct(product);
            out.println(productAsText);
            out.flush();
        }
        out.close();

        return 1;
    }

}
