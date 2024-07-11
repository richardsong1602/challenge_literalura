package com.example.LiterAlura.services;

import com.example.LiterAlura.entities.Autor;
import com.example.LiterAlura.entities.Libro;
import com.example.LiterAlura.repositories.AutorRepository;
import com.example.LiterAlura.repositories.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LiterAluraService {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LibroRepository libroRepository;

    public List<Autor> getAllAutores() {
        return autorRepository.findAll();
    }

    public Optional<Autor> getAutorById(Long id) {
        return autorRepository.findById(id);
    }

    public Autor saveAutor(Autor autor) {
        return autorRepository.save(autor);
    }

    public void deleteAutor(Long id) {
        autorRepository.deleteById(id);
    }

    public List<Libro> getAllLibros() {
        return libroRepository.findAll();
    }

    public Optional<Libro> getLibroById(Long id) {
        return libroRepository.findById(id);
    }

    public Libro saveLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    public void deleteLibro(Long id) {
        libroRepository.deleteById(id);
    }
}
