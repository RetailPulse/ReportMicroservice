package com.retailpulse.domain.port;

import com.retailpulse.dto.InventoryTransactionDto;

import java.util.List;

public interface InventoryPort {
    List<InventoryTransactionDto> fetchByDateRange(String start, String end);
}
