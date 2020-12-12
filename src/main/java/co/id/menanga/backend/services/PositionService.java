package co.id.menanga.backend.services;

import co.id.menanga.backend.dao.PositionDAO;
import co.id.menanga.backend.model.Position;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService {

    private final PositionDAO dao;

    public PositionService(PositionDAO dao) {
        this.dao = dao;
    }

    public List<Position> getList() {
        return dao.getlist();
    }
}
