package br.com.fa7.restaurante.business;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import br.com.fa7.restaurante.dao.FuncionarioDAO;
import br.com.fa7.restaurante.model.Funcionario;

public class FuncionarioBC {
	@Inject
	private FuncionarioDAO dao;

	public List<Funcionario> listarTodos() {
		return dao.listarTodos();
	}

	public Funcionario obterPorId(Long idFuncionario) throws UsuarioNaoEncontradoException {
		Funcionario funcionario = dao.obterPorId(idFuncionario);

		if (funcionario == null) {
			throw new UsuarioNaoEncontradoException();
		}

		return funcionario;
	}

	@Transactional
	public Long salvar(Funcionario funcionario) throws ValidacaoException {
		validar(funcionario);

		dao.salvar(funcionario);

		return funcionario.getId();
	}

	@Transactional
	public Funcionario remover(Long idFuncionario) throws UsuarioNaoEncontradoException {
		Funcionario funcionario = obterPorId(idFuncionario);

		dao.remover(funcionario);

		return funcionario;
	}

	private void validar(Funcionario funcionario) throws ValidacaoException {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Funcionario>> violations = validator.validate(funcionario);

		if (!violations.isEmpty()) {
			ValidacaoException validacaoException = new ValidacaoException();

			for (ConstraintViolation<Funcionario> violation : violations) {
				String entidade = violation.getRootBeanClass().getSimpleName();
				String propriedade = violation.getPropertyPath().toString();
				String mensagem = violation.getMessage();
				validacaoException.adicionar(entidade, propriedade, mensagem);
			}

			throw validacaoException;
		}
	}
}