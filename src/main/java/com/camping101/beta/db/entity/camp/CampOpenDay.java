package com.camping101.beta.db.entity.camp;

import com.camping101.beta.db.entity.BaseEntity;
import com.camping101.beta.db.entity.camp.enums.DayOfTheWeek;
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
public class CampOpenDay extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private DayOfTheWeek dayOfTheWeek;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "camp_id")
    private Camp camp;

    public CampOpenDay(DayOfTheWeek dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public static CampOpenDay create(String dayOfTheWeek) {
        CampOpenDay campOpenDay = new CampOpenDay();
        campOpenDay.dayOfTheWeek = DayOfTheWeek.valueOf(dayOfTheWeek);
        return campOpenDay;
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
        CampOpenDay that = (CampOpenDay) o;
        return getDayOfTheWeek() == that.getDayOfTheWeek();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDayOfTheWeek());
    }
}
