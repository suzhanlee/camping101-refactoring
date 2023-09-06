package com.camping101.beta.db.entity.member;

import static com.camping101.beta.db.entity.member.status.MemberStatus.IN_USE;
import static com.camping101.beta.db.entity.member.type.MemberType.OWNER;
import static com.camping101.beta.db.entity.member.type.SignUpType.GOOGLE;

import com.camping101.beta.db.entity.BaseEntity;
import com.camping101.beta.db.entity.attachfile.AttachFile;
import com.camping101.beta.db.entity.camp.Camp;
import com.camping101.beta.db.entity.member.status.MemberStatus;
import com.camping101.beta.db.entity.member.type.MemberType;
import com.camping101.beta.db.entity.member.type.SignUpType;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.AuditOverride;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@AuditOverride(forClass = BaseEntity.class)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    private String phoneNumber;

    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SignUpType signUpType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberType memberType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberStatus memberStatus;

    private LocalDateTime deletedAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attachFile_id")
    private AttachFile profileImage;

    @OneToMany(mappedBy = "member")
    private List<Camp> camps = new ArrayList<>();

    public static Member createOwnerByGoogle(String email, String password, String phoneNumber, String nickname) {
        Member member = new Member();
        member.email = email;
        member.password = password;
        member.phoneNumber = phoneNumber;
        member.nickname = nickname;
        member.signUpType = GOOGLE;
        member.memberType = OWNER;
        member.memberStatus = IN_USE;
        return member;
    }

    public static Member createMockMember() {
        Member member = new Member();
        member.memberId = 1L;
        member.email = "wlscw@gmail.com";
        member.password = "1234";
        member.phoneNumber = "010-1234-5678";
        member.nickname = "suchan";
        member.signUpType = GOOGLE;
        member.memberType = OWNER;
        member.memberStatus = IN_USE;
        return member;
    }

    public void activateMember() {
        this.memberStatus = IN_USE;
    }

    public void addCamp(Camp camp) {
        if (camp.getMember() == null) {
            camp.addMember(this);
        }
        this.camps.add(camp);
    }
}
