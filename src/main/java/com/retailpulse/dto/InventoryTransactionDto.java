package com.retailpulse.dto;

public record InventoryTransactionDto(String transactionId,
                                      ProductDto product,
                                      ProductPricingDto productPricing,
                                      BusinessEntityDto source,
                                      BusinessEntityDto destination,
                                      String transactionDateTime
                                            ) {
}
