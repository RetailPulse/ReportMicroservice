package com.retailpulse.dto;

public record ProductResponseDto(Long id,
                                 String sku,
                                 String description,
                                 String category,
                                 String subcategory,
                                 String brand,
                                 String origin,
                                 String uom,
                                 String vendorCode,
                                 String barcode,
                                 double rrp,
                                 boolean active) {
}
