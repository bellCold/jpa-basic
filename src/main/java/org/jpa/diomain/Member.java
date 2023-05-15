package org.jpa.diomain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String username;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public Member(String username, Team team) {
        this.username = username;
    }

    // 연관관계 편의 메소드
    public void changeTeam(Team team) {
        if(this.team != null) {
            this.team.getMembers().remove(team);
        }
        this.team = team;
        team.getMembers().add(this);
    }
}
