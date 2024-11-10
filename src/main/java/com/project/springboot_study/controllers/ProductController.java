package com.project.springboot_study.controllers;

import com.project.springboot_study.dtos.ProductRecordDto;
import com.project.springboot_study.models.ProductModel;
import com.project.springboot_study.repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController // define uma classe controladora
public class ProductController {

    @Autowired // injeção de dependências de forma automática
    ProductRepository productRepository;

    @PostMapping("/products")
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDto productRecordDto) {
        // @Valid garante que as requisições de NotBlank e NotNull sejam cumpridas
        var productModel = new ProductModel();
        BeanUtils.copyProperties(productRecordDto, productModel); // Conversão de DTO para Model
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productModel));
        // Envia para o cliente que o recurso foi criado, passando o código 201 (CREATED), com as informações do que foi salvo no corpo da resposta
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductModel>> getAllProducts() {
        List<ProductModel> productModelList = productRepository.findAll();
        if(!productModelList.isEmpty()) {
            for (ProductModel productModel : productModelList) {
                UUID idProduct = productModel.getIdProduct();
                productModel.add(linkTo(methodOn(ProductController.class).getOneProduct(idProduct)).withSelfRel());
                // Linka o atributo da lista para o seu produto em específico
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(productModelList);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id") UUID id) {
        Optional<ProductModel> productById = productRepository.findById(id);
        if(productById.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        productById.get().add(linkTo(methodOn(ProductController.class).getAllProducts()).withRel("Products list"));
        // Mostra um link para ver todos os produtos
        return ResponseEntity.status(HttpStatus.OK).body(productById.get());
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value = "id") UUID id,
                                                @RequestBody @Valid ProductRecordDto productRecordDto) {
        Optional<ProductModel> productById = productRepository.findById(id);
        if(productById.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        var productModel = productById.get();
        BeanUtils.copyProperties(productRecordDto, productModel);
        return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(productModel));
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") UUID id) {
        Optional<ProductModel> productById = productRepository.findById(id);
        if(productById.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        productRepository.delete(productById.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted succesfully");
    }

}
