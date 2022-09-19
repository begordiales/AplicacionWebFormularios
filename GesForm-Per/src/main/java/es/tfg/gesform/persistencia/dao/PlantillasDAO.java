package es.tfg.gesform.persistencia.dao;

import java.util.List;

import es.tfg.gesform.persistencia.vo.PlantillasVO;
import es.tfg.gesform.persistencia.vo.PlantillasCamposVO;
import es.tfg.gesform.persistencia.vo.PlantillasCamposOpcionesVO;

/**
 * DAO para acceder a la tabla Clientes.
 */
public interface PlantillasDAO {



//	FormulariosVO selectByPrimaryKey(long id);
//
//	void insertar(FormulariosVO obj)  throws Exception;
//	
//	void actualizar(FormulariosVO obj);
//
//	List<FormulariosVO> obtenerTodosFormularios(Integer limit);
	
	PlantillasVO obtenerPlantillaPorCodigo (String codigo);
	
	List<PlantillasCamposVO> obtenerCamposPlantillaPorCodigoPlantilla (String codigo);
	
	List<PlantillasCamposOpcionesVO> obtenerOpcionesCampoPorIdCampo(long idCampo);

//	List<ClientesVO> findByExample(ClientesVO cliente);
//
//	void update(ClientesVO cliente);
//
//	void delete(ClientesVO cliente);

}

