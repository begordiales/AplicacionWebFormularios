package es.tfg.gesform.negocio.dto;

import java.util.Date;

public class ValoresTiposDTO {
	private long ide;
	private String nombre;
	private String descripcion;
	private String codigo;
	private TiposDTO tipo;
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
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
	
	public TiposDTO getTipo() {
		return tipo;
	}
	public void setTipo(TiposDTO tipo) {
		this.tipo = tipo;
	}

	
	
}
