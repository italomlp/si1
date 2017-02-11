package ufcg.edu.br.routes;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ufcg.edu.br.models.enums.Priority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Italo on 08/02/2017.
 */
@RestController
@RequestMapping("/priorities")
public class PriorityRouter {

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Map<String, Object>>> getPriorities() {
        Collection<Map<String, Object>> priorities = new ArrayList<>(3);
        for (Priority priority : Priority.values()) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", priority.getName());
            map.put("priority", priority);
            priorities.add(map);
        }

        return new ResponseEntity<Collection<Map<String, Object>>>(priorities, HttpStatus.OK);
    }


}
