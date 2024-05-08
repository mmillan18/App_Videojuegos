import controller.ControllerVideoJuego;
import view.GUIPrincipal;
import model.EmpresaVideojuego;
import static controller.ControllerVideoJuego.agregarVideoJuego;

public class Main {
    public static void main(String[] args) {


        EmpresaVideojuego emp1 = EmpresaVideojuego.getEmpresa();
        ControllerVideoJuego controllerVideoJuego = new ControllerVideoJuego();
        GUIPrincipal guiPrincipal = new GUIPrincipal();
        guiPrincipal.setVideojuego(controllerVideoJuego);
        guiPrincipal.setVisible(true);
    }
}