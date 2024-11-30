
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO { // Clase UsuarioDAO que contiene métodos para validar al usuario en la base de datos

private Conexion conexion; // Instancia de la clase Conexion para gestionar la conexión
public UsuarioDAO() {
this.conexion = new Conexion();
}
// Método para validar si el usuario y la contraseña soncorrectos
public boolean validarUsuario(Usuario usuario) {boolean existe = false;
String sql = "SELECT * FROM users WHERE username = ? AND password = ? "; // Seleccionar datos de la BD
try (Connection conn = conexion.getConnection();
PreparedStatement ps = conn.prepareStatement(sql)) {
ps.setString(1, usuario.getUsername()); // Establece el valor de username en la consulta
ps.setString(2, usuario.getPassword()); // Establece el valor de password en la consulta

/*
ps.setInt(3, usuario.getSaldo()); //    
int money = usuario.getSaldo();

ps.setString(4, usuario.getRut()); // 
String run = usuario.getRut();
*/

ResultSet rs = ps.executeQuery(); // Ejecuta la consulta y obtiene el resultado
if (rs.next()) {
existe = true; // Si se encuentra el usuario,existe se vuelve verdadero
}
} catch (SQLException e) {e.printStackTrace();
}
return existe;
}

// Método para obtener el saldo del usuario
public int obtenerSaldo(Usuario usuario) {
    int saldo=0;
    String sql = "SELECT saldo FROM users WHERE username = ?";
    try (Connection conn = conexion.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, usuario.getUsername()); // Establece el valor de username en la consulta
        ResultSet rs = ps.executeQuery(); // Ejecuta la consulta y obtiene el resultado
        if (rs.next()) {
        saldo = rs.getInt("saldo"); // Obtiene el saldo del resultado
        }
        }catch (SQLException e) {e.printStackTrace();}
        return saldo;   
        }

// Método para actualizar el saldo del usuario
public boolean actualizarSaldo(Usuario usuario, int nuevoSaldo) {
    boolean actualizado = false;
    String sql = "UPDATE users SET saldo = ? WHERE username = ?";
    try (Connection conn = conexion.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, nuevoSaldo); // Establece el nuevo saldo en la consulta
        ps.setString(2, usuario.getUsername()); // Establece el valor de username en la consulta
        int filasAfectadas = ps.executeUpdate(); // Ejecuta la actualización
        if (filasAfectadas > 0) {
            actualizado = true; // Si se actualizó al menos una fila, se considera exitoso
            }
            } catch (SQLException e) {e.printStackTrace();
            }
        return actualizado;
        }
    }


    

