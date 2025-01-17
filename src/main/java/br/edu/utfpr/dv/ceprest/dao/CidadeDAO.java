package br.edu.utfpr.dv.ceprest.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.edu.utfpr.dv.ceprest.model.Cidade;
import br.edu.utfpr.dv.ceprest.model.PesquisaCidade;

public class CidadeDAO {
	
	public List<Cidade> listar() throws SQLException{
		Statement stmt = Conexao.getInstance().getConexao().createStatement();
		ResultSet rs = stmt.executeQuery("SELECT city.*, state.title AS stateTitle, state.letter " +
				"FROM city INNER JOIN state ON state.id=city.id_state ORDER BY city.title");
		List<Cidade> list = new ArrayList<Cidade>();
		
		while(rs.next()){
			Cidade e = new Cidade();
			
			e.setId(rs.getInt("id"));
			e.setNome(rs.getString("title"));
			e.setDdd(rs.getInt("iso_ddd"));
			e.setPopulacao(rs.getInt("population"));
			e.getEstado().setId(rs.getInt("id_state"));
			e.getEstado().setNome(rs.getString("stateTitle"));
			e.getEstado().setSigla(rs.getString("letter"));
			
			list.add(e);
		}
		
		return list;
	}
	
	public List<Cidade> listarPorEstado(String sigla) throws SQLException{
		PreparedStatement stmt = Conexao.getInstance().getConexao().prepareStatement(
				"SELECT city.*, state.title AS stateTitle, state.letter " +
				"FROM city INNER JOIN state ON state.id=city.id_state " +
				"WHERE state.letter=? ORDER BY city.title");
		stmt.setString(1, sigla);
		ResultSet rs = stmt.executeQuery();
		List<Cidade> list = new ArrayList<Cidade>();
		
		while(rs.next()){
			Cidade e = new Cidade();
			
			e.setId(rs.getInt("id"));
			e.setNome(rs.getString("title"));
			e.setDdd(rs.getInt("iso_ddd"));
			e.setPopulacao(rs.getInt("population"));
			e.getEstado().setId(rs.getInt("id_state"));
			e.getEstado().setNome(rs.getString("stateTitle"));
			e.getEstado().setSigla(rs.getString("letter"));
			
			list.add(e);
		}
		
		return list;
	}

	public List<Cidade> listarPorEstadoPopulacao(String sigla) throws SQLException{
		PreparedStatement stmt = Conexao.getInstance().getConexao().prepareStatement(
				"SELECT city.*, state.title AS stateTitle, state.letter " +
				"FROM city INNER JOIN state ON state.id=city.id_state " +
				"WHERE state.letter=? ORDER BY city.population desc");
		stmt.setString(1, sigla);
		ResultSet rs = stmt.executeQuery();
		List<Cidade> list = new ArrayList<Cidade>();
		
		while(rs.next()){
			Cidade e = new Cidade();
			
			e.setId(rs.getInt("id"));
			e.setNome(rs.getString("title"));
			e.setDdd(rs.getInt("iso_ddd"));
			e.setPopulacao(rs.getInt("population"));
			e.getEstado().setId(rs.getInt("id_state"));
			e.getEstado().setNome(rs.getString("stateTitle"));
			e.getEstado().setSigla(rs.getString("letter"));
			
			list.add(e);
		}
		
		return list;
	}

	public List<Cidade> listarPorDdd(int ddd) throws SQLException{
		PreparedStatement stmt = Conexao.getInstance().getConexao().prepareStatement(
				"SELECT city.*, state.title AS stateTitle, state.letter " +
				"FROM city INNER JOIN state ON state.id=city.id_state " +
				"WHERE city.iso_ddd=? ORDER BY city.title");
		stmt.setInt(1, ddd);
		ResultSet rs = stmt.executeQuery();
		List<Cidade> list = new ArrayList<Cidade>();

		while(rs.next()){
			Cidade e = new Cidade();
			
			e.setId(rs.getInt("id"));
			e.setNome(rs.getString("title"));
			e.setDdd(rs.getInt("iso_ddd"));
			e.setPopulacao(rs.getInt("population"));
			e.getEstado().setId(rs.getInt("id_state"));
			e.getEstado().setNome(rs.getString("stateTitle"));
			e.getEstado().setSigla(rs.getString("letter"));
			
			list.add(e);
		}
		
		return list;
	}


	public List<Cidade> pesquisa(PesquisaCidade pesquisa) throws SQLException{
		PreparedStatement stmt = Conexao.getInstance().getConexao().prepareStatement(
				"SELECT city.*, state.title AS stateTitle, state.letter " +
				"FROM city INNER JOIN state ON state.id=city.id_state " +
				"WHERE state.letter=? and city.population>=? ORDER BY city.population desc");

		stmt.setString(1, pesquisa.getSigla());
		stmt.setInt(2, pesquisa.getMinPopulacao());
		ResultSet rs = stmt.executeQuery();
		List<Cidade> list = new ArrayList<Cidade>();

		while(rs.next()){
			Cidade e = new Cidade();
			
			e.setId(rs.getInt("id"));
			e.setNome(rs.getString("title"));
			e.setDdd(rs.getInt("iso_ddd"));
			e.setPopulacao(rs.getInt("population"));
			e.getEstado().setId(rs.getInt("id_state"));
			e.getEstado().setNome(rs.getString("stateTitle"));
			e.getEstado().setSigla(rs.getString("letter"));
			
			list.add(e);
		}
		
		return list;
	}
}
