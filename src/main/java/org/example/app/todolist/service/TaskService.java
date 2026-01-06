package org.example.app.todolist.service;

import org.example.app.todolist.dto.TaskDTO;
import org.example.app.todolist.entity.Task;
import org.example.app.todolist.exception.TaskNotFoundException;
import org.example.app.todolist.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    public List<TaskDTO> getAllTask () {
        List<Task> taskList = taskRepository.findAll();
        List<TaskDTO> taskDTOS = new ArrayList<>();

        for (Task task:  taskList) {
            taskDTOS.add(new TaskDTO(task.getName(), task.getDescription()));
        }

        return taskDTOS;
    }

    public TaskDTO getTask(Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);

        if (optionalTask.isEmpty()) {
            throw new TaskNotFoundException("A tarefa informada não existe!");
        }

        Task task = optionalTask.get();
        TaskDTO taskDTO = new TaskDTO(task.getName(), task.getDescription());

        return taskDTO;

    }

    @Transactional
    public TaskDTO registerTask(TaskDTO taskDTO) {

        Task task = new Task();

        task.setName(taskDTO.name());
        task.setDescription(taskDTO.description());
        taskRepository.save(task);

        return taskDTO;
    }

    @Transactional
    public TaskDTO updateTask(TaskDTO taskDTO, Long id){
        Optional<Task> optionalTask = taskRepository.findById(id);

        if (optionalTask.isEmpty()) {
            throw new TaskNotFoundException("A tarefa informada não existe!");
        }

        Task task = optionalTask.get();
        task.setName(taskDTO.name());
        task.setDescription(taskDTO.description());

        taskRepository.save(task);

        return taskDTO;
    }

    public void deleteTask(Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);

        if (optionalTask.isEmpty()) {
            throw new TaskNotFoundException("A tarefa informada não existe!");
        }
        taskRepository.deleteById(id);
    }
}
