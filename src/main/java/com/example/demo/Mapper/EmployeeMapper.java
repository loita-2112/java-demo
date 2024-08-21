package com.example.demo.Mapper;

import com.example.demo.DTO.EmployeeDTO;
import com.example.demo.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    @Mapping(target = "roleId", source = "employeeRole.id")
    EmployeeDTO toDTO(Employee employee);

    @Mapping(target = "employeeRole.id", source = "roleId")
    Employee toEntity(EmployeeDTO employeeDTO);
}
