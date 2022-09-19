package es.tfg.gesform.persistencia.dao;

import java.util.Date;
import java.util.List;

import es.tfg.gesform.persistencia.vo.FormulariosEnviosVO;

/**
 * DAO para acceder a la tabla FormulariosEnvios.
 */

public interface FormulariosEnviosDAO {



	FormulariosEnviosVO selectByPrimaryKey(long id);

	void insertar(FormulariosEnviosVO obj)  throws Exception;
	
	void actualizar(FormulariosEnviosVO obj);

	List<FormulariosEnviosVO> obtenerTodosEnvios(Integer limit);
	
	List<FormulariosEnviosVO> obtenerEnviosForm(long idForm);
	
	List<Object> obtenerFormulariosEnvios();
	
	List<FormulariosEnviosVO> obtenerEnviosFechasVOPorEjemplo(FormulariosEnviosVO example, Date fechaDesde, Date fechaHasta) throws Exception;
	
//	Long insertarBloquePlantilla(Long idForm, PlantillasVO plantillavo) throws Exception;

//	List<ClientesVO> findByExample(ClientesVO cliente);
//
//	void update(ClientesVO cliente);
//
//	void delete(ClientesVO cliente);

}

