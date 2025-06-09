package com.example.demo.member;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
  Optional<Member> findById(Long id);
  Optional<Member> findByUsername(String username);
  boolean existsByUsername(String username);
  boolean existsByEmail(String email);
}
