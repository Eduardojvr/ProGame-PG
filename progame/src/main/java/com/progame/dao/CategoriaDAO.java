package com.progame.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.progame.entity.*;

public class CategoriaDAO {

	public CategoriaDAO() {

	}

	public List<Categoria> todasCategorias() throws Exception {
		Connection db = ConnectionManager.getDBConnection();
		List<Categoria> arrayList = new ArrayList<Categoria>();

		PreparedStatement pstmt = null;
		ResultSet result = null;

		try {
			pstmt = db.prepareStatement("SELECT * FROM categoria");
			result = pstmt.executeQuery();

			while (result.next()) {
				Categoria categoria = new Categoria();
				categoria.setCategoria(result.getString("categoria"));
				arrayList.add(categoria);
			}

		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			if (pstmt != null)
				pstmt.close();
			db.close();
		}
		return arrayList;
	}

}
