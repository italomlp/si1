package ufcg.edu.br.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ufcg.edu.br.models.Task;
import ufcg.edu.br.models.TaskList;
import ufcg.edu.br.repositories.TaskListRepository;
import ufcg.edu.br.repositories.TaskRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Italo on 08/02/2017.
 */
@RestController
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskListRepository taskListRepository;

    public Collection<TaskList> getAllLists() {
        return taskListRepository.findAll();
    }

    public void saveList(TaskList taskList) {
        if (taskList != null) {
            taskListRepository.save(taskList);
//            Long id = taskList.getId();
//
//            if (id == null){
//                taskListRepository.save(taskList);
//            } else {
//                TaskList updatedTaskList = taskListRepository.findOne(id);
//                updatedTaskList.setName(taskList.getName());
//                taskListRepository.save(updatedTaskList);
//            }
        }
    }

    public TaskList getOneList(Long listId) {
        return taskListRepository.findOne(listId);
    }

    public void removeOneList(Long listId) { // remover conteudo tambem
        TaskList taskList = taskListRepository.findOne(listId);
        List<Task> list = new ArrayList<>(taskList.getTasks());
        taskListRepository.delete(taskList);
        for (Task task : list) {
            System.out.println(task.getId());
            taskRepository.delete(task.getId());
        }
    }

    public void removeAllLists() {
        taskListRepository.deleteAll();
        taskRepository.deleteAll();
    }

    public Collection<Task> getAllTasks(Long listId) {
        TaskList taskList = getOneList(listId);
        return taskList != null ? taskList.getTasks() : null;
    }

    public void saveTask(Long listId, Task task) {
        if (task != null) {
            TaskList taskList = getOneList(listId);
            if (taskList != null) {
                if (task.getId() == null) {
                    taskList.add(task);
                    taskRepository.save(task);
                    taskListRepository.save(taskList);
                } else {
                    Task updatedTask = taskRepository.findOne(task.getId());
                    updatedTask.setPriority(task.getPriority());
                    updatedTask.setName(task.getName());
                    updatedTask.setCategory(task.getCategory());
                    updatedTask.setDescription(task.getDescription());
                    updatedTask.setDone(task.getDone());
                    taskList.updateDonePercent();
                    taskRepository.save(updatedTask);
                }
            }

        }

    }

    public Task getOneTask(Long listId, Long taskId) {
        TaskList taskList = getOneList(listId);
        return taskList.getTask(taskId);
    }

    public void removeOneTask(Long listId, Long taskId) {
        TaskList taskList = getOneList(listId);
        Task task = taskList.getTask(taskId);
        taskList.remove(taskId);
        taskList.updateDonePercent();
        taskRepository.delete(taskId);
        taskListRepository.save(taskList);

    }

    public void removeAllTasks(Long listId) {
        TaskList taskList = taskListRepository.findOne(listId);
        List<Task> list = new ArrayList<>(taskList.getTasks());
        for (Task task : list) {
            taskList.remove(task);
            taskListRepository.save(taskList);
            taskRepository.delete(task.getId());
        }
    }

}
