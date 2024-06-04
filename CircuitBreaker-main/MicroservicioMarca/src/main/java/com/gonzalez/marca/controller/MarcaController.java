package com.gonzalez.marca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gonzalez.marca.entity.Carro;
import com.gonzalez.marca.repository.CarroRepository;

@RestController
@RequestMapping("/carros")
public class MarcaController {

	@Autowired
    private CarroRepository carroRepository;
	
	@GetMapping
	public List<Carro> getCars(){
		return carroRepository.findAll();
	}
	@GetMapping("/{marca}")
	public List<Carro> getCarsByMarca(@PathVariable String marca){
		return carroRepository.findByMarca(marca);
	}
}