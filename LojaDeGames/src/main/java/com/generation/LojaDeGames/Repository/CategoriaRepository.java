package com.generation.LojaDeGames.Repository;

import com.generation.LojaDeGames.Model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria,Long> {

    public List <Categoria> findAllByTipoContainingIgnoreCase (@Param("tipo")String tipo);
}
