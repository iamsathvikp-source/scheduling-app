package com.scheduling.scheduler.Shift;

import com.scheduling.scheduler.Employee.Employee;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Shift {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    @ManyToOne
    private Employee employee;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Shift(){
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
