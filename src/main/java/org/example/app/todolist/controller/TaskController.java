package org.example.app.todolist.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.app.todolist.dto.TaskDTO;
import org.example.app.todolist.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Tag(name = "Gerenciamento de tarefas")
@RestController
@RequestMapping("/tarefas")
public class TaskController {

    private final TaskService taskService;

    private TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Operation(summary = "Lista todas as tarefas!")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Lista de tarefas retornada com sucesso!"),
            @ApiResponse(responseCode = "422", description = "Nenhuma tarefa foi encontrada!"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping
    public ResponseEntity<List<TaskDTO>> listAllTask() {
        return ResponseEntity.ok().body(taskService.getAllTask());
    }

    @Operation(summary = "Busca uma tarefa pelo seu id!")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Tarefa retornada com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Nenhuma tarefa foi encontrada!"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTask(@Valid @PathVariable Long id) {
        return ResponseEntity.ok().body(taskService.getTask(id));
    }

    @Operation(summary = "Registrar uma nova tarefa!")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201", description = "Tarefa registraa com sucesso!"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping
    public ResponseEntity<TaskDTO> registerTask(@Valid @RequestBody TaskDTO taskDTO) {
        TaskDTO registerTask = taskService.registerTask(taskDTO);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(registerTask)
                .toUri();

        return ResponseEntity.created(uri).body(registerTask);
    }

    @Operation(summary = "Atualiza uma tarefa pelo seu id!")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Tarefa atualizada com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Nenhuma tarefa foi encontrada!"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(@Valid @RequestBody TaskDTO taskDTO, @PathVariable Long id){
        return ResponseEntity.ok().body(taskService.updateTask(taskDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
