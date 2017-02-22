package br.com.fa7.restaurante.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.fa7.restaurante.business.ItemBC;
import br.com.fa7.restaurante.business.ItemNaoEncontradoException;
import br.com.fa7.restaurante.business.ValidacaoException;
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
	public EspecificacaoItem obterPorId(@PathParam("id") Integer id) {
		try {
			return itemBC.obterPorId(id);

		} catch (ItemNaoEncontradoException e) {
			throw new NotFoundException();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response inserir(EspecificacaoItem body) {
		try {
			Integer id = itemBC.salvar(body);

			String url = "/api/itens/" + id;

			return Response.status(Status.CREATED).header("Location", url).entity(id).build();

		} catch (ValidacaoException e) {
			return tratarValidacaoException(e);
		}
	}

	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response atualizar(@PathParam("id") Integer id, EspecificacaoItem item) {
		try {
			item.setId(id);

			itemBC.salvar(item);

			return Response.status(Status.OK).entity(id).build();

		} catch (ValidacaoException e) {
			return tratarValidacaoException(e);
		}
	}

	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response remover(@PathParam("id") Integer id) {
		try {
			EspecificacaoItem item = itemBC.remover(id);

			return Response.status(Status.OK).entity(item).build();

		} catch (ItemNaoEncontradoException e) {
			throw new NotFoundException(e);
		}
	}

	private Response tratarValidacaoException(ValidacaoException e) {
		return Response.status(Status.NOT_ACCEPTABLE).entity(e.getErros()).build();
	}
}
