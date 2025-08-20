package com.retailpulse.dto;

public record ProductDto(String sku,
                         String description,
                         String category,
                         String subcategory,
                         String brand) {
}
