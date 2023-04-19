package backend.Kwote.repositories;

import backend.Kwote.entities.ProjectEntity;
import backend.Kwote.entities.TradeEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<ProjectEntity, Integer> {
}
