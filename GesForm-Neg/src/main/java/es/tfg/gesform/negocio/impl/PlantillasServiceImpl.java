package es.tfg.gesform.negocio.impl;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.tfg.comun.utils.Utilidades;


import es.tfg.gesform.negocio.PlantillasService;
import es.tfg.gesform.persistencia.dao.PlantillasDAO;
import es.tfg.gesform.negocio.dto.PlantillasDTO;
import es.tfg.gesform.negocio.dto.ValoresTiposDTO;
import es.tfg.gesform.persistencia.vo.PlantillasVO;
import es.tfg.gesform.persistencia.vo.ValoresTiposVO;


@Service
public class PlantillasServiceImpl implements PlantillasService {

	@Autowired
	private PlantillasDAO plantillasDAO;

	public PlantillasDTO obtenerPlantillaPorCodigo(String codigo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

//	public ClientesVO buscar(Integer clienteNumber) {
//		return clientesDAO.selectByPrimaryKey(clienteNumber);
//	}

//	public List<PlantillasDTO> obtenerTodasPlantillas(int limit)  throws Exception {
//		
//		try {
//			List<FormulariosVO> listaVO = new ArrayList<FormulariosVO>();
//			List<FormulariosDTO> listaDTO = new ArrayList<FormulariosDTO>();
//			
//		
//
//			listaVO = this.getFormulariosDAO().obtenerTodosFormularios(limit);
//
//			for (FormulariosVO vo : listaVO) {
//
//				FormulariosDTO dto = this.getFormulariosDTO(vo);
//
//				listaDTO.add(dto);
//			}
//
//			return listaDTO;
//
//		} catch (Exception e) {
//			throw e;
//		}
//
//	}
	
	
//	public FormulariosDTO getFormulariosDTO(FormulariosVO vo) throws Exception {
//
//		FormulariosDTO dto = null;
//
//		if (vo != null) {
//
//			dto = new FormulariosDTO();
//			//id
//			dto.setIde(Utilidades.lNoNullEsNull(vo.getId()));
//			dto.setNombre(Utilidades.sNoNull(vo.getNombre()));
//			dto.setDescripcion(Utilidades.sNoNull(vo.getDescripcion()));
//			
//			
//			if (vo.getConsejeria() != null && vo.getConsejeria().getId() != (long)-1) {
//				
//				dto.setConsejeria(new ConsejeriasDTO());
//				dto.getConsejeria().setIde(Utilidades.lNoNullEsNull(vo.getConsejeria().getId()));
//				dto.getConsejeria().setNombre(Utilidades.sNoNull(vo.getConsejeria().getNombre()));
//
//			}
//			
//			if (vo.getTipoform() != null) {
//	
//				dto.setTipoForm(new ValoresTiposDTO());
//				dto.getTipoForm().setIde(Utilidades.lNoNullEsNull(vo.getTipoform().getId()));
//				dto.getTipoForm().setCodigo(Utilidades.sNoNull(vo.getTipoform().getCodigo()));
//				dto.getTipoForm().setNombre(Utilidades.sNoNull(vo.getTipoform().getNombre()));
//				dto.getTipoForm().setDescripcion(Utilidades.sNoNull(vo.getTipoform().getDescripcion()));
//			}
//			
//			dto.setFechaIniPub(vo.getFechaInicio());
//			dto.setFechaFinPub(vo.getFechaFin());
//			
//			dto.setCip(Utilidades.sNoNull(vo.getCip()));
//			dto.setUrl(Utilidades.sNoNull(vo.getUrl()));
//			
//		}
//			
//	
//		return dto;
//	}
	
//	public FormulariosVO getFormulariosVO(FormulariosDTO dto) throws Exception {
//
//		FormulariosVO vo = null;
//
//		if (dto != null) {
//
//			vo = new FormulariosVO();
//			//id
//			vo.setId(Utilidades.lNoNullEsNull(dto.getIde()));
//			vo.setNombre(Utilidades.sNoNull(dto.getNombre()));
//			vo.setDescripcion(Utilidades.sNoNull(dto.getDescripcion()));
//			vo.setUrl(Utilidades.sNoNull(dto.getUrl()));
//			
//			
//			if (dto.getConsejeria() != null && dto.getConsejeria().getIde() != (long)-1) {
//				vo.setConsejeria(new ConsejeriasVO());
//				vo.getConsejeria().setId(Utilidades.lNoNullEsNull(dto.getConsejeria().getIde()));
////				vo.getConsejeria().setNombre(Utilidades.sNoNull(dto.getConsejeria().getNombre()));
//
//			}
//			
//			if (dto.getTipoForm() != null) {
//	
//				vo.setTipoform(new ValoresTiposVO());
//				//traer por ejemplo
//				vo.getTipoform().setId(Utilidades.lNoNullEsNull(dto.getTipoForm().getIde()));
////				vo.getTipoform().setCodigo(Utilidades.sNoNull(dto.getTipoForm().getCodigo()));
////				vo.getTipoform().setNombre(Utilidades.sNoNull(dto.getTipoForm().getNombre()));
////				vo.getTipoform().setDescripcion(Utilidades.sNoNull(dto.getTipoForm().getDescripcion()));
//			}
//			
//			vo.setFechaInicio(dto.getFechaIniPub());
//			vo.setFechaFin(dto.getFechaFinPub());
//			
//			vo.setCip(Utilidades.sNoNull(dto.getCip()));
//			vo.setUrl(Utilidades.sNoNull(dto.getUrl()));
//			
//		}
//			
//	
//		return vo;
//	}
	

	
//	@Transactional
//	public Long insertar(FormulariosDTO obj) throws Exception {
//
//			FormulariosVO vo = this.getFormulariosVO(obj);
//			if (vo != null) {
//				this.getFormulariosDAO().insertar(vo);
//			}
//			return vo.getId();
//
//	}

//	public void guardar(FormulariosDTO obj) {
//		FormulariosVO vo = this.getFormulariosDAO().selectByPrimaryKey(obj.getIde());
//		vo = this.establecerCamposEditables(vo, obj);
//		this.getFormulariosDAO().actualizar(vo);
//
//	}

//public PlantillasDTO obtenerPlantillaPorId(long id) throws Exception {
//		PlantillasVO vo = getPlantillasDAO().selectByPrimaryKey(id);
//		if (vo != null)
//			return this.getPlantillasDTO(vo);
//		else 
//			return null;
//	}
	
//private FormulariosVO establecerCamposEditables(FormulariosVO vo, FormulariosDTO dto) {
//		
//		if (dto != null) {
//			
//			if (vo == null) {
//				vo = new FormulariosVO();
//			}
//				
//			vo.setId(Utilidades.lNoNullEsNull(dto.getIde()));
//			vo.setNombre(Utilidades.sNoNull(dto.getNombre()));
//			vo.setDescripcion(Utilidades.sNoNull(dto.getDescripcion()));
//			vo.setUrl(Utilidades.sNoNull(dto.getUrl()));
//			
//			
//			if (dto.getConsejeria() != null && dto.getConsejeria().getIde() != (long)-1) {
//				if (vo.getConsejeria() == null || vo.getConsejeria().getId() == (long)-1) {
//					vo.setConsejeria(null);
//				}
//				vo.getConsejeria().setId(Utilidades.lNoNullEsNull(dto.getConsejeria().getIde()));
//
//			}
//			else {
//				vo.setConsejeria(null);
//			}
//			
//			if (dto.getTipoForm() != null) {
//
//				vo.getTipoform().setId(Utilidades.lNoNullEsNull(dto.getTipoForm().getIde()));
//			}
//			
//			vo.setFechaInicio(dto.getFechaIniPub());
//			vo.setFechaFin(dto.getFechaFinPub());
//			
//			vo.setCip(Utilidades.sNoNull(dto.getCip()));
//			vo.setUrl(Utilidades.sNoNull(dto.getUrl()));
//			
//		}
//		return vo;
//	}



	
	
}