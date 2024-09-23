package com.noman.ems.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Department Name is required")
    @Column(name="department_name")
    private String departmentName;

    @NotBlank(message = "Department Description can not be empty")
    @Size(min=15,message = "Department description must be more then 15 characters")
    @Column(name="department_description")
    private String departmentDescription;
}
