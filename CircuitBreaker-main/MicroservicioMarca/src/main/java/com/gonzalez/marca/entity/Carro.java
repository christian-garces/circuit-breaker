package com.gonzalez.marca.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Carro")
public class Carro {

	@Id
	@GeneratedValue
	private int id;
	private String nombre;
	private String marca;
	private String color;
	private int precio;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	
	public Carro(String nombre, String marca, String color, int precio) {
		this.nombre = nombre;
		this.marca = marca;
		this.color = color;
		this.precio = precio;
	}
	
	public Carro() {
		
	}
}
