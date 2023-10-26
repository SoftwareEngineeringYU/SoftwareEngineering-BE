package com.example.sogong.domain.member.domain;

import com.example.sogong.domain.common.BaseTimeEntity;

import com.example.sogong.domain.member.dto.request.MemberRequestDto;
import com.example.sogong.domain.order.domain.Order;

import com.example.sogong.domain.review.domain.Review;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.time.Instant;
import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;
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

    @Column(unique = true, nullable = false)
    private String email;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "member_role",
            joinColumns = @JoinColumn(name = "MEMBER_ID")
    )
    @OrderColumn
    @Convert(converter = MemberRoleConverter.class)
    private Set<MemberRole> roles = new HashSet<>();

    @OneToMany(mappedBy = "buyer")
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "")
    private List<Review> reviews = new ArrayList<>();


    @Builder
    protected Member(String password, String nickname, String email, Set<MemberRole> roles) {
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.roles = roles;
    }

    public void update(MemberRequestDto memberRequestDto) {
        this.nickname = memberRequestDto.getNickname();
        this.email = memberRequestDto.getEmail();
        this.password = memberRequestDto.getPassword();

    }
}
