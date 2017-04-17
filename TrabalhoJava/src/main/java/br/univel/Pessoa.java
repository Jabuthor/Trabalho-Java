package br.univel;

import java.math.BigDecimal;

public class Pessoa {
	
	@Coluna(nome="idade")
	int numero = 1;
	@Coluna(nome="nome")
	String nome = "denis";
	@Coluna(nome="peso")
	BigDecimal peso = new BigDecimal(65.0);


}
