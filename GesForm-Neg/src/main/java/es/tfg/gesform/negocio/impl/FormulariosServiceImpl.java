package es.tfg.gesform.negocio.impl;

import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import java.util.Iterator;
import javax.faces.model.SelectItem;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import es.tfg.comun.utils.Utilidades;

import es.tfg.gesform.negocio.FormulariosService;
import es.tfg.gesform.negocio.FormulariosBloquesCamposOpcionesService;
import es.tfg.gesform.persistencia.dao.FormulariosDAO;
import es.tfg.gesform.persistencia.dao.PlantillasDAO;
import es.tfg.gesform.persistencia.dao.FormulariosBloquesDAO;
import es.tfg.gesform.persistencia.dao.FormulariosBloquesCamposDAO;
import es.tfg.gesform.persistencia.dao.FormulariosBloquesCamposOpcionesDAO;
import es.tfg.gesform.negocio.dto.FormulariosDTO;

import es.tfg.gesform.negocio.dto.ValoresTiposDTO;
import es.tfg.gesform.persistencia.vo.FormulariosVO;
import es.tfg.gesform.persistencia.vo.FormulariosBloquesVO;
import es.tfg.gesform.persistencia.vo.FormulariosCamposOpcionesVO;
import es.tfg.gesform.persistencia.vo.FormulariosCamposVO;
import es.tfg.gesform.persistencia.vo.ValidacionesVO;
import es.tfg.gesform.persistencia.vo.PlantillasVO;
import es.tfg.gesform.persistencia.vo.PlantillasCamposVO;
import es.tfg.gesform.persistencia.vo.PlantillasCamposOpcionesVO;

import es.tfg.gesform.negocio.dto.DepartamentosDTO;
import es.tfg.gesform.negocio.dto.ContenidoFormularioDTO;
import es.tfg.gesform.persistencia.vo.DepartamentosVO;
import es.tfg.gesform.persistencia.vo.ValoresTiposVO;
import es.tfg.gesform.negocio.dto.FormulariosCamposOpcionesDTO;
import es.tfg.gesform.negocio.dto.FormulariosCamposDTO;
import es.tfg.gesform.negocio.dto.FormulariosBloquesDTO;

@Service
public class FormulariosServiceImpl implements FormulariosService {

	@Autowired
	private FormulariosDAO formulariosDAO;
	
	@Autowired
	private PlantillasDAO plantillasDAO;
	
	@Autowired
	private FormulariosBloquesDAO formulariosBloquesDAO;
	
	@Autowired
	private FormulariosBloquesCamposDAO formulariosBloquesCamposDAO;
	
	@Autowired
	private FormulariosBloquesCamposOpcionesDAO formulariosBloquesCamposOpcionesDAO;
	
	@Autowired
	private FormulariosBloquesCamposOpcionesService opcionesService;
//	public ClientesVO buscar(Integer clienteNumber) {
//		return clientesDAO.selectByPrimaryKey(clienteNumber);
//	}

	public List<FormulariosDTO> obtenerTodosFormularios(int limit)  throws Exception {
		
		try {
			List<FormulariosVO> listaVO = new ArrayList<FormulariosVO>();
			List<FormulariosDTO> listaDTO = new ArrayList<FormulariosDTO>();
			
		

			listaVO = this.getFormulariosDAO().obtenerTodosFormularios(limit);

			for (FormulariosVO vo : listaVO) {

				FormulariosDTO dto = this.getFormulariosDTO(vo);

				listaDTO.add(dto);
			}

			return listaDTO;

		} catch (Exception e) {
			throw e;
		}

	}
	
	
	public FormulariosDTO getFormulariosDTO(FormulariosVO vo) throws Exception {

		FormulariosDTO dto = null;

		if (vo != null) {

			dto = new FormulariosDTO();
			//id
			dto.setIde(Utilidades.lNoNullEsNull(vo.getId()));
			dto.setNombre(Utilidades.sNoNull(vo.getNombre()));
			dto.setDescripcion(Utilidades.sNoNull(vo.getDescripcion()));
			
			
			if (vo.getDepartamento() != null && vo.getDepartamento().getId() != (long)-1) {
				
				dto.setDepartamento(new DepartamentosDTO());
				dto.getDepartamento().setIde(Utilidades.lNoNullEsNull(vo.getDepartamento().getId()));
				dto.getDepartamento().setNombre(Utilidades.sNoNull(vo.getDepartamento().getNombre()));

			}
			
			if (vo.getTipoform() != null) {
	
				dto.setTipoForm(new ValoresTiposDTO());
				dto.getTipoForm().setIde(Utilidades.lNoNullEsNull(vo.getTipoform().getId()));
				dto.getTipoForm().setCodigo(Utilidades.sNoNull(vo.getTipoform().getCodigo()));
				dto.getTipoForm().setNombre(Utilidades.sNoNull(vo.getTipoform().getNombre()));
				dto.getTipoForm().setDescripcion(Utilidades.sNoNull(vo.getTipoform().getDescripcion()));
			}
			
			dto.setFechaIniPub(vo.getFechaInicio());
			dto.setFechaFinPub(vo.getFechaFin());

			dto.setUrl(Utilidades.sNoNull(vo.getUrl()));
			
		}
			
	
		return dto;
	}
	
