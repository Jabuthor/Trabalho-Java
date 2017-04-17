package br.univel;

public class Principal {
	
	public static void main(String[] args) {

		Pessoa p = new Pessoa();
		Usql sql = new Usql();
		
		
		ConectarBanco conect = new ConectarBanco();
		sql.createNewTable();
		System.out.println(sql.getSql(p));
		
	}
	

}
