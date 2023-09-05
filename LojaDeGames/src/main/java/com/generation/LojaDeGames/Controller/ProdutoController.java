package com.generation.LojaDeGames.Controller;

import com.generation.LojaDeGames.Model.Produto;
import com.generation.LojaDeGames.Repository.CategoriaRepository;
import com.generation.LojaDeGames.Repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public ResponseEntity<List<Produto>> getAll() {
        return ResponseEntity.ok(produtoRepository.findAll());
    }



    @GetMapping("/id/{id}")
    public ResponseEntity<List<Produto>> getById(@PathVariable String id){
        ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(id));
        return null;
    }
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Produto>> getByNome (@PathVariable String nome){
        ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(nome));
        return null;
    }
    @PostMapping
    public ResponseEntity<Produto> post(@Valid@RequestBody Produto produto){
     if (categoriaRepository.existsById(produto.getCategoria().getId()))
         return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não existe", null);
    }

    @PutMapping
    public ResponseEntity<Produto> put(@Valid@RequestBody Produto produto){
        if (produtoRepository.existsById(produto.getId())){
            if (categoriaRepository.existsById(produto.getCategoria().getId()))
                return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produto));

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não existe", null);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete (@PathVariable Long id){
        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        produtoRepository.deleteById(id);
    }


}
