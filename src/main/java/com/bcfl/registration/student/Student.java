package com.bcfl.registration.student;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.bcfl.registration.guardian.Guardian;
import com.bcfl.registration.studentcontact.StudentContact;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "student", schema = "public")
public class Student implements Serializable {

    @Id
    @Column(name = "membership_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long membership_id;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private StudentContact studentContact;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "guardian_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private Guardian guardian;

    @Column(name = "guardian_id", insertable = false, updatable = false)
    private long guardian_id;

    @Column(name = "preferred_name")
    private String preferred_name;

    @Column(name = "legal_name")
    private String legal_name;

    @Column(name = "date_of_birth")
    private Date date_of_birth;

    @Column(name = "gender")
    private String gender;

    @Column(name = "membership_type")
    private String membership_type;

    @Column(name = "grade")
    private int grade;

    @Column(name = "date_of_registration")
    private Date date_of_registration;

    @Column(name = "school")
    private String school;

    @CreationTimestamp
    @Column(name = "last_update")
    @Setter(AccessLevel.NONE)
    private Date last_updated;

}
