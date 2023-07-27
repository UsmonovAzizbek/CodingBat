package com.example.codingbat.service;

import com.example.codingbat.dto.ApiResponse;
import com.example.codingbat.dto.UserDTO;
import com.example.codingbat.entity.User;
import com.example.codingbat.repository.UserRepository;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Size
public class UserService {

    @Autowired
    UserRepository userRepository;
    public List<User> getAllUser(){
        List<User> userList = userRepository.findAll();
        return userList;
    }

    public User getuser(Integer id){
        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.get();
        return user;
    }

    public ApiResponse adduser(UserDTO userDTO){
        boolean existsByEmail = userRepository.existsByEmail(userDTO.getEmail());
        if (existsByEmail){
            return new ApiResponse("Bunday emailda user mavjud!", false);
        }
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        userRepository.save(user);
        return new ApiResponse("New User Saqlandi", true);
    }
}
