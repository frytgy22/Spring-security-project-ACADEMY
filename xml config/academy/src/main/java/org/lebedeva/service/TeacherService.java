package org.lebedeva.service;

import org.lebedeva.dto.TeacherDto;
import org.lebedeva.model.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TeacherService extends GenericService<TeacherDto, Teacher> {
    Page<TeacherDto> findTeachersDtoByNameOrSurname(String name, Pageable pageable);
}
