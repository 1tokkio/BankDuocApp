
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    // Clase Conexion que establece la conexión con la base de datos MySQL

private static final String URL = "jdbc:mysql://localhost:3306/loginDB"; // Ruta de la base de datos
private static final String USER = "root"; // Usuario deMySQL
private static final String PASSWORD = ""; // Contraseñade MySQL
// Método para obtener la conexión
public Connection getConnection() {
Connection connection = null;
try {
connection = DriverManager.getConnection(URL, USER, PASSWORD);
System.out.println("Conexión exitosa.");
} catch (SQLException e) {
e.printStackTrace();
}
return connection;
}
}

