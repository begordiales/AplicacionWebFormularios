package es.tfg.gesform.negocio.dto;
 
import java.util.Date;

public class FormulariosUsuariosDTO {
	private long ide;
	private long idForm;
	private long idUsuario;
	private Date fechaAlta;
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
	public long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	
	
}
