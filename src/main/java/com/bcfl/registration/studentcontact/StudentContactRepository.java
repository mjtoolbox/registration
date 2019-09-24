package com.bcfl.registration.studentcontact;

import com.bcfl.registration.student.Student;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * [IMPORTANT]
 * findByxxx method where xxx must be a property of this entity. In this case, Payroll which defines @ManyToOne relationship.
 */
public interface StudentContactRepository extends PagingAndSortingRepository<StudentContact, Long> {
    StudentContact findByStudent(Student student);
}
