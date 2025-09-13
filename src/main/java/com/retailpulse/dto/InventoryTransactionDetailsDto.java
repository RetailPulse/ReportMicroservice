package com.retailpulse.dto;

public record InventoryTransactionDetailsDto(InventoryTransactionDto inventoryTransaction, ProductResponseDto product,
                                             BusinessEntityDto source, BusinessEntityDto destination) {
}
