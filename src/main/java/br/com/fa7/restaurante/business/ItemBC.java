package br.com.fa7.restaurante.business;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import br.com.fa7.restaurante.dao.ItemDAO;
import br.com.fa7.restaurante.model.EspecificacaoItem;

public class ItemBC {
	@Inject
	private ItemDAO dao;

	public List<EspecificacaoItem> listarTodos() {
		return dao.listarTodos();
	}

	public EspecificacaoItem obterPorId(Integer id) throws ItemNaoEncontradoException {
		EspecificacaoItem item = dao.obterPorId(id);

		if (item == null) {
			throw new ItemNaoEncontradoException();
		}

		return item;
	}

	public Integer salvar(EspecificacaoItem item) throws ValidacaoException {
		validar(item);

		dao.salvar(item);

		return item.getId();
	}

	public EspecificacaoItem remover(Integer id) throws ItemNaoEncontradoException {
		EspecificacaoItem item = obterPorId(id);

		dao.remover(item);

		return item;
	}

	private void validar(EspecificacaoItem item) throws ValidacaoException {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<EspecificacaoItem>> violations = validator.validate(item);

		if (!violations.isEmpty()) {
			ValidacaoException validacaoException = new ValidacaoException();

			for (ConstraintViolation<EspecificacaoItem> violation : violations) {
				String entidade = violation.getRootBeanClass().getSimpleName();
				String propriedade = violation.getPropertyPath().toString();
				String mensagem = violation.getMessage();
				validacaoException.adicionar(entidade, propriedade, mensagem);
			}

			throw validacaoException;
		}
	}
}
