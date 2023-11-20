package UdeA.zerohunger.exception;


public class HomeHogarNotFoundException extends RuntimeException {
    public HomeHogarNotFoundException(String nombre) {
        super("No se encontró la familia: " + nombre);
    }
}

