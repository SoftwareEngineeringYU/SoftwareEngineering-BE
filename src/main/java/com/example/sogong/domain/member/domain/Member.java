package com.example.sogong.domain.member.domain;

import com.example.sogong.domain.address.domain.Address;
import com.example.sogong.domain.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Embedded
    private Address address;

    @Column(unique = true, nullable = false)
    private String email;

    private Instant lastLoginAt;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "member_role",
            joinColumns = @JoinColumn(name = "MEMBER_ID")
    )
    @OrderColumn
    @Convert(converter = MemberRoleConverter.class)
    private Set<MemberRole> roles = new HashSet<>();

    @Builder
    private Member(String password, String nickname, String email, Address address, Set<MemberRole> roles) {
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.address = address;
        this.roles = roles;
    }
}
