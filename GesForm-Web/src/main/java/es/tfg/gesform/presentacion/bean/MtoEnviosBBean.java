package es.tfg.gesform.presentacion.bean;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import es.tfg.comun.constantes.Constantes;
import es.tfg.comun.utils.FacesUtils;
import es.tfg.comun.utils.Utilidades;
//import es.tfg.gesform.negocio.dto.FormulariosDTO;
import es.tfg.gesform.negocio.FormulariosEnviosService;
import es.tfg.gesform.negocio.FormulariosRespuestasService;
import es.tfg.gesform.negocio.dto.ContenidoFormularioDTO;
import es.tfg.gesform.negocio.dto.DepartamentosDTO;
import es.tfg.gesform.negocio.dto.FormulariosDTO;
import es.tfg.gesform.negocio.dto.FormulariosEnviosDTO;
import es.tfg.gesform.negocio.dto.ValoresTiposDTO;



@Component("mtoEnviosBBean")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class MtoEnviosBBean implements Serializable {

	@Autowired
	private FormulariosEnviosService enviosService;
	
	@Autowired
	private FormulariosRespuestasService respuestasService;
	


	private FormulariosEnviosDTO envioBuscar;
	private FormulariosEnviosDTO envioAnadir;
	private ContenidoFormularioDTO contenidoBuscar;
	private Date fechaEnvioDesde;
	private Date fechaEnvioHasta;
	



	private List<FormulariosEnviosDTO> listEnvios;
	private List<SelectItem> elementosFormulariosBuscar;
	
	//private long numEnvio; /*para la descarga de ficheros*/


	@PostConstruct
	public void onLoad() {
		try {

			
			envioBuscar = new FormulariosEnviosDTO();
			listEnvios = new ArrayList<FormulariosEnviosDTO>();
			elementosFormulariosBuscar = new ArrayList<SelectItem>();
			//elementosFormulariosBuscar = enviosService.obtenerFormulariosConEnvios();
			
			

//			tiposCodigosRespuesta = valoresTipoBo.obtenerValoresTipos(Constantes.TIPO_CATEGORIA_CODIGO_RESPUESTA);
		}catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Se ha producido un error al realizar el proceso"));
		}

	}


	public String irAEnvios(){

		try {
			
			envioBuscar = new FormulariosEnviosDTO();
			elementosFormulariosBuscar = new ArrayList<SelectItem>();
			elementosFormulariosBuscar = enviosService.obtenerFormulariosConEnvios();
			

			
//			userLoginBBean.obtenerDescripcionCaminoMigas(Constantes.CLAVE_ID_OPERACION_MANTENIMIENTOS_PARAMETROSGENERALES);
//			listEnvios = enviosService.obtenerTodosEnvios(Constantes.NUMERO_RESULTADOS_CONSULTA_SOLICITUDES);
		} catch (Exception e) {
			return Constantes.NAVEGACION_ERROR;
		}

		return Constantes.NAVEGACION_ENVIOS;

	}
	
	public void buscar() {
		try {	
				this.listEnvios = enviosService.obtenerEnviosFechasPorEjemplo(envioBuscar, fechaEnvioDesde, fechaEnvioHasta);
	
		}catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Se ha producido un error al realizar el proceso"));
		}
	}

	public String volver(){
		try {
			if (listEnvios == null || listEnvios.isEmpty()) {
				
			   buscar();
			}
			
		}catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Se ha producido un error al realizar el proceso"));
			return null;
		}

		return Constantes.NAVEGACION_ENVIOS;


	}
	
	public String cargarRespuestas(long idEnvio) {
		try {
			envioAnadir = enviosService.obtenerEnvioPorId(idEnvio);
			contenidoBuscar=respuestasService.obtenerRespuestasPorEnvio(envioAnadir);
		}catch (Exception e) {
			return Constantes.NAVEGACION_ERROR;
		}
		return Constantes.NAVEGACION_RESPUESTA; 
	}
	
	public void descargarFichero(String rutaRel){

		try {
			String rutaAbs = FacesUtils.getParameterConfg(Constantes.RUTA_FICHEROS);

			File file = new File(rutaRel);

			String fileName = file.getName();
			int pos = fileName.lastIndexOf(".");
//			if (pos > 0) {
//				fileName = fileName.substring(0, pos);
//			}

//			String resultado = respuestasService.abrirFichero(fileName, rutaAbs + rutaRel, Constantes.EXTENSION_PDF);
			String resultado = respuestasService.abrirFichero(fileName, rutaAbs + rutaRel);
			if(!Utilidades.esNullVacio(resultado)) {
				throw new Exception("Error en el método abrirFichero");
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Se ha producido un error al realizar el proceso"));
					//new FacesMessage(FacesMessage.SEVERITY_ERROR, "Constantes.JUSTIFICANTES_ERROR_RECUPERAR_DOCUMENTO", ""));
		}
	}



	public void todos() {
		this.envioBuscar = new FormulariosEnviosDTO();
		fechaEnvioDesde = null;
		fechaEnvioHasta = null;
		buscar();
	}

	public void limpiar(){
		this.envioBuscar = new FormulariosEnviosDTO();
	}


	public FormulariosEnviosService getEnviosService() {
		return enviosService;
	}


	public void setEnviosService(FormulariosEnviosService enviosService) {
		this.enviosService = enviosService;
	}


	public FormulariosEnviosDTO getEnvioBuscar() {
		return envioBuscar;
	}


	public void setEnvioBuscar(FormulariosEnviosDTO envioBuscar) {
		this.envioBuscar = envioBuscar;
	}

	
	public FormulariosEnviosDTO getEnvioAnadir() {
		return envioAnadir;
	}


	public void setEnvioAnadir(FormulariosEnviosDTO envioAnadir) {
		this.envioAnadir = envioAnadir;
	}


	public List<FormulariosEnviosDTO> getListEnvios() {
		return listEnvios;
	}


	public void setListEnvios(List<FormulariosEnviosDTO> listEnvios) {
		this.listEnvios = listEnvios;
	}


	public ContenidoFormularioDTO getContenidoBuscar() {
		return contenidoBuscar;
	}


	public void setContenidoBuscar(ContenidoFormularioDTO contenidoBuscar) {
		this.contenidoBuscar = contenidoBuscar;
	}


	public FormulariosRespuestasService getRespuestasService() {
		return respuestasService;
	}


	public void setRespuestasService(FormulariosRespuestasService respuestasService) {
		this.respuestasService = respuestasService;
	}


	public Date getFechaEnvioDesde() {
		return fechaEnvioDesde;
	}


	public void setFechaEnvioDesde(Date fechaEnvioDesde) {
		this.fechaEnvioDesde = fechaEnvioDesde;
	}


	public Date getFechaEnvioHasta() {
		return fechaEnvioHasta;
	}


	public void setFechaEnvioHasta(Date fechaEnvioHasta) {
		this.fechaEnvioHasta = fechaEnvioHasta;
	}


	public List<SelectItem> getElementosFormulariosBuscar() {
		return elementosFormulariosBuscar;
	}


	public void setElementosFormulariosBuscar(List<SelectItem> elementosFormulariosBuscar) {
		this.elementosFormulariosBuscar = elementosFormulariosBuscar;
	}


}
