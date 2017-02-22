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

import br.com.fa7.restaurante.business.FuncionarioBC;
import br.com.fa7.restaurante.business.UsuarioNaoEncontradoException;
import br.com.fa7.restaurante.business.ValidacaoException;
import br.com.fa7.restaurante.model.Funcionario;

@Path("funcionarios")
public class FuncionarioRS {
	@Inject
	private FuncionarioBC funcionarioBC;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Funcionario> listarTodos() {
		return funcionarioBC.listarTodos();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Funcionario obterPorId(@PathParam("id") Long id) {
		try {
			return funcionarioBC.obterPorId(id);

		} catch (UsuarioNaoEncontradoException e) {
			throw new NotFoundException();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response inserir(Funcionario body) {
		try {
			Long id = funcionarioBC.salvar(body);

			String url = "/api/funcionarios/" + id;

			return Response.status(Status.CREATED).header("Location", url).entity(id).build();

		} catch (ValidacaoException e) {
			return tratarValidacaoException(e);
		}
	}

	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response atualizar(@PathParam("id") Long id, Funcionario funcionario) {
		try {
			funcionario.setId(id);

			funcionarioBC.salvar(funcionario);

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
			Funcionario funcionario = funcionarioBC.remover(id);

			return Response.status(Status.OK).entity(funcionario).build();

		} catch (UsuarioNaoEncontradoException e) {
			throw new NotFoundException();
		}
	}

	private Response tratarValidacaoException(ValidacaoException e) {
		return Response.status(Status.NOT_ACCEPTABLE).entity(e.getErros()).build();
	}
}