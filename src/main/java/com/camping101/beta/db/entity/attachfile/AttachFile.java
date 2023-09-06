package com.camping101.beta.db.entity.attachfile;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AttachFile {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "attachFile_id")
    private Long id;

    @Column(nullable = false)
    private String fileUid;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String filePath;

    @Column(nullable = false)
    private Integer fileSize;

    public AttachFile(String fileUid, String fileName, String filePath, Integer fileSize) {
        this.fileUid = fileUid;
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }

    public static AttachFile create(String fileUid, String fileName, String filePath, Integer fileSize) {
        AttachFile attachFile = new AttachFile();
        attachFile.fileUid = fileUid;
        attachFile.fileName = fileName;
        attachFile.filePath = filePath;
        attachFile.fileSize = fileSize;
        return attachFile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AttachFile that = (AttachFile) o;
        return Objects.equals(getFileUid(), that.getFileUid()) && Objects.equals(
            getFileName(), that.getFileName()) && Objects.equals(getFilePath(),
            that.getFilePath()) && Objects.equals(getFileSize(), that.getFileSize());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFileUid(), getFileName(), getFilePath(), getFileSize());
    }
}
