package br.com.fa7.restaurante.dao;

import java.util.List;

import org.hibernate.Query;

import br.com.fa7.restaurante.model.ItemPedido;

public class ItemPedidoDAO extends GenericDAO<ItemPedido> {

	public ItemPedidoDAO() throws Exception {
		super(ItemPedido.class);
	}

	@SuppressWarnings("unchecked")
	public List<ItemPedido> getItensPedido(Long idPedido) {
		String consulta = "from ItemPedido ip join fetch ip.especificacaoItem ei where ip.pedido.id = :idPedido";

		return session.createQuery(consulta).setParameter("idPedido", idPedido).list();
	}

	public void removerDoPedido(Long idPedido) {
		Query query = session.createQuery("delete from ItemPedido ip where ip.pedido.id = :idPedido");
		query.setParameter("idPedido", idPedido);
		query.executeUpdate();
	}

	public void removerDoPedidoExceto(Long idPedido, List<Long> itensAtuais) {
		if (itensAtuais != null && !itensAtuais.isEmpty()) {
			Query query = session.createQuery(
					"delete from ItemPedido ip where ip.pedido.id = :idPedido and ip.id not in (:itensAtuais)");
			query.setParameter("idPedido", idPedido);
			query.setParameterList("itensAtuais", itensAtuais);
			query.executeUpdate();
		}
	}
}
