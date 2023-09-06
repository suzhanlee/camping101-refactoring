package com.camping101.beta.db.entity.attachfile;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AttachFileTest {

    @Test
    @DisplayName("attachFile을 만든다.")
    void createTest() {
        // given // when
        AttachFile attachFile =
            AttachFile.create("fileUid", "fileName", "filePath", 10);

        // then
        Assertions.assertThat(attachFile)
            .isEqualTo(new AttachFile("fileUid", "fileName", "filePath", 10));
    }

}