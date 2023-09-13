package com.camping101.beta.web.domain.site.model.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.camping101.beta.db.entity.attachfile.AttachFile;
import com.camping101.beta.db.entity.site.SiteFile;
import com.camping101.beta.web.domain.attachfile.AttachFileDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SiteFileDtoTest {

    @Test
    @DisplayName("dto로 SiteFile을 만들 수 있습니다.")
    void toEntityTest() {
        // given
        SiteFileDto siteFileDto = new SiteFileDto(
            new AttachFileDto("fileUid", "fileName", "filePath", 10), "mainImage");

        // when
        SiteFile siteFile = siteFileDto.toEntity();

        // then
        assertThat(siteFile).isEqualTo(
            new SiteFile(
                new AttachFile("fileUid", "fileName", "filePath", 10),
                "mainImage"
            )
        );
    }

}