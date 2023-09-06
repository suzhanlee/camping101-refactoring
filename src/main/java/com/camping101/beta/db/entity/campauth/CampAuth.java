package com.camping101.beta.db.entity.campauth;

import static com.camping101.beta.db.entity.campauth.CampAuthStatus.UNAUTHORIZED;

import com.camping101.beta.db.entity.BaseEntity;
import com.camping101.beta.db.entity.camp.Camp;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CampAuth extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "camp_auth_id")
    private Long campAuthId;

    @Enumerated(EnumType.STRING)
    private CampAuthStatus campAuthStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "camp_id")
    private Camp camp;

    public static CampAuth createCampAuth(Camp camp) {
        CampAuth campAuth = new CampAuth();
        campAuth.campAuthStatus = UNAUTHORIZED;
        campAuth.camp = camp;
        return campAuth;
    }

    public void editCampAuthStatus() {
        this.campAuthStatus = CampAuthStatus.AUTHORIZED;
    }

}
