package org.lebedeva.mapper;

import org.lebedeva.dto.StudentDto;
import org.lebedeva.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
        (componentModel = "spring", uses = {GroupMapper.class})
public interface StudentMapper extends EntityMapper<StudentDto, Student> {

    @Mapping(source = "password", target = "confirmPassword")
    @Mapping(source = "group.id", target = "groupId")
    @Mapping(source = "group.name", target = "groupName")
    StudentDto toDto(Student student);

    @Mapping(source = "groupId", target = "group.id")
    Student toEntity(StudentDto studentDto);
}
