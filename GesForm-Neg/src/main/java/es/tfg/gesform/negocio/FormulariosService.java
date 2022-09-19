package es.tfg.gesform.negocio;

import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

import es.tfg.gesform.negocio.dto.ContenidoFormularioDTO;
import es.tfg.gesform.negocio.dto.FormulariosBloquesDTO;
import es.tfg.gesform.negocio.dto.FormulariosCamposDTO;
import es.tfg.gesform.negocio.dto.FormulariosDTO;



public interface FormulariosService {
//	ClientesVO buscar(Integer clienteNumber);

//	void insertar(ClientesVO cliente);

	public List<FormulariosDTO> obtenerTodosFormularios(int limit)  throws Exception;
	public Long insertar(FormulariosDTO obj) throws Exception;
	public void guardar(FormulariosDTO obj) ;
	public void borrar(FormulariosDTO obj) ;
	public FormulariosDTO obtenerFormularioPorId(long id) throws  Exception;
	public String obtenerNombreFormularioPorId(long id) throws Exception;
	public List<FormulariosDTO> obtenerFormulariosPorEjemplo(FormulariosDTO example) throws  Exception;
	public List<FormulariosDTO> obtenerFormulariosFechasPorEjemplo(FormulariosDTO example, Date fechaDesde, Date fechaHasta) throws  Exception;
	public ContenidoFormularioDTO obtenerContenidoPorIdForm(long idForm)throws  Exception;
	
	public FormulariosCamposDTO obtenerCampoPorId(long id) throws  Exception;
	public void anadirPlantilla(long id, String codPlantilla, String valor) throws Exception;
	public void guardarCampo(FormulariosCamposDTO obj) throws Exception;
	public void anadirBloque(long id, FormulariosBloquesDTO obj) throws Exception;
	public void anadirCampo(FormulariosCamposDTO obj) throws Exception;

	public List<SelectItem> obtenerListaBloquesPorForm(long id) throws Exception;
}