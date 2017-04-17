package br.univel;

import java.math.BigDecimal;

public class Pessoa {
	
	@Coluna(nome="idade")
	int idade;
	@Coluna(nome="nome")
	String nome;
	@Coluna(nome="peso")
	BigDecimal peso = new BigDecimal(65.0);
	
	
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public BigDecimal getPeso() {
		return peso;
	}
	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}
	
	


}
