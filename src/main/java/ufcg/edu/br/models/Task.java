package ufcg.edu.br.models;


import org.hibernate.validator.constraints.NotBlank;
import ufcg.edu.br.models.enums.Priority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by Italo on 02/02/2017.
 */
@Entity
@Table(name = "tb_tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 200)
    @Column(name = "NAME")
    private String name;

    @Size(max = 500)
    @Column(name = "DESCRIPTION")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "PRIORITY")
    private Priority priority;

//    @Column(name = "CATEGORY")
    @ManyToOne
    private Category category;

    @Column(name = "DONE")
    private boolean done;

    @OneToMany
    private List<Task> subTasks;

    protected Task() {

    }

    public Task(String name, Priority priority) {
        setName(name);
        setPriority(priority);
    }

    public Long getId() {
        return id;
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

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean getDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
