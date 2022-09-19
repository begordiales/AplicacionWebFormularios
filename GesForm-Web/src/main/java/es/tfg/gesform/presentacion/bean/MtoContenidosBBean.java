package es.tfg.gesform.presentacion.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import es.tfg.comun.constantes.Constantes;
import es.tfg.gesform.negocio.FormulariosService;
import es.tfg.gesform.negocio.ValoresTiposService;
import es.tfg.gesform.negocio.dto.ContenidoFormularioDTO;
import es.tfg.gesform.negocio.dto.DepartamentosDTO;
import es.tfg.gesform.negocio.dto.FormulariosBloquesDTO;
import es.tfg.gesform.negocio.dto.FormulariosCamposDTO;
import es.tfg.gesform.negocio.dto.FormulariosDTO;
import es.tfg.gesform.negocio.dto.ValoresTiposDTO;

@Component("mtoContenidosBBean")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class MtoContenidosBBean implements Serializable {

	@Autowired
	private FormulariosService formulariosService;
	
	@Autowired
	private ValoresTiposService valoresTiposService;
	
	private FormulariosDTO formularioBuscar;
	private ContenidoFormularioDTO contenidoBuscar;
	private FormulariosBloquesDTO formularioBloqueAnadir;
	private FormulariosCamposDTO formularioCampoAnadir;
	private String valorCampo;
	private List<SelectItem> listBloques;
	private List<SelectItem> listTiposCampo;
	private List<SelectItem> listaOpciones;
	
	private boolean muestraCamposOpciones;
	private boolean muestraCamposLiteral;
	private String valorOpcion;
	private String labelOpcion;

	@PostConstruct
	public void onLoad() {
		try {			
			formularioBuscar = new FormulariosDTO();
			formularioBuscar.setDepartamento(new DepartamentosDTO());
			formularioBuscar.setTipoForm(new ValoresTiposDTO());
		
			contenidoBuscar = new ContenidoFormularioDTO();
			formularioBloqueAnadir = new FormulariosBloquesDTO();
			formularioCampoAnadir = new FormulariosCamposDTO();
			
			
			this.setValorCampo(null);
			this.setMuestraCamposLiteral(false);
			this.setMuestraCamposOpciones(false);
			
			listBloques = new ArrayList<SelectItem>();
			listTiposCampo = new ArrayList<SelectItem>();
			listaOpciones = new ArrayList<SelectItem>();

			
		}catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Se ha producido un error al realizar el proceso"));
		}

	}


	public void nuevoContenido() {
		try {
			this.setValorCampo(null);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Se ha producido un error al realizar el proceso"));
		}
	}
	
	public String irAContenidos(long idForm){
		
		try{
			limpiar();
			this.formularioBuscar = formulariosService.obtenerFormularioPorId(idForm);
			this.contenidoBuscar = formulariosService.obtenerContenidoPorIdForm(idForm);
			
			//obtener lista de bloques del formulario que no son plantillas
			
			this.listBloques = formulariosService.obtenerListaBloquesPorForm(idForm);
			this.listTiposCampo = valoresTiposService.obtenerListaValoresTipos(Constantes.TIPO_CAMPOS);
//			this.setValorCampo(null);
//			formularioBloqueAnadir = new FormulariosBloquesDTO();
//			formularioCampoAnadir = new FormulariosCamposDTO();

		}catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Se ha producido un error al realizar el proceso"));
		}


		return Constantes.NAVEGACION_CONTENIDOS;
	}


	public void anadirPlantilla(long idForm, String codigoPlantilla) {
		
		boolean loggedIn = false;
		try {
			formulariosService.anadirPlantilla(idForm,codigoPlantilla,valorCampo);
			this.setValorCampo(null);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "La operación se ha realizado correctamente"));
			loggedIn = true;
			
		}catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Se ha producido un error al realizar el proceso"));
		}
		PrimeFaces.current().ajax().addCallbackParam("loggedIn", loggedIn);
		this.irAContenidos(idForm);
	}
	
	public void nuevoBloque(){
		try {
			this.formularioBloqueAnadir = new FormulariosBloquesDTO();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Se ha producido un error al realizar el proceso"));
		}	
	}
	

	
	public void nuevoCampo(){
		try {
			this.formularioCampoAnadir = new FormulariosCamposDTO();
			this.setValorCampo(null);
			this.setValorOpcion(null);
			this.setLabelOpcion(null);
			listaOpciones = new ArrayList<SelectItem>();
			
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Se ha producido un error al realizar el proceso"));
		}	
	}
	
	
	public void anadirBloque(long idForm) {
		boolean loggedIn = false;
		try {
			this.formularioBloqueAnadir.setIdForm(idForm);
			//VALIDAR CAMPOS
			formulariosService.anadirBloque(idForm, formularioBloqueAnadir);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "La operación se ha realizado correctamente"));
			loggedIn = true;
			
		}catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Se ha producido un error al realizar el proceso"));
		}
		PrimeFaces.current().ajax().addCallbackParam("loggedIn", loggedIn);
		this.irAContenidos(idForm);
		
	}
	
	public void anadirCampo(long idForm) {
		//Le pasamos el idForm para volver
		boolean loggedIn = false;
		try {
			
			//VALIDAR CAMPOS
			 if (this.listaOpciones!=null) {
			    	formularioCampoAnadir.setListaopciones(listaOpciones);
			  }
			  if (this.valorCampo!=null) {
			    //añadir valor para el campo 
				  formularioCampoAnadir.setValor(valorCampo);
			  }
			formulariosService.anadirCampo(formularioCampoAnadir);
			//comprobamos si hay informacion adicional (valor para literal, opciones)
			String tipoCampo = this.valoresTiposService.obtenerCodBasicaPorId(formularioCampoAnadir.getIdTipocampo());
		   
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "La operación se ha realizado correctamente"));
			loggedIn = true;
			
		}catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Se ha producido un error al realizar el proceso"));
		}
		PrimeFaces.current().ajax().addCallbackParam("loggedIn", loggedIn);
		this.irAContenidos(idForm);
		
	}
	
	public void anadirOpcionLista() {
		SelectItem s = new SelectItem(this.getValorOpcion(), this.getLabelOpcion());
		this.listaOpciones.add(s);
		this.setValorOpcion(null);
		this.setLabelOpcion(null);
			
	}
	
	public void modificarOpcion(FormulariosCamposDTO campo){
		try {
			formulariosService.guardarCampo(campo);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto. Campo modificado correctamente", campo.getValor()));
		}catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Se ha producido un error al realizar el proceso"));
		}
	}
	
	public void borrarOpcion() {
		
	}
	
	//metodos para editar la tabla de RGPD y poder modificar los valores
	
	public void modificarCampo(FormulariosCamposDTO campo) {
		try {
			formulariosService.guardarCampo(campo);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto. Campo modificado correctamente", campo.getValor()));
		}catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Se ha producido un error al realizar el proceso"));
		}
	}
	
	public void borrarCampo(FormulariosCamposDTO campo) {
		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Dentro de borrarCampoRGPD "));
	}

	// FIN ****** metodos para editar la tabla de RGPD y poder modificar los valores
	public void limpiar(){
		try {

			formularioBuscar = new FormulariosDTO();
			formularioBuscar.setDepartamento(new DepartamentosDTO());
			formularioBuscar.setTipoForm(new ValoresTiposDTO());
			
			contenidoBuscar = new ContenidoFormularioDTO();
			formularioBloqueAnadir = new FormulariosBloquesDTO();
			listBloques = new ArrayList<SelectItem>();
			listTiposCampo  = new ArrayList<SelectItem>();
			listaOpciones = new ArrayList<SelectItem>();
			
			this.setValorCampo(null);
			this.setValorOpcion(null);
			this.setLabelOpcion(null);
			this.setMuestraCamposLiteral(false);
			this.setMuestraCamposOpciones(false);
			

		}catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Se ha producido un error al realizar el proceso"));
		}
	}
	
	public String volver(){

		return Constantes.NAVEGACION_FORMULARIOS;


	}
	
	public void valueChanged() {
		try {
			this.setValorOpcion(null);
			this.setLabelOpcion(null);
			listaOpciones = new ArrayList<SelectItem>();
			this.setValorCampo(null);
			String tipoCampo = this.valoresTiposService.obtenerCodBasicaPorId(formularioCampoAnadir.getIdTipocampo());
		    if (tipoCampo.equals(Constantes.VALORES_TIPOS_CAMPO_CHECK) ||tipoCampo.equals(Constantes.VALORES_TIPOS_CAMPO_RADIO) 
		    		|| tipoCampo.equals(Constantes.VALORES_TIPOS_CAMPO_SELECT)) {
		    	this.setMuestraCamposLiteral(false);
				this.setMuestraCamposOpciones(true);
		    }
		    else if (tipoCampo.equals(Constantes.VALORES_TIPOS_CAMPO_LITERAL)){
		    	this.setMuestraCamposLiteral(true);
				this.setMuestraCamposOpciones(false);
		    }
		    else {
		    	this.setMuestraCamposLiteral(false);
				this.setMuestraCamposOpciones(false);
		    }
		    	
		    }catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(
						null, 
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Se ha producido un error al realizar el proceso"));
			}
	}
	
	public void guardarCampoAnadirOpciones() {
		
	}
	
	public FormulariosDTO getFormularioBuscar() {
		return formularioBuscar;
	}


	public void setFormularioBuscar(FormulariosDTO formularioBuscar) {
		this.formularioBuscar = formularioBuscar;
	}


	public ContenidoFormularioDTO getContenidoBuscar() {
		return contenidoBuscar;
	}


	public void setContenidoBuscar(ContenidoFormularioDTO contenidoBuscar) {
		this.contenidoBuscar = contenidoBuscar;
	}


	public String getValorCampo() {
		return valorCampo;
	}


	public void setValorCampo(String valorCampo) {
		this.valorCampo = valorCampo;
	}


	public FormulariosBloquesDTO getFormularioBloqueAnadir() {
		return formularioBloqueAnadir;
	}


	public void setFormularioBloqueAnadir(FormulariosBloquesDTO formularioBloqueAnadir) {
		this.formularioBloqueAnadir = formularioBloqueAnadir;
	}


	public FormulariosCamposDTO getFormularioCampoAnadir() {
		return formularioCampoAnadir;
	}


	public void setFormularioCampoAnadir(FormulariosCamposDTO formularioCampoAnadir) {
		this.formularioCampoAnadir = formularioCampoAnadir;
	}


	public List<SelectItem> getListBloques() {
		return listBloques;
	}


	public void setListBloques(List<SelectItem> listBloques) {
		this.listBloques = listBloques;
	}


	public List<SelectItem> getListTiposCampo() {
		return listTiposCampo;
	}


	public void setListTiposCampo(List<SelectItem> listTiposCampo) {
		this.listTiposCampo = listTiposCampo;
	}


	public boolean isMuestraCamposOpciones() {
		return muestraCamposOpciones;
	}


	public void setMuestraCamposOpciones(boolean muestraCamposOpciones) {
		this.muestraCamposOpciones = muestraCamposOpciones;
	}


	public boolean isMuestraCamposLiteral() {
		return muestraCamposLiteral;
	}


	public void setMuestraCamposLiteral(boolean muestraCamposLiteral) {
		this.muestraCamposLiteral = muestraCamposLiteral;
	}


	public List<SelectItem> getListaOpciones() {
		return listaOpciones;
	}


	public void setListaOpciones(List<SelectItem> listaOpciones) {
		this.listaOpciones = listaOpciones;
	}


	public String getValorOpcion() {
		return valorOpcion;
	}


	public void setValorOpcion(String valorOpcion) {
		this.valorOpcion = valorOpcion;
	}


	public String getLabelOpcion() {
		return labelOpcion;
	}


	public void setLabelOpcion(String labelOpcion) {
		this.labelOpcion = labelOpcion;
	}
	

}
