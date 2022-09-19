package es.tfg.gesform.negocio.dto;

import java.util.Date;
import java.util.List;
import javax.faces.model.SelectItem;


public class FormulariosCamposDTO {
	private long ide;
	private String nombre;
	private String etiqueta;
	private int obligatorio;
	private int tamano;
	private int ancho;
	private int orden;
	private long idBloque;
	private long idTipocampo;
	private String codigoTipoCampo;
	private long idValidacion;
	private String codigoValidacion;
	private String funcionValidacion;
	//private List<FormulariosCamposOpcionesDTO> listaopciones;
	private List<SelectItem> listaopciones;
	private Date fechaAlta;
	private Date fechaBaja;
	private String valor;
	private Integer tamanofichero;
	private String tiposfichero;
	private Integer numficheros;
	private Object respuesta;
	
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
	
	
	public int getAncho() {
		return ancho;
	}
	public void setAncho(int ancho) {
		this.ancho = ancho;
	}
	public int getOrden() {
		return orden;
	}
	public void setOrden(int orden) {
		this.orden = orden;
	}
	
	public long getIdBloque() {
		return idBloque;
	}
	public void setIdBloque(long idBloque) {
		this.idBloque = idBloque;
	}
	public long getIdTipocampo() {
		return idTipocampo;
	}
	public void setIdTipocampo(long idTipocampo) {
		this.idTipocampo = idTipocampo;
	}
	
	public String getCodigoTipoCampo() {
		return codigoTipoCampo;
	}
	public void setCodigoTipoCampo(String codigoTipoCampo) {
		this.codigoTipoCampo = codigoTipoCampo;
	}
	public long getIdValidacion() {
		return idValidacion;
	}
	public void setIdValidacion(long idValidacion) {
		this.idValidacion = idValidacion;
	}
	
	public String getCodigoValidacion() {
		return codigoValidacion;
	}
	public void setCodigoValidacion(String codigoValidacion) {
		this.codigoValidacion = codigoValidacion;
	}
	public String getFuncionValidacion() {
		return funcionValidacion;
	}
	public void setFuncionValidacion(String funcionValidacion) {
		this.funcionValidacion = funcionValidacion;
	}
	
	
	
	public List<SelectItem> getListaopciones() {
		return listaopciones;
	}
	public void setListaopciones(List<SelectItem> listaopciones) {
		this.listaopciones = listaopciones;
	}
	//	public List<FormulariosCamposOpcionesDTO> getListaopciones() {
//		return listaopciones;
//	}
//	public void setListaopciones(List<FormulariosCamposOpcionesDTO> listaopciones) {
//		this.listaopciones = listaopciones;
//	}
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
	public Object getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(Object respuesta) {
		this.respuesta = respuesta;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getTiposfichero() {
		return tiposfichero;
	}
	public void setTiposfichero(String tiposfichero) {
		this.tiposfichero = tiposfichero;
	}
	public Integer getTamanofichero() {
		return tamanofichero;
	}
	public void setTamanofichero(Integer tamanofichero) {
		this.tamanofichero = tamanofichero;
	}
	public Integer getNumficheros() {
		return numficheros;
	}
	public void setNumficheros(Integer numficheros) {
		this.numficheros = numficheros;
	}


	
	
}
