package es.tfg.gesform.negocio;

import java.util.List;
import javax.faces.model.SelectItem;

import es.tfg.comun.utils.PersistenciaException;
import es.tfg.gesform.negocio.dto.DepartamentosDTO;
import es.tfg.gesform.persistencia.dao.DepartamentosDAO;
import es.tfg.gesform.persistencia.vo.DepartamentosVO;

public interface DepartamentosService {
List<SelectItem> obtenerListaDepartamentos() throws Exception;
public Long insertar(DepartamentosDTO obj) throws PersistenciaException;
public void guardar(DepartamentosDTO obj);
public void borrar(DepartamentosDTO obj);
public DepartamentosDTO obtenerDepartamentoPorId(Long id) throws PersistenciaException;
public List<DepartamentosDTO> buscarTodos() throws PersistenciaException;
public List<DepartamentosDTO> obtenerDepartamentosPorEjemplo(DepartamentosDTO example);
public DepartamentosDAO getDepartamentosDAO();
public void setDepartamentosDAO(DepartamentosDAO departamentosDAO);
public DepartamentosDTO getDepartamentosDTO(DepartamentosVO vo);
public DepartamentosVO getDepartamentosVO(DepartamentosDTO dto);
	
}