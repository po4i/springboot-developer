package me.leetaeyeon.springbootdeveloper.repository;

import me.leetaeyeon.springbootdeveloper.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article,Long> {
    
}
