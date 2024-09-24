package com.noman.ems.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//use : transfer tha data between client and server
//will use it for response of REST API
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private Long departmentId;
}
