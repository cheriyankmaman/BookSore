package com.ckm.store.domain.repository;

import com.ckm.store.domain.dto.BookDetail;

import java.util.Optional;

public interface BookRepository {
    Optional<BookDetail> findByName(String name);
}
