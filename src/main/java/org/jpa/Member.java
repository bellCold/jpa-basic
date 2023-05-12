package org.jpa;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
        uniqueConstraints =
        @UniqueConstraint(name = "unique_rrn", columnNames = {"RRN", "AGE"})
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    private Long id;

    @Column(name = "USERNAME", nullable = false)
    private String name;

    private int age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Column(name = "RRN", nullable = false)
    private String rrn;

    @Column(length = 50)
    @Lob
    private String script;

    @Column(updatable = false)
    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    @Transient
    private String temp;

    @Builder
    public Member(Long id, String name, int age, RoleType roleType, String rrn, String script, LocalDateTime createAt, LocalDateTime updateAt) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.roleType = roleType;
        this.rrn = rrn;
        this.script = script;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }
}
