package br.com.fa7.restaurante.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.fa7.restaurante.business.LojaBC;
import br.com.fa7.restaurante.business.UsuarioNaoEncontradoException;
import br.com.fa7.restaurante.model.Loja;

@Path("lojas")
public class LojaRS {
	@Inject
	private LojaBC lojaBC;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Loja> listarTodos() {
		return lojaBC.listarTodos();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Loja obterPorId(@PathParam("id") Long id) {
		try {
			return lojaBC.obterPorId(id);

		} catch (UsuarioNaoEncontradoException e) {
			throw new NotFoundException();
		}
	}
}
