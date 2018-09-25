package spring.boot.rest.validation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.boot.rest.validation.dao.Course;

public interface CourseRepository extends JpaRepository<Course, Long>{

}
