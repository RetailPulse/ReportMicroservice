package com.retailpulse.infrastructure;

import com.retailpulse.domain.ReportDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ReportDocumentRepository extends MongoRepository<ReportDocument, String> {
    // Exclude the 'content' field when listing to avoid large payloads
    @Query(value = "{}", fields = "{ 'content': 0 }")
    List<ReportDocument> findAllWithoutContent();
}
