package ufcg.edu.br.routes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufcg.edu.br.controllers.TaskController;
import ufcg.edu.br.models.Task;
import ufcg.edu.br.models.TaskList;

import java.util.Collection;

/**
 * Created by Italo on 08/02/2017.
 */
@RestController
@RequestMapping("/task_lists")
public class TaskListRouter {

    @Autowired
    private TaskController taskController;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<TaskList>> getAllLists() {
        return new ResponseEntity<Collection<TaskList>>(taskController.getAllLists(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveList(@RequestBody TaskList taskList) {
        taskController.saveList(taskList);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/{listId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskList> getOneList(@PathVariable Long listId) {
        return new ResponseEntity<TaskList>(taskController.getOneList(listId), HttpStatus.OK);
    }

    @RequestMapping(value = "/{listId}", method = RequestMethod.DELETE)
    public ResponseEntity removeOneList(@PathVariable Long listId) {
        taskController.removeOneList(listId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity removeAllLists() {
        taskController.removeAllLists();
        return new ResponseEntity(HttpStatus.OK);
    }

}
