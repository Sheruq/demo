package repo;

import models.list;
import org.springframework.data.repository.CrudRepository;

public interface listRepo extends CrudRepository<list, Long> {
}
