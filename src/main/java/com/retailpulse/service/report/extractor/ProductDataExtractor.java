package com.retailpulse.service.report.extractor;

import com.retailpulse.dto.ProductResponseDto;

public class ProductDataExtractor implements TableDataExtractor<ProductResponseDto> {
    @Override
    public Object[] getRowData(ProductResponseDto item, int serialNumber) {
        return new Object[]{
                serialNumber,
                item.sku(),
                item.description(),
                item.category(),
                item.subcategory(),
                item.brand()
        };
    }
}
