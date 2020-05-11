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
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class AcademyServiceTestGetAllDto {
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
//        when(groupRepository.findAll())
//                .thenReturn(List.of(group));
//
//        List<GroupDto> groupsDto = groupService.findGroupsDto();
//
//        assertNotNull(groupsDto);
//        assertNotNull(groupsDto.get(0).getStudents());
//        assertEquals(1, groupsDto.size());
//        assertEquals(1, groupsDto.get(0).getStudents().size());
//        assertEquals("Java", groupsDto.get(0).getName());
//        assertEquals("Ivan Ivanov", groupsDto.get(0).getStudents().get(0));
//        assertEquals(1, groupsDto.get(0).getId());
//    }
//
//    @Test
//    void saveTeacherDto() {
//        when(teacherRepository.findAll())
//                .thenReturn(List.of(teacher));
//
//        List<TeacherDto> teachersDto = teacherService.findTeachersDto();
//
//        assertNotNull(teachersDto);
//        assertNotNull(teachersDto.get(0).getGroupsId());
//        assertNotNull(teachersDto.get(0).getGroupsName());
//        assertEquals(1, teachersDto.size());
//        assertEquals(1, teachersDto.get(0).getGroupsName().size());
//        assertEquals(1, teachersDto.get(0).getGroupsId().size());
//        assertEquals("Petr", teachersDto.get(0).getName());
//        assertEquals("Petrov", teachersDto.get(0).getSurname());
//        assertEquals(LocalDate.of(2020, 2, 2), teachersDto.get(0).getStartWork());
//        assertEquals("Java", teachersDto.get(0).getGroupsName().get(0));
//        assertEquals(1, teachersDto.get(0).getGroupsId().get(0));
//        assertEquals(1, teachersDto.get(0).getId());
//    }
//}
