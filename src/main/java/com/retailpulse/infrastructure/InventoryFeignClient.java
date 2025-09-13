package com.retailpulse.infrastructure;

import com.retailpulse.dto.InventoryTransactionDto;
import com.retailpulse.dto.ProductResponseDto;
import com.retailpulse.infrastructure.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/*
 * During stubbed development, point `url` to WireMock (e.g., http://localhost:9090).
 * Later switch to service discovery or the real base URL.
 */
@FeignClient(
        name = "inventoryClient",
        url = "${inventory.api.base-url}",
        configuration = FeignConfig.class
)
public interface InventoryFeignClient {

    @GetMapping("/api/inventoryTransaction")
    List<InventoryTransactionDto> getTransactions(
            @RequestParam("start") String start,
            @RequestParam("end") String end
    );

    @GetMapping("/api/products")
    List<ProductResponseDto> getAllProducts();

}
