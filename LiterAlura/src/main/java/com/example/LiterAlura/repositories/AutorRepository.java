package com.example.LiterAlura.repositories;

import com.example.LiterAlura.entities.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {
}
