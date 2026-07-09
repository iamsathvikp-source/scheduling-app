package com.scheduling.scheduler.Employee;

/**
 * Unlike the employee creation, this class sends the response from the database where it can
 * use getId
 * @param id
 * @param name
 * @param email
 * @param role
 */
public record EmployeeResponse(Long id, String name, String email, String role) {
}