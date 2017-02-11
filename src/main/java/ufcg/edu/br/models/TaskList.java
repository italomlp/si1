package ufcg.edu.br.models;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Italo on 07/02/2017.
 */
@Entity
@Table(name = "tb_taskLists")
public class TaskList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Autowired
    @NotNull
    @Column(name = "name")
    public String name;

    @Autowired
    @OneToMany
    public List<Task> tasks;

    @Autowired
    @NotNull
    @Column(name = "donePercent")
    public double donePercent;

    public TaskList() {
        this.donePercent = 100;
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

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
        this.updateDonePercent();
    }

    public double getDonePercent() {
        return donePercent;
    }

    public void setDonePercent(double donePercent) {
        this.donePercent = donePercent;
    }

    public int size() {
        return tasks.size();
    }

    public void updateDonePercent() {
        int size = this.size();
        if (size >= 0) {
            double dones = 0;
            for (Task task : this.tasks) {
                if (task.getDone()) {
                    dones++;
                }
            }
            this.donePercent = (dones / this.size()) * 100.0;
        } else {
            this.donePercent = 100;
        }
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public boolean contains(Task task) {
        return tasks.contains(task);
    }

    public Task getTask(Long taskId) {
        for (Task task : this.tasks) {
            if (task.getId().equals(taskId)) {
                return task;
            }
        }

        return null;
    }

    public boolean add(Task task) {
        boolean result = tasks.add(task);
        this.updateDonePercent();
        return result;
    }

    public boolean remove(Task task) {
        boolean result = tasks.remove(task);
        this.updateDonePercent();
        return result;
    }

    public void remove(Long taskId) {
        remove(getTask(taskId));
    }

    public boolean removeAll() {
        boolean result = this.tasks.removeAll(this.tasks);
        this.updateDonePercent();
        return result;
    }

//    public void doTask(Task task) {
//        task.doIt();
//    }
//
//    public void undoTask(Task task) {
//        task.undoIt();
//    }
}
