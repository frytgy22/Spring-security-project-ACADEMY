package org.lebedeva.service;

import org.lebedeva.dto.GroupDto;
import org.lebedeva.model.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GroupService extends GenericService<GroupDto, Group> {
    Page<GroupDto> findGroupsDtoByName(String name, Pageable pageable);

    List<GroupDto> findGroupsDto();
}
