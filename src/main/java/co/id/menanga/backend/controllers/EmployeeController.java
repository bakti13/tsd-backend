package co.id.menanga.backend.controllers;

import co.id.menanga.backend.model.Datatables;
import co.id.menanga.backend.model.Employee;
import co.id.menanga.backend.model.Position;
import co.id.menanga.backend.model.ResponseAPI;
import co.id.menanga.backend.services.EmployeeService;
import co.id.menanga.backend.services.PositionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping(value = {"/api/v1"})
@CrossOrigin
public class EmployeeController {

    private final PositionService positionService;

    private final EmployeeService employeeService;

    public EmployeeController(PositionService positionService, EmployeeService employeeService) {
        this.positionService = positionService;
        this.employeeService = employeeService;
    }

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

    @RequestMapping(method = RequestMethod.GET, value = {"all-employee"}, produces = "application/json")
    public void index(HttpServletResponse response, @RequestParam("draw") int draw,
                    @RequestParam(value = "start", defaultValue = "0") int page,
                      @RequestParam(value = "length", defaultValue = "10") int size,
                      @RequestParam(value = "search[value]", defaultValue = "") String search) {
        Datatables data = employeeService.getListForPagination(page, size, search);
        setJsonResponse(data, response);
    }

    @RequestMapping(method = RequestMethod.GET, value = {"get-employee/{id}"}, produces = "application/json")
    public Employee getEmployeById(HttpServletResponse response, @PathVariable("id") Long id) {
        Employee employee = employeeService.getById(id);
        setJsonResponse(employee, response);
        return employee;
    }

    @RequestMapping(method = RequestMethod.POST, value = {"create-employee"}, produces = "application/json")
    public void insert(HttpServletResponse response, @RequestBody Employee employee) {
        ResponseAPI result = employeeService.insert(employee);
        setJsonResponse(result, response);
    }

    @RequestMapping(method = RequestMethod.PUT, value = {"update-employee/{id}"}, produces = "application/json")
    public void update(HttpServletResponse response, @PathVariable Long id, @RequestBody Employee employee) {
        ResponseAPI result = employeeService.update(employee, id);
        setJsonResponse(result, response);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = {"delete-employee/{id}"}, produces = "application/json")
    public void delete(HttpServletResponse response, @PathVariable Long id) {
        ResponseAPI result = employeeService.delete(id);
        setJsonResponse(result, response);
    }


    @RequestMapping(value = {"get-position"}, produces = "application/json")
    public void selectLokasi(HttpServletResponse response) {
        List<Position> position = positionService.getList();
        setJsonResponse(position, response);
    }

    @RequestMapping(produces = "application/json")
    public void mainRoot(HttpServletResponse response) {
        setJsonResponse(new ResponseAPI("00", "Success"), response);
    }
}
