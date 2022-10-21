package net.myphenotype.blob.processing.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Blob;

@Data
@Entity
@Slf4j
@Component
@Table(name = "blob_table")
public class BlobData {

    @Id
    @GeneratedValue
    @Column(name = "blob_key")
    private Integer blobKey;
    @Column(name = "blob_description")
    private String blobDescription;
    @Column(name = "blob_data")
    private Blob blobData;

    public BlobData() {
    }

    public BlobData(Integer blobKey, String blobDescription, Blob blobData) {
        this.blobKey = blobKey;
        this.blobDescription = blobDescription;
        this.blobData = blobData;
    }
}
