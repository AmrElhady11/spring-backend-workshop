package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/product")
@Tag(name = "Product-Manager")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/display/{id}")
    @Operation(description = "getting a product")
    @ApiResponses(value = {@ApiResponse(responseCode = "201" ,description ="Accepeted with warning"),
                           @ApiResponse(responseCode = "403",description ="not allowed") })
    public Product display(@PathVariable int id) {


        Product product = productService.getProduct(id);


        return product;

    }
    @GetMapping("/displayAllProduct")
    @Operation(description = "getting all products")
    public List<Product> displayAllProducts() {

       List<Product> products =productService.getProducts();
        return products;

    }
    @PostMapping("/addProduct")
    @Operation(description = "adding a product")
    public Product addProduct(@RequestBody Product product) {

        return productService.addProduct(product);

    }
    @GetMapping("/test")
    @Hidden
    @Operation(description = "testing the app")
    public String test() {
        return "test";
    }


}
