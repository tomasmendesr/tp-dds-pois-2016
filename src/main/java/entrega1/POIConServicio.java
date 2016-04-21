package entrega1;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.joda.time.DateTime;

public abstract class POIConServicio extends POI {

		
	//ATRIBUTOS
	
	private ArrayList<Servicio> 	servicios;
	
	
	//GETERS Y SETERS
	
	public ArrayList<Servicio> getColeccionServicios(){
		return servicios;
	}
	
	public void setColeccionServicios(ArrayList<Servicio> coleccionDeServicios){
		servicios = coleccionDeServicios;
	}
	
	public void addServicio(Servicio unServicio){
		servicios.add(unServicio);
	}
	
	//METODOS
	
	public boolean estaDisponible(String unNombreDeServicio,Tiempo unTiempo){
		if(unNombreDeServicio == null){
			return this.algunServicioDisponible();
		} else {		
			return this.servicioDisponible(unNombreDeServicio,unTiempo);
		}
	}
	
	public boolean algunServicioDisponible(){
		Tiempo 	horaDelMomento = this.crearHoraDelMomento();	//Instancio la hora del momento
		return	this.getColeccionServicios().stream().
				anyMatch(servicio -> servicio.estaDentroDelRangoDeAtencion(horaDelMomento));
	}
	
	public Tiempo crearHoraDelMomento(){
		DateTime dateTime = new DateTime();
		int		diaSemana = dateTime.getDayOfWeek();
		double 	hora = dateTime.getHourOfDay();
		double	minutos = dateTime.getMinuteOfHour();
		Fecha	fecha = new Fecha(dateTime.getDayOfMonth(),dateTime.getMonthOfYear(),dateTime.getYear());
		return new Tiempo(fecha,diaSemana,hora,minutos);
	}
	
	public boolean servicioDisponible(String unNombreDeServicio, Tiempo unTiempo){
		return this.buscarServicio(unNombreDeServicio).estaDentroDelRangoDeAtencion(unTiempo);
	}
	
	public Servicio buscarServicio(String unNombreDeServicio){
		return this.getColeccionServicios().stream().
				filter(servicio -> servicio.getNombre() == unNombreDeServicio).
				collect(Collectors.toList()).get(0); //SE SUPONE QUE EL SERVICIO INGRESADO SIEMPRE ES VALIDO
	}
}