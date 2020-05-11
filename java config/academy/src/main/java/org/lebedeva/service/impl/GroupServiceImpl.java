package org.lebedeva.service.impl;

import org.lebedeva.dto.GroupDto;
import org.lebedeva.mapper.GroupMapper;
import org.lebedeva.mapper.StudentMapper;
import org.lebedeva.mapper.TeacherMapper;
import org.lebedeva.model.Group;
import org.lebedeva.repository.GroupRepository;
import org.lebedeva.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final StudentMapper studentMapper;
    private final TeacherMapper teacherMapper;
    private final GroupMapper groupMapper;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository,
                            StudentMapper studentMapper,
                            TeacherMapper teacherMapper,
                            GroupMapper groupMapper) {
        this.groupRepository = groupRepository;
        this.groupMapper = groupMapper;
        this.studentMapper = studentMapper;
        this.teacherMapper = teacherMapper;
    }

    @Override
    public void delete(Integer id) {
        groupRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<GroupDto> findGroupsDto() {
        return groupRepository.findAll()
                .stream().map(groupMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Page<GroupDto> findGroupsDtoByName(String name, Pageable pageable) {

        return groupRepository.findAllByName("%" + name.trim() + "%", pageable)
                .map(g -> new GroupDto(g.getId(), g.getName(), g.getStudents().stream()
                        .map(studentMapper::toDto)
                        .collect(Collectors.toList()), g.getTeachersList().stream()
                        .map(teacherMapper::toDto)
                        .collect(Collectors.toList())));
    }

    @Override
    public Group save(GroupDto dto) {
        Group group = groupRepository.findOneWithEagerRelationships(dto.getId()).orElse(new Group());
        group.setName(dto.getName());
        return groupRepository.save(group);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Page<GroupDto> findAll(Pageable pageable) {

        return groupRepository.findAllWithEagerRelationships(pageable)
                .map(g -> new GroupDto(g.getId(), g.getName(), g.getStudents().stream()
                        .map(studentMapper::toDto)
                        .collect(Collectors.toList()), g.getTeachersList().stream()
                        .map(teacherMapper::toDto)
                        .collect(Collectors.toList())));
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Optional<GroupDto> findById(Integer id) {
        Group group = groupRepository.findOneWithEagerRelationships(id).orElse(null);

        return group != null ?
                Optional.of(new GroupDto(group.getId(), group.getName(), group.getStudents().stream()
                        .map(studentMapper::toDto)
                        .collect(Collectors.toList()), group.getTeachersList().stream()
                        .map(teacherMapper::toDto).collect(Collectors.toList())))
                : Optional.empty();
    }
}
