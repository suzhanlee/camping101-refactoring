package com.camping101.beta.db.entity.camp;

import static com.camping101.beta.db.entity.camp.enums.Facility.TOILET;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.camping101.beta.db.entity.camp.enums.Facility;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CampFacilityTest {

    @Test
    @DisplayName("CampFacility를 만든다.")
    void createTest(){
        // given // when
        CampFacility campFacility = CampFacility.create("TOILET", 3);

        // then
        assertThat(campFacility).isEqualTo(new CampFacility(TOILET, 3));
    }



}