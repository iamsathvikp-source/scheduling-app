package com.scheduling.scheduler.Employee;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA allows EmployeeRepository interface to use these methods
 * save
 * findById
 * findAll
 * deleteById
 * count
 * as it extends JpaRepository.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}