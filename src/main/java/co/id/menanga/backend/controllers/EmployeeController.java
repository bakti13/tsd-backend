package co.id.menanga.backend.controllers;

import co.id.menanga.backend.model.Position;
import co.id.menanga.backend.services.PositionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping(value = {"/api/v1"})
public class EmployeeController {

    @Autowired
    private PositionService positionService;

    private void setJsonResponse(Object object, HttpServletResponse response) {
        try {
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = mapper.writeValueAsString(object);

            out.print(jsonInString);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = {"get-position"}, produces = "application/json")
    public void selectLokasi(HttpServletResponse response) {
        List<Position> position = positionService.getList();
        setJsonResponse(position, response);
    }


}
