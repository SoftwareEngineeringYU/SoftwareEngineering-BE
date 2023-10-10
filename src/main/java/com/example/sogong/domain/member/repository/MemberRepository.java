package com.example.sogong.domain.member.repository;

import com.example.sogong.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("SELECT m FROM Member m LEFT JOIN FETCH m.roles WHERE m.email=:email")
    Optional<Member> findByEmailWithRoles(String email);

    boolean existsByEmailOrNickname(String email, String nickname);

}
