package ufcg.edu.br.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ufcg.edu.br.models.Category;

/**
 * Created by Italo on 08/02/2017.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
