package es.tfg.gesform.presentacion.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.primefaces.context.PrimeRequestContext;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import es.tfg.comun.constantes.Constantes;
import es.tfg.gesform.negocio.DepartamentosService;
import es.tfg.gesform.negocio.dto.DepartamentosDTO;


@Component("mtoDepartamentosBBean")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class MtoDepartamentosBBean implements Serializable {

	@Autowired
	private DepartamentosService departamentosService;

	

	private DepartamentosDTO departamentosBuscar;
	private DepartamentosDTO departamentosAnadir;


	private List<DepartamentosDTO> listDepartamentos;

	@PostConstruct
	public void onLoad() {
		departamentosAnadir = new DepartamentosDTO();
		departamentosBuscar = new DepartamentosDTO();

	}


	public String irADepartamentos(){

		try {
			departamentosAnadir = new DepartamentosDTO();
			departamentosBuscar = new DepartamentosDTO();
			//loginBBean.obtenerDescripcionCaminoMigas(Constantes.CLAVE_ID_OPERACION_MANTENIMIENTOS_Consejerias);
			listDepartamentos = departamentosService.buscarTodos();
		} catch (Exception e) {
			return Constantes.NAVEGACION_ERROR;
		}

		return Constantes.NAVEGACION_DEPARTAMENTOS;
	}


	public void nuevo(){

		try {
			departamentosAnadir = new DepartamentosDTO();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Se ha Producido un Error a realizar el proceso"));
		}
	}


	public void todos() {
		this.departamentosBuscar = new DepartamentosDTO();
		buscar();
	}

	public void limpiar(){
		this.departamentosAnadir = new DepartamentosDTO();
	}


	public void buscar() {
		try {
			this.listDepartamentos = getDepartamentosService().obtenerDepartamentosPorEjemplo(departamentosBuscar);
		}catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Se ha Producido un Error a realizar el proceso"));
		}
	}

	public void borrar(){	
		try{
			if (this.departamentosAnadir!=null && this.departamentosAnadir.getIde()!=0){
				this.getDepartamentosService().borrar(departamentosAnadir);
				this.listDepartamentos = this.getDepartamentosService().buscarTodos();
				limpiar();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "La operación se ha realizado correctamente"));
			}
		}catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Se ha Producido un Error a realizar el proceso"));
		}
	}

	public void guardar(){
		PrimeRequestContext context = PrimeRequestContext.getCurrentInstance();
		boolean loggedIn = false;
		try{
			if(this.departamentosAnadir.getIde() == 0){
				if (validar()){

					this.getDepartamentosService().insertar(departamentosAnadir);

					limpiar();
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "La operación se ha realizado correctamente"));
					loggedIn = true;
				} 
			}else{
				if (validar()){
					this.getDepartamentosService().guardar(departamentosAnadir);

					limpiar();
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "La operación se ha realizado correctamente"));
					loggedIn = true;
				}
			}
			this.listDepartamentos = this.getDepartamentosService().buscarTodos();
		}catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Se ha Producido un Error a realizar el proceso"));
		}

		PrimeFaces.current().ajax().addCallbackParam("loggedIn", loggedIn);
	}

	private boolean validar() {
		boolean ok = true;
		 

		 
		if (this.departamentosAnadir.getNombre() ==null || this.departamentosAnadir.getNombre().length() == 0) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El campo nombre es obligatorio."));
			ok = false;
		}

		return ok;
	}


	public void cargar(Long id){
		try{
			this.departamentosAnadir = getDepartamentosService().obtenerDepartamentoPorId(id);
		}catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Se ha producido un error al realizar el proceso"));
		}
	}
	

	public DepartamentosService getDepartamentosService() {
		return departamentosService;
	}


	public void setDepartamentosService(DepartamentosService departamentosService) {
		this.departamentosService = departamentosService;
	}


	public DepartamentosDTO getDepartamentosBuscar() {
		return departamentosBuscar;
	}


	public void setDepartamentosBuscar(DepartamentosDTO departamentosBuscar) {
		this.departamentosBuscar = departamentosBuscar;
	}


	public DepartamentosDTO getDepartamentosAnadir() {
		return departamentosAnadir;
	}


	public void setDepartamentosAnadir(DepartamentosDTO departamentosAnadir) {
		this.departamentosAnadir = departamentosAnadir;
	}


	public List<DepartamentosDTO> getListDepartamentos() {
		return listDepartamentos;
	}


	public void setListDepartamentos(List<DepartamentosDTO> listDepartamentos) {
		this.listDepartamentos = listDepartamentos;
	}







}