	public FormulariosVO getFormulariosVO(FormulariosDTO dto) throws Exception {

		FormulariosVO vo = null;

		if (dto != null) {

			vo = new FormulariosVO();
			//id
			vo.setId(Utilidades.lNoNullEsNull(dto.getIde()));
			vo.setNombre(Utilidades.sNoNull(dto.getNombre()));
			vo.setDescripcion(Utilidades.sNoNull(dto.getDescripcion()));
			vo.setUrl(Utilidades.sNoNull(dto.getUrl()));
			
			
			if (dto.getDepartamento() != null && dto.getDepartamento().getIde() != (long)-1) {
				vo.setDepartamento(new DepartamentosVO());
				vo.getDepartamento().setId(Utilidades.lNoNullEsNull(dto.getDepartamento().getIde()));
//				vo.getConsejeria().setNombre(Utilidades.sNoNull(dto.getConsejeria().getNombre()));

			}
			
			if (dto.getTipoForm() != null) {
	
				vo.setTipoform(new ValoresTiposVO());
				//traer por ejemplo
				vo.getTipoform().setId(Utilidades.lNoNullEsNull(dto.getTipoForm().getIde()));
//				vo.getTipoform().setCodigo(Utilidades.sNoNull(dto.getTipoForm().getCodigo()));
//				vo.getTipoform().setNombre(Utilidades.sNoNull(dto.getTipoForm().getNombre()));
//				vo.getTipoform().setDescripcion(Utilidades.sNoNull(dto.getTipoForm().getDescripcion()));
			}
			
			vo.setFechaInicio(dto.getFechaIniPub());
			vo.setFechaFin(dto.getFechaFinPub());
			
			vo.setUrl(Utilidades.sNoNull(dto.getUrl()));
			
		}
			
	
		return vo;
	}
	



	@Transactional
	public Long insertar(FormulariosDTO obj) throws Exception {
			FormulariosVO vo = this.getFormulariosVO(obj);
			if (vo != null) {
				this.getFormulariosDAO().insertar(vo);
			}
			return vo.getId();
		
	}

	public void guardar(FormulariosDTO obj) {
		FormulariosVO vo = this.getFormulariosDAO().selectByPrimaryKey(obj.getIde());
		vo = this.establecerCamposEditables(vo, obj);
		this.getFormulariosDAO().actualizar(vo);

	}
	
	public void borrar(FormulariosDTO obj) {
		FormulariosVO vo = this.getFormulariosDAO().selectByPrimaryKey(obj.getIde());
		vo.setFechaBaja(new Date());
		this.getFormulariosDAO().actualizar(vo);

	}
	
	
	public FormulariosDTO obtenerFormularioPorId(long id) throws Exception {
		FormulariosVO vo = getFormulariosDAO().selectByPrimaryKey(id);
		if (vo != null)
			return this.getFormulariosDTO(vo);
		else 
			return null;
	}
	
	

	
	public String obtenerNombreFormularioPorId(long id) throws Exception {
		FormulariosVO vo = getFormulariosDAO().selectByPrimaryKey(id);
		if (vo != null)
			return this.getFormulariosDTO(vo).getNombre();
		else 
			return null;
	}
	
