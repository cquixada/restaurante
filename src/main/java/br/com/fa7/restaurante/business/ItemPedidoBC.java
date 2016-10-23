package br.com.fa7.restaurante.business;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import br.com.fa7.restaurante.dao.ItemPedidoDAO;
import br.com.fa7.restaurante.model.ItemPedido;

public class ItemPedidoBC {

	@Inject
	private ItemPedidoDAO dao;

	public List<ItemPedido> listarTodos() {
		return dao.listarTodos();
	}

	public ItemPedido obterPorId(Long idPedido) throws UsuarioNaoEncontradoException {
		ItemPedido pedido = dao.obterPorId(idPedido);

		if (pedido == null) {
			throw new UsuarioNaoEncontradoException();
		}

		return pedido;
	}

	public Long salvar(ItemPedido pedido) throws ValidacaoException {
		validar(pedido);

		dao.salvar(pedido);

		return pedido.getId();
	}

	public ItemPedido remover(Long idPedido) throws UsuarioNaoEncontradoException {
		ItemPedido pedido = obterPorId(idPedido);

		dao.remover(pedido);

		return pedido;
	}

	private void validar(ItemPedido Pedido) throws ValidacaoException {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<ItemPedido>> violations = validator.validate(Pedido);

		if (!violations.isEmpty()) {
			ValidacaoException validacaoException = new ValidacaoException();

			for (ConstraintViolation<ItemPedido> violation : violations) {
				String entidade = violation.getRootBeanClass().getSimpleName();
				String propriedade = violation.getPropertyPath().toString();
				String mensagem = violation.getMessage();
				validacaoException.adicionar(entidade, propriedade, mensagem);
			}

			throw validacaoException;
		}
	}
}
