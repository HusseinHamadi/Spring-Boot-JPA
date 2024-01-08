package com.github.HusseinHamadi.spring.data.jpa.tutorial.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "course")
public class CourseMaterial {

    @Id
    @SequenceGenerator(
            name = "course_material_sequence",
            sequenceName = "course_material_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_material_sequence"
    )
    private Long courseMaterialId;
    private String url;

    //courseMaterial doesn't exist without a course so we defined an instance of it.
    //it's a one to one mapping (i.e 1 course has 1 course material and vice versa)
    //@JoinColumn is the mapping, we named it course_id and we referenced it to the courseId
    //because we are creating a courseMaterial and passing a course without creating the course
    //then we cannot create the courseMaterial so we use cascade to persist without this obstacle
    //this will allow us create the course
    @OneToOne(
            cascade = CascadeType.ALL,
            //will create everything we need, fetchtype.eager will create everything(needed and not needed)
            fetch = FetchType.LAZY,
            //this means that when i create a course material it has to have a course associated to it, by default it's true
            optional = false
    )
    @JoinColumn(
            name = "course_id",
            referencedColumnName = "courseId"
    )
    private Course course;
}
