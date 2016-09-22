package ObserversTerminal;

import Master.ResultadoBusqueda;
import Master.Terminal;

import java.util.HashMap;

import javax.persistence.Entity;

@Entity
public class ReporteTotalesPorUsuario extends FuncionalidadExtraTerminal {
	
	//Atributos
	private HashMap<Terminal,Integer> 		resultadosPorTerminal;
	
	//Constructor
	public ReporteTotalesPorUsuario(){
		resultadosPorTerminal = new HashMap<Terminal,Integer>();
	}
	
	//Metodos
	public void realizarAccion(Terminal unaTerminal, ResultadoBusqueda unResultadoBusqueda) {		
		this.generarTotalesPorUsuario(unaTerminal); // Guardo los datos
	}

	// Reporte totales por usuario
	public void generarTotalesPorUsuario(Terminal unaTerminal){
		resultadosPorTerminal.put(unaTerminal, unaTerminal.obtenerResultadosTotales());
	}
	
	public Integer generarReporteTotalPorTerminal(Terminal terminal){ // Sirve para el test por ahora
		return resultadosPorTerminal.get(terminal);
	}
}
