package com.example.demo.comment;

import org.springframework.stereotype.Service;
import com.example.demo.article.ArticleRepository;
import com.example.demo.comment.dto.CommentRequest;
import com.example.demo.common.excepition.ErrorCode;
import com.example.demo.common.excepition.NotFoundException;
import com.example.demo.member.MemberRepository;

@Service
public class CommentService {
  private final CommentRepository commentRepository;
  private final ArticleRepository articleRepository;
  private final MemberRepository memberRepository;

  public CommentService(CommentRepository commentRepository, ArticleRepository articleRepository,
      MemberRepository memberRepository) {
    this.commentRepository = commentRepository;
    this.articleRepository = articleRepository;
    this.memberRepository = memberRepository;
  }

  public void addComment(CommentRequest commentRequest, Long id, Long userId) {
    Comment comment = Comment.builder()
        .content(commentRequest.getContent())
        .author(memberRepository.findById(userId)
            .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND)))
        .article(articleRepository.findById(id)
            .orElseThrow(() -> new NotFoundException(ErrorCode.ARTICLE_NOT_FOUND)))
        .build();
    commentRepository.save(comment);
  }
}
