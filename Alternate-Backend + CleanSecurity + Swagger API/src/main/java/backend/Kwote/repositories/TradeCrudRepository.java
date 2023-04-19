package backend.Kwote.repositories;

import backend.Kwote.entities.TradeEntity;
import org.springframework.data.repository.CrudRepository;

public interface TradeCrudRepository extends CrudRepository<TradeEntity, Integer> {
}
