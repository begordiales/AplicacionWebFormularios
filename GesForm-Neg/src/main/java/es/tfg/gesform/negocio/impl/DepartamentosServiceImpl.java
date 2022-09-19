package es.tfg.gesform.negocio.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.tfg.comun.utils.PersistenciaException;
import es.tfg.comun.utils.Utilidades;
import es.tfg.gesform.negocio.DepartamentosService;
import es.tfg.gesform.negocio.dto.DepartamentosDTO;
import es.tfg.gesform.persistencia.dao.DepartamentosDAO;
import es.tfg.gesform.persistencia.vo.DepartamentosVO;




@Service
public class DepartamentosServiceImpl implements DepartamentosService {

	@Autowired
	private DepartamentosDAO DepartamentosDAO;


	public Long insertar(DepartamentosDTO obj) throws PersistenciaException {

		DepartamentosVO vo = this.getDepartamentosVO(obj);
		this.getDepartamentosDAO().insert(vo);
		return vo.getId();
	}

	public void guardar(DepartamentosDTO obj) {
		
		DepartamentosVO vo = this.getDepartamentosDAO().selectByPrimaryKey(obj.getIde());
		vo = this.establecerCamposEditables(vo, obj);
		this.getDepartamentosDAO().update(vo);
		
	}
	
	public void borrar(DepartamentosDTO obj) {
		DepartamentosVO vo = this.getDepartamentosDAO().selectByPrimaryKey(obj.getIde());
		if (vo != null) {
			vo.setFechaBaja(new Date());
			this.getDepartamentosDAO().update(vo);
		}
	}
	
	
    private DepartamentosVO establecerCamposEditables(DepartamentosVO vo, DepartamentosDTO dto) {
		
		if (dto != null) {
			
			if (vo == null) {
				vo = new DepartamentosVO();
			}
			
			vo.setNombre(dto.getNombre());
	 		vo.setId(Utilidades.lNoNullEsNull(dto.getIde()));
		 
		}
		return vo;
	}

	public List<SelectItem> obtenerListaDepartamentos() throws Exception {
		List<DepartamentosVO> l = this.getDepartamentosDAO().obtenerTodosActivos();
		
		List<SelectItem> lc = new ArrayList<SelectItem>();
		
		for (DepartamentosVO consejeriaVO : l) {
			SelectItem s = new SelectItem(consejeriaVO.getId(), consejeriaVO.getNombre());
			lc.add(s);
		}
		return lc;
	}

	
	public DepartamentosDTO obtenerDepartamentoPorId(Long id) throws PersistenciaException {
		DepartamentosVO vo = this.getDepartamentosDAO().selectByPrimaryKey(id);
		return this.getDepartamentosDTO(vo);
	}

	 
	public List<DepartamentosDTO> buscarTodos() throws PersistenciaException {
		List<DepartamentosVO> l = this.getDepartamentosDAO().obtenerDepartamentosPorEjemplo(new DepartamentosVO());

		List<DepartamentosDTO> ldto = new ArrayList<DepartamentosDTO>();

		for (Iterator iterator = l.iterator(); iterator.hasNext();) {
			DepartamentosVO vo = (DepartamentosVO) iterator.next();
			DepartamentosDTO dto = this.getDepartamentosDTO(vo);
			ldto.add(dto);
		}

		return ldto;
	}
	
	public List<DepartamentosDTO> obtenerDepartamentosPorEjemplo(DepartamentosDTO example)
			throws PersistenciaException {
		
		DepartamentosVO vo = this.getDepartamentosVO(example);
		
		List<DepartamentosVO> l = this.getDepartamentosDAO().obtenerDepartamentosPorEjemplo(vo);
		
		List<DepartamentosDTO> ldto = new ArrayList<DepartamentosDTO>();
		
		for (Iterator iterator = l.iterator(); iterator.hasNext();) {
			DepartamentosVO aux = (DepartamentosVO) iterator.next();
			DepartamentosDTO dto = this.getDepartamentosDTO(aux);
			ldto.add(dto);
		}

		return ldto;
	}


//	
	public DepartamentosDTO getDepartamentosDTO(DepartamentosVO vo) {

		DepartamentosDTO dto  = null;

		if (vo != null) {

			dto = new DepartamentosDTO();

			dto.setIde(Utilidades.lNoNullEsNull(vo.getId())); 
			dto.setNombre(Utilidades.sNoNull(vo.getNombre()));
			 

		}

		return dto;
	}
	
	
	public DepartamentosVO getDepartamentosVO(DepartamentosDTO dto) {

		DepartamentosVO vo  = null;

		if (dto != null) {

			vo = new DepartamentosVO();

			vo.setId(Utilidades.lNoNullEsNull(dto.getIde())); 
			vo.setNombre(Utilidades.sNoNull(dto.getNombre()));
			 

		}

		return vo;
	}


	public DepartamentosDAO getDepartamentosDAO() {
		return DepartamentosDAO;
	}


	public void setDepartamentosDAO(DepartamentosDAO DepartamentosDAO) {
		this.DepartamentosDAO = DepartamentosDAO;
	}
	
	
	
	
   
}
