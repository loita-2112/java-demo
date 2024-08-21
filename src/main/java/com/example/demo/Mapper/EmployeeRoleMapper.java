package com.example.demo.Mapper;


import com.example.demo.DTO.EmployeeDTO;
import com.example.demo.DTO.EmployeeRoleDTO;
import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmployeeRoleMapper {
    EmployeeRoleMapper INSTANCE = Mappers.getMapper(EmployeeRoleMapper.class);

    EmployeeRoleDTO toDTO(Employee employee);

    EmployeeRole toEntity(EmployeeRoleDTO employeeDTO);
}