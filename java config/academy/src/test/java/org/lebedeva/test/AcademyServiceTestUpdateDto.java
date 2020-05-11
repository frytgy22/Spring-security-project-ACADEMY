//package org.lebedeva.test;
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
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class AcademyServiceTestUpdateDto {
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
//    TeacherDto teacherDto;
//    StudentDto studentDto;
//    GroupDto groupDto;
//
//    Teacher teacher;
//    Student student;
//    Group group;
//
//    @BeforeEach
//    public void init() {
//        groupDto = new GroupDto(1, "Java", List.of(studentDto));
//        studentDto = new StudentDto(1, "Ivan", "Ivanov",
//                LocalDate.of(1999, 9, 9), 1, "Java");
//        teacherDto = new TeacherDto(1, "Petr", "Petrov", LocalDate.of(2020, 2, 2),
//                List.of(1), List.of("Java"));
//
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
//    void saveGroupDto() {
//        when(groupRepository.findById(1))
//                .thenReturn(Optional.of(group));
//
//        Group newGroup = groupService.updateGroupDto(groupDto);
//
//        assertNotNull(newGroup);
//        assertNotNull(newGroup.getStudents());
//        assertNotNull(newGroup.getTeachersList());
//        assertEquals("Java", newGroup.getName());
//
//        assertEquals(1, newGroup.getTeachersList().size());
//        assertEquals(1, newGroup.getTeachersList().size());
//
//        assertEquals(1, newGroup.getStudents().get(0).getId());
//        assertEquals(LocalDate.of(2020, 2, 2), newGroup.getTeachersList().get(0).getStartWork());
//        assertEquals("Petr", newGroup.getTeachersList().get(0).getName());
//        assertEquals("Petrov", newGroup.getTeachersList().get(0).getSurname());
//
//        assertEquals(1, newGroup.getTeachersList().get(0).getId());
//        assertEquals(LocalDate.of(1999, 9, 9), newGroup.getStudents().get(0).getBirthDate());
//        assertEquals("Ivan", newGroup.getStudents().get(0).getName());
//        assertEquals("Ivanov", newGroup.getStudents().get(0).getSurname());
//    }
//
//    @Test
//    void saveTeacherDto() {
//        when(groupRepository.findById(1))
//                .thenReturn(Optional.of(group));
//
//        Teacher newTeacher = teacherService.saveTeacherDto(teacherDto);
//
//        assertNotNull(newTeacher);
//        assertEquals("Petr", newTeacher.getName());
//        assertEquals("Petrov", newTeacher.getSurname());
//        assertEquals(LocalDate.of(2020, 2, 2), newTeacher.getStartWork());
//        assertNotNull(newTeacher.getGroups());
//        assertEquals(1, newTeacher.getGroups().size());
//        assertEquals("Java", newTeacher.getGroups().get(0).getName());
//        assertEquals(1, newTeacher.getGroups().get(0).getId());
//    }
//}
