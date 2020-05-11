package org.lebedeva.mapper;

import org.lebedeva.dto.TeacherDto;
import org.lebedeva.model.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {GroupMapper.class})
public interface TeacherMapper extends EntityMapper<TeacherDto, Teacher> {

//    @Mapping(source = "confirmPassword", target = "password")
    Teacher toEntity(TeacherDto teacherDTO);

    @Mapping(source = "password", target = "confirmPassword")
    TeacherDto toDto(Teacher teacher);
}