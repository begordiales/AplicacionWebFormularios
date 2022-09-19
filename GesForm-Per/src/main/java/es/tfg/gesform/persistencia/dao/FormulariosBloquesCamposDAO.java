package es.tfg.gesform.persistencia.dao;

import java.util.List;

import es.tfg.gesform.persistencia.vo.FormulariosCamposVO;


/**
 * DAO para acceder a la tabla FORMULARIOS_BLOQUES.
 */
public interface FormulariosBloquesCamposDAO {



	FormulariosCamposVO selectByPrimaryKey(long id);
//
//	void insertar(FormulariosVO obj)  throws Exception;
//	
	void actualizarCampo(FormulariosCamposVO obj) throws Exception;
//
//	List<FormulariosVO> obtenerTodosFormularios(Integer limit);
	
	Long insertarBloqueCampoPlantilla(FormulariosCamposVO obj) throws Exception;
	
	List<FormulariosCamposVO> obtenerCamposBloque(long idBloque);

//	List<ClientesVO> findByExample(ClientesVO cliente);
//
//	void update(ClientesVO cliente);
//
//	void delete(ClientesVO cliente);

}

