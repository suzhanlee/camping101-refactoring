package com.camping101.beta.web.domain.attachfile;

import com.camping101.beta.db.entity.attachfile.AttachFile;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class AttachFileDto {

    private String fileUid;

    private String fileName;

    private String filePath;

    private Integer fileSize;

    public AttachFileDto(String fileUid, String fileName, String filePath, Integer fileSize) {
        this.fileUid = fileUid;
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }

    public AttachFile toEntity() {
        return AttachFile.create(fileUid, fileName, filePath, fileSize);
    }
}
