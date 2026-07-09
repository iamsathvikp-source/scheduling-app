package com.scheduling.scheduler.Shift;

import com.scheduling.scheduler.Employee.Employee;
import com.scheduling.scheduler.Employee.EmployeeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/shifts")
public class ShiftController {
    private final ShiftRepository shiftRepository;
    private final EmployeeRepository employeeRepository;

    public ShiftController(ShiftRepository shiftRepository, EmployeeRepository employeeRepository) {
        this.shiftRepository = shiftRepository;
        this.employeeRepository = employeeRepository;
    }

    @PostMapping
    public ResponseEntity<ShiftResponse> createShift(@RequestBody CreateShiftRequest createShiftRequest) {

        Optional<Employee> employeeById = employeeRepository.findById(createShiftRequest.employeeId());

        if (employeeById.isEmpty()){
            return ResponseEntity.notFound().build();
        } else {
            Shift shift = new Shift();
            shift.setStartDate(createShiftRequest.startDate());
            shift.setEndDate(createShiftRequest.endDate());
            shift.setEmployee(employeeById.get());
            shiftRepository.save(shift);
            ShiftResponse createdShift = new ShiftResponse(shift.getId(), shift.getStartDate(), shift.getEndDate(), shift.getEmployee().getName());
            return ResponseEntity.ok(createdShift);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<ShiftResponse> findShiftById(@PathVariable Long id) {

        Optional<Shift> findByIdShift = shiftRepository.findById(id);

        if (findByIdShift.isEmpty()){
            return ResponseEntity.notFound().build();
        } else {
            Shift shift = findByIdShift.get();
            ShiftResponse foundShift = new ShiftResponse(shift.getId(), shift.getStartDate(), shift.getEndDate(), shift.getEmployee().getName());
            return ResponseEntity.ok(foundShift);
        }
    }

    @GetMapping
    public List<ShiftResponse> findAllShifts() {
        List<Shift> shifts = shiftRepository.findAll();

        return  shifts.stream().map(
                shift -> new ShiftResponse(shift.getId(),
                        shift.getStartDate(),
                        shift.getEndDate(),
                        shift.getEmployee().getName())
        ).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShiftById(@PathVariable Long id) {

        Optional<Shift> findByIdShift = shiftRepository.findById(id);

        if (findByIdShift.isEmpty()){
            return ResponseEntity.notFound().build();
        } else {
            shiftRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShiftResponse> updateShiftById(@PathVariable Long id, @RequestBody CreateShiftRequest shiftRequest) {

        Optional<Shift> shiftById = shiftRepository.findById(id);
        Optional<Employee> employeeById = employeeRepository.findById(shiftRequest.employeeId());

        if (shiftById.isEmpty()|| employeeById.isEmpty()){
            return ResponseEntity.notFound().build();
        } else {
            Shift shift = shiftById.get();
            shift.setStartDate(shiftRequest.startDate());
            shift.setEndDate(shiftRequest.endDate());

            Employee employee = employeeById.get();
            shift.setEmployee(employee);
            shiftRepository.save(shift);

            ShiftResponse shiftResponse = new ShiftResponse(shift.getId(), shift.getStartDate(), shift.getEndDate(), shift.getEmployee().getName());
            return ResponseEntity.ok(shiftResponse);
        }

    }
}
