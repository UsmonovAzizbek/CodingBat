package com.example.codingbat.service;

import com.example.codingbat.dto.ApiResponse;
import com.example.codingbat.dto.CategoryDTO;
import com.example.codingbat.entity.Category;
import com.example.codingbat.entity.Language;
import com.example.codingbat.repository.CategoryRepository;
import com.example.codingbat.repository.LanguageRepository;
import com.example.codingbat.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    LanguageRepository languageRepository;
    public List<Category> getAllCate(){
        List<Category> repositoryAll = categoryRepository.findAll();
        return repositoryAll;
    }

    public Category getCate(Integer id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        Category category = optionalCategory.get();
        return category;
    }

    public ApiResponse addCate(CategoryDTO categoryDTO){
        Category category = new Category();
        category.setNameCate(categoryDTO.getNameCate());
        category.setStarCount(categoryDTO.getStarCount());
        category.setDescription(categoryDTO.getDescription());
        Optional<Language> optionalLanguage = languageRepository.findById(categoryDTO.getLanguage_id());
        if (optionalLanguage.isPresent()){
            category.setLanguage(optionalLanguage.get());
            categoryRepository.save(category);
            return new ApiResponse("New Category Save", true);
        }else {
            return new ApiResponse("Bunday id li  language mavjud emas ", false);
        }

    }

    public ApiResponse delete(Integer id){
        categoryRepository.deleteById(id);
        return new ApiResponse("Delete Category", true);
    }

    public ApiResponse update(CategoryDTO categoryDTO, Integer id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()){
            Category category = optionalCategory.get();
            category.setNameCate(categoryDTO.getNameCate());
            category.setStarCount(categoryDTO.getStarCount());
            category.setDescription(categoryDTO.getDescription());
            categoryRepository.save(category);
            return new ApiResponse("Update Category", true);
        }
        return new ApiResponse("Bunday Id li Language yoq", false);
    }
}
