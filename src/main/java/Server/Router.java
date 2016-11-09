package Server;


import Controller.BusquedaController;
import Controller.LoginController;
import Controller.AdminController;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.utils.BooleanHelper;
import spark.utils.HandlebarsTemplateEngineBuilder;

public class Router {
	public static void configure() {
		HandlebarsTemplateEngine engine = HandlebarsTemplateEngineBuilder
				.create()
				.withDefaultHelpers()
				.withHelper("isTrue", BooleanHelper.isTrue)
				.build();
		
	Spark.staticFiles.location("/public");
	
	BusquedaController busquedaController = new BusquedaController();
	AdminController adminController = new AdminController();
	
	Spark.get("/", LoginController::login, engine);
	Spark.get("/terminal/:id", busquedaController::listarPois, engine);
	Spark.get("/poi:id", busquedaController::mostrar, engine);
	Spark.get("/admin", adminController::menu, engine);
	Spark.get("/admin/pois", adminController::listarPois, engine);
	Spark.get("/admin/modif/poi:id", adminController::modifPoi, engine);
	Spark.get("/admin/elim/poi:id", adminController::elimPoi, engine);
	Spark.get("/admin/terminales", adminController::listarTerminales, engine);
	Spark.get("/admin/terminales", adminController::listarBusquedas, engine);
	}
}
