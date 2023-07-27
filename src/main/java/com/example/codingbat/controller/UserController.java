package com.example.codingbat.controller;

import com.example.codingbat.dto.ApiResponse;
import com.example.codingbat.dto.UserDTO;
import com.example.codingbat.entity.User;
import com.example.codingbat.service.UserService;
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
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public List<User> getAllUser(){
        List<User> allUser = userService.getAllUser();
        return allUser;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Integer id){
        User getuser = userService.getuser(id);
        return getuser;
    }

    @PostMapping
    public ApiResponse addUser(@Valid @RequestBody UserDTO userDTO){
        ApiResponse adduser = userService.adduser(userDTO);
        return adduser;
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
}
