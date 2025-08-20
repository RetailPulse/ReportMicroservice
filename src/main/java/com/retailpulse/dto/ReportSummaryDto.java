package com.retailpulse.dto;

import java.time.Instant;

public record ReportSummaryDto(
        String id,
        String reportType,
        String fileName,
        Instant startDateTime,
        Instant endDateTime,
        Instant createdAt
) {}
