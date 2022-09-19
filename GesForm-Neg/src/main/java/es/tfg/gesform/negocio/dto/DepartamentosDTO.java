package es.tfg.gesform.negocio.dto;

import java.util.Date;

public class DepartamentosDTO {
	private long ide;
	private String nombre;
	private Date f_alta;
	private Date f_baja;
	
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
	
	public Date getFechaAlta() {
		return f_alta;
	}
	public void setFechaAlta(Date f_alta) {
		this.f_alta = f_alta;
	}
	
	public Date getFechaBaja() {
		return f_baja;
	}
	public void setFechaBaja(Date f_baja) {
		this.f_baja = f_baja;
	}

	
	
}
