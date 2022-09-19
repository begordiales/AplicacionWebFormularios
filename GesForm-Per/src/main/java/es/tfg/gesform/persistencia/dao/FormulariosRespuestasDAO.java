package es.tfg.gesform.persistencia.dao;

import java.util.List;

import es.tfg.gesform.persistencia.vo.FormulariosRespuestasVO;



public interface FormulariosRespuestasDAO {



	FormulariosRespuestasVO selectByPrimaryKey(long id);

	void insertar(FormulariosRespuestasVO obj)  throws Exception;
	
	void actualizar(FormulariosRespuestasVO obj);
	
	FormulariosRespuestasVO obtenerRespuestaCampo (long idEnvio, long idCampo) throws Exception;
//
//	List<FormulariosVO> obtenerTodosFormularios(Integer limit);
	
//	Long insertarBloquePlantilla(Long idForm, PlantillasVO plantillavo) throws Exception;

//	List<ClientesVO> findByExample(ClientesVO cliente);
//
//	void update(ClientesVO cliente);
//
//	void delete(ClientesVO cliente);

}

