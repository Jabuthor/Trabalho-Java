package br.univel;

import java.lang.reflect.Field;
import java.math.BigDecimal;

import br.univel.tela.TelaPrincipal;

public class Usql {
	
	
public String createNewTable() {
	
	String strCreateTable = getCreateTable(Pessoa.class);
	return strCreateTable;
}
public String getCreateTable(Class<?> cl) {

  try {

    StringBuilder sb = new StringBuilder();

    {
      String nomeTabela;
      if (cl.isAnnotationPresent(Tabela.class)) {

        Tabela anotacaoTabela = cl.getAnnotation(Tabela.class);
        nomeTabela = anotacaoTabela.value();

      } else {
        nomeTabela = cl.getSimpleName().toUpperCase();

      }
      sb.append("CREATE TABLE ").append(nomeTabela).append(" (");
    }

    Field[] atributos = cl.getDeclaredFields();

    {
      for (int i = 0; i < atributos.length; i++) {

        Field field = atributos[i];

        String nomeColuna;
        String tipoColuna;

        if (field.isAnnotationPresent(Coluna.class)) {
          Coluna anotacaoColuna = field.getAnnotation(Coluna.class);

          if (anotacaoColuna.nome().isEmpty()) {
            nomeColuna = field.getName().toUpperCase();
          } else {
            nomeColuna = anotacaoColuna.nome();
          }

        } else {
          nomeColuna = field.getName().toUpperCase();
        }

        Class<?> tipoParametro = field.getType();

        if (tipoParametro.equals(String.class)) {
          tipoColuna = "VARCHAR(100)";

        } else if (tipoParametro.equals(int.class)) {
          tipoColuna = "INTEGER";

        } else if (tipoParametro.equals(BigDecimal.class)){
          tipoColuna = "NUMERIC(8,2)";
        } else {
        	tipoColuna = "DESCONHECIDO";
        }
        if (i > 0) {
          sb.append(",");
        }

        sb.append("\n\t").append(nomeColuna).append(' ').append(tipoColuna);
      }
    }


    sb.append("\n);");

    return sb.toString();

  } catch (SecurityException e) {
    throw new RuntimeException(e);
  }
}

	
	
	public String getSql(Object o){
		StringBuilder sb = new StringBuilder();
		try {
			sb.append("INSERT INTO " 
			+ getTableName(o)
			+ " (" 
			+ getAttributes(o) 
			+ ") VALUES (" 
			+ getValues(o)
			+ ");");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return sb.toString();
		
	}
	

	private String getValues(Object o) throws IllegalArgumentException, IllegalAccessException {
		
		Class<?> clazz = o.getClass();
		StringBuilder sb = new StringBuilder();
		
		
		int x = 0;
		for(Field f : clazz.getDeclaredFields()){
			if(x>0){
				sb.append(", ");
			}
			f.setAccessible(true);
			if(f.getType().equals(String.class)){
				sb.append("'" +f.get(o)+"'");
				x++;
			}else{
				sb.append(f.get(o));
				x++;
			}
		}

		return sb.toString();
	}

	private String getAttributes(Object o) {
		Class<?> clazz = o.getClass();
		StringBuilder sb = new StringBuilder();
		
		int x = 0;
		for(Field f : clazz.getDeclaredFields()){
			if(x>0){
				sb.append(", ");
			}
			Coluna anotacao = f.getAnnotation(Coluna.class);
			sb.append(anotacao.nome());
			x++;
		}

		return sb.toString();
	}

	private String getTableName(Object o) {
		return o.getClass().getSimpleName().toLowerCase();
	}

}
