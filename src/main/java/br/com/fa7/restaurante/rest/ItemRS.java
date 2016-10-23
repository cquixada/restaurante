package br.com.fa7.restaurante.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.fa7.restaurante.business.ItemBC;
import br.com.fa7.restaurante.business.LojaNaoEncontradaException;
import br.com.fa7.restaurante.model.EspecificacaoItem;

@Path("itens")
public class ItemRS {
	@Inject
	private ItemBC itemBC;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<EspecificacaoItem> listarTodos() {
		return itemBC.listarTodos();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public EspecificacaoItem obterPorId(@PathParam("id") Long id) {
		try {
			return itemBC.obterPorId(id);

		} catch (LojaNaoEncontradaException e) {
			throw new NotFoundException();
		}
	}
}
