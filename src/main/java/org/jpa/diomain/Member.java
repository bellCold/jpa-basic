package org.jpa.diomain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false, length = 10)
    private String username;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "member_city")),
            @AttributeOverride(name = "street", column = @Column(name = "member_street")),
            @AttributeOverride(name = "zipcode", column = @Column(name = "member_zipcode"))
    })
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> order = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public Member(String username, Team team) {
        this.username = username;
        setTeam(team);
    }

    private void setTeam(Team team) {
        if (Objects.isNull(team)) {
            return;
        }
        if (Objects.nonNull(this.team)) {
            this.team.getMemberList().remove(this);
        }
        this.team = team;
        team.getMemberList().add(this);
    }
}