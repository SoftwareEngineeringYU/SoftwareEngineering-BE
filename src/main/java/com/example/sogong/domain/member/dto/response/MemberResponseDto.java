package com.example.sogong.domain.member.dto.response;

import com.example.sogong.domain.member.domain.Member;
import com.example.sogong.domain.order.domain.Order;
import com.example.sogong.domain.review.domain.Review;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Getter
public class MemberResponseDto {
    private String nickname;
    private String email;
    private List<Order> orders = new ArrayList<>();
    private List<Review> reviews = new ArrayList<>();

    public MemberResponseDto(Member member) {
        this.nickname = member.getNickname();
        this.email = member.getEmail();
        this.orders = member.getOrders();
        this.reviews = member.getReviews();
    }
}