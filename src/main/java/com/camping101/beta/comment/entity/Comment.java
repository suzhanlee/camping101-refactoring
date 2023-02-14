package com.camping101.beta.comment.entity;

import com.camping101.beta.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(value = {AuditingEntityListener.class})
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    Member member;

    private Long parentId;
    private boolean recommendYn;
    private String content;
    private long like;

    public void changeComment(Member member) {
        this.member = member;
        if (!member.getComments().contains(this)) {
            member.getComments().add(this);
        }
    }
}
