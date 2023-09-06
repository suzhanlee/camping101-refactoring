package com.camping101.beta.web.domain.camp.model.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.camping101.beta.db.entity.camp.CampDetail;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CampDetailDtoTest {

    @Test
    @DisplayName("dto로 CampDetail을 만든다.")
    void toEntityTest(){
        // given
        CampDetailDto campDetailDto = new CampDetailDto("망치, 가위", "www.camping101.com");

        // when
        CampDetail campDetail = campDetailDto.toEntity();

        // then
        assertThat(campDetail).isEqualTo(new CampDetail("망치, 가위", "www.camping101.com"));
    }

}