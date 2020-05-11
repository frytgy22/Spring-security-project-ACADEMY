package org.lebedeva.service.impl;

import org.lebedeva.dto.StudentDto;
import org.lebedeva.mapper.StudentMapper;
import org.lebedeva.model.Student;
import org.lebedeva.object.Name;
import org.lebedeva.repository.StudentRepository;
import org.lebedeva.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    public void delete(Integer id) {
        studentRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Page<StudentDto> findStudentsDtoByNameOrSurname(String name, Pageable pageable) {

        Name searchName = Name.splitFullName(name);

        Page<Student> students = searchName.isHasSurname() ?       //если ввели >1 слова, поиск по имени и фамилии (And)
                studentRepository.findAllByNameContainsIgnoreCaseAndSurnameContainsIgnoreCase(
                        searchName.getName(), searchName.getSurname(), pageable)
                //иначе ищем введенное слово в имени или фамилии (Or)
                : studentRepository.findAllByNameContainsIgnoreCaseOrSurnameContainsIgnoreCase(
                searchName.getName(), searchName.getName(), pageable);

        return students.map(studentMapper::toDto);
    }

    @Override
    public Student save(StudentDto dto) {
        Student student = studentMapper.toEntity(dto);
        return studentRepository.save(student);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Page<StudentDto> findAll(Pageable pageable) {

        return studentRepository.findAllWithEagerRelationships(pageable)
                .map(studentMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Optional<StudentDto> findById(Integer id) {
        return studentRepository.findById(id).map(studentMapper::toDto);
    }
}
