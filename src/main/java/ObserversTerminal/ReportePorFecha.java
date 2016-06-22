package ObserversTerminal;

import Master.ResultadoBusqueda;
import Master.Terminal;

import java.time.LocalDate;
import java.util.HashMap;

public class ReportePorFecha implements FuncionalidadExtraTerminal{

	//Atributos
	private HashMap<LocalDate,Integer>		cantidadBusquedasXFecha;

	//Constructor
	public ReportePorFecha(){
		cantidadBusquedasXFecha = new HashMap<LocalDate,Integer>();
	}
	
	//Getters y Setters	
	public void realizarAccion(Terminal unaTerminal, ResultadoBusqueda unResultadoBusqueda) {
		this.contabilizarBusquedaXFecha(unResultadoBusqueda);
	}

	// Reporte de busquedas por Fecha
	public void contabilizarBusquedaXFecha(ResultadoBusqueda unResultadoBusqueda){
		LocalDate fechaBusqueda = unResultadoBusqueda.getMomentoDeBusqueda().toLocalDate();
		if(cantidadBusquedasXFecha.get(fechaBusqueda) == null){ // Verifica si ya se hicieron busquedas en esa fecha
			cantidadBusquedasXFecha.put(fechaBusqueda, 0);
		}
		int cantidadAnterior = cantidadBusquedasXFecha.get(fechaBusqueda);
		cantidadBusquedasXFecha.put(fechaBusqueda,cantidadAnterior + 1);
	}

	public Integer busquedasEnFecha(LocalDate fecha){ // Sirve para el test por ahora
		return cantidadBusquedasXFecha.get(fecha);

	}
}