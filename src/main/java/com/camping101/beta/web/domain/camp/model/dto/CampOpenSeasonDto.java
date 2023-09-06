package com.camping101.beta.web.domain.camp.model.dto;

import com.camping101.beta.db.entity.camp.CampOpenSeason;
import com.camping101.beta.db.entity.camp.enums.Season;
import lombok.Getter;

@Getter
public class CampOpenSeasonDto {

    private String season;

    public CampOpenSeasonDto(String season) {
        this.season = season;
    }

    public CampOpenSeason toEntity() {
        return CampOpenSeason.create(season);
    }
}
