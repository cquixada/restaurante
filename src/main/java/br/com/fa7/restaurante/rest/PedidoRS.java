package br.com.fa7.restaurante.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
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
import br.com.fa7.restaurante.business.UsuarioNaoEncontradoException;
import br.com.fa7.restaurante.business.ValidacaoException;
import br.com.fa7.restaurante.model.EspecificacaoItem;
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
			List<EspecificacaoItem> itens = new ArrayList<>();
			itemPedidoBC.getItensPedido(id).forEach(itemPedido ->{
				itens.add(itemPedido.getEspecificacaoItem());
			});
			Pedido pedido = pedidoBC.obterPorId(id);
			EspecificacaoItem[] listaItens = itens.toArray(new EspecificacaoItem[0]);
			pedido.setItens(listaItens);
			return pedido;

		} catch (UsuarioNaoEncontradoException e) {
			throw new NotFoundException();
		} catch (Exception e) {			
			e.printStackTrace();
			return null;
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response inserir(Pedido pedido) {
		try {
			pedido.setDataHora(new Date(Calendar.getInstance().getTimeInMillis()));
			pedido.setStatusPedido(StatusPedido.Aberto);			

			Long id = pedidoBC.salvar(pedido);
			for(EspecificacaoItem item : pedido.getItens()){
				ItemPedido itemPedido = new ItemPedido();
				itemPedido.setEspecificacaoItem(item);
				itemPedido.setPedido(pedido);
				itemPedido.setQuantidade(1);
				itemPedidoBC.salvar(itemPedido);
			}
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
	public Response atualizar(@PathParam("id") Long id, Pedido pedido) {
		try {
			pedido.setId(id);
			pedido.setDataHora(new Date(Calendar.getInstance().getTimeInMillis()));
			
			for(EspecificacaoItem item : pedido.getItens()){
				ItemPedido itemPedido = new ItemPedido();
				itemPedido.setEspecificacaoItem(item);
				itemPedido.setPedido(pedido);
				itemPedido.setQuantidade(1);
				itemPedidoBC.salvar(itemPedido);
			}
			pedidoBC.salvar(pedido);
			return Response.status(Status.OK).entity(id).build();

		} catch (ValidacaoException e) {
			return tratarValidacaoException(e);
		}
	}

	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response remover(@PathParam("id") Long id) {
		try {
			for(ItemPedido item : itemPedidoBC.listarTodos()){
				if(item.getPedido().getId().longValue() == id.longValue()){
					itemPedidoBC.remover(item.getId());
				}
			}
			Pedido pedido = pedidoBC.remover(id);
			return Response.status(Status.OK).entity(pedido).build();

		} catch (UsuarioNaoEncontradoException e) {
			throw new NotFoundException();
		}
	}

	private Response tratarValidacaoException(ValidacaoException e) {
		return Response.status(Status.NOT_ACCEPTABLE).entity(e.getErros()).build();
	}
}
