package com.example.codingbat.controller;

import com.example.codingbat.dto.ApiResponse;
import com.example.codingbat.dto.LanguageDTO;
import com.example.codingbat.entity.Language;
import com.example.codingbat.service.LanguageService;
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
@RequestMapping(value = "/language")
public class LanguageController {
    @Autowired
    LanguageService languageService;
    @GetMapping
    public List<Language> getAllLang(){
        List<Language> allLng = languageService.getAllLng();
        return allLng;
    }

    @GetMapping("/{id}")
    public Language getLang(@PathVariable Integer id){
        Language lang = languageService.getLang(id);
        return lang;
    }

    @PostMapping
    public ApiResponse addLang(@Valid  @RequestBody LanguageDTO languageDTO){
        ApiResponse apiResponse = languageService.addLang(languageDTO);
        return apiResponse;
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
        ApiResponse delete = languageService.delete(id);
        return delete;
    }

    @PutMapping("/{id}")
    public ApiResponse update(@RequestBody LanguageDTO languageDTO, @PathVariable Integer id){
        ApiResponse update = languageService.update(languageDTO, id);
        return update;
    }

}
