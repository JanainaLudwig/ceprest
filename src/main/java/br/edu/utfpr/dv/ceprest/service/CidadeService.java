package br.edu.utfpr.dv.ceprest.service;

import java.sql.SQLException;
import java.util.List;

import br.edu.utfpr.dv.ceprest.dao.CidadeDAO;
import br.edu.utfpr.dv.ceprest.model.Cidade;
import br.edu.utfpr.dv.ceprest.model.Estado;
import br.edu.utfpr.dv.ceprest.model.PesquisaCidade;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/cidades")
public class CidadeService {

	@GET
	@Path("/listar")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Cidade> listar() {
		try {
			return new CidadeDAO().listar();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@GET
	@Path("/listar/{sigla}/populacao")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Cidade> listarPorPopulacao(@PathParam("sigla") String sigla) {
		try {
			return new CidadeDAO().listarPorEstadoPopulacao(sigla.toUpperCase());
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}	

	@GET
	@Path("/listar/ddd/{ddd}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Cidade> listarPorDdd(@PathParam("ddd") int ddd) {
		try {
			return new CidadeDAO().listarPorDdd(ddd);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/listar/{param}")
	public List<Cidade> listarPorEstado(@PathParam("param") String sigla) {
		try {
			return new CidadeDAO().listarPorEstado(sigla);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@POST
	@Path("/listar/porestado")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Cidade> listarPorEstadoPost(Estado estado) {
		try {
			return new CidadeDAO().listarPorEstado(estado.getSigla());
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	

	/**
	 * Exemplo: 
	 * curl -X POST -H "Content-type: application/json" -d "{\"sigla\": \"SC\", \"minPopulacao\": 100000}" http://localhost:8080/cidades/listar/pesquisa
	 * @param pesquisa
	 * @return
	 */
	@POST
	@Path("/listar/pesquisa")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Cidade> pesquisaCidade(PesquisaCidade pesquisa) {
		try {
			return new CidadeDAO().pesquisa(pesquisa);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
