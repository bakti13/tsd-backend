package co.id.menanga.backend.dao;


import co.id.menanga.backend.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionDAO extends JpaRepository<Position, Long> {

    @Query(value = "SELECT * FROM t1_position",
            nativeQuery = true)
    List<Position> getlist();
}
