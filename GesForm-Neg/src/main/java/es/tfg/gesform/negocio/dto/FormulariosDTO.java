package es.tfg.gesform.negocio.dto;

import java.util.Date;

public class FormulariosDTO {
	private long ide;
	private String nombre;
	private String descripcion;
	private String url;
	private Date fechaIniPub;
	private Date fechaFinPub;
	private DepartamentosDTO departamento;
	private ValoresTiposDTO tipoForm;
	private Date fechaAlta;
	private Date fechaBaja;
	
	public long getIde() {
		return ide;
	}
	public void setIde(long ide) {
		this.ide = ide;
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
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	
	public Date getFechaIniPub() {
		return fechaIniPub;
	}
	public void setFechaIniPub(Date fechaIniPub) {
		this.fechaIniPub = fechaIniPub;
	}
	
	public Date getFechaFinPub() {
		return fechaFinPub;
	}
	public void setFechaFinPub(Date fechaFinPub) {
		this.fechaFinPub = fechaFinPub;
	}
		
	public DepartamentosDTO getDepartamento() {
		return departamento;
	}
	public void setDepartamento(DepartamentosDTO departamento) {
		this.departamento = departamento;
	}
	public ValoresTiposDTO getTipoForm() {
		return tipoForm;
	}
	public void setTipoForm(ValoresTiposDTO tipoForm) {
		this.tipoForm = tipoForm;
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
	
}
