package es.tfg.gesform.negocio.dto;

import java.util.Date;

import es.tfg.gesform.persistencia.vo.PlantillasCamposVO;
import es.tfg.gesform.persistencia.vo.PlantillasVO;
import es.tfg.gesform.persistencia.vo.ValidacionesVO;
import es.tfg.gesform.persistencia.vo.ValoresTiposVO;

public class PlantillasCamposOpcionesDTO {
	private long id;
	private int valor;
	private String descripcion;
	private int orden;
	private PlantillasCamposVO campo;
	private Date fechaAlta;
	private Date fechaBaja;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public PlantillasCamposVO getCampo() {
		return campo;
	}
	public void setCampo(PlantillasCamposVO campo) {
		this.campo = campo;
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
