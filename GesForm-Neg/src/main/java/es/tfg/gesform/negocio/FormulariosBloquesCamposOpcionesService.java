package es.tfg.gesform.negocio;

import java.util.List;
import javax.faces.model.SelectItem;


public interface FormulariosBloquesCamposOpcionesService {
//	ClientesVO buscar(Integer clienteNumber);

//	void insertar(ClientesVO cliente);

List<SelectItem> obtenerListaOpcionesPorIdCampo(long idCampo) throws Exception;
	
	
}