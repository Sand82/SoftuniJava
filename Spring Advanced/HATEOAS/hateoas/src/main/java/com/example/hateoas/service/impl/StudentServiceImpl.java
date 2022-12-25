package com.example.hateoas.service.impl;

import com.example.hateoas.models.dto.StudentDTO;
import com.example.hateoas.models.entities.Course;
import com.example.hateoas.models.entities.Order;
import com.example.hateoas.models.entities.Student;
import com.example.hateoas.repositories.CourseRepository;
import com.example.hateoas.repositories.OrderRepository;
import com.example.hateoas.repositories.StudentRepository;
import com.example.hateoas.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;
    private CourseRepository courseRepository;
    private OrderRepository orderRepository;
    private ModelMapper modelMapper;


    public StudentServiceImpl(StudentRepository studentRepository,
                              CourseRepository courseRepository,
                              OrderRepository orderRepository,
                              ModelMapper modelMapper)
                               {

        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
                               }

    @Override
    public void initializeDatabase() {

        if (studentRepository.count() == 0) {

            Student student1 = new Student();
            student1.setName("Sand").setAge(40);

            Student student2 = new Student();
            student2.setName("Mimi").setAge(36);

            studentRepository.saveAll(List.of(student1, student2));

            Course course1 = new Course();
            course1.setName("Spring database").setPrice(new BigDecimal(500));

            Course course2 = new Course();
            course2.setName("Spring advance").setPrice(new BigDecimal(450));

            Course course3 = new Course();
            course3.setName("Java advanced").setPrice(new BigDecimal(400));

            courseRepository.saveAll(List.of(course1, course2, course3));

            Order order1 = new Order();
            order1.setStudent(student1);
            order1.setCourse(course1);

            Order order2 = new Order();
            order2.setStudent(student1);
            order2.setCourse(course2);

            Order order3 = new Order();
            order3.setStudent(student2);
            order3.setCourse(course3);

            orderRepository.saveAll(List.of(order1, order2, order3));
        }
    }

    @Override
    public StudentDTO getStudent(Long studentsId) {

        StudentDTO model = studentRepository
                .findById(studentsId)
                .map(s -> modelMapper.map(s, StudentDTO.class)).orElse(null);

        return model;
    }

    @Override
    public StudentDTO updateStudent(Long id, StudentDTO studentDTO) {

        return null;
    }

    @Override
    public List<StudentDTO> getAllStudents() {

        return studentRepository.findAll().stream().map(s -> modelMapper.map(s, StudentDTO.class)).toList();
    }
}
