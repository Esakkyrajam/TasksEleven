package com.pcs.productcatalogsystem.controller;



import com.pcs.productcatalogsystem.entity.Product;
import com.pcs.productcatalogsystem.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Home page with two options
    @GetMapping("/")
    public String homePage() {
        return "index";
    }

    // Show Add Product form
    @GetMapping("/add")
    public String addProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "addProduct";
    }

    // Save product to DB
    @PostMapping("/add")
    public String saveProduct(@ModelAttribute Product product) {
        productService.saveProduct(product);
        return "redirect:/";
    }

    // Display products
    @GetMapping("/display")
    public String displayProducts(Model model, @RequestParam(required = false) String category) {
        if (category != null && !category.isEmpty()) {
            model.addAttribute("products", productService.getProductsByCategory(category));
        } else {
            model.addAttribute("products", productService.getAllProducts());
        }
        return "displayProducts";
    }
}
