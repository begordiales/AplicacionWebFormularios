package es.tfg.gesform.negocio.dto;

import java.util.Date;

import es.tfg.gesform.persistencia.vo.PlantillasVO;
import es.tfg.gesform.persistencia.vo.ValidacionesVO;
import es.tfg.gesform.persistencia.vo.ValoresTiposVO;

public class PlantillasCamposDTO {
	private long ide;
	private String nombre;
	private String etiqueta;
	private int obligatorio;
	private int tamano;
	private int orden;
	private PlantillasVO plantilla;
	private ValoresTiposVO tipocampo;
	private ValidacionesVO validacion;
	private Date fechaAlta;
	private Date fechaBaja;
	private String valor;
	private int tamanofichero;
	private String tiposfichero;
	private int numficheros;
	
	
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
	public String getEtiqueta() {
		return etiqueta;
	}
	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}
	public int getObligatorio() {
		return obligatorio;
	}
	public void setObligatorio(int obligatorio) {
		this.obligatorio = obligatorio;
	}
	public int getTamano() {
		return tamano;
	}
	public void setTamano(int tamano) {
		this.tamano = tamano;
	}
	public int getOrden() {
		return orden;
	}
	public void setOrden(int orden) {
		this.orden = orden;
	}
	public PlantillasVO getPlantilla() {
		return plantilla;
	}
	public void setPlantilla(PlantillasVO plantilla) {
		this.plantilla = plantilla;
	}
	public ValoresTiposVO getTipocampo() {
		return tipocampo;
	}
	public void setTipocampo(ValoresTiposVO tipocampo) {
		this.tipocampo = tipocampo;
	}
	public ValidacionesVO getValidacion() {
		return validacion;
	}
	public void setValidacion(ValidacionesVO validacion) {
		this.validacion = validacion;
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
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public int getTamanofichero() {
		return tamanofichero;
	}
	public void setTamanofichero(int tamanofichero) {
		this.tamanofichero = tamanofichero;
	}
	public String getTiposfichero() {
		return tiposfichero;
	}
	public void setTiposfichero(String tiposfichero) {
		this.tiposfichero = tiposfichero;
	}
	public int getNumficheros() {
		return numficheros;
	}
	public void setNumficheros(int numficheros) {
		this.numficheros = numficheros;
	}
	
		
	
}
