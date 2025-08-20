package com.retailpulse.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

@Document(collection = "reports")
public class ReportDocument {

    public  ReportDocument() {}

    public  ReportDocument(String title, String fileName, byte[] baos, Instant start, Instant end) {
        this.reportType = title;
        this.fileName = fileName;
        this.startDateTime = start;
        this.endDateTime = end;
        this.content = baos;
        this.createdAt = Instant.now();
    }

    @Id
    @Getter @Setter
    private String id;

    @Field("reportType")
    @Getter @Setter
    private String reportType;

    @Field("name")
    @Getter @Setter
    private String name;

    @Field("fileName")
    @Getter @Setter
    private String fileName;

    @Field("startDateTime")
    @Getter @Setter
    private Instant startDateTime;

    @Field("endDateTime")
    @Getter @Setter
    private Instant endDateTime;

    @CreatedDate
    @Field("createdAt")
    @Getter @Setter
    private Instant createdAt;

    @Field("content")
    @Getter @Setter
    private byte[] content;
}
