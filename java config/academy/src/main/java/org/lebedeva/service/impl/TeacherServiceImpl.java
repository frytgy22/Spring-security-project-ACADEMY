package org.lebedeva.service.impl;

import org.lebedeva.dto.TeacherDto;
import org.lebedeva.mapper.TeacherMapper;
import org.lebedeva.model.Group;
import org.lebedeva.model.Teacher;
import org.lebedeva.object.Name;
import org.lebedeva.repository.GroupRepository;
import org.lebedeva.repository.TeacherRepository;
import org.lebedeva.service.TeacherService;
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
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final GroupRepository groupRepository;
    private final TeacherMapper teacherMapper;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository,
                              GroupRepository groupRepository,
                              TeacherMapper teacherMapper) {
        this.teacherRepository = teacherRepository;
        this.groupRepository = groupRepository;
        this.teacherMapper = teacherMapper;
    }

    @Override
    public void delete(Integer id) {
        teacherRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Page<TeacherDto> findTeachersDtoByNameOrSurname(String name, Pageable pageable) {

        Name searchName = Name.splitFullName(name);
        Page<Teacher> teachers;

        if (searchName.isHasSurname()) {           //если ввели >1 слова, поиск по имени и фамилии (And)
            teachers = teacherRepository.findAllByNameContainsIgnoreCaseAndSurnameContainsIgnoreCase(
                    searchName.getName(), searchName.getSurname(), pageable);
        } else {                                   //иначе ищем введенное слово в имени или фамилии (Or)
            teachers = teacherRepository.findAllByNameContainsIgnoreCaseOrSurnameContainsIgnoreCase(
                    searchName.getName(), searchName.getName(), pageable);
        }

        return teachers.map(t -> new TeacherDto(t.getId(), t.getName(), t.getSurname(), t.getStartWork(),
                t.getPhoto(), t.getEmail(), t.getPassword(), t.getPassword(), t.getRole(),
                t.getGroups().parallelStream()
                        .map(Group::getId).collect(Collectors.toList()),
                t.getGroups().parallelStream()
                        .map(Group::getName).collect(Collectors.toList())));
    }


    @Override
    public Teacher save(TeacherDto dto) {
        List<Group> groups = dto.getGroupsId().stream()
                .map(id -> groupRepository.findById(id).orElse(null))
                .collect(Collectors.toList());

        Teacher teacher = teacherMapper.toEntity(dto);
        teacher.setGroups(groups);
        return teacherRepository.save(teacher);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Page<TeacherDto> findAll(Pageable pageable) {
        return teacherRepository.findAllWithEagerRelationships(pageable)
                .map(t -> new TeacherDto(t.getId(), t.getName(), t.getSurname(), t.getStartWork(),
                        t.getPhoto(), t.getEmail(), t.getPassword(), t.getPassword(), t.getRole(),
                        t.getGroups().parallelStream()
                                .map(Group::getId).collect(Collectors.toList()),
                        t.getGroups().parallelStream()
                                .map(Group::getName).collect(Collectors.toList())));
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Optional<TeacherDto> findById(Integer id) {
        Teacher teacher = teacherRepository.findById(id).orElse(null);

        return teacher != null ?
                Optional.of(new TeacherDto(teacher.getId(), teacher.getName(), teacher.getSurname(),
                        teacher.getStartWork(), teacher.getPhoto(), teacher.getEmail(), teacher.getPassword(),
                        teacher.getPassword(), teacher.getRole(), teacher.getGroups()
                        .parallelStream()
                        .map(Group::getId).collect(Collectors.toList()),
                        teacher.getGroups()
                                .parallelStream()
                                .map(Group::getName).collect(Collectors.toList())))
                : Optional.empty();
    }
}
