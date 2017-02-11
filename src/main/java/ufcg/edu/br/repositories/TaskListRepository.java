package ufcg.edu.br.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ufcg.edu.br.models.TaskList;

/**
 * Created by Italo on 08/02/2017.
 */
public interface TaskListRepository extends JpaRepository<TaskList, Long> {
}
