package rest.api.orders.rest.api.orders.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import rest.api.orders.rest.api.orders.dto.ProductDto;
import rest.api.orders.rest.api.orders.repositories.ProductRepository;
import rest.api.orders.rest.api.orders.domain.Product;
import rest.api.orders.rest.api.orders.dto.ProductCreateDto;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<Object> create(@RequestBody @Valid ProductCreateDto productCreateDto, UriComponentsBuilder uriComponentsBuilder){
        var product = new Product(productCreateDto);
        var uri  = uriComponentsBuilder.path("/products/{id}").buildAndExpand(product.getId()).toUri();

        productRepository.save(product);

        var productDto = new ProductDto(product);
        return ResponseEntity.created(uri).body(productDto);

    }

    @GetMapping
    public ResponseEntity<Object> index(){
        var products = productRepository.findAll();

        return ResponseEntity.ok(products.stream().map(ProductDto::new).toList());
    }
}
