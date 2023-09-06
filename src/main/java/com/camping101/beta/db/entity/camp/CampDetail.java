package com.camping101.beta.db.entity.camp;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CampDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String equipmentTools;

    private String homepage;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "camp_id")
    private Camp camp;

    public CampDetail(String equipmentTools, String homepage) {
        this.equipmentTools = equipmentTools;
        this.homepage = homepage;
    }

    public static CampDetail create(String equipmentTools, String homepage) {
        CampDetail campDetail = new CampDetail();
        campDetail.equipmentTools = equipmentTools;
        campDetail.homepage = homepage;
        return campDetail;
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
        CampDetail that = (CampDetail) o;
        return Objects.equals(getEquipmentTools(), that.getEquipmentTools())
            && Objects.equals(getHomepage(), that.getHomepage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEquipmentTools(), getHomepage());
    }
}
