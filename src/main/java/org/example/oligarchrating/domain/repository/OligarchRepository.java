package org.example.oligarchrating.domain.repository;

import org.example.oligarchrating.infrastructure.repository.model.Oligarch;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("NullableProblems")
public interface OligarchRepository extends JpaRepository<Oligarch, Long> {

    @Override
    @Transactional
    @Caching(evict = {
            @CacheEvict(value = "oligarchCache", key = "#oligarch.id"),
            @CacheEvict(value = "oligarchCache", key = "'all'")
    })
    Oligarch save(Oligarch oligarch);

    @Override
    @Cacheable(value = "oligarchCache", key = "#id")
    Optional<Oligarch> findById(Long id);

    @Override
    @Cacheable(value = "oligarchCache", key = "'all'")
    List<Oligarch> findAll();
}
