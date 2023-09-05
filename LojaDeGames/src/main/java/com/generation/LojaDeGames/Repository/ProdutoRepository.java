package com.generation.LojaDeGames.Repository;

import com.generation.LojaDeGames.Model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProdutoRepository extends JpaRepository <Produto,Long> {
    public List <Produto> findAllByNomeContainingIgnoreCase (@Param("nome")String nome);
}
