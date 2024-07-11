package com.example.LiterAlura.controllers;

import com.example.LiterAlura.entities.Autor;
import com.example.LiterAlura.entities.Libro;
import com.example.LiterAlura.services.LiterAluraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class LiterAluraController {

    @Autowired
    private LiterAluraService literAluraService;

    @GetMapping("/autores")
    public List<Autor> getAllAutores() {
        return literAluraService.getAllAutores();
    }

    @GetMapping("/autores/{id}")
    public Optional<Autor> getAutorById(@PathVariable Long id) {
        return literAluraService.getAutorById(id);
    }

    @PostMapping("/autores")
    public Autor createAutor(@RequestBody Autor autor) {
        return literAluraService.saveAutor(autor);
    }

    @DeleteMapping("/autores/{id}")
    public void deleteAutor(@PathVariable Long id) {
        literAluraService.deleteAutor(id);
    }

    @GetMapping("/libros")
    public List<Libro> getAllLibros() {
        return literAluraService.getAllLibros();
    }

    @GetMapping("/libros/{id}")
    public Optional<Libro> getLibroById(@PathVariable Long id) {
        return literAluraService.getLibroById(id);
    }

    @PostMapping("/libros")
    public Libro createLibro(@RequestBody Libro libro) {
        return literAluraService.saveLibro(libro);
    }

    @DeleteMapping("/libros/{id}")
    public void deleteLibro(@PathVariable Long id) {
        literAluraService.deleteLibro(id);
    }
}

