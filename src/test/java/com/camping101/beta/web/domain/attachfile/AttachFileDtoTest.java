package com.camping101.beta.web.domain.attachfile;

import static org.assertj.core.api.Assertions.assertThat;

import com.camping101.beta.db.entity.attachfile.AttachFile;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AttachFileDtoTest {

    @Test
    @DisplayName("dto로 attachFile을 만든다.")
    void toEntityTest() {
        // given
        AttachFileDto attachFileDto = new AttachFileDto("fileUid", "fileName",
            "filePath", 10);

        // when
        AttachFile attachFile = attachFileDto.toEntity();

        // then
        assertThat(attachFile).isEqualTo(
            new AttachFile("fileUid", "fileName",
            "filePath", 10)
        );
    }

}