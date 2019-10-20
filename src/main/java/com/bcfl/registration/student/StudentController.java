package com.bcfl.registration.student;


import com.bcfl.registration.guardian.Guardian;
import com.bcfl.registration.guardian.GuardianRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@CrossOrigin()
@RestController
@Slf4j
public class StudentController {

    @Resource
    StudentRepository studentRepository;

    @Resource
    GuardianRepository guardianRepository;



    @GetMapping("/students")
    public List<Student> retrieveAllStudents() {
        return StreamSupport.stream(studentRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @GetMapping("/students/{membership_id}")
    public Student findById(@PathVariable long membership_id) {
        return studentRepository.findById(membership_id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + membership_id));
    }


    @PostMapping("/guardians/{guardian_id}/students")
    public Student createStudent(@PathVariable long guardian_id, @Valid @RequestBody Student student) {
        Guardian guardian = guardianRepository.findById(guardian_id)
                .orElseThrow(() -> new ResourceNotFoundException("Guardian not found with ID: " + guardian_id));

        student.setGuardian_id(guardian_id);
        student.setGuardian(guardian);

        return studentRepository.save(student);
    }





    // Update student by membership_id
    @PutMapping("/students/{membership_id}")
    public Student updateStudent(@PathVariable long membership_id, @Valid @RequestBody Student student) {
        Student studentFromDB = studentRepository.findById(membership_id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + membership_id));
        studentFromDB.setPreferred_name(student.getPreferred_name());
        studentFromDB.setLegal_name(student.getLegal_name());
        studentFromDB.setDate_of_birth(student.getDate_of_birth());
        studentFromDB.setGender(student.getGender());
        studentFromDB.setMembership_type(student.getMembership_type());
        studentFromDB.setGrade(student.getGrade());
        studentFromDB.setDate_of_registration(student.getDate_of_registration());
        studentFromDB.setSchool(student.getSchool());
        return studentRepository.save(studentFromDB);
    }

    @DeleteMapping("/students/{membership_id}")
    public void delete(@PathVariable long membership_id) {
        studentRepository.findById(membership_id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + membership_id));
        studentRepository.deleteById(membership_id);
    }
}
