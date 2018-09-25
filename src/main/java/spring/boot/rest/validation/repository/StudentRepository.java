package spring.boot.rest.validation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.boot.rest.validation.dao.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	Optional<Student> findByStudentId(Long Id);
	void deleteByStudentId(Long id);
}
