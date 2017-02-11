package ufcg.edu.br.routes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufcg.edu.br.controllers.TaskController;
import ufcg.edu.br.models.Task;

import java.util.Collection;

/**
 * Created by Italo on 08/02/2017.
 */
@RestController
@RequestMapping("/task_lists/tasks")
public class TaskRouter {

    @Autowired
    private TaskController taskController;

    @RequestMapping(value = "/{listId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Task>> getAllTasks(@PathVariable Long listId) {
        Collection<Task> tasks = taskController.getAllTasks(listId);
        if (tasks != null) {
            return new ResponseEntity<Collection<Task>>(taskController.getAllTasks(listId), HttpStatus.OK);
        } else {
            return new ResponseEntity<Collection<Task>>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{listId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> saveTask(@PathVariable Long listId, @RequestBody Task task) {
        taskController.saveTask(listId, task);
        return new ResponseEntity<Task>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{listId}/{taskId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> getOneTask(@PathVariable Long listId, @PathVariable Long taskId) {
        return new ResponseEntity<Task>(taskController.getOneTask(listId, taskId), HttpStatus.OK);
    }

    @RequestMapping(value = "/{listId}/{taskId}", method = RequestMethod.DELETE)
    public void removeOneTask(@PathVariable Long listId, @PathVariable Long taskId) {
        taskController.removeOneTask(listId, taskId);
    }

    @RequestMapping(value = "/{listId}", method = RequestMethod.DELETE)
    public ResponseEntity removeAllTasks(@PathVariable Long listId) {
        taskController.removeAllTasks(listId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
