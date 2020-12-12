package co.id.menanga.backend.dao;

import co.id.menanga.backend.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeDAO extends JpaRepository<Employee, Long> {

    // get list employee
//    List<Employee> findAllBy(@Param("start") int start, @Param("length") int length, @Param("filter") String search);

    Page<Employee> findAllBy(Pageable pageable);

    @Query(value = "SELECT * " +
            "FROM t2_employee t2 INNER JOIN t1_position t1 ON t2.POSITION_ID = t1.ID " +
            "WHERE t2.IS_DELETE = 0 AND ( t2.NAME LIKE %:cari% OR t2.BIRTH_DATE LIKE %:cari% OR t1.NAME LIKE %:cari% " +
            "OR t2.ID_NUMBER LIKE %:cari% ) order by t2.ID ASC",
            nativeQuery = true)
    Page<Employee> getListForPagination(@Param("cari") String search, Pageable pageable);

    @Query(value = "SELECT * FROM t2_employee t2 WHERE t2.ID = ? ",nativeQuery = true)
    Employee getById(Long id);



    //
//    void insert(Employee employee);
//    void update(Employee employee);
//    void deleta(Employee employee);
}
