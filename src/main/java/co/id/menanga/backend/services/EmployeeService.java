package co.id.menanga.backend.services;

import co.id.menanga.backend.dao.EmployeeDAO;
import co.id.menanga.backend.model.Datatables;
import co.id.menanga.backend.model.Employee;
import co.id.menanga.backend.model.ResponseAPI;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final EmployeeDAO thisDao;

    public EmployeeService(EmployeeDAO thisDao) {
        this.thisDao = thisDao;
    }

    public Datatables getListForPagination(int page, int size, String cari) {
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
        return Datatables.builder().draw(page).data(result.getContent()).recordsFiltered((int) result.getTotalElements())
                .recordsTotal((int) result.getTotalElements()).build();
//        return result;
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

    public ResponseAPI insert(Employee employee) {
        if (thisDao.existsByIdNumber(employee.getIdNumber())) {
            return new ResponseAPI("50", "NIP sudah ada");
        } else {
            thisDao.save(employee);
        }
        return new ResponseAPI("00", "Data berhasil ditambahkan");
    }

    public ResponseAPI update(Employee employee, Long id) {
        employee.setId(id);
        thisDao.save(employee);
        return new ResponseAPI("00", "Data berhasil diupdate");
    }

    public ResponseAPI delete(Long id) {
        Employee employee = thisDao.getById(id);
        employee.setIsDelete(1);
        thisDao.save(employee);
        return new ResponseAPI("00", "Data berhasil dihapus");
    }

    enum JenisKelamin {
        Pria,
        Wanita
    }

}
