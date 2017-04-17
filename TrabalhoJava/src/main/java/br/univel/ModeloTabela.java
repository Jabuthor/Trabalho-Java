package br.univel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.univel.Coluna;

public class ModeloTabela extends AbstractTableModel {
	List<Object> lista;
	String[][] matriz;

	public ModeloTabela(Object o){
		Class<?> p = o.getClass();
		ConectarBanco cb = new ConectarBanco();
		cb.searchAll(p);

	}
	
	public ModeloTabela(List<Object> alunos) {
		this.lista = alunos;
	}

	@Override
	public int getRowCount() {
		return lista.size();
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		Object a = lista.get(rowIndex);
		Class<?> c = a.getClass();
		switch (columnIndex) {
		case 0:
		case 1:
		case 2:
		}

		return "erro";
	}

	public List<Object> buscarTodos(String sql, Object obj) {
		Connection con = null;
		List lista = new ArrayList();
		PreparedStatement ps;
		int i;
		Class<?> c = obj.getClass();
		try {
			ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				i = 1;
				while(i < c.getDeclaredFields().length){
                   lista.add(rs.getObject(i));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;

	}
}
