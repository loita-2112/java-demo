package com.example.demo.DTO;

public record EmployeeDTO(
        Integer id,
        String email,
        String name,
        Integer roleId
        ) {
}