package com.projet.springproject.service;

import com.projet.springproject.entity.Category;
import com.projet.springproject.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAll(){
        return categoryRepository.findAll();
    }

    public Optional<Category> getById(final Long id) {
        return categoryRepository.findById(id);
    }

    public Category create(Category tutorial) {
        return categoryRepository.save(tutorial);
    }

    public void delete(final Long id) {
        categoryRepository.deleteById(id);
    }
}
