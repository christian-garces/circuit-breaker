package com.gonzalez.carro.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.gonzalez.carro.entity.Carro;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/detalle-carro")
public class CarroController {

	@Autowired
	@Lazy
    private RestTemplate restTemplate;

    public static final String DETALLE_CARRO = "detalleCarro";

    private static final String BASEURL = "http://localhost:9191/carros";
    
    @GetMapping("/mostrarCarros")
    @CircuitBreaker(name = DETALLE_CARRO, fallbackMethod = "getExampleCars")
    public List<Carro> mostrarCarros(@RequestParam("marca") String marca) {
          String url = marca == "" ? BASEURL : BASEURL + "/" + marca;
          return restTemplate.getForObject(url, ArrayList.class);
      }

    public List<Carro> getExampleCars(Exception e){
        return Stream.of(
                new Carro(1,"Picanto", "Kia", "gris", 25000),
                new Carro(2,"Mustang", "Ford", "negro", 130000),
                new Carro(3,"Camaro", "Chevrolet", "amarillo", 125000),
                new Carro(4,"Logan", "Renault", "gris", 30000),
                new Carro(5,"Mini", "Cooper", "blanco", 60000),
                new Carro(6,"Beatle", "Volkswagen", "azul", 35000)
        ).collect(Collectors.toList());
    }
    
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
