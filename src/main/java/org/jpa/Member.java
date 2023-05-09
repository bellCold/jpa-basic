package org.jpa;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    private Long id;

    private String name;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
