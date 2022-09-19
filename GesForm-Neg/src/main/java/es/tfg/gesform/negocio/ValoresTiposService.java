package es.tfg.gesform.negocio;

import java.util.List;
import javax.faces.model.SelectItem;

import es.tfg.gesform.negocio.dto.ValoresTiposDTO;



public interface ValoresTiposService {
//	ClientesVO buscar(Integer clienteNumber);

//	void insertar(ClientesVO cliente);

	List<ValoresTiposDTO> obtenerValoresTipos() throws Exception;
	List<SelectItem> obtenerListaValoresTipos(String CodigoTipo);
	String obtenerCodBasicaPorId(long id);
	
	
}