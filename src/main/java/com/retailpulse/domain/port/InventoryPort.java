package com.retailpulse.domain.port;

import com.retailpulse.dto.InventoryTransactionDto;
import com.retailpulse.dto.ProductResponseDto;

import java.util.List;

public interface InventoryPort {
    List<InventoryTransactionDto> fetchByDateRange(String start, String end);
    List<ProductResponseDto> fetchAllProducts();
}
