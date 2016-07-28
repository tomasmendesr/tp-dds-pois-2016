package Procesos;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import Adapters.AdapterServicioRestBajaPOIs;
import Master.GestorProcesos;
import Master.POI;
import Master.RepositorioPOIs;

public class BajaDePOI extends Proceso{
	
	//CONSTRUCTOR
	
	public BajaDePOI(Integer id, Date fecha){
		this.setRepositorioDePOIs(RepositorioPOIs.getInstance());
		this.setFecha(fecha);
		this.setIdPOI(id);
	}

	//ATRIBUTOS

	private RepositorioPOIs 	repositorioDePOIs;
	private	Integer 			idPOI;
	private Date				fecha;

	//METODOS

	@Override
	public ResultadoProceso realizarProceso(){
		ResultadoProceso resultado = new ResultadoProceso();
		resultado.setMomentoDeEjecucion(LocalDateTime.now());
		this.darDeBaja();
		if(this.fueBorrado()) {
			resultado.setCantElementosAfectados(1);
			resultado.setResultadoDelProceso(true);
			} else {
				resultado.setCantElementosAfectados(0);
				resultado.setResultadoDelProceso(false);
				}
		return resultado;
	}
	
	public void darDeBaja(){
		this.getRepositorioDePOIs().getColeccionDePOIS().removeIf(poi -> poi.getID() == this.getIdPOI());
	}

	//EXTRAS

	public Boolean fueBorrado(){
		return !this.getRepositorioDePOIs().getColeccionDePOIS().stream().anyMatch(poi -> poi.getID() == this.getIdPOI());
	}

	//GETERS Y SETERS

	public RepositorioPOIs getRepositorioDePOIs() {
		return repositorioDePOIs;
	}

	public void setRepositorioDePOIs(RepositorioPOIs repositorioDePOIs) {
		this.repositorioDePOIs = repositorioDePOIs;
	}

	public Integer getIdPOI() {
		return idPOI;
	}

	public void setIdPOI(Integer idPOI) {
		this.idPOI = idPOI;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}