package com.example.demo.comment;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import com.example.demo.article.Article;
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

  public Slice<Comment> getCommentsByArticleId(Long articleId, int size, Long lastItemId) {
    Article article = articleRepository.findById(articleId)
        .orElseThrow(() -> new NotFoundException(ErrorCode.ARTICLE_NOT_FOUND));
    if (lastItemId == null) {
      return commentRepository.findAllByArticleOrderByIdDesc(article, PageRequest.of(0, size));
    }
    return commentRepository.findAllByIdLessThanAndArticleOrderByIdDesc(lastItemId, article,
        PageRequest.of(0, size));
  }
}
