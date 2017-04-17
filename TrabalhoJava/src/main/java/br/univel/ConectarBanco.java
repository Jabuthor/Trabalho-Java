package br.univel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConectarBanco {
	
	private Connection con;
	Usql sql = new Usql();
	Pessoa p = new Pessoa();
	
	public ConectarBanco(){
		
		
		//connectDB();
		//createTSQL();
		//handleSQL();
		//disconnectDB();
	}
	
	public void connectDB(){
		
		String url = "jdbc:postgresql://localhost:5432/postgres";
		String user = "postgres";
		String password = "123";
		
		boolean verifyCon = false;
		
		try {
			con = DriverManager.getConnection(url, user, password);
			verifyCon = true;
		} catch (SQLException e) {
			e.printStackTrace();
			disconnectDB();
		}
		
		if(verifyCon == false){
			System.out.println("Failed");
		}else{
			System.out.println("Connected");
		}
		
	}

	public void disconnectDB(){
		if(con != null){
			try {
				con.close();
				System.out.println("Disconnected");				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void createTSQL(){
		PreparedStatement ps;
		connectDB();
		String sq = sql.createNewTable();
		try {
			connectDB();
			ps = con.prepareStatement(sq);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disconnectDB();
		
		
		
	}
	public void handleSQL(String sql){
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void dropTable(Class<?> o){
		PreparedStatement ps;
		StringBuilder sb = new StringBuilder();
		String nomeTabela;
	      if (o.isAnnotationPresent(Tabela.class)) {

	        Tabela anotacaoTabela = o.getAnnotation(Tabela.class);
	        nomeTabela = anotacaoTabela.value();

	      } else {
	        nomeTabela = o.getSimpleName().toUpperCase();

	      }
	      sb.append("DROP TABLE ").append(nomeTabela).append(";");
	      handleSQL(sb.toString());

	}

}
