package com.scheduling.scheduler.Shift;

import java.time.LocalDateTime;

/**
 * @param id
 * @param startDate
 * @param endDate
 * @param employeeName
 */
public record ShiftResponse(Long id, LocalDateTime startDate, LocalDateTime endDate, String employeeName) {
}
