package org.jpa.diomain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @Builder
    public Member(String username, Address address) {
        this.username = username;
        this.address = address;
    }

}