	public FormulariosCamposDTO obtenerCampoPorId(long id) throws  Exception {
		FormulariosCamposVO vo = this.getFormulariosBloquesCamposDAO().selectByPrimaryKey(id);
		if (vo != null)
			return this.getFormulariosCamposDTO(vo);
		else 
			return null;
	}
	
private FormulariosVO establecerCamposEditables(FormulariosVO vo, FormulariosDTO dto) {
		
		if (dto != null) {
			
			if (vo == null) {
				vo = new FormulariosVO();
			}
			
			vo.setId(Utilidades.lNoNullEsNull(dto.getIde()));
			vo.setNombre(Utilidades.sNoNull(dto.getNombre()));
			vo.setDescripcion(Utilidades.sNoNull(dto.getDescripcion()));
			vo.setUrl(Utilidades.sNoNull(dto.getUrl()));
			
			
			if (dto.getDepartamento() != null && dto.getDepartamento().getIde() != (long)-1) {
				if (vo.getDepartamento() == null || vo.getDepartamento().getId() == (long)-1) {
					vo.setDepartamento(new DepartamentosVO());
				}
				vo.getDepartamento().setId(Utilidades.lNoNullEsNull(dto.getDepartamento().getIde()));
			}
			else {
				vo.setDepartamento(null);
//				vo.getConsejeria().setId(-1);
			}
			
			if (dto.getTipoForm() != null) {
	
//				vo.setTipoform(new ValoresTiposVO());
				//traer por ejemplo
				vo.getTipoform().setId(Utilidades.lNoNullEsNull(dto.getTipoForm().getIde()));
//				vo.getTipoform().setCodigo(Utilidades.sNoNull(dto.getTipoForm().getCodigo()));
//				vo.getTipoform().setNombre(Utilidades.sNoNull(dto.getTipoForm().getNombre()));
//				vo.getTipoform().setDescripcion(Utilidades.sNoNull(dto.getTipoForm().getDescripcion()));
			}
			
			vo.setFechaInicio(dto.getFechaIniPub());
			vo.setFechaFin(dto.getFechaFinPub());

			vo.setUrl(Utilidades.sNoNull(dto.getUrl()));
			
		}
		return vo;
	}


public void anadirPlantilla(long id, String codPlantilla, String valor) throws Exception {
	try {
		//obtenemos los datos de PLANTILLA 
		PlantillasVO  plantillavo = this.getPlantillasDAO().obtenerPlantillaPorCodigo(codPlantilla);
		
		
		FormulariosBloquesVO bloquevo = new FormulariosBloquesVO();
		
		bloquevo.setNombre(plantillavo.getNombre());
		bloquevo.setDescripcion(plantillavo.getDescripcion());
		bloquevo.setFormulario(new FormulariosVO());
		bloquevo.getFormulario().setId(id);
		bloquevo.setPlantilla(new PlantillasVO());
		bloquevo.getPlantilla().setId(plantillavo.getId());
		bloquevo.setColumnas(plantillavo.getColumnas());
		
		
		//obtenemos el orden del bloque (depende de los bloques ya insertados en el formulario)
		bloquevo.setOrden(this.formulariosDAO.obtenerOrdenBloque(id));
		
		Long idBloque = this.getFormulariosBloquesDAO().insertarBloquePlantilla(bloquevo);
		//insertamos los datos en FORMULARIOS_BLOQUES
		
		//obtenemos los campos de la PLANTILLA
		List<PlantillasCamposVO> listacamposvo = this.getPlantillasDAO().obtenerCamposPlantillaPorCodigoPlantilla(codPlantilla);
		PlantillasCamposVO campoplantillavo = new PlantillasCamposVO();

		if (listacamposvo !=null) {
			for (Iterator iterator = listacamposvo.iterator(); iterator.hasNext();) {
				campoplantillavo = (PlantillasCamposVO) iterator.next();
				//rellenamos objeto tipo formularioscampovo con los datos del campoplantillavo
				FormulariosCamposVO campovo = new FormulariosCamposVO();
				
				campovo.setNombre(campoplantillavo.getNombre());
				campovo.setBloque(bloquevo);
				campovo.setEtiqueta(campoplantillavo.getEtiqueta());
				campovo.setObligatorio(campoplantillavo.getObligatorio());
				campovo.setOrden(campoplantillavo.getOrden());
				campovo.setTamano(Utilidades.iNoNullEsCero(campoplantillavo.getTamano()));
				campovo.setTipocampo(campoplantillavo.getTipoCampo());
				campovo.setValidacion(campoplantillavo.getValidacion());
				if (valor != null) {
					//recuperamos el valor del campo para el caso de plantilla titulo o subtitulo
					campovo.setValor(valor);
				}
				else {
					//para plantillas que tienen valor en sus campos, por ej. proteccion de datos
					campovo.setValor(campoplantillavo.getValor());
				}
				
				campovo.setTamanofichero(Utilidades.iNoNullEsCero(campoplantillavo.getTamanofichero()));
				campovo.setTiposfichero(Utilidades.sNoNull(campoplantillavo.getTiposfichero()));
				campovo.setNumficheros(Utilidades.iNoNullEsCero(campoplantillavo.getNumficheros()));
				
				Long idcampo = this.getFormulariosBloquesCamposDAO().insertarBloqueCampoPlantilla(campovo);
				//obtenemos los posibles valores del campo
				List<PlantillasCamposOpcionesVO> listaopcionesvo = this.getPlantillasDAO().obtenerOpcionesCampoPorIdCampo(campoplantillavo.getId());
				if (listaopcionesvo != null) {
					PlantillasCamposOpcionesVO opcionesplantillavo = new PlantillasCamposOpcionesVO();
					for (Iterator iterator2 = listaopcionesvo.iterator();iterator2.hasNext();) {
						opcionesplantillavo = (PlantillasCamposOpcionesVO) iterator2.next();
						FormulariosCamposOpcionesVO opcionesvo = new FormulariosCamposOpcionesVO();
						
						opcionesvo.setValor(opcionesplantillavo.getValor());
						opcionesvo.setCampo(campovo);
						opcionesvo.setDescripcion(opcionesplantillavo.getDescripcion());
						opcionesvo.setOrden(opcionesplantillavo.getOrden());
						
						
						this.getFormulariosBloquesCamposOpcionesDAO().insertarBloqueCampoOpcionesPlantilla(opcionesvo);
					}
					

				}

			}
		}
	} catch (Exception e) {
		throw e;
	}
	
	
}




public ContenidoFormularioDTO obtenerContenidoPorIdForm(long idForm) throws Exception {
	
	FormulariosDTO formDTO = new FormulariosDTO();
	ContenidoFormularioDTO contenidoDto = new ContenidoFormularioDTO();
	//obtenemos los bloques
	List<FormulariosBloquesVO>  listabloquesvo = this.getFormulariosBloquesDAO().obtenerBloquesFormulario(idForm);
	List<FormulariosBloquesDTO> listabloquesdto = new ArrayList<FormulariosBloquesDTO>();
	FormulariosBloquesVO bloquevo = new FormulariosBloquesVO();
	//recorremos los bloques para obtener los campos
	
	FormulariosCamposVO camposvo = new FormulariosCamposVO();
	//vamos insertando en FORMULARIOS_CAMPOS y cuando corresponda en FORMULARIOS_CAMPOS_OPCIONES


	for (Iterator iterator = listabloquesvo.iterator(); iterator.hasNext();) {
		bloquevo = (FormulariosBloquesVO) iterator.next();
		
		//pasamos bloquevo a bloquedto
		FormulariosBloquesDTO bloquedto = new FormulariosBloquesDTO();
		
		bloquedto = this.getFormulariosBloquesDTO(bloquevo);
		

		//obtenemos los campos del bloque
		List<FormulariosCamposVO> listacamposvo = this.getFormulariosBloquesCamposDAO().obtenerCamposBloque(bloquevo.getId());
		List<FormulariosCamposDTO> listacamposdto = new ArrayList<FormulariosCamposDTO>();
		
		
		for (FormulariosCamposVO campovo : listacamposvo) {
			FormulariosCamposDTO campodto = this.getFormulariosCamposDTO(campovo);
			
			//obtenemos las opciones del campo
//			List<FormulariosCamposOpcionesVO> listaopcionesvo = this.getFormulariosBloquesCamposOpcionesDAO().obtenerOpcionesCampos(campodto.getIde());
			
			List<SelectItem> listaopciones = this.getOpcionesService().obtenerListaOpcionesPorIdCampo(campodto.getIde());
//			List<FormulariosCamposOpcionesDTO> listaopcionesdto = new ArrayList<FormulariosCamposOpcionesDTO>();
//			
//			for (SelectItem opcionvo: listaopciones) {
//				FormulariosCamposOpcionesDTO opciondto = this.getFormulariosCamposOpcionesDTO(opcionvo);
//				listaopcionesdto.add(opciondto);
//			}
			campodto.setListaopciones(listaopciones);
			listacamposdto.add(campodto);
		}
		bloquedto.setCampos(listacamposdto);
		listabloquesdto.add(bloquedto);

	}
	
	formDTO = this.obtenerFormularioPorId(idForm);
	contenidoDto.setIdForm(idForm);
	contenidoDto.setBloques(listabloquesdto);
	return contenidoDto;
}

public FormulariosBloquesDTO getFormulariosBloquesDTO(FormulariosBloquesVO vo) throws Exception {

	FormulariosBloquesDTO dto = null;

	if (vo != null) {

		dto = new FormulariosBloquesDTO();


		dto.setId(Utilidades.lNoNullEsNull(vo.getId()));
		dto.setNombre(Utilidades.sNoNull(vo.getNombre()));
		dto.setDescripcion(Utilidades.sNoNull(vo.getDescripcion()));
		dto.setOrden(Utilidades.iNoNullEsNull(vo.getOrden()));
		dto.setColumnas(Utilidades.iNoNullEsNull(vo.getColumnas()));

		
		
		if (vo.getFormulario() != null) {
			dto.setIdForm(vo.getFormulario().getId());
		}
		
		if (vo.getPlantilla() !=null) {
			dto.setIdPlantilla(vo.getPlantilla().getId());
			dto.setCodigoPlantilla(vo.getPlantilla().getCodigo());
		}
		
		//PREGUNTAR SI HAY CAMPOS Y RECORRERLOS Y SINO INICIALIZAR LA LISTA
		
//		if (vo.getCampos() == null) {
//			vo.setCampos(new FormulariosBloquesDTO());
			
//		}
		



	}
	return dto;
}

public FormulariosCamposDTO getFormulariosCamposDTO(FormulariosCamposVO vo) throws Exception {

	FormulariosCamposDTO dto = null;

	if (vo != null) {

		dto = new FormulariosCamposDTO();


		dto.setIde(Utilidades.lNoNullEsNull(vo.getId()));
		dto.setNombre(Utilidades.sNoNull(vo.getNombre()));
		dto.setEtiqueta(Utilidades.sNoNull(vo.getEtiqueta()));
		dto.setObligatorio(Utilidades.iNoNullEsNull(vo.getObligatorio()));
		dto.setTamano(Utilidades.iNoNullEsNull(vo.getTamano()));
		dto.setAncho(Utilidades.iNoNullEsNull(vo.getAncho()));
		dto.setOrden(Utilidades.iNoNullEsNull(vo.getOrden()));
		dto.setValor(Utilidades.sNoNull(vo.getValor()));
		dto.setTamanofichero(Utilidades.iNoNullEsNull(vo.getTamanofichero()));
		dto.setTiposfichero(Utilidades.sNoNull(vo.getTiposfichero()));
		dto.setNumficheros(Utilidades.iNoNullEsCero(vo.getNumficheros()));
		
		if (vo.getBloque() != null) {
			dto.setIdBloque(vo.getBloque().getId());
		}
		
		if (vo.getTipocampo() !=null) {
			dto.setIdTipocampo(vo.getTipocampo().getId());
			dto.setCodigoTipoCampo(vo.getTipocampo().getCodigo());
		}
		
		if (vo.getValidacion() != null) {
			dto.setIdValidacion(vo.getValidacion().getId());
			dto.setFuncionValidacion(vo.getValidacion().getFuncion());
		}
		



	}
	return dto;
}

public FormulariosCamposVO getFormulariosCamposVO(FormulariosCamposDTO dto) throws Exception {

	FormulariosCamposVO vo = null;

	if (dto != null) {

		vo = new FormulariosCamposVO();
		
		vo.setId(Utilidades.lNoNullEsNull(dto.getIde()));
		vo.setNombre(Utilidades.sNoNull(dto.getNombre()));
		vo.setEtiqueta(Utilidades.sNoNull(dto.getEtiqueta()));
		vo.setObligatorio(Utilidades.iNoNullEsNull(dto.getObligatorio()));
		vo.setTamano(Utilidades.iNoNullEsNull(dto.getTamano()));
		vo.setAncho(Utilidades.iNoNullEsNull(dto.getAncho()));
		vo.setOrden(Utilidades.iNoNullEsNull(dto.getOrden()));
		vo.setValor(Utilidades.sNoNull(dto.getValor()));


//		dto.setIde(Utilidades.lNoNullEsNull(vo.getId()));
//		dto.setNombre(Utilidades.sNoNull(vo.getNombre()));
//		dto.setEtiqueta(Utilidades.sNoNull(vo.getEtiqueta()));
//		dto.setObligatorio(Utilidades.iNoNullEsNull(vo.getObligatorio()));
//		dto.setTamano(Utilidades.iNoNullEsNull(vo.getTamano()));
//		dto.setAncho(Utilidades.iNoNullEsNull(vo.getAncho()));
//		dto.setOrden(Utilidades.iNoNullEsNull(vo.getOrden()));
//		dto.setValor(Utilidades.sNoNull(vo.getValor()));
		
		if (dto.getIdBloque() != (long)-1) {
			vo.setBloque(new FormulariosBloquesVO());
			vo.getBloque().setId(dto.getIdBloque());

		}
		
		if (dto.getIdTipocampo() != (long)-1) {
			vo.setTipocampo(new ValoresTiposVO());
			vo.getTipocampo().setId(dto.getIdTipocampo());
			vo.getTipocampo().setCodigo(dto.getCodigoTipoCampo());

		}
		
		if (dto.getIdValidacion()!= (long)0) {
			vo.setValidacion(new ValidacionesVO());
			vo.getValidacion().setId(dto.getIdValidacion());
			
		}

	}
	return vo;
}

public FormulariosCamposOpcionesDTO getFormulariosCamposOpcionesDTO(FormulariosCamposOpcionesVO vo) throws Exception {

	FormulariosCamposOpcionesDTO dto = null;

	if (vo != null) {

		dto = new FormulariosCamposOpcionesDTO();


		dto.setIde(Utilidades.lNoNullEsNull(vo.getId()));
		dto.setIdCampo(Utilidades.lNoNullEsNull(vo.getCampo().getId()));
		dto.setValor(Utilidades.iNoNullEsNull(vo.getValor()));
		dto.setDescripcion(Utilidades.sNoNull(vo.getDescripcion()));
		dto.setOrden(Utilidades.iNoNullEsNull(vo.getOrden()));

	}
	return dto;
}

public void guardarCampo(FormulariosCamposDTO obj) throws Exception{
	FormulariosCamposVO vo = this.getFormulariosCamposVO(obj);	
	this.getFormulariosBloquesCamposDAO().actualizarCampo(vo);
}


public List<FormulariosDTO> obtenerFormulariosPorEjemplo(FormulariosDTO example) throws Exception {
	FormulariosVO vo = this.getFormulariosVO(example);

	List<FormulariosVO> l = this.getFormulariosDAO().obtenerFormulariosVOPorEjemplo(vo);

	List<FormulariosDTO> ldto = new ArrayList<FormulariosDTO>();

	for (Iterator iterator = l.iterator(); iterator.hasNext();) {
		FormulariosVO aux = (FormulariosVO) iterator.next();
		FormulariosDTO dto = this.getFormulariosDTO(aux);
		ldto.add(dto);
	}

	return ldto;
}


public List<FormulariosDTO> obtenerFormulariosFechasPorEjemplo(FormulariosDTO example, Date fechaDesde, Date fechaHasta)throws Exception {
	FormulariosVO vo = this.getFormulariosVO(example);

	List<FormulariosVO> l = this.getFormulariosDAO().obtenerFormulariosFechasVOPorEjemplo(vo,fechaDesde,fechaHasta);

	List<FormulariosDTO> ldto = new ArrayList<FormulariosDTO>();

	for (Iterator iterator = l.iterator(); iterator.hasNext();) {
		FormulariosVO aux = (FormulariosVO) iterator.next();
		FormulariosDTO dto = this.getFormulariosDTO(aux);
		ldto.add(dto);
	}

	return ldto;
}


public FormulariosDAO getFormulariosDAO() {
	return formulariosDAO;
}

public void setFormulariosDAO(FormulariosDAO formulariosDAO) {
	this.formulariosDAO = formulariosDAO;
}




public FormulariosBloquesDAO getFormulariosBloquesDAO() {
	return formulariosBloquesDAO;
}


public void setFormulariosBloquesDAO(FormulariosBloquesDAO formulariosBloquesDAO) {
	this.formulariosBloquesDAO = formulariosBloquesDAO;
}


public PlantillasDAO getPlantillasDAO() {
	return plantillasDAO;
}


public void setPlantillasDAO(PlantillasDAO plantillasDAO) {
	this.plantillasDAO = plantillasDAO;
}


public FormulariosBloquesCamposDAO getFormulariosBloquesCamposDAO() {
	return formulariosBloquesCamposDAO;
}


public void setFormulariosBloquesCamposDAO(FormulariosBloquesCamposDAO formulariosBloquesCamposDAO) {
	this.formulariosBloquesCamposDAO = formulariosBloquesCamposDAO;
}


public FormulariosBloquesCamposOpcionesDAO getFormulariosBloquesCamposOpcionesDAO() {
	return formulariosBloquesCamposOpcionesDAO;
}


public void setFormulariosBloquesCamposOpcionesDAO(
		FormulariosBloquesCamposOpcionesDAO formulariosBloquesCamposOpcionesDAO) {
	this.formulariosBloquesCamposOpcionesDAO = formulariosBloquesCamposOpcionesDAO;
}


public FormulariosBloquesCamposOpcionesService getOpcionesService() {
	return opcionesService;
}


public void setOpcionesService(FormulariosBloquesCamposOpcionesService opcionesService) {
	this.opcionesService = opcionesService;
}


public void anadirBloque(long id, FormulariosBloquesDTO obj) throws Exception {
	try {

		FormulariosBloquesVO bloquevo = new FormulariosBloquesVO();
		
		bloquevo.setNombre(obj.getNombre());
		bloquevo.setDescripcion(obj.getDescripcion());
		bloquevo.setFormulario(new FormulariosVO());
		bloquevo.getFormulario().setId(id);
		bloquevo.setColumnas(obj.getColumnas());
		
		
		//obtenemos el orden del bloque (depende de los bloques ya insertados en el formulario)
		bloquevo.setOrden(this.formulariosDAO.obtenerOrdenBloque(id));
		
		Long idBloque = this.getFormulariosBloquesDAO().insertarBloquePlantilla(bloquevo);

	} catch (Exception e) {
		throw e;
	}
	
}

public void anadirCampo(FormulariosCamposDTO obj) throws Exception{
	FormulariosCamposVO campovo = new FormulariosCamposVO();
	
	campovo = this.getFormulariosCamposVO(obj);
	
	Long idCampo = this.formulariosBloquesCamposDAO.insertarBloqueCampoPlantilla(campovo);
	
	//insertamos las opciones del campo si las hay
	List<SelectItem> listaopcionesvo = obj.getListaopciones();
	if (listaopcionesvo != null) {
//		PlantillasCamposOpcionesVO opcionesplantillavo = new PlantillasCamposOpcionesVO();
		SelectItem opcionesplantillavo = new SelectItem();
		int orden = 1; //no lo pedimos en el formulario de entrada de opciones
		for (Iterator iterator2 = listaopcionesvo.iterator();iterator2.hasNext();) {
			
			opcionesplantillavo = (SelectItem) iterator2.next();
			FormulariosCamposOpcionesVO opcionesvo = new FormulariosCamposOpcionesVO();

			opcionesvo.setValor(Integer.parseInt(opcionesplantillavo.getValue().toString()));
			opcionesvo.setCampo(campovo);
			opcionesvo.setDescripcion(opcionesplantillavo.getLabel());
			opcionesvo.setOrden(orden);
			orden ++;
			
			
			this.getFormulariosBloquesCamposOpcionesDAO().insertarBloqueCampoOpcionesPlantilla(opcionesvo);
		}
		

	}

}

public List<SelectItem> obtenerListaBloquesPorForm(long id) throws Exception {
	List<FormulariosBloquesVO> l = this.getFormulariosBloquesDAO().obtenerListaBloquesFormulario(id);
	
	List<SelectItem> lb = new ArrayList<SelectItem>();
	
	for (FormulariosBloquesVO bloqueVO : l) {
		SelectItem s = new SelectItem(bloqueVO.getId(), bloqueVO.getNombre());
		lb.add(s);
	}
	return lb;
}










	
	
}