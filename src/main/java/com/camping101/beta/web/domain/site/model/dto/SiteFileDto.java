package com.camping101.beta.web.domain.site.model.dto;

import com.camping101.beta.db.entity.site.SiteFile;
import com.camping101.beta.web.domain.attachfile.AttachFileDto;
import java.util.Objects;
import lombok.Getter;

@Getter
public class SiteFileDto {

    private AttachFileDto attachFileDto;

    private String name;

    public SiteFileDto(AttachFileDto attachFileDto, String name) {
        this.attachFileDto = attachFileDto;
        this.name = name;
    }

    public SiteFile toEntity() {
        return SiteFile.create(attachFileDto.toEntity(), name);
    }
}
