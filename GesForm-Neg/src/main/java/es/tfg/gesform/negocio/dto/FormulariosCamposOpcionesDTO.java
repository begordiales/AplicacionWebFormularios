package es.tfg.gesform.negocio.dto;

import java.util.Date;


public class FormulariosCamposOpcionesDTO {
	private long ide;
	private long idCampo;
	private int valor;
	private String descripcion;
	private int orden;
	private Date fechaAlta;
	private Date fechaBaja;
	public long getIde() {
		return ide;
	}
	public void setIde(long ide) {
		this.ide = ide;
	}
	public long getIdCampo() {
		return idCampo;
	}
	public void setIdCampo(long idCampo) {
		this.idCampo = idCampo;
	}
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
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
