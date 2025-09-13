package com.retailpulse.infrastructure;

import com.retailpulse.domain.port.InventoryPort;
import com.retailpulse.dto.InventoryTransactionDto;
import com.retailpulse.dto.ProductResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InventoryFeignAdapter implements InventoryPort {

    private final InventoryFeignClient client;

    public InventoryFeignAdapter(InventoryFeignClient client) {
        this.client = client;
    }

    @Override
    public List<InventoryTransactionDto> fetchByDateRange(String start, String end) {
        return client.getTransactions(start, end);
    }

    @Override
    public List<ProductResponseDto> fetchAllProducts() {
        return client.getAllProducts();
    }

}
