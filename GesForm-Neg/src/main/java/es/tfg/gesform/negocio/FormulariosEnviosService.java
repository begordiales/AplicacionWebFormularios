package es.tfg.gesform.negocio;

import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

import es.tfg.gesform.negocio.dto.FormulariosDTO;
import es.tfg.gesform.negocio.dto.FormulariosEnviosDTO;



public interface FormulariosEnviosService {

	List<FormulariosEnviosDTO> obtenerTodosEnvios(int limit)  throws Exception;
	public FormulariosEnviosDTO obtenerEnvioPorId (long idEnvio) throws Exception;
	public long insertar(long idForm) throws Exception;
	public boolean tieneEnvios(long idForm) throws Exception;
	public List<SelectItem> obtenerFormulariosConEnvios();
//	public List<FormulariosEnviosDTO> obtenerEnviosPorEjemplo(FormulariosEnviosDTO example) throws  Exception;
	public List<FormulariosEnviosDTO> obtenerEnviosFechasPorEjemplo(FormulariosEnviosDTO example, Date fechaDesde, Date fechaHasta) throws  Exception;
//	public void guardar(FormulariosDTO obj);
//	public FormulariosDTO obtenerFormularioPorId(long id) throws  Exception;
//	public ContenidoFormularioDTO obtenerContenidoPorIdForm(long idForm)throws  Exception;	
//	public void anadirPlantilla(long id, String codPlantilla) throws Exception;
}