package es.tfg.gesform.persistencia.dao;

import java.util.List;

import es.tfg.gesform.persistencia.vo.FormulariosCamposOpcionesVO;


/**
 * DAO para acceder a la tabla FORMULARIOS_BLOQUES.
 */
public interface FormulariosBloquesCamposOpcionesDAO {



//	FormulariosVO selectByPrimaryKey(long id);
//
//	void insertar(FormulariosVO obj)  throws Exception;
//	
//	void actualizar(FormulariosVO obj);
//
//	List<FormulariosVO> obtenerTodosFormularios(Integer limit);
	
	void insertarBloqueCampoOpcionesPlantilla(FormulariosCamposOpcionesVO obj) throws Exception;
	
	List<FormulariosCamposOpcionesVO> obtenerOpcionesCampos(long idCampo);

//	List<ClientesVO> findByExample(ClientesVO cliente);
//
//	void update(ClientesVO cliente);
//
//	void delete(ClientesVO cliente);

}

