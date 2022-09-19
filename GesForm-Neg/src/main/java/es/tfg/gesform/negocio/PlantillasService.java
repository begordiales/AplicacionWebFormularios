package es.tfg.gesform.negocio;

import java.util.List;

import es.tfg.gesform.negocio.dto.PlantillasDTO;



public interface PlantillasService {

//	List<PlantillasDTO> obtenerTodasPlantillas(int limit)  throws Exception;
//	public Long insertar(PlantillasDTO obj) throws Exception;
//	public void guardar(PlantillasDTO obj);
//	public PlantillasDTO obtenerPlantillaPorId(long id) throws  Exception;
	public PlantillasDTO obtenerPlantillaPorCodigo(String codigo) throws Exception;
	
//	public void anadirPlantilla(long id, String codPlantilla) throws Exception;
}