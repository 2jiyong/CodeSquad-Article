package com.example.demo.comment;

import org.springframework.stereotype.Service;
import com.example.demo.article.ArticleRepository;
import com.example.demo.comment.dto.CommentRequest;
import com.example.demo.member.Member;

@Service
public class CommentService {
  private final CommentRepository commentRepository;
  private final ArticleRepository articleRepository;

  public CommentService(CommentRepository commentRepository, ArticleRepository articleRepository) {
    this.commentRepository = commentRepository;
    this.articleRepository = articleRepository;
  }

  private static final Member DEFAULT_MEMBER = Member.builder()
      .id(1L)
      .username("defaultUser")
      .password("defaultPassword")
      .build();

  public void addComment(CommentRequest commentRequest, Long id) {
    Comment comment = Comment.builder()
        .content(commentRequest.getContent())
        .author(DEFAULT_MEMBER)
        .article(articleRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Article not found with id: " + id)))
        .build();
    commentRepository.save(comment);
  }
}
