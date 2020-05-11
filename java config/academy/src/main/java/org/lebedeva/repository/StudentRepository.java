package org.lebedeva.repository;

import org.lebedeva.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Page<Student> findAllByNameContainsIgnoreCaseOrSurnameContainsIgnoreCase(
            String name, String surname, Pageable pageable);

    Page<Student> findAllByNameContainsIgnoreCaseAndSurnameContainsIgnoreCase(
            String name, String surname, Pageable pageable);

    Optional<Student> findStudentByEmail(String email);

    @Query(value = "select distinct students from Student students left join fetch students.group",
            countQuery = "select count (distinct students) from Student  students")
    Page<Student> findAllWithEagerRelationships(Pageable pageable);
}