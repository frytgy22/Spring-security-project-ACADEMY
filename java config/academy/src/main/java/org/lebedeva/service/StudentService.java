package org.lebedeva.service;

import org.lebedeva.dto.StudentDto;
import org.lebedeva.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentService extends GenericService<StudentDto, Student> {
    Page<StudentDto> findStudentsDtoByNameOrSurname(String name, Pageable pageable);
}
