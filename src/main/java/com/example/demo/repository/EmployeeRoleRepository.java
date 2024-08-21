package com.example.demo.repository;

import com.example.demo.model.EmployeeRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRoleRepository extends JpaRepository<EmployeeRole, Integer> {
    EmployeeRole findEmployeeRoleById(Integer id);
}
