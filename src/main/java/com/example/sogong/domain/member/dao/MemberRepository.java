package com.example.sogong.domain.member.dao;

import com.example.sogong.domain.member.domain.Member;
import com.example.sogong.domain.order.domain.Order;
import com.example.sogong.domain.review.domain.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>
{
    List<Order> findOrdersByMemberId(Long memberId, Pageable pageable);
    List<Review> findReviewsByMemberId(Long memberId, Pageable pageable);
}