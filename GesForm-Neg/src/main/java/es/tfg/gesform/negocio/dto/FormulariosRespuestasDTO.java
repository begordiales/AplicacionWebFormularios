package es.tfg.gesform.negocio.dto;


public class FormulariosRespuestasDTO {
	private long idEnvio;
	private long idCampo;
	private long idTipocampo;
	private String nombreCampo;
	private String codigoTipoCampo;
	private String codigoValidacion;
	private String funcionValidacion;
	private int obligatorio;
	private Object respuesta;
	
	public long getIdEnvio() {
		return idEnvio;
	}
	public void setIdEnvio(long idEnvio) {
		this.idEnvio = idEnvio;
	}
	public long getIdCampo() {
		return idCampo;
	}
	public void setIdCampo(long idCampo) {
		this.idCampo = idCampo;
	}
	
	public long getIdTipocampo() {
		return idTipocampo;
	}
	public void setIdTipocampo(long idTipocampo) {
		this.idTipocampo = idTipocampo;
	}
	
	
	public String getNombreCampo() {
		return nombreCampo;
	}
	public void setNombreCampo(String nombreCampo) {
		this.nombreCampo = nombreCampo;
	}
	public String getCodigoTipoCampo() {
		return codigoTipoCampo;
	}
	public void setCodigoTipoCampo(String codigoTipoCampo) {
		this.codigoTipoCampo = codigoTipoCampo;
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
	
	public int getObligatorio() {
		return obligatorio;
	}
	public void setObligatorio(int obligatorio) {
		this.obligatorio = obligatorio;
	}
	public Object getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(Object respuesta) {
		this.respuesta = respuesta;
	}

	
	
		
}
