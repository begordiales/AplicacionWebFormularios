package es.tfg.gesform.negocio.dto;

import java.util.List;


public class ContenidoFormularioDTO {
	private long idForm;
	private String codigoCip;
	private List<FormulariosBloquesDTO> bloques;
	
	public long getIdForm() {
		return idForm;
	}
	public void setIdForm(long idForm) {
		this.idForm = idForm;
	}
	
	public String getCodigoCip() {
		return codigoCip;
	}
	public void setCodigoCip(String codigoCip) {
		this.codigoCip = codigoCip;
	}
	public List<FormulariosBloquesDTO> getBloques() {
		return bloques;
	}
	public void setBloques(List<FormulariosBloquesDTO> bloques) {
		this.bloques = bloques;
	}
	
	
}
