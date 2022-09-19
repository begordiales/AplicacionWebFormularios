package es.tfg.gesform.negocio.impl;

import java.util.List;

import javax.faces.model.SelectItem;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.tfg.comun.utils.Utilidades;

import es.tfg.gesform.negocio.FormulariosBloquesCamposOpcionesService;
import es.tfg.gesform.persistencia.dao.FormulariosBloquesCamposOpcionesDAO;
import es.tfg.gesform.negocio.dto.DepartamentosDTO;
import es.tfg.gesform.negocio.dto.ValoresTiposDTO;
import es.tfg.gesform.persistencia.vo.FormulariosCamposOpcionesVO;



@Service
public class FormulariosBloquesCamposOpcionesServiceImpl implements FormulariosBloquesCamposOpcionesService {

	@Autowired
	private FormulariosBloquesCamposOpcionesDAO opcionesDAO;

//	public ClientesVO buscar(Integer clienteNumber) {
//		return clientesDAO.selectByPrimaryKey(clienteNumber);
//	}




	public List<SelectItem> obtenerListaOpcionesPorIdCampo(long idCampo) throws Exception {
		List<FormulariosCamposOpcionesVO> l = this.getOpcionesDAO().obtenerOpcionesCampos(idCampo);
		
		List<SelectItem> lo = new ArrayList<SelectItem>();
		
		for (FormulariosCamposOpcionesVO opcionvo : l) {
			SelectItem s = new SelectItem(opcionvo.getId(), opcionvo.getDescripcion());
			lo.add(s);
		}
		return lo;
	}



	public FormulariosBloquesCamposOpcionesDAO getOpcionesDAO() {
		return opcionesDAO;
	}

	public void setOpcionesDAO(FormulariosBloquesCamposOpcionesDAO opcionesDAO) {
		this.opcionesDAO = opcionesDAO;
	}







	
}