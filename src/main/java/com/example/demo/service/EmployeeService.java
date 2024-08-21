package com.example.demo.service;

import com.example.demo.DTO.EmployeeDTO;
import com.example.demo.DTO.EmployeeRoleDTO;
import com.example.demo.Mapper.EmployeeMapper;
import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeRole;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.EmployeeRoleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;

import java.util.List;

import static java.rmi.server.LogStream.log;

@Service
public class EmployeeService {
    private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);
    private final EmployeeRepository employeeRepo;
    private final EmployeeRoleRepository employeeRoleRepo;
    private final EmployeeMapper employeeMapper;

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepo, EmployeeRoleRepository employeeRoleRepo, EmployeeMapper employeeMapper) {
        this.employeeRepo = employeeRepo;
        this.employeeRoleRepo = employeeRoleRepo;
        this.employeeMapper = employeeMapper;
    }

    public void addEmployee(EmployeeDTO employeeDTO) {

        Employee employee = employeeMapper.toEntity(employeeDTO);
        // Kiểm tra nếu nhân viên đã tồn tại
        if (employeeRepo.existsById(employee.getId())) {
            throw new RuntimeException("Employee already exists with id " + employee.getId());
        }
        EmployeeRole currentEmployeeRoleDTO = employeeRoleRepo.findById(employeeDTO.roleId()).orElseThrow(()
                -> new EntityNotFoundException("Employee not found with ID: " + employeeDTO.roleId()));
        employee.setEmployeeRole(currentEmployeeRoleDTO);
        employee.setPassword("passworddefault");
        employeeRepo.save(employee);
    }

    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepo.findAll()
                .stream()
                .map(employeeMapper::toDTO)
                .toList();
    }

    public void updateEmployee(EmployeeDTO employeeDTO) {
        Employee employee = employeeRepo.findById(employeeDTO.id())
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + employeeDTO.id()));
        employee.setEmail(employeeDTO.email());
        employee.setName(employeeDTO.name());
        EmployeeRole currentEmployeeRoleDTO = employeeRoleRepo.findById(employeeDTO.roleId()).orElseThrow(()
                -> new EntityNotFoundException("Employee not found with ID: " + employeeDTO.roleId()));
        employee.setEmployeeRole(currentEmployeeRoleDTO);
        employeeRepo.save(employee);
    }

    public EmployeeDTO findEmployeeById(Integer employeeId) {
        Employee employee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with ID: " + employeeId));


        return employeeMapper.toDTO(employee);
    }

    public void deleteEmployee(Integer employeeId) {
        if (!employeeRepo.existsById(employeeId)) {
            throw new RuntimeException("Employee not found with id " + employeeId);
        }
        employeeRepo.deleteById(employeeId);
    }
}
