package org.example.app.todolist.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tarefas")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, message = "O nome da tarefa deve conter pelo menos 3 caracteres")
    @NotBlank(message = "O nome da tarefa deve ser informado!")
    private String name;

    @Size(min = 5, message = "A descrição deve conter pelo menos 5 caracteres")
    @NotBlank(message = "Uma descrição deve ser informada!")
    private String description;

    public Task() {
    }

    public Task(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
