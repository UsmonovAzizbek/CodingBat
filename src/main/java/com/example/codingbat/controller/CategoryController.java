package com.example.codingbat.controller;

import com.example.codingbat.dto.ApiResponse;
import com.example.codingbat.dto.CategoryDTO;
import com.example.codingbat.entity.Category;
import com.example.codingbat.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @GetMapping
    public List<Category> getAllCate(){
        List<Category> allCate = categoryService.getAllCate();
        return allCate;
    }

    @GetMapping("/{id}")
    public Category getCate(@PathVariable Integer id){
        Category cate = categoryService.getCate(id);
        return cate;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addCate(@Valid  @RequestBody CategoryDTO categoryDTO){
        ApiResponse apiResponse = categoryService.addCate(categoryDTO);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleValidationError(MethodArgumentNotValidException ex){
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorsResponse = new HashMap<>();
        errorsResponse.put("errors", errors);
        return errorsResponse;
    }

    @DeleteMapping("/{id}")
     public ApiResponse delete(@PathVariable Integer id){
        ApiResponse delete = categoryService.delete(id);
        return delete;
    }


    @PutMapping("/{id}")
    public ApiResponse update(@RequestBody CategoryDTO categoryDTO, @PathVariable Integer id){
        ApiResponse update = categoryService.update(categoryDTO, id);
        return update;
    }
}
