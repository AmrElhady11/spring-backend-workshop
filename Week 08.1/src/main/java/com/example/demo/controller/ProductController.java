package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Api(tags = "Endpoints for ProductApp")
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/display/{id}")
    @ApiOperation("getting a product")
    @ApiResponses(value = {@ApiResponse(code = 201,message ="Accepeted with warning"),
                           @ApiResponse(code = 403,message ="not allowed") })
    public Product display(@PathVariable int id) {


        Product product = productService.getProduct(id);


        return product;

    }
    @GetMapping("/displayAllProduct")
    @ApiOperation("getting all products")
    public List<Product> displayAllProducts() {

       List<Product> products =productService.getProducts();
        return products;

    }
    @PostMapping("/addProduct")
    @ApiOperation("adding a product")
    public Product addProduct(@RequestBody Product product) {

        return productService.addProduct(product);

    }
    @GetMapping("/test")
    @ApiIgnore
    @ApiOperation("testing the app")
    public String test() {
        return "test";
    }


}
