package es.tfg.gesform.persistencia.dao;

import java.util.List;

import es.tfg.gesform.persistencia.vo.ValoresTiposVO;

/**
 * DAO para acceder a la tabla Clientes.
 */
public interface ValoresTiposDAO {


	ValoresTiposVO selectByPrimaryKey(long id);


	List<ValoresTiposVO> obtenerTodosActivos();
	List<ValoresTiposVO> obtenerValoresTiposPorCodBasica(String CodigoTipo);	
	
	
//
//	void insert(ClientesVO cliente);
//	List<ClientesVO> findByExample(ClientesVO cliente);
//
//	void update(ClientesVO cliente);
//
//	void delete(ClientesVO cliente);

}

