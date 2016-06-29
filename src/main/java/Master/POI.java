package Master;

import org.uqbar.geodds.Point;

import POIsExt.Comuna;
import POIsExt.RangoDeAtencion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.String;

public abstract class POI {
/*
	@Override
	public boolean equals(Object obj){
	  if (!(obj instanceof POI))
	    return false;
	  POI unPOI = (POI) obj;
	  return (this.getNombre().equals(unPOI.getNombre()) &&  this.getDireccion().equals(unPOI.getDireccion()));
	}*/
	
	//ATRIBUTOS
	private Point 					ubicacion;
	private String 					nombre;
	private String 					direccion;
	private List<String> 			tags; //Array de String que contienen todos los tags de busqueda libre
	private List<RangoDeAtencion>	listaDeRangosDeAtencion; 
	private Comuna					comuna;
	
	
	//CONSTRUCTOR 
	public POI(Point unaUbicacion) {
		this.setUbicacion(unaUbicacion);
		this.instanciarNuevaColeccionDeTags(); //Para inicializar el ArrayList de Tags
	}	
		
	
	//GETTERS Y SETTERS
	void setUbicacion(Point unaUbicacion) {
		ubicacion = unaUbicacion;
		
	}
	
	public List<RangoDeAtencion> getListaDeRangosDeAtencion() {
		return listaDeRangosDeAtencion;
	}

	public void setListaDeRangosDeAtencion(List<RangoDeAtencion> unaListaDeRangosDeAtencion) {
		listaDeRangosDeAtencion =  unaListaDeRangosDeAtencion;
	}
	

	public Point getUbicacion(){
		return ubicacion;
	}
	
	public String getNombre(){
		return nombre;
	}
	
	public void setNombre(String unNombre){
		nombre = unNombre;
	}
	
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String unaDireccion) {
		direccion = unaDireccion;
	}
	
	public void instanciarNuevaColeccionDeTags(){
		tags = new ArrayList<String>();
	}
	
	public List<String> getTags(){
		return tags;
	}
	
	public void addTag(String tag){//Agrega un tag al ArrayList
		tags.add(tag);
	}
	
	public void removeTag(String tag){
		tags.remove(tag);
	}
	
	public void setComuna(Comuna unaComuna){
		comuna = unaComuna;
	}
	
	public Comuna getComuna(){
		return this.comuna;
	}
	//METODOS
	
	public boolean estaAMenosDeXMetrosDeOtroPOI(POI otroPOI,double metros){
		return this.getUbicacion().distance(otroPOI.getUbicacion())*1000 < metros;	// Para pasarlo a metros
	}
	
	public boolean esPOIValido(){
		return this.estaGeolocalizado() && this.tieneNombre();
	}
	
	public boolean estaGeolocalizado(){
		return this.getUbicacion() != null;
	}
	
	public boolean tieneNombre(){
		return this.getNombre() != null;
	}
	
	
	// Busqueda por texto libre
	public boolean detectarTagBuscado(String unTag){
		return tags.contains(unTag);
	}

	//Calculo de cercania
	
	public boolean estaCercaDe(Point unaUbicacion){
		return this.getUbicacion().distance(unaUbicacion) * 1000 < this.cercaniaRequerida();
	}
	
	public double cercaniaRequerida(){ // Defino la cercania requerida standar
		return 500.0;
	}
	
	public boolean estaCercaDeUnPOI(POI unPOI){
		return this.estaAMenosDeXMetrosDeOtroPOI(unPOI, 500); //para pasar a metros
	}
	
	public void quitarTagAModificar(String tagAModificar){
		tags.removeIf(tag -> tag == tagAModificar);
	}
	
	// Es valido
	public boolean esValido(){
		return (this.estaGeolocalizado() && this.tieneNombre()); 
	}

	// Identificar si tiene el nombre especificado en el texto(para locales comerciales)
	public Integer tieneElNombreEspecificado(String unNombre,  String[] unasPalabras){
		if(nombre.equals(unNombre)){ return this.tienePalabrasEspecificadas(unasPalabras); }
		else return 0;
	}

	public Integer tienePalabrasEspecificadas(String[] unasPalabras) { return 0; }

}
