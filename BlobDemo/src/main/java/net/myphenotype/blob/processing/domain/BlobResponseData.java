package net.myphenotype.blob.processing.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class BlobResponseData {

    private Integer blobKey;
    private String blobDescription;
    private String blobData;

    public BlobResponseData() {
    }
}
