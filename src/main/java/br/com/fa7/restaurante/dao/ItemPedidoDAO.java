package br.com.fa7.restaurante.dao;

import java.util.List;

import br.com.fa7.restaurante.model.ItemPedido;

public class ItemPedidoDAO extends GenericDAO<ItemPedido>{

	public ItemPedidoDAO() throws Exception {
		super(ItemPedido.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<ItemPedido> getItensPedidos(Long id) throws Exception{
    	String consulta = "from ItemPedido i where i.pedido.id = :id ";
    	return (List<ItemPedido>)super.session.createQuery(consulta).setParameter("id", id).list();
    }
}
