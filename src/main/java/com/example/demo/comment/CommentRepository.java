package com.example.demo.comment;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.article.Article;

public interface CommentRepository extends JpaRepository<Comment, Long> {
  Slice<Comment> findAllByIdLessThanAndArticleOrderByIdDesc(Long commentId, Article article,
      Pageable pageable);
  Slice<Comment> findAllByArticleOrderByIdDesc(Article article, Pageable pageable);
}
