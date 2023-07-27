package com.example.codingbat.controller;

import com.example.codingbat.dto.ApiResponse;
import com.example.codingbat.dto.TaskDTO;
import com.example.codingbat.entity.Task;
import com.example.codingbat.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
@RequestMapping(value = "/task")
public class TaskController {
    @Autowired
    TaskService taskService;
    @GetMapping
    public List<Task> getAllTask(){
        List<Task> allTask = taskService.getAllTask();
        return allTask;
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable Integer id){
        Task task = taskService.getTask(id);
        return task;
    }

    @GetMapping("/page/{id}")
    public Page<Task> taskPage(@PathVariable Integer page){
        Page<Task> taskPage = taskService.taskPage(page);
        return taskPage;
    }

    @PostMapping
    public ApiResponse addTask(@Valid  @RequestBody TaskDTO taskDTO){
        ApiResponse apiResponse = taskService.addTask(taskDTO);
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
        ApiResponse delete = taskService.delete(id);
        return delete;
    }

    @PutMapping("/{id}")
    public ApiResponse update(@RequestBody TaskDTO taskDTO, @PathVariable Integer id){
        ApiResponse update = taskService.update(taskDTO, id);
        return update;
    }
}
