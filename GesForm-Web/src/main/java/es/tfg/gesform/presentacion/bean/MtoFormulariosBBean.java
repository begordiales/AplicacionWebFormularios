package es.tfg.gesform.presentacion.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import es.tfg.gesform.negocio.DepartamentosService;
import es.tfg.gesform.negocio.FormulariosEnviosService;
import es.tfg.gesform.negocio.FormulariosService;
import es.tfg.gesform.negocio.ValoresTiposService;
import es.tfg.gesform.negocio.dto.DepartamentosDTO;
import es.tfg.gesform.negocio.dto.FormulariosDTO;
import es.tfg.gesform.negocio.dto.ValoresTiposDTO;


@Component("mtoFormulariosBBean")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class MtoFormulariosBBean implements Serializable {

	@Autowired
	private FormulariosService formulariosService;
	
	@Autowired
	private FormulariosEnviosService enviosService;
	
	@Autowired
	private DepartamentosService departamentosService;
	
	@Autowired
	private ValoresTiposService valoresTiposService;
	
//	@Autowired
//	private UsuariosService usuariosService;
	
//	@Autowired
//	private UserLoginBBean userLoginBBean;
	
	

	private FormulariosDTO formularioBuscar;
	private FormulariosDTO formularioAnadir;
	
	private Date fechaDesde;
	private Date fechaHasta;


	private List<FormulariosDTO> listFormularios;
	
	//private List<FormulariosUsuariosDTO> listFormulariosUsuarios;
	private List<SelectItem> elementosDepartamentosBuscar;
	private List<SelectItem> elementosDepartamentosAnadir;
	private List<SelectItem> elementosTipoForm;
	
	
	private long idDepartamento;
	private long idUsuario;
	private long idPerfil;
//	private String nombrePerfil;
	
	


	@PostConstruct
	public void onLoad() {
		try {
			formularioAnadir = new FormulariosDTO();
			formularioAnadir.setDepartamento(new DepartamentosDTO());
			formularioAnadir.setTipoForm(new ValoresTiposDTO());
			
			formularioBuscar = new FormulariosDTO();
			formularioBuscar.setDepartamento(new DepartamentosDTO());
			formularioBuscar.setTipoForm(new ValoresTiposDTO());
			
			this.elementosDepartamentosBuscar = this.getDepartamentosService().obtenerListaDepartamentos();
			this.elementosTipoForm = this.getValoresTiposService().obtenerListaValoresTipos(Constantes.TIPO_FORMULARIOS);
//			tiposCodigosRespuesta = valoresTipoBo.obtenerValoresTipos(Constantes.TIPO_CATEGORIA_CODIGO_RESPUESTA);
		}catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Se ha producido un error al realizar el proceso"));
		}

	}


	/*public String irAFormularios_ConUsuario(){

		try {
			formularioAnadir = new FormulariosDTO();
			formularioAnadir.setDepartamento(new DepartamentosDTO());
			formularioAnadir.setTipoForm(new ValoresTiposDTO());
			
			formularioBuscar = new FormulariosDTO();
			formularioBuscar.setDepartamento(new DepartamentosDTO());
			formularioBuscar.setTipoForm(new ValoresTiposDTO());
			listFormularios = new ArrayList<FormulariosDTO>();
			 
			
			UsuariosDTO dto = new UsuariosDTO();
			
			Object object = null;

			object = FacesUtils.getSessionParameter("loginBBean");
			 
			Method meth;

			meth = object.getClass().getMethod("getNombrePerfil");
			Object result = meth.invoke(object);
			nombrePerfil=result.toString();
			
			meth = object.getClass().getMethod("getUsername");
			Object result3 = meth.invoke(object);
			String username=result3.toString();
			
			meth = object.getClass().getMethod("getEntornoVisado");
			Object result2 = meth.invoke(object);
			String entornoVisado=result2.toString();
			
			idDepartamento =  dto.getIdConsejeria();
			
			dto.setUsername(username);
 			List<UsuariosDTO > usuarios = this.usuariosService.obtenerUsuariosPorEjemplo(dto);
 
 			if (usuarios != null && usuarios.size() >0) {
 				UsuariosDTO usuariologado = usuarios.get(0);
 				
  				if (usuariologado.getIdConsejeria() != 0  ) {
 					idConsejeria = usuariologado.getIdConsejeria();
				}
				else 
				{
					throw new Exception(Constantes.LOGIN_ERROR_CONSEJERIA);
				
				}			
 			}
			if ( (nombrePerfil.equals(Constantes.PF_ACCESO)) || (nombrePerfil.equals(Constantes.PF_GESTOR)) )
			{
				//OBTENERMOS EL IDUSUARIO DEL USUARIO CONECTADO
				//OBTENEMOS LOS FORMUALARIOS A LOS QUE HA SIDO ASIGNADO
				idUsuario=this.usuariosService.obtenerIdUsuario(username);
				listFormulariosUsuarios = this.formulariosUsuariosService.obtenerFormulariosUsuario(idUsuario);
				
				//OBTENEMOS LA LISTA DE FORMULARIOS A MOSTRAR
				for (FormulariosUsuariosDTO lista : listFormulariosUsuarios) {
	 			    listFormularios.add( this.getFormulariosService().obtenerFormularioPorId(lista.getIdForm()));
				}
				
			}
			
		 
			
			if (nombrePerfil.equals(Constantes.PF_ADMINISTRADOR))
			{
				listFormularios = formulariosService.obtenerTodosFormularios(Constantes.NUMERO_RESULTADOS_CONSULTA_SOLICITUDES);
			}
			
	  		
//			userLoginBBean.obtenerDescripcionCaminoMigas(Constantes.CLAVE_ID_OPERACION_MANTENIMIENTOS_PARAMETROSGENERALES);
			
			this.elementosTipoForm = this.getValoresTiposService().obtenerListaValoresTipos(Constantes.TIPO_FORMULARIOS);
			
			
		} catch (Exception e) {
			return Constantes.NAVEGACION_ERROR;
		}

		return Constantes.NAVEGACION_FORMULARIOS;
	}*/
	
	public String irAFormularios(){

		try {
			formularioAnadir = new FormulariosDTO();
			formularioAnadir.setDepartamento(new DepartamentosDTO());
			formularioAnadir.setTipoForm(new ValoresTiposDTO());
			
			formularioBuscar = new FormulariosDTO();
			formularioBuscar.setDepartamento(new DepartamentosDTO());
			formularioBuscar.setTipoForm(new ValoresTiposDTO());
			
			this.elementosDepartamentosBuscar = this.getDepartamentosService().obtenerListaDepartamentos();
			
			listFormularios = new ArrayList<FormulariosDTO>();
			listFormularios = formulariosService.obtenerTodosFormularios(Constantes.NUMERO_RESULTADOS_CONSULTA);
			
			this.elementosTipoForm = this.getValoresTiposService().obtenerListaValoresTipos(Constantes.TIPO_FORMULARIOS);
			
			
		} catch (Exception e) {
			return Constantes.NAVEGACION_ERROR;
		}

		return Constantes.NAVEGACION_FORMULARIOS;
	}


	public void nuevo(){

		try {
			formularioAnadir = new FormulariosDTO();
			formularioAnadir.setDepartamento(new DepartamentosDTO());
			formularioAnadir.setTipoForm(new ValoresTiposDTO());
			this.elementosDepartamentosAnadir = this.getDepartamentosService().obtenerListaDepartamentos();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Se ha producido un error al realizar el proceso"));
		}
	}


	public void todos() {
		this.formularioBuscar = new FormulariosDTO();
		formularioBuscar.setDepartamento(new DepartamentosDTO());
		formularioBuscar.setTipoForm(new ValoresTiposDTO());
		fechaDesde = null;
		fechaHasta = null;
		buscar();
	}

	public void limpiar(){
		this.formularioAnadir = new FormulariosDTO();
	}


	/*public void buscar_ConUsuario() {
		try {
			
			
			if (  (nombrePerfil.equals(Constantes.PF_GESTOR)) )
			{
				//OBTENERMOS EL IDUSUARIO DEL USUARIO CONECTADO
				//OBTENEMOS LOS FORMUALARIOS A LOS QUE HA SIDO ASIGNADO
				formularioBuscar.getDepartamento().setIde(idConsejeria);
				this.listFormularios = getFormulariosService().obtenerFormulariosFechasPorEjemplo(formularioBuscar, fechaDesde, fechaHasta);
			 
			   
			}
	 			
			if (nombrePerfil.equals(Constantes.PF_ADMINISTRADOR))
			{
				this.listFormularios = getFormulariosService().obtenerFormulariosFechasPorEjemplo(formularioBuscar, fechaDesde, fechaHasta);
			}
			
			
		}catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Se ha producido un error al realizar el proceso"));
		}
	}*/
	
	public void buscar() {
		try {	
				this.listFormularios = getFormulariosService().obtenerFormulariosFechasPorEjemplo(formularioBuscar, fechaDesde, fechaHasta);
	
		}catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Se ha producido un error al realizar el proceso"));
		}
	}

	public void borrar(Long id){
		boolean hayEnvios = false;
		boolean loggedIn = false;
		try{
			//comprobar si el formulario tiene envios
			hayEnvios = this.enviosService.tieneEnvios(id);
			if (!hayEnvios) {
				this.formularioAnadir = formulariosService.obtenerFormularioPorId(id);
				this.formulariosService.borrar(formularioAnadir);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "El formulario se ha borrado correctamente"));
				loggedIn = true;
			}
			else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El formulario tiene envíos. No es posible su borrado."));
				loggedIn = true;
			}
		}catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Se ha producido un error al realizar el proceso"));
		}
		
		try {
			listFormularios = formulariosService.obtenerFormulariosFechasPorEjemplo(formularioBuscar, fechaDesde, fechaHasta);
		} catch (Exception e) {
			e.printStackTrace();
		}

		PrimeFaces.current().ajax().addCallbackParam("loggedIn", loggedIn);

	}

	public void guardar(){

		boolean loggedIn = false;
		try{
			if(this.formularioAnadir.getIde() == 0){
				if (validar()){		
					if (this.formulariosService.insertar(formularioAnadir)!=null) {
						//limpiar();
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "La operación se ha realizado correctamente"));
						loggedIn = true;
					}

				} 
			}
			else{
				if (validar()){
					this.formulariosService.guardar(formularioAnadir);
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "La operación se ha realizado correctamente"));
					loggedIn = true;
				}
			}


		}catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Se ha producido un error al realizar el proceso"));
		}
		
		try {
			listFormularios = formulariosService.obtenerTodosFormularios(Constantes.NUMERO_RESULTADOS_CONSULTA);
		} catch (Exception e) {
			e.printStackTrace();
		}

		PrimeFaces.current().ajax().addCallbackParam("loggedIn", loggedIn);
		
	}
	
	private boolean validar() {
		boolean ok = true;
		
		if (this.formularioAnadir.getNombre() ==null || this.formularioAnadir.getNombre().length() == 0) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El campo Nombre es obligatorio."));
			ok = false;
		}
		
		if (this.formularioAnadir.getFechaIniPub() ==null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El campo Fecha publicación desde es obligatorio."));
			ok = false;
		}
		
		if (this.formularioAnadir.getTipoForm().getIde()== (long)-1) {

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El campo Tipo es obligatorio."));
			ok = false;
		}
		
		return ok;
	}


	public void cargar(Long id){
		try{
			this.formularioAnadir = formulariosService.obtenerFormularioPorId(id);
			if (formularioAnadir.getDepartamento() == null) {
				formularioAnadir.setDepartamento(new DepartamentosDTO());
				formularioAnadir.getDepartamento().setIde((long)-1);				
			}
				
			this.elementosDepartamentosAnadir = this.getDepartamentosService().obtenerListaDepartamentos();
		}catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Se ha producido un error al realizar el proceso"));
		}
	}
	
	public FormulariosService getFormulariosService() {
		return formulariosService;
	}


	public void setFormulariosService(FormulariosService formulariosService) {
		this.formulariosService = formulariosService;
	}

	

	public DepartamentosService getDepartamentosService() {
		return departamentosService;
	}


	public void setDepartamentosService(DepartamentosService departamentosService) {
		this.departamentosService = departamentosService;
	}


	
	public List<SelectItem> getElementosDepartamentosBuscar() {
		return elementosDepartamentosBuscar;
	}


	public void setElementosDepartamentosBuscar(List<SelectItem> elementosDepartamentosBuscar) {
		this.elementosDepartamentosBuscar = elementosDepartamentosBuscar;
	}


	public List<SelectItem> getElementosDepartamentosAnadir() {
		return elementosDepartamentosAnadir;
	}


	public void setElementosDepartamentosAnadir(List<SelectItem> elementosDepartamentosAnadir) {
		this.elementosDepartamentosAnadir = elementosDepartamentosAnadir;
	}


	public FormulariosDTO getFormularioBuscar() {
		return formularioBuscar;
	}


	public void setFormularioBuscar(FormulariosDTO formularioBuscar) {
		this.formularioBuscar = formularioBuscar;
	}


	public FormulariosDTO getFormularioAnadir() {
		return formularioAnadir;
	}


	public void setFormularioAnadir(FormulariosDTO formularioAnadir) {
		this.formularioAnadir = formularioAnadir;
	}


	public List<FormulariosDTO> getListFormularios() {
		return listFormularios;
	}


	public void setListFormularios(List<FormulariosDTO> listFormularios) {
		this.listFormularios = listFormularios;
	}


	public ValoresTiposService getValoresTiposService() {
		return valoresTiposService;
	}


	public void setValoresTiposService(ValoresTiposService valoresTiposService) {
		this.valoresTiposService = valoresTiposService;
	}


	public List<SelectItem> getElementosTipoForm() {
		return elementosTipoForm;
	}


	public void setElementosTipoForm(List<SelectItem> elementosTipoForm) {
		this.elementosTipoForm = elementosTipoForm;
	}




	public long getIdDepartamento() {
		return idDepartamento;
	}


	public void setIdDepartamento(long idDepartamento) {
		this.idDepartamento = idDepartamento;
	}


	public Date getFechaDesde() {
		return fechaDesde;
	}


	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}


	public Date getFechaHasta() {
		return fechaHasta;
	}


	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}


	public long getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}


	public long getIdPerfil() {
		return idPerfil;
	}


	public void setIdPerfil(long idPerfil) {
		this.idPerfil = idPerfil;
	}


	public FormulariosEnviosService getEnviosService() {
		return enviosService;
	}


	public void setEnviosService(FormulariosEnviosService enviosService) {
		this.enviosService = enviosService;
	}


}
