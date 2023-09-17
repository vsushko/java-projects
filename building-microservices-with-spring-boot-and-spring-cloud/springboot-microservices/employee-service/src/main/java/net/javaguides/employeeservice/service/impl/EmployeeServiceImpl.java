package net.javaguides.employeeservice.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javaguides.employeeservice.dto.APIResponseDto;
import net.javaguides.employeeservice.dto.DepartmentDto;
import net.javaguides.employeeservice.dto.EmployeeDto;
import net.javaguides.employeeservice.entity.Employee;
import net.javaguides.employeeservice.exception.EmailAlreadyExistsException;
import net.javaguides.employeeservice.exception.ResourceNotFoundException;
import net.javaguides.employeeservice.mapper.EmployeeMapper;
import net.javaguides.employeeservice.repository.EmployeeRepository;
import net.javaguides.employeeservice.service.APIClient;
import net.javaguides.employeeservice.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author vsushko
 */
@Slf4j
@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    // private RestTemplate restTemplate;

    // private WebClient webClient;

    private APIClient apiClient;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.MAPPER.mapToEmployee(employeeDto);

        Optional<Employee> optionalUser = employeeRepository.findByEmail(employeeDto.getEmail());

        if (optionalUser.isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists for Employee");
        }

        Employee savedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.MAPPER.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public APIResponseDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "id", employeeId)
        );
//        ResponseEntity<DepartmentDto> responseEntity =
//                restTemplate.getForEntity(
//                        "http://localhost:8080/api/departments/" + employee.getDepartmentCode(),
//                        DepartmentDto.class);

//        DepartmentDto departmentDto = webClient.get()
//                .uri("http://localhost:8080/api/departments/" + employee.getDepartmentCode())
//                .retrieve()
//                .bodyToMono(DepartmentDto.class)
//                .block();

        DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode()).getBody();

        EmployeeDto employeeDto = EmployeeMapper.MAPPER.mapToEmployeeDto(employee);

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);

        return apiResponseDto;
    }
}
