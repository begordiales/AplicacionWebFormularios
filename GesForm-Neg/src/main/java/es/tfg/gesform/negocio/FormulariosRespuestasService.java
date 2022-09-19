package es.tfg.gesform.negocio;

import java.util.List;

import javax.faces.application.FacesMessage;

import es.tfg.gesform.negocio.dto.ContenidoFormularioDTO;
import es.tfg.gesform.negocio.dto.FormulariosEnviosDTO;



public interface FormulariosRespuestasService {
//	ClientesVO buscar(Integer clienteNumber);

//	void insertar(ClientesVO cliente);

//	List<FormulariosDTO> obtenerTodosFormularios(int limit)  throws Exception;
	public List<String> validarRespuestas(ContenidoFormularioDTO respuestas) throws Exception;
	public void insertar(long idEnvio, ContenidoFormularioDTO respuestas) throws Exception;
	public ContenidoFormularioDTO obtenerRespuestasPorEnvio(FormulariosEnviosDTO envio) throws Exception;
	//public String abrirFichero(String nombreFichero, String rutaFichero, String extension);
	public String abrirFichero(String nombreFichero, String rutaFichero);
//	public void guardar(FormulariosDTO obj);
//	public FormulariosDTO obtenerFormularioPorId(long id) throws  Exception;
//	public ContenidoFormularioDTO obtenerContenidoPorIdForm(long idForm)throws  Exception;	
//	public void anadirPlantilla(long id, String codPlantilla) throws Exception;
}