
package controlador;

import modelo.Usuario; // Importar package modelo de Usuario
import modelo.UsuarioDAO; // Importar package modelo de UsuarioDAO 
import vista.LoginView; // Importar package de LoginView
import vista.SecondFrame; // Importar package de SecondFrame 
import javax.swing.*; // Importar todas las librerías de swing
import java.awt.event.ActionEvent; // Importar librería event de ActionEvent
import java.awt.event.ActionListener;
import java.text.NumberFormat; 
import java.util.Locale;// Importar librería de event de ActionListener

// Clase LoginController que maneja la lógica del botón y valida al usuario
// El LoginController es para accionar las funciones de cada clase creada
public class LoginController {
private LoginView view;
private UsuarioDAO usuarioDAO;
private SecondFrame secondFrame;

// Constructor que recibe las 2 vistas y el modelo para inicializar el controlador
public LoginController(LoginView view, UsuarioDAO usuarioDAO, SecondFrame secondFrame) {
this.view = view;
this.usuarioDAO = usuarioDAO;
this.secondFrame = secondFrame;

// Asignar un ActionListener al botón de login
this.view.getBtnLogin().addActionListener(new ActionListener() {
@Override
public void actionPerformed(ActionEvent e) {verificarLogin(); // Llama al método verificar Login cuando se hace clic en el botón
}
});

// Asignar un ActionListener al botón de mostrar saldo
this.secondFrame.getBtnMostrarsaldo().addActionListener(new ActionListener() {
@Override
public void actionPerformed(ActionEvent e) {mostrarSaldo(); // Llama al método verificar Login cuando se hace clic en el botón
}
});

// Asignar un ActionListener al botón de Depositar
this.secondFrame.getBtnDepositar().addActionListener(new ActionListener() {
@Override
public void actionPerformed(ActionEvent e) {hacerDeposito(); // Llama al método hacerDeposito cuando se hace clic en el botón
}
});

// Asignar un ActionListener al botón Retirar
this.secondFrame.getBtnRetirar().addActionListener(new ActionListener() {
@Override
public void actionPerformed(ActionEvent e) {hacerRetiro(); // Llama al método hacerRetiro cuando se hace clic en el botón
}
});

// Asignar un ActionListener al botón Salir
this.secondFrame.getBtnSalir().addActionListener(new ActionListener() {
@Override
public void actionPerformed(ActionEvent e) {volverPrimerFrame(); // Llama al método hacerRetiro cuando se hace clic en el botón
}
});


}

// Método para verificar si las credenciales del usuario son correctas
private void verificarLogin() {
String username = view.getTxtUsername().getText(); //Obtener el texto del campo usuario
String password = new String(view.getTxtPassword().getPassword()); // Obtener el texto del campo contraseña
// Crear un objeto Usuario con los datos ingresados
Usuario usuario = new Usuario(username, password);
// Validar el usuario con el método de UsuarioDAO
boolean existe = usuarioDAO.validarUsuario(usuario);
// Mostrar mensajes según el resultado de la validación
if (existe) {
    abrirSegundoFrame();
JOptionPane.showMessageDialog(view, "Login exitoso"); // JOption para mostrar ventana emergente con mensaje
} else {
JOptionPane.showMessageDialog(view, "Usuario o contraseña incorrecta");
}
}

// Método para mostrar el saldo del usuario 
private void mostrarSaldo() {
String username = view.getTxtUsername().getText();
Usuario usuario = new Usuario(username, ""); // Crear un objeto Usuario con solo el username
int saldo = usuarioDAO.obtenerSaldo(usuario);
NumberFormat formatoNumero = NumberFormat.getInstance(new Locale("es", "ES"));
String numeroFormateado = formatoNumero.format(saldo);// Llamando a obtenerSaldo actual del usuario
if (saldo >= 0 || saldo < 0) { 
JOptionPane.showMessageDialog(secondFrame, "Saldo: $" + numeroFormateado); // JOption para mostrar ventana emergente con saldo
} else {
JOptionPane.showMessageDialog(secondFrame, "Usuario no encontrado");
}
}

// Método para hacer un depósito en la cuenta del usuario
private void hacerDeposito() {
    String username = view.getTxtUsername().getText();
    Usuario usuario = new Usuario(username, ""); // Crear un objeto Usuario con solo el username
    int saldoActual = usuarioDAO.obtenerSaldo(usuario); // Llamando a obtenerSaldo actual del usuario
    if (saldoActual >= 0 || saldoActual < 0) {
        int amount = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el monto a depositar:")); // JOption para mostrar ventana emergente con input para ingresar datos
        int nuevoSaldo = saldoActual + amount;
        NumberFormat formatoNumero = NumberFormat.getInstance(new Locale("es", "ES"));
        String numeroFormateado = formatoNumero.format(nuevoSaldo);
        usuarioDAO.actualizarSaldo(usuario, nuevoSaldo); // Llamando al método actualizarSaldo para el usuario
        JOptionPane.showMessageDialog(secondFrame, "Depósito exitoso. Nuevo saldo: $" + numeroFormateado);
        } else {
        { JOptionPane.showMessageDialog(secondFrame, "Usuario no encontrado");
    }
}
}

// Método para hacer un retiro en la cuenta del usuario
private void hacerRetiro() {
    String username = view.getTxtUsername().getText();
    Usuario usuario = new Usuario(username, ""); // Crear un objeto Usuario con solo el username
    int saldoActual = usuarioDAO.obtenerSaldo(usuario); // Obtener el saldo actual del usuario
    if (saldoActual >= 0 || saldoActual < 0) {
        int amount = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el monto a retirar:"));
                if(amount > saldoActual){
                  JOptionPane.showMessageDialog(secondFrame, "No puedes retirar, porque excedes el máximo:");
                }else{
        int nuevoSaldo = saldoActual - amount;
        NumberFormat formatoNumero = NumberFormat.getInstance(new Locale("es", "ES"));
        String numeroFormateado = formatoNumero.format(nuevoSaldo);
        usuarioDAO.actualizarSaldo(usuario, nuevoSaldo);
        JOptionPane.showMessageDialog(secondFrame, "Retirar exitoso. Nuevo saldo: $" + numeroFormateado);}
    }else{
        { JOptionPane.showMessageDialog(secondFrame, "Usuario no encontrado");
    }
}
}

// Método para abrir SecondFrame
private void abrirSegundoFrame() {
    secondFrame.setVisible(true);
    view.dispose();
}

// Método para volver a pantalla principal
private void volverPrimerFrame() {
    String username = view.getTxtUsername().getText(); //Obtener el texto del campo usuario
    String password = new String(view.getTxtPassword().getPassword()); // Obtener el texto del campo contraseña
    Usuario usuario = new Usuario(username, password);
    // Validar el usuario con el método de UsuarioDAO
    boolean existe = usuarioDAO.validarUsuario(usuario);
    // Mostrar mensajes según el resultado de la validación
    if (existe) {
    JOptionPane.showMessageDialog(view, "Sesión cerrada"); // JOption para mostrar ventana emergente con mensaje
    }else {
    JOptionPane.showMessageDialog(view, "Usuario o contraseña incorrecta");
    }
    // LoginView view = new LoginView();
    view.clearFields(); // Limpiar los campos de texto
    view.setVisible(true); // Mostrar el Panel nuevo 
    secondFrame.dispose(); // Cerrar el Panel Frame anterior
}
}

