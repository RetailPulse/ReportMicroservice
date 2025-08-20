package com.retailpulse.infrastructure;

import com.retailpulse.domain.port.ProductPort;
import com.retailpulse.dto.ProductDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductFeignAdapter implements ProductPort {
    private final ProductFeignClient client;

    public ProductFeignAdapter(ProductFeignClient client) {
        this.client = client;
    }

    @Override
    public List<ProductDto> fetchAllProducts() {
        return client.getAllProducts();
    }
}
