package org.lebedeva.mapper;

import org.lebedeva.dto.GroupDto;
import org.lebedeva.model.Group;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {StudentMapper.class, TeacherMapper.class})
public interface GroupMapper extends EntityMapper<GroupDto, Group> {

    Group toEntity(GroupDto dto);

    GroupDto toDto(Group group);
}