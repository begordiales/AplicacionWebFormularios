package es.tfg.gesform.negocio.dto;

import java.util.Date;
import java.util.List;




public class FormulariosBloquesDTO {
	
	
	private long id;
	private String nombre;
	private String descripcion;
	private int orden;
	private int columnas;
	private long idForm;
	private long idPlantilla;
	private String codigoPlantilla;
	private List<FormulariosCamposDTO> campos;
	private Date fechaAlta;
	private Date fechaBaja;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getOrden() {
		return orden;
	}
	public void setOrden(int orden) {
		this.orden = orden;
	}

	public int getColumnas() {
		return columnas;
	}
	public void setColumnas(int columnas) {
		this.columnas = columnas;
	}
	public List<FormulariosCamposDTO> getCampos() {
		return campos;
	}
	public void setCampos(List<FormulariosCamposDTO> campos) {
		this.campos = campos;
	}
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public Date getFechaBaja() {
		return fechaBaja;
	}
	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
	public long getIdForm() {
		return idForm;
	}
	public void setIdForm(long idForm) {
		this.idForm = idForm;
	}
	public long getIdPlantilla() {
		return idPlantilla;
	}
	public void setIdPlantilla(long idPlantilla) {
		this.idPlantilla = idPlantilla;
	}
	public String getCodigoPlantilla() {
		return codigoPlantilla;
	}
	public void setCodigoPlantilla(String codigoPlantilla) {
		this.codigoPlantilla = codigoPlantilla;
	}
	
	
		
}
