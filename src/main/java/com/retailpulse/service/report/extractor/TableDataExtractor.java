package com.retailpulse.service.report.extractor;

public interface TableDataExtractor<T> {
    Object[] getRowData(T item, int serialNumber);
}
