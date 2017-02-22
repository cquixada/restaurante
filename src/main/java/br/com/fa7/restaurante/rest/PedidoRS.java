package br.com.fa7.restaurante.rest;

import java.util.ArrayList;
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

import br.com.fa7.restaurante.business.ItemPedidoBC;
import br.com.fa7.restaurante.business.PedidoBC;
import br.com.fa7.restaurante.business.PedidoNaoEncontradoException;
import br.com.fa7.restaurante.business.ValidacaoException;
import br.com.fa7.restaurante.model.ItemPedido;
import br.com.fa7.restaurante.model.Pedido;
import br.com.fa7.restaurante.model.StatusPedido;

@Path("pedidos")
public class PedidoRS {

	@Inject
	private PedidoBC pedidoBC;

	@Inject
	private ItemPedidoBC itemPedidoBC;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Pedido> listarTodos() {
		return pedidoBC.listarTodos();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Pedido obterPorId(@PathParam("id") Long id) {
		try {
			return pedidoBC.obterPorId(id);

		} catch (PedidoNaoEncontradoException e) {
			throw new NotFoundException(e);
		}
	}

	@GET
	@Path("itensPedido/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ItemPedido> obterItensPedido(@PathParam("id") Long id) {
		return itemPedidoBC.getItensPedido(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response inserir(Pedido pedido) {
		try {
			pedido.setStatusPedido(StatusPedido.Aberto);

			for (ItemPedido itemPedido : pedido.getItensPedido()) {
				itemPedido.setPedido(pedido);
			}

			Long id = pedidoBC.salvar(pedido);

			String url = "/api/pedidos/" + id;

			return Response.status(Status.CREATED).header("Location", url).entity(id).build();

		} catch (ValidacaoException e) {
			return tratarValidacaoException(e);
		}
	}

	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response atualizar(Pedido pedido) {
		try {
			List<Long> itensAtuais = new ArrayList<>();

			for (ItemPedido itemPedido : pedido.getItensPedido()) {
				if (itemPedido.getId() != null) {
					itensAtuais.add(itemPedido.getId());
				}

				itemPedido.setPedido(pedido);
			}

			// Remove os itens cancelados.
			itemPedidoBC.removerDoPedidoExceto(pedido.getId(), itensAtuais);

			// Atualiza os itens mantidos e inclui os novos.
			pedidoBC.salvar(pedido);

			return Response.status(Status.OK).entity(pedido.getId()).build();

		} catch (ValidacaoException e) {
			return tratarValidacaoException(e);
		}
	}

	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response remover(@PathParam("id") Long id) {
		Pedido pedido = null;

		try {
			pedido = pedidoBC.remover(id);

		} catch (PedidoNaoEncontradoException e) {
			new NotFoundException(e);
		}

		return Response.status(Status.OK).entity(pedido).build();
	}

	private Response tratarValidacaoException(ValidacaoException e) {
		return Response.status(Status.NOT_ACCEPTABLE).entity(e.getErros()).build();
	}
}
