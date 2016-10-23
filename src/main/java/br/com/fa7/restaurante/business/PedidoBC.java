package br.com.fa7.restaurante.business;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import br.com.fa7.restaurante.dao.PedidoDAO;
import br.com.fa7.restaurante.model.Pedido;

public class PedidoBC {

	@Inject
	private PedidoDAO dao;

	public List<Pedido> listarTodos() {
		return dao.listarTodos();
	}

	public Pedido obterPorId(Long idPedido) throws UsuarioNaoEncontradoException {
		Pedido pedido = dao.obterPorId(idPedido);

		if (pedido == null) {
			throw new UsuarioNaoEncontradoException();
		}

		return pedido;
	}

	public Long salvar(Pedido pedido) throws ValidacaoException {
		validar(pedido);

		dao.salvar(pedido);

		return pedido.getId();
	}

	public Pedido remover(Long idPedido) throws UsuarioNaoEncontradoException {
		Pedido pedido = obterPorId(idPedido);

		dao.remover(pedido);

		return pedido;
	}

	private void validar(Pedido Pedido) throws ValidacaoException {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Pedido>> violations = validator.validate(Pedido);

		if (!violations.isEmpty()) {
			ValidacaoException validacaoException = new ValidacaoException();

			for (ConstraintViolation<Pedido> violation : violations) {
				String entidade = violation.getRootBeanClass().getSimpleName();
				String propriedade = violation.getPropertyPath().toString();
				String mensagem = violation.getMessage();
				validacaoException.adicionar(entidade, propriedade, mensagem);
			}

			throw validacaoException;
		}
	}
}
