import modelo.UsuarioDAO;
import vista.LoginView;
import vista.SecondFrame;
import controlador.LoginController;

public class Main {public static void main(String[] args) {
LoginView view = new LoginView(); // Crear la vista de login
SecondFrame secondFrame = new SecondFrame();
UsuarioDAO usuarioDAO = new UsuarioDAO(); // Crear lainstancia del modelo DAO
LoginController controller = new LoginController(view, usuarioDAO, secondFrame); // Crear el controlador y conectar la vista y el modelo
view.setVisible(true); // Mostrar la vista al usuario
secondFrame.setVisible(false);
}
}
    

