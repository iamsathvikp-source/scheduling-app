package com.scheduling.scheduler.Shift;

import java.time.LocalDateTime;

/**
 * @param startDate
 * @param endDate
 * @param employeeId
 */
public record CreateShiftRequest(LocalDateTime startDate, LocalDateTime endDate, Long employeeId) {
}
