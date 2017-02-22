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

	public ItemPedido obterPorId(Long id) throws ItemNaoEncontradoException {
		ItemPedido pedido = dao.obterPorId(id);

		if (pedido == null) {
			throw new ItemNaoEncontradoException();
		}

		return pedido;
	}

	public void salvar(ItemPedido itemPedido) throws ValidacaoException {
		validar(itemPedido);

		dao.salvar(itemPedido);
	}

	public ItemPedido remover(Long id) throws ItemNaoEncontradoException {
		ItemPedido pedido = obterPorId(id);

		dao.remover(pedido);

		return pedido;
	}

	public void removerDoPedido(Long idPedido) {
		dao.removerDoPedido(idPedido);
	}

	public void removerDoPedidoExceto(Long idPedido, List<Long> itensAtuais) {
		dao.removerDoPedidoExceto(idPedido, itensAtuais);
	}

	public List<ItemPedido> getItensPedido(Long idPedido) {
		return dao.getItensPedido(idPedido);
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
