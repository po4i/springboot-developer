package me.leetaeyeon.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.leetaeyeon.springbootdeveloper.domain.Article;
import me.leetaeyeon.springbootdeveloper.dto.AddArticleRequest;
import me.leetaeyeon.springbootdeveloper.dto.UpdateArticleRequest;
import me.leetaeyeon.springbootdeveloper.service.BlogService;
import me.leetaeyeon.springbootdeveloper.dto.ArticleResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BlogApiController {
    private final BlogService blogService;

    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request){
        Article savedArticle = blogService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }
    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles(){
        List<ArticleResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(articles);
    }

    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id){
        Article article = blogService.findById(id);
        return ResponseEntity.ok()
                .body(new ArticleResponse(article));
    }

    // 애너테이션 잘 쓸 것!! 잘못쓰면 실행 안됨
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable long id){
        blogService.delete(id);
        return ResponseEntity.ok()
                .build();
    }

    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable long id,
                                                 @RequestBody UpdateArticleRequest request){
        Article updatedArticle = blogService.update(id,request);
        return ResponseEntity.ok()
                .body(updatedArticle);
    }
}
