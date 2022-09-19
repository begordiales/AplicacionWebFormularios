package es.tfg.gesform.negocio.impl;

import java.util.List;
import java.util.ArrayList;

import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.tfg.comun.utils.Utilidades;

import es.tfg.gesform.negocio.ValoresTiposService;
import es.tfg.gesform.persistencia.dao.ValoresTiposDAO;
import es.tfg.gesform.negocio.dto.ValoresTiposDTO;
import es.tfg.gesform.persistencia.vo.ValoresTiposVO;
import es.tfg.gesform.negocio.dto.TiposDTO;


@Service
public class ValoresTiposServiceImpl implements ValoresTiposService {

	@Autowired
	private ValoresTiposDAO valoresTiposDAO;

//	public ClientesVO buscar(Integer clienteNumber) {
//		return clientesDAO.selectByPrimaryKey(clienteNumber);
//	}

	public List<ValoresTiposDTO> obtenerValoresTipos() {

		List<ValoresTiposVO> listaVO = new ArrayList<ValoresTiposVO>();
		List<ValoresTiposDTO> listaDTO = new ArrayList<ValoresTiposDTO>();

		listaVO = this.getValoresTiposDAO().obtenerTodosActivos();

		for (ValoresTiposVO vo : listaVO) {

			ValoresTiposDTO dto = this.getValoresTiposDTO(vo);

			listaDTO.add(dto);
		}

		return listaDTO;
	}
	

	/**
	 * Obtenemos el listado de valores tipos convertidos en una lista de SelectItem
	 * 
	 * @param CodigoTipo
	 * @return
	 */
	
	public List<SelectItem> obtenerListaValoresTipos(String CodigoTipo) {

		List<ValoresTiposVO> lista = getValoresTiposDAO().obtenerValoresTiposPorCodBasica(CodigoTipo);	
		List<SelectItem> salida = new ArrayList<SelectItem>();

		for(ValoresTiposVO elementoValoresTipos : lista) {
			SelectItem select = new SelectItem(elementoValoresTipos.getId(),elementoValoresTipos.getNombre());
			salida.add(select);
		}
		return salida;
	}
	
	
	
public String obtenerCodBasicaPorId(long id) {
	ValoresTiposVO vo = getValoresTiposDAO().selectByPrimaryKey(id);
	if (vo!=null)
			return vo.getCodigo();
	else
		return null;
}
	
public ValoresTiposDTO getValoresTiposDTO(ValoresTiposVO vo) {
		
		ValoresTiposDTO dto  = null;
		
		if (vo != null) {
			
			//TiposBo tiposBo = new TiposBoImpl();
			
			dto = new ValoresTiposDTO();
			
			dto.setIde(Utilidades.lNoNullEsNull(vo.getId()));
			dto.setCodigo(Utilidades.sNoNull(vo.getCodigo()));
			dto.setNombre(Utilidades.sNoNull(vo.getNombre()));
			dto.setDescripcion(Utilidades.sNoNull(vo.getDescripcion()));
			
			if (vo.getTipo() != null) {
				dto.setTipo(new TiposDTO());
				dto.getTipo().setCodigo(vo.getTipo().getCodigo());
				dto.getTipo().setNombre(vo.getTipo().getNombre());
				dto.getTipo().setDescripcion(vo.getTipo().getDescripcion());
			}		
		}
		
		return dto;
		
	}


	public ValoresTiposDAO getValoresTiposDAO() {
		return valoresTiposDAO;
	}
	
	
	public void setValoresTiposDAO(ValoresTiposDAO valoresTiposDAO) {
		this.valoresTiposDAO = valoresTiposDAO;
	}
	



//	@Transactional
//	public void insertar(ClientesVO cliente) {
//		this.clientesDAO.insert(cliente);
//	}
	
	
}