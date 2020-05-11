package org.lebedeva.service;

import org.lebedeva.model.Student;
import org.lebedeva.model.Teacher;
import org.lebedeva.repository.StudentRepository;
import org.lebedeva.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public CustomUserDetailsService(TeacherRepository teacherRepository, StudentRepository studentRepository) {
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Teacher teacher = teacherRepository.findTeacherByEmail(email).orElse(null);
        Student student = studentRepository.findStudentByEmail(email).orElse(null);

        return student != null ? student : teacher;
    }
}
