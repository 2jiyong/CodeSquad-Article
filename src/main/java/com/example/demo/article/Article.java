package com.example.demo.article;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.example.demo.member.Member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Article {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 50)
  private String title;
  @Column(nullable = false)
  private String content;
  @ManyToOne(optional = false)
  @JoinColumn(name = "author", nullable = false)
  private Member author;
  @CreationTimestamp
  @Column(nullable = false, length = 20, updatable = false)
  private LocalDateTime createdAt;

  public void update(String title, String content) {
    this.title = title;
    this.content = content;
  }
}
