package es.tfg.comun.infraestructura;

import org.springframework.context.ApplicationContext;

public class Context {
	 private static ApplicationContext context;

	 public static void setApplicationContext(ApplicationContext applicationContext) {
		 context = applicationContext;
	 }

	 public static ApplicationContext getApplicationContext() {
		 return context;
	 }
}
