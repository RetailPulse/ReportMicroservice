package com.retailpulse.domain.port;

import com.retailpulse.dto.ProductDto;

import java.util.List;

public interface ProductPort {
    List<ProductDto> fetchAllProducts();
}
