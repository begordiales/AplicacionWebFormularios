package es.tfg.gesform.negocio.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.tfg.comun.utils.Utilidades;
import es.tfg.gesform.negocio.FormulariosEnviosService;
import es.tfg.gesform.negocio.dto.FormulariosEnviosDTO;
import es.tfg.gesform.persistencia.dao.FormulariosEnviosDAO;
import es.tfg.gesform.persistencia.vo.FormulariosEnviosVO;
import es.tfg.gesform.persistencia.vo.FormulariosVO;


@Service
public class FormulariosEnviosServiceImpl implements FormulariosEnviosService {

	@Autowired
	private FormulariosEnviosDAO formulariosEnviosDAO;
	
	
	
	public FormulariosEnviosDTO getFormulariosEnviosDTO(FormulariosEnviosVO vo) throws Exception {

		FormulariosEnviosDTO dto = null;

		if (vo != null) {

			dto = new FormulariosEnviosDTO();
			//id
			dto.setIde(Utilidades.lNoNullEsNull(vo.getId()));
			dto.setIdForm(Utilidades.lNoNullEsNull(vo.getFormulario().getId()));
			dto.setNombreForm(Utilidades.sNoNull(vo.getFormulario().getNombre()));
			dto.setFechaEnvio(vo.getFechaEnvio());
			dto.setFechaBaja(vo.getFechaBaja());
			
		}
			
		return dto;
	}
	
	public FormulariosEnviosVO getFormulariosEnviosVO(FormulariosEnviosDTO dto) throws Exception {

		FormulariosEnviosVO vo = null;

		if (dto != null) {

			vo = new FormulariosEnviosVO();
			//id
			vo.setId(Utilidades.lNoNullEsNull(dto.getIde()));
			vo.setFormulario(new FormulariosVO());
			vo.getFormulario().setId(Utilidades.lNoNullEsNull(dto.getIdForm()));
			if (vo.getFechaEnvio()!=null)
				vo.setFechaEnvio(vo.getFechaEnvio());
			if (vo.getFechaBaja()!=null)
				vo.setFechaBaja(dto.getFechaBaja());
			
		}
			
		return vo;
	}
	



	@Transactional
	public long insertar(long idForm) throws Exception {
		FormulariosEnviosVO envio = new FormulariosEnviosVO();
		envio.setFormulario(new FormulariosVO());
		envio.getFormulario().setId(idForm);
			this.getFormulariosEnviosDAO().insertar(envio);

		return envio.getId();

		
	}
	
	
public List<FormulariosEnviosDTO> obtenerTodosEnvios(int limit)  throws Exception {
		
		try {
			List<FormulariosEnviosVO> listaVO = new ArrayList<FormulariosEnviosVO>();
			List<FormulariosEnviosDTO> listaDTO = new ArrayList<FormulariosEnviosDTO>();
			
		

			listaVO = this.getFormulariosEnviosDAO().obtenerTodosEnvios(limit);

			for (FormulariosEnviosVO vo : listaVO) {

				FormulariosEnviosDTO dto = this.getFormulariosEnviosDTO(vo);

				listaDTO.add(dto);
			}

			return listaDTO;

		} catch (Exception e) {
			throw e;
		}

	}


public List<SelectItem> obtenerFormulariosConEnvios() {
	List<Object> l = this.getFormulariosEnviosDAO().obtenerFormulariosEnvios();
	
	List<SelectItem> lc = new ArrayList<SelectItem>();
	for (Object envioVO : l) {
		Object[] array = (Object[]) envioVO;
			SelectItem s = new SelectItem(Utilidades.sNoNull(array[0]), Utilidades.sNoNull(array[1]));
			lc.add(s);
		}
	return lc;
}


public boolean tieneEnvios(long idForm) throws Exception {
	List<FormulariosEnviosVO> listaVO = new ArrayList<FormulariosEnviosVO>(); 
	listaVO = this.getFormulariosEnviosDAO().obtenerEnviosForm(idForm);
	if (listaVO.isEmpty())
		return false;
	else
		return true;
}


	public FormulariosEnviosDTO obtenerEnvioPorId(long idEnvio) throws Exception {
		FormulariosEnviosVO vo = getFormulariosEnviosDAO().selectByPrimaryKey(idEnvio);
		if (vo != null)
			return this.getFormulariosEnviosDTO(vo);
		else 
			return null;
	}

	public FormulariosEnviosDAO getFormulariosEnviosDAO() {
		return formulariosEnviosDAO;
	}

	public void setFormulariosEnviosDAO(FormulariosEnviosDAO formulariosEnviosDAO) {
		this.formulariosEnviosDAO = formulariosEnviosDAO;
	}

	public List<FormulariosEnviosDTO> obtenerEnviosFechasPorEjemplo(FormulariosEnviosDTO example, Date fechaDesde,Date fechaHasta) throws Exception {

			FormulariosEnviosVO vo = this.getFormulariosEnviosVO(example);

			List<FormulariosEnviosVO> l = this.getFormulariosEnviosDAO().obtenerEnviosFechasVOPorEjemplo(vo,fechaDesde,fechaHasta);

			List<FormulariosEnviosDTO> ldto = new ArrayList<FormulariosEnviosDTO>();

			for (Iterator iterator = l.iterator(); iterator.hasNext();) {
				FormulariosEnviosVO aux = (FormulariosEnviosVO) iterator.next();
				FormulariosEnviosDTO dto = this.getFormulariosEnviosDTO(aux);
				ldto.add(dto);
			}

			return ldto;
		}
	

}