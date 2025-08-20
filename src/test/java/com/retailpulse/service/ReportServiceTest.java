package com.retailpulse.service;

import com.retailpulse.domain.ReportDocument;
import com.retailpulse.domain.port.InventoryPort;
import com.retailpulse.domain.port.ProductPort;
import com.retailpulse.dto.BusinessEntityDto;
import com.retailpulse.dto.InventoryTransactionDto;
import com.retailpulse.dto.ProductDto;
import com.retailpulse.dto.ProductPricingDto;
import com.retailpulse.infrastructure.ReportDocumentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReportServiceTest {

    @Mock
    private InventoryPort inventoryPort;

    @Mock
    private ProductPort productPort;

    @Mock
    private ReportDocumentRepository reportDocumentRepository;

    @InjectMocks
    private ReportService reportService;

    @Test
    void testFindInventoryTransactions_ReturnsExpectedList() {
        Instant start = Instant.parse("2024-01-01T00:00:00Z");
        Instant end = Instant.parse("2024-01-02T00:00:00Z");
        List<InventoryTransactionDto> expected = List.of(
                new InventoryTransactionDto("T-001",
                        new ProductDto("SKU-100", "Product 100", "Category A", "Subcategory A", "Brand X"),
                        new ProductPricingDto(3, 100.5),
                        new BusinessEntityDto("Store A", "Store 1", "STORE"),
                        new BusinessEntityDto("Store B", "Store 2", "STORE"),
                        "2024-01-01T10:00:00Z"
                ),
                new InventoryTransactionDto("T-002",
                        new ProductDto("SKU-200", "Product 200", "Category B", "Subcategory B", "Brand Y"),
                        new ProductPricingDto(5, 10.5),
                        new BusinessEntityDto("Store F", "Store 5", "STORE"),
                        new BusinessEntityDto("Store G", "Store 7", "STORE"),
                        "2024-02-02T20:00:00Z"
                )
        );

        when(inventoryPort.fetchByDateRange(anyString(), anyString())).thenReturn(expected);

        List<InventoryTransactionDto> result = reportService.findInventoryTransactions(start, end);

        assertEquals(expected, result);
        verify(inventoryPort).fetchByDateRange(anyString(), anyString());
    }

    @Test
    void testFindAllProducts_ReturnsExpectedList() {
        List<ProductDto> expected = List.of(
                new ProductDto("SKU-100", "Product 100", "Category A", "Subcategory A", "Brand X"),
                new ProductDto("SKU-200", "Product 200", "Category B", "Subcategory B", "Brand Y")
        );

        when(productPort.fetchAllProducts()).thenReturn(expected);

        List<ProductDto> result = reportService.findAllProducts();

        assertEquals(expected, result);
        verify(productPort).fetchAllProducts();
    }

    @Test
    void testExportInventoryTransactionsReport_ExcelFormat_SavesReportAndSetsResponseHeaders() throws Exception {
        Instant start = Instant.parse("2024-01-01T00:00:00Z");
        Instant end = Instant.parse("2024-01-02T00:00:00Z");
        List<InventoryTransactionDto> data = List.of(
                new InventoryTransactionDto("T-001", null, null, null, null, "2024-01-01T10:00:00Z")
        );
        when(inventoryPort.fetchByDateRange(anyString(), anyString())).thenReturn(data);

        // Use Spring's MockHttpServletResponse for testing
        jakarta.servlet.http.HttpServletResponse response = new org.springframework.mock.web.MockHttpServletResponse();

        reportService.exportInventoryTransactionsReport(response, start, end, "excel");

        // Verify repository save is called
        verify(reportDocumentRepository).save(any(ReportDocument.class));

        // Optionally, assert response headers/content type
        assertEquals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", response.getContentType());
        assertTrue(response.getHeader("Content-Disposition").contains("attachment; filename="));
    }

    @Test
    void testExportProductReport_PdfFormat_SavesReportAndSetsResponseHeaders() throws Exception {
        List<ProductDto> data = List.of(
                new ProductDto("SKU-100", "Product 100", "Category A", "Subcategory A", "Brand X")
        );
        when(productPort.fetchAllProducts()).thenReturn(data);

        jakarta.servlet.http.HttpServletResponse response = new org.springframework.mock.web.MockHttpServletResponse();

        reportService.exportProductReport(response, "pdf");

        verify(reportDocumentRepository).save(any(ReportDocument.class));
        assertEquals("application/pdf", response.getContentType());
        assertTrue(response.getHeader("Content-Disposition").contains("attachment; filename="));
    }

}
