package com.example.monapp.controller;

import com.example.monapp.model.Product;
import com.example.monapp.service.ArticleService;
import com.example.monapp.service.CommentService;
import com.example.monapp.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductViewController {

    private final ProductService productService;
    private final ArticleService articleService;
    private final CommentService commentService;

    public ProductViewController(ProductService productService,
                                 ArticleService articleService,
                                 CommentService commentService) {
        this.productService = productService;
        this.articleService = articleService;
        this.commentService = commentService;
    }
    @GetMapping("/products")
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }
    @PostMapping("/products/add")
    public String addProduct(@RequestParam String name,
                             @RequestParam Double price,
                             @RequestParam String description) {
        Product product = new Product(name, price, description);
        productService.createProduct(product);
        return "redirect:/products";
    }
    // Voir un produit
    @GetMapping("/products/view/{id}")
    public String viewProduct(@PathVariable Long id, Model model) {
        productService.getProductById(id).ifPresent(p -> model.addAttribute("product", p));
        return "product-view";
    }

    // Afficher le formulaire de modification
    @GetMapping("/products/edit/{id}")
    public String editProductForm(@PathVariable Long id, Model model) {
        productService.getProductById(id).ifPresent(p -> model.addAttribute("product", p));
        return "product-edit";
    }

    // Traiter la modification
    @PostMapping("/products/edit/{id}")
    public String editProduct(@PathVariable Long id,
                              @RequestParam String name,
                              @RequestParam Double price,
                              @RequestParam String description) {
        Product product = new Product(name, price, description);
        productService.updateProduct(id, product);
        return "redirect:/products";
    }

    // Supprimer un produit
    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("totalProducts", productService.getAllProducts().size());
        model.addAttribute("totalArticles", articleService.getAllArticles().size());
        model.addAttribute("totalComments", commentService.getAllComments().size());
        return "index";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}