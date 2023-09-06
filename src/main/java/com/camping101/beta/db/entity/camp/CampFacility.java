package com.camping101.beta.db.entity.camp;

import com.camping101.beta.db.entity.BaseEntity;
import com.camping101.beta.db.entity.camp.enums.Facility;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CampFacility extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private Facility facility;

    @Column(nullable = false)
    private Integer facilityNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "camp_id")
    private Camp camp;

    public CampFacility(Facility facility, Integer facilityNum) {
        this.facility = facility;
        this.facilityNum = facilityNum;
    }

    public static CampFacility create(String facility, Integer facilityNum) {
        CampFacility campFacility = new CampFacility();
        campFacility.facility = Facility.valueOf(facility);
        campFacility.facilityNum = facilityNum;
        return campFacility;
    }

    public void addCamp(Camp camp) {
        this.camp = camp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CampFacility that = (CampFacility) o;
        return getFacility() == that.getFacility() && Objects.equals(getFacilityNum(),
            that.getFacilityNum()) && Objects.equals(getCamp(), that.getCamp());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFacility(), getFacilityNum(), getCamp());
    }
}
