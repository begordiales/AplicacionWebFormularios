package es.tfg.gesform.negocio.dto;

import java.sql.Timestamp;
import java.util.Date;

import org.exolab.castor.types.DateTime;

public class FormulariosEnviosDTO {
	private long ide;
	private long idForm;
	private String nombreForm;
	private Timestamp fechaEnvio;
	private Date fechaBaja;
	public long getIde() {
		return ide;
	}
	public void setIde(long ide) {
		this.ide = ide;
	}
	public long getIdForm() {
		return idForm;
	}
	public void setIdForm(long idForm) {
		this.idForm = idForm;
	}
	
	public String getNombreForm() {
		return nombreForm;
	}
	public void setNombreForm(String nombreForm) {
		this.nombreForm = nombreForm;
	}
	public Timestamp getFechaEnvio() {
		return fechaEnvio;
	}
	public void setFechaEnvio(Timestamp fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}
	public Date getFechaBaja() {
		return fechaBaja;
	}
	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
	
	
	
}
