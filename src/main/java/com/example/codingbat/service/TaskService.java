package com.example.codingbat.service;

import com.example.codingbat.dto.ApiResponse;
import com.example.codingbat.dto.TaskDTO;
import com.example.codingbat.entity.Category;
import com.example.codingbat.entity.Task;
import com.example.codingbat.repository.CategoryRepository;
import com.example.codingbat.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    CategoryRepository categoryRepository;
    public List<Task> getAllTask(){
        List<Task> repositoryAll = taskRepository.findAll();
        return  repositoryAll;
    }

    public Task getTask(Integer id){
        Optional<Task> optionalTask = taskRepository.findById(id);
        Task task = optionalTask.get();
        return task;
    }

    public Page<Task> taskPage(Integer page){
        Pageable pageable = PageRequest.of(page,5);
        Page<Task> taskList = taskRepository.findAll(pageable);
        return taskList;
    }

    public ApiResponse addTask(TaskDTO taskDTO){
        Task task = new Task();
        task.setTaskName(taskDTO.getTaskName());
        task.setCondition(taskDTO.getCondition());
        task.setExample(taskDTO.getExample());
        task.setStar(taskDTO.getStar());
        Optional<Category> optionalCategory = categoryRepository.findById(taskDTO.getCategory_id());
        if (optionalCategory.isPresent()){
            task.setCategory(optionalCategory.get());
            taskRepository.save(task);
            return  new ApiResponse("New Task Save", true);
        }else {
            return new ApiResponse("Bunday Id li Category yoq", false);
        }
    }

    public ApiResponse delete(Integer id){
        taskRepository.deleteById(id);
        return new ApiResponse("Delete Task", true);
    }

    public ApiResponse update(TaskDTO taskDTO, Integer id){
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()){
            Task task = optionalTask.get();
            task.setTaskName(taskDTO.getTaskName());
            task.setCondition(taskDTO.getCondition());
            task.setExample(taskDTO.getExample());
            task.setStar(taskDTO.getStar());
            taskRepository.save(task);
            return new ApiResponse("Update Task", true);
        }
        return new ApiResponse("Bunday Id li Category yoq", false);
    }
}
