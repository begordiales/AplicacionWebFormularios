package es.tfg.formularios.presentacion.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import es.tfg.comun.constantes.Constantes;
import es.tfg.comun.utils.Utilidades;
import es.tfg.gesform.negocio.FormulariosEnviosService;
import es.tfg.gesform.negocio.FormulariosRespuestasService;
import es.tfg.gesform.negocio.FormulariosService;
import es.tfg.gesform.negocio.dto.ContenidoFormularioDTO;



@Component("cargarFormularioBBean")
@Scope(WebApplicationContext.SCOPE_SESSION)
//@SessionScoped
public class CargarFormularioBBean implements Serializable {
	
	protected static final Logger log = Logger.getLogger(CargarFormularioBBean.class);

	private String formulario;


	@Autowired
	private FormulariosService formulariosService;
	
	@Autowired
	private FormulariosEnviosService formulariosEnviosService;
	
	@Autowired
	private FormulariosRespuestasService formulariosRespuestasService;


	private ContenidoFormularioDTO contenidoBuscar;
	
//	private String archivo;
	private byte[] contenido;
	
	private UploadedFile file;


	@PostConstruct
	public String cargarFormulario() throws Exception {
		System.out.println("ENTRO");
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		if (req.getParameter("IdForm")!=null) {
			int idForm = Integer.parseInt(Utilidades.sNoNull(req.getParameter("IdForm")));
//			int idOrigen = Integer.parseInt(Utilidades.sNoNull(req.getParameter("IdOrigen")));
			
			contenidoBuscar = new ContenidoFormularioDTO();
			
			
			try {
				this.contenidoBuscar = formulariosService.obtenerContenidoPorIdForm(idForm);
			}catch (Exception e) {
				String error = (e.getMessage() != null ? e.getMessage() : (e.getCause() != null && e.getCause().getMessage() != null ? e.getCause().getMessage() : "Indeterminado."));
				System.out.println (error);
	//			Utilidades.log_error(this.log, "--- ERROR: error indeterminado en el obtención del formulario. " + error);
			}
		}
		return Constantes.NAVEGACION_PUBLICA;
	}


	
	public void guardar() {
		
		System.out.println("ENTRO EN GUARDAR");	
		
		
		List<String> listaErrores = new ArrayList<String>();
		
		try {
			
			/***********PRUEBA SUBIDA FICHERO*****************/
//			this.saveUploadedFile();
/*
			String ruta = "";
			ruta = "C:/DOCGESFORM";
			String nombre = "";
			nombre = file.getFileName();
			contenido = IOUtils.toByteArray(file.getInputstream());
			FileOutputStream fos = new FileOutputStream(ruta+"/"+nombre);
			fos.write(contenido);
			fos.close();
*/
			/***********FIN PRUEBA SUBIDA FICHERO*************/
			//validaciones de datos enviados
			
			listaErrores = formulariosRespuestasService.validarRespuestas(contenidoBuscar);
				
				if (!listaErrores.isEmpty()) {
					//recorremos la lista y mostramos los mensajes de error
					for (String error : listaErrores) {
						FacesContext.getCurrentInstance().addMessage(
								null, 
								new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", error));	
					}
					
				}
				else {
					long idEnvio = formulariosEnviosService.insertar(contenidoBuscar.getIdForm());
					formulariosRespuestasService.insertar(idEnvio, contenidoBuscar);
					/****** SUBIR LOS FICHEROS SI HAY *********/
					System.out.println(idEnvio);
					System.out.println("FIN GUARDAR - COMPROBAR TABLA FORMULARIOS_RESPUESTAS");
					FacesContext.getCurrentInstance().addMessage(
							null, 
							new FacesMessage(FacesMessage.SEVERITY_INFO, "CORRECTO", "Formulario enviado correctamente"));
				}
			
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Se ha producido un error al realizar el proceso"));
		}
		//return Constantes.NAVEGACION_PUBLICA;
		
	}
	
/*	 public void handleFileUpload(FileUploadEvent event) {
		 
		 archivo = "";
		 UploadedFile file = event.getFile();
		 archivo = file.getFileName();
		 
		 try {
			contenido = IOUtils.toByteArray(file.getInputstream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  	
		 
//	        FacesMessage message = new FacesMessage("Successful", event.getFile().getFileName() + " is uploaded.");
//	        FacesContext.getCurrentInstance().addMessage(null, message);
	    }*/
	    
/**************	 
	 public void upload() {  
		 //JUAN IGNACIO

		        try {
		            
		            if (file != null && file.getContents() != null && file.getContents().length > 0) {

//		               String rutaManual = FacesUtils.getParameterConfig(Constantes.RUTA_MANUALES);
		               String ruta = "";
		               ruta = "C:/DOCGESFORM/";

		               moveFile(file.getInputstream(), ruta);

		               FacesMessage message = new FacesMessage("Correcto", file.getFileName() + " subido correctamente.");
		                FacesContext.getCurrentInstance().addMessage(null, message);
		            }else {
		                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "No se ha encontrado un fichero válido."));
		            }
//		            log_debug(Constantes.LOG_DEBUG_FIN_METODO, this.log);
		        } catch (Exception e) {
//		            if (super.logDebug) {                e.printStackTrace();            }
//		            Utilidades.log_error(this.log, null, "--- ERROR: Se ha producido un error: " + e.getMessage());
		            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Se ha producido un error a realizar el proceso"));
		        }
		    }
		 
//	        FacesMessage msg = new FacesMessage("Ok", "Fichero " + archivo.getFileName() + " subido correctamente.");
//	    	FacesContext.getCurrentInstance().addMessage(null, msg);
//	    }
	 
 
	 public Boolean moveFile(InputStream inputStream, String ruta) throws Exception {

	       OutputStream outputStream = null;
	        Boolean flag = false;
	        try {
	            File dir = new File(ruta);



	           if (!dir.exists())
	                dir.mkdir();

	           outputStream = new FileOutputStream(new File(ruta+"prueba"));
	           int read = 0;
	            byte[] bytes = new byte[1024];



	           while ((read = inputStream.read(bytes)) != -1) {
	                outputStream.write(bytes, 0, read);
	            }



	           flag = true;
	        } catch (IOException e) {
	            flag = false;
	            throw e;
	        } finally {
	            if (inputStream != null) {
	                try {
	                    inputStream.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	            if (outputStream != null) {
	                try {
	                    // outputStream.flush();
	                    outputStream.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	        return flag;
	    }
*****************/
	public FormulariosService getFormulariosService() {
		return formulariosService;
	}



	public void setFormulariosService(FormulariosService formulariosService) {
		this.formulariosService = formulariosService;
	}



	public ContenidoFormularioDTO getContenidoBuscar() {
		return contenidoBuscar;
	}



	public void setContenidoBuscar(ContenidoFormularioDTO contenidoBuscar) {
		this.contenidoBuscar = contenidoBuscar;
	}



	public String getFormulario() {
		return formulario;
	}



	public void setFormulario(String formulario) {
		this.formulario = formulario;
	}



//	public String getArchivo() {
//		return archivo;
//	}
//
//
//
//	public void setArchivo(String archivo) {
//		this.archivo = archivo;
//	}



	public UploadedFile getFile() {
		return file;
	}



	public void setFile(UploadedFile file) {
		this.file = file;
	}


	
	


//	public String getArchivo() {
//		return archivo;
//	}
//
//
//
//	public void setArchivo(String archivo) {
//		this.archivo = archivo;
//	}









	
}
