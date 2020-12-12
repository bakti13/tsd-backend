package co.id.menanga.backend.services;

import co.id.menanga.backend.dao.EmployeeDAO;
import co.id.menanga.backend.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    enum JenisKelamin {
        Pria,
        Wanita
    }

    private final EmployeeDAO thisDao;

    public EmployeeService(EmployeeDAO thisDao) {
        this.thisDao = thisDao;
    }

    public Page<Employee> getListForPagination(int page, int size, String cari) {
        Pageable paging = PageRequest.of(page, size);
//        Page<Employee> result = thisDao.findAllBy(paging);
        Page<Employee> result = thisDao.getListForPagination(cari, paging);

        for (Employee e : result) {
            if (e.getGender().equals("1")) {
                e.setGender(JenisKelamin.Pria.toString());
            } else {
                e.setGender(JenisKelamin.Wanita.toString());
            }
        }
        return result;
    }

    public Employee getById(Long id) {

        Employee result = thisDao.getById(id);
        if (result.getGender().equals("1")) {
            result.setGender(JenisKelamin.Pria.toString());
        } else {
            result.setGender(JenisKelamin.Wanita.toString());
        }
        return result;
    }

    public Employee insert(Employee employee) {
        Employee result = thisDao.save(employee);

        return result;
    }

}
