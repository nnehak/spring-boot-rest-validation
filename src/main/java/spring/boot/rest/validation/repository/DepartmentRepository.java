package spring.boot.rest.validation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.boot.rest.validation.dao.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
