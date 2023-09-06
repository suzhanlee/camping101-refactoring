package com.camping101.beta.web.domain.camp.model.dto;

import com.camping101.beta.db.entity.camp.CampOpenDay;
import com.camping101.beta.db.entity.camp.enums.DayOfTheWeek;
import lombok.Getter;

@Getter
public class CampOpenDayDto {

    private String dayOfTheWeek;

    public CampOpenDayDto(String dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public CampOpenDay toEntity() {
        return CampOpenDay.create(dayOfTheWeek);
    }
}
