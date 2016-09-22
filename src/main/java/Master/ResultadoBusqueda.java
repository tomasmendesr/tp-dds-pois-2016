package Master;

import DAO.ResultadoBusquedaDAOImpl;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity@Table
public class ResultadoBusqueda{
	
	// ATRIBUTOS
	@Id	@GeneratedValue @Column(name="RESULTADO_BUSQUEDA_ID")
	private int 			id;
	@Column(name="FRASE_BUSCADA")
	private String 			fraseBuscada;
	@Column(name="DURACION_BUSQUEDA")
	private double 			duracionBusqueda;
	@Transient
	private List<POI>		resultadoBusqueda;
	@Column (name="RES_MOMENTO_BUSQUEDA")
	private LocalDateTime	momentoDeBusqueda;
	@Column(name="TIEMPO_ESTIMADO_BUSQUEDA")
	private Double			tiempoEstimadoBusqueda;
	@Column(name = "RES_TERMINAL_ID") @ManyToOne
	private Long			terminalId;
	@Transient
	private ResultadoBusquedaDAOImpl resultadoBusquedaDAO;
	
	//CONSTRUCTOR
	public ResultadoBusqueda(){}

	public ResultadoBusqueda(String fraseBuscada, List<POI> resultadoBusqueda, double duracionConsulta, Terminal terminal){
		setMomentoDeBusqueda(LocalDateTime.now());
		setFraseBuscada(fraseBuscada);
		setResultadoBusqueda(resultadoBusqueda);
		setDuracionBusqueda(duracionConsulta);
		setTerminalId(terminal.getId());
		//resultadoBusquedaDAO = new ResultadoBusquedaDAOImpl(); aca tira nullPointer
	}
	
	//METODOS
	public int cantidadDeResultados(){
		return this.getResultadoBusqueda().size();
	}

	public boolean sinTiempoEstimado(){
		return tiempoEstimadoBusqueda == null;
	}

	public void persistite(){
		resultadoBusquedaDAO.persistirResultadoBusqueda(this);
	}
	
	// GETTERS Y SETTERS
	public int getId() { return id;  }

	public void setId(int id) {  this.id = id;  }

	public int getCantidadDeResultados(){
		return resultadoBusqueda.size();
	}
	
	public String getFraseBuscada() {
		return fraseBuscada;
	}

	public void setFraseBuscada(String fraseBuscada) {
		this.fraseBuscada = fraseBuscada;
	}

	public List<POI> getResultadoBusqueda() {
		return resultadoBusqueda;
	}

	public void setResultadoBusqueda(List<POI> resultadoBusqueda) {
		this.resultadoBusqueda = resultadoBusqueda;
	}

	public double getDuracionBusqueda(){
		return duracionBusqueda;
	}
		
	public void setDuracionBusqueda(double unaDuracionBusqueda){
		duracionBusqueda = unaDuracionBusqueda;
	}
	
	public LocalDateTime getMomentoDeBusqueda() {
		return momentoDeBusqueda;
	}

	public void setMomentoDeBusqueda(LocalDateTime momentoDeBusqueda) {
		this.momentoDeBusqueda = momentoDeBusqueda;
	}
	
	public double getTiempoEstimadoBusqueda() {
		return tiempoEstimadoBusqueda;
	}

	public void setTiempoEstimadoBusqueda(double tiempoEstimadoBusqueda) {
		this.tiempoEstimadoBusqueda = tiempoEstimadoBusqueda;
	}

	public Long getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(Long terminalId) {
		this.terminalId = terminalId;
	}


}
