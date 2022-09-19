package es.tfg.gesform.persistencia.dao;

import java.util.List;

import es.tfg.gesform.persistencia.vo.FormulariosBloquesVO;


/**
 * DAO para acceder a la tabla FORMULARIOS_BLOQUES.
 */
public interface FormulariosBloquesDAO {



//	FormulariosVO selectByPrimaryKey(long id);
//
//	void insertar(FormulariosVO obj)  throws Exception;
//	
//	void actualizar(FormulariosVO obj);
//
//	List<FormulariosVO> obtenerTodosFormularios(Integer limit);
	
	Long insertarBloquePlantilla(FormulariosBloquesVO obj) throws Exception;
	List<FormulariosBloquesVO>  obtenerBloquesFormulario(long idForm);
	List<FormulariosBloquesVO> obtenerListaBloquesFormulario(long idForm);

//	List<ClientesVO> findByExample(ClientesVO cliente);
//
//	void update(ClientesVO cliente);
//
//	void delete(ClientesVO cliente);

}

