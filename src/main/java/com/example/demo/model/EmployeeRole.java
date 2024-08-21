package com.example.demo.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "employee_role")
public class EmployeeRole implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "role_name ")
    private String roleName;

    @OneToMany(mappedBy = "employeeRole", fetch = FetchType.LAZY)
    private List<Employee> employees;
}
