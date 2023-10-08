package com.example.sogong.domain.member.repository;

import com.example.sogong.domain.member.domain.Member;
import com.example.sogong.domain.order.domain.Order;
import com.example.sogong.domain.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Order> findOrdersById(Long memberId, Pageable pageable);

    List<Review> findReviewsById(Long memberId, Pageable pageable);


    Optional<Member> findByEmail(String email);

    boolean existsByEmailOrNickname(String email, String nickname);

}
