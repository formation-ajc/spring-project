package com.projet.springproject.service;

import com.projet.springproject.entity.Product;
import com.projet.springproject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll(){
        return productRepository.findAll();
    }

    public Optional<Product> getById(final Long id) {
        return productRepository.findById(id);
    }

    public Product create(Product tutorial) {
        return productRepository.save(tutorial);
    }

    public void delete(final Long id) {
        productRepository.deleteById(id);
    }
}
