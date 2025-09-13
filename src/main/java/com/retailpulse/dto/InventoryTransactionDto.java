package com.retailpulse.dto;

public record InventoryTransactionDto(String transactionId,
                                      ProductResponseDto product,
                                      ProductPricingDto productPricing,
                                      BusinessEntityDto source,
                                      BusinessEntityDto destination,
                                      String transactionDateTime
                                            ) {
}
