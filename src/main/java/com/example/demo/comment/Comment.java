package com.example.demo.comment;

import java.time.LocalDateTime;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import com.example.demo.article.Article;
import com.example.demo.member.Member;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Comment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(optional = false)
  @JoinColumn(name = "author", nullable = false)
  private Member author;

  @ManyToOne(cascade = CascadeType.ALL, optional = false)
  @JoinColumn(name = "article", nullable = false)
  private Article article;

  @Column(nullable = false, length = 500)
  private String content;

  @CreationTimestamp
  @Column(nullable = false, length = 20, updatable = false)
  private LocalDateTime createdAt;
}
