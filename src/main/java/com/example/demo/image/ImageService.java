package com.example.demo.image;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.article.ArticleRepository;
import com.example.demo.common.excepition.ErrorCode;
import com.example.demo.common.excepition.NotFoundException;

@Service
public class ImageService {
  private final ImageRepository imageRepository;
  private final ArticleRepository articleRepository;

  public ImageService(ImageRepository imageRepository, ArticleRepository articleRepository) {
    this.imageRepository = imageRepository;
    this.articleRepository = articleRepository;
  }
  public Image uploadImage(Long id, MultipartFile imageFile, Long userId) {
    Image image = Image.builder()
        .imageBinaryData(convert(imageFile))
        .article(articleRepository.findById(id).orElseThrow(() -> new NotFoundException(
            ErrorCode.ARTICLE_NOT_FOUND)))
        .build();

    return imageRepository.save(image);
  }

  private byte[] convert(MultipartFile imageFile) {
    try {
      return imageFile.getBytes();
    } catch (Exception e) {
      throw new RuntimeException("Failed to convert image file to byte array", e);
    }
  }
}
