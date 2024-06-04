package com.gonzalez.marca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gonzalez.marca.entity.Carro;

public interface CarroRepository extends JpaRepository<Carro,Integer>{

	List<Carro> findByMarca(String marca);
}
