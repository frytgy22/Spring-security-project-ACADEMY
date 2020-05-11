//package org.lebedeva.test;
//
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.lebedeva.dto.GroupDto;
//import org.lebedeva.dto.StudentDto;
//import org.lebedeva.dto.TeacherDto;
//import org.lebedeva.model.Group;
//import org.lebedeva.model.Student;
//import org.lebedeva.model.Teacher;
//import org.lebedeva.repository.GroupRepository;
//import org.lebedeva.repository.StudentRepository;
//import org.lebedeva.repository.TeacherRepository;
//import org.lebedeva.service.GroupService;
//import org.lebedeva.service.StudentService;
//import org.lebedeva.service.TeacherService;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class AcademyServiceTestGetDto {
//
//    @Mock
//    GroupRepository groupRepository;
//    @Mock
//    StudentRepository studentRepository;
//    @Mock
//    TeacherRepository teacherRepository;
//
//    @InjectMocks
//    GroupService groupService;
//    @InjectMocks
//    StudentService studentService;
//    @InjectMocks
//    TeacherService teacherService;
//
//    Teacher teacher;
//    Student student;
//    Group group;
//
//    @BeforeEach
//    public void init() {
//        group = new Group(1, "Java", null, null);
//        student = new Student(1, "Ivan", "Ivanov",
//                LocalDate.of(1999, 9, 9), group);
//        group.setStudents(List.of(student));
//        teacher = new Teacher(1, "Petr", "Petrov", LocalDate.of(2020, 2, 2),
//                List.of(group));
//        group.setTeachersList(List.of(teacher));
//    }
//
//    @Test
//    void getStudentDto() {
//        when(studentRepository.findById(1))
//                .thenReturn(Optional.of(student));
//
//        StudentDto studentDto = studentService.getStudentDto(1);
//
//        //check that the method was invoked only once
//        verify(studentRepository, times(1)).findById(1);
//
//        assertNotNull(studentDto);
//        assertEquals(1, studentDto.getId());
//        assertEquals("Ivan", studentDto.getName());
//        assertEquals("Ivanov", studentDto.getSurname());
//        assertEquals("Java", studentDto.getGroupName());
//        assertEquals(1, studentDto.getGroupId());
//    }
//
//    @Test
//    void getGroupDto() {
//        when(groupRepository.findById(1))
//                .thenReturn(Optional.of((group)));
//
//        GroupDto groupDto = groupService.getGroupDto(1);
//
//        verify(groupRepository, times(1)).findById(1);
//
//        assertNotNull(groupDto);
//        assertEquals(1, groupDto.getId());
//        assertEquals("Java", groupDto.getName());
//        assertEquals(1, groupDto.getStudents().size());
//        assertEquals("Ivan Ivanov", groupDto.getStudents().get(0));
//    }
//
//    @Test
//    void getTeacherDto() {
//        when(teacherRepository.findById(1))
//                .thenReturn(Optional.of(teacher));
//
//        TeacherDto teacherDto = teacherService.getTeacherDto(1);
//
//        verify(teacherRepository, times(1)).findById(1);
//
//        assertNotNull(teacherDto);
//        assertEquals(1, teacherDto.getId());
//        assertEquals("Petr", teacherDto.getName());
//        assertEquals("Petrov", teacherDto.getSurname());
//        assertEquals(LocalDate.of(2020, 2, 2), teacherDto.getStartWork());
//        assertEquals(1, teacherDto.getGroupsId().size());
//        assertEquals(1, teacherDto.getGroupsName().size());
//        assertEquals("Java", teacherDto.getGroupsName().get(0));
//        assertEquals(1, teacherDto.getGroupsId().get(0));
//    }
//}
