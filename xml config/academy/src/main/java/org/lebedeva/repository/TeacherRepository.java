package org.lebedeva.repository;

import org.lebedeva.model.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    Page<Teacher> findAllByNameContainsIgnoreCaseOrSurnameContainsIgnoreCase(
            String name, String surname, Pageable pageable);

    Page<Teacher> findAllByNameContainsIgnoreCaseAndSurnameContainsIgnoreCase(
            String name, String surname, Pageable pageable);

    @Query(value = "select distinct teacher from Teacher teacher left join fetch teacher.groups",
            countQuery = "select count (distinct teacher) from Teacher teacher")
    Page<Teacher> findAllWithEagerRelationships(Pageable pageable);

    Optional<Teacher> findTeacherByEmail(String email);
}
