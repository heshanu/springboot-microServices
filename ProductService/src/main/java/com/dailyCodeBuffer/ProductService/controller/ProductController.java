package com.dailyCodeBuffer.ProductService.controller;

import com.dailyCodeBuffer.ProductService.model.ProductRequest;
import com.dailyCodeBuffer.ProductService.model.ProductResponse;
import com.dailyCodeBuffer.ProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<Long> addProduct(@RequestBody ProductRequest request){
        try{
          long productId =productService.addProduct(request);
          return new ResponseEntity<>(productId,HttpStatus.CREATED);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/list/{productId}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable long productId){
        ProductResponse productResponse=productService.getProductById(productId);
        return new ResponseEntity<>(productResponse,HttpStatus.ACCEPTED);
    }

    @PutMapping("/list/reducedQuanitiy/{id}")
    public ResponseEntity<String> reducedQuantity(@PathVariable("id") long productId,@RequestParam long quantity){
        productService.reducedQuantity(productId,quantity);
        return new ResponseEntity<>("Updated for id {id}",HttpStatus.OK);
        //return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
