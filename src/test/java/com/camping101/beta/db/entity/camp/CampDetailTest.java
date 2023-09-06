package com.camping101.beta.db.entity.camp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CampDetailTest {

    @Test
    @DisplayName("CampDetail을 만든다.")
    void createTest(){
        // given // when
        CampDetail campDetail = CampDetail.create("망치, 가위", "www.camping101.com");

        // then
        assertThat(campDetail).isEqualTo(new CampDetail("망치, 가위", "www.camping101.com"));
    }

}