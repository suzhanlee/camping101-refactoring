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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CampLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private String environment;

    @Column(nullable = false)
    private String addr1;

    @Column(nullable = false)
    private String addr2;

    @Column(nullable = false)
    private String latitude;

    @Column(nullable = false)
    private String longitude;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "camp_id")
    private Camp camp;

    public CampLocation(String environment, String addr1, String addr2, String latitude,
        String longitude) {
        this.environment = environment;
        this.addr1 = addr1;
        this.addr2 = addr2;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static CampLocation create(String environment, String addr1, String addr2,
        String latitude, String longitude) {
        CampLocation campLocation = new CampLocation();
        campLocation.environment = environment;
        campLocation.addr1 = addr1;
        campLocation.addr2 = addr2;
        campLocation.latitude = latitude;
        campLocation.longitude = longitude;
        return campLocation;
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
        CampLocation that = (CampLocation) o;
        return Objects.equals(getEnvironment(), that.getEnvironment())
            && Objects.equals(getAddr1(), that.getAddr1()) && Objects.equals(
            getAddr2(), that.getAddr2()) && Objects.equals(getLatitude(), that.getLatitude())
            && Objects.equals(getLongitude(), that.getLongitude());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEnvironment(), getAddr1(), getAddr2(), getLatitude(),
            getLongitude());
    }
}
