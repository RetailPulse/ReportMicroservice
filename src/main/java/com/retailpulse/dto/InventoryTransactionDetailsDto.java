package com.retailpulse.dto;

public record InventoryTransactionDetailsDto(InventoryTransactionDto inventoryTransaction, ProductDto product,
                                             BusinessEntityDto source, BusinessEntityDto destination) {
}
