/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

// Clase Usuario que representa la estructura de un usuario con nombre de usuario y contraseña
public class Usuario {
    private String username;
    private String password;
   
    private int saldo;
    /*
    private String rut;
    */
    
// Constructor para inicializar los atributos de usuario
public Usuario(String username, String password) {
this.username = username;
this.password = password;
this.saldo = 0;
/*
this.rut = rut;
*/
}

// Getters y Setters para acceder y modificar los datos de username y password
public String getUsername() {
return username;
}

public void setUsername(String username) {
this.username = username;
}

public String getPassword() {
return password;
}

public void setPassword(String password) {
this.password = password;
}

public int getSaldo() {
return saldo;
}

public void setSaldo(int saldo) {
this.saldo = saldo;
}

/*
public String getRut() {
return rut;
}
public void setRut(String rut) {
this.rut = rut;
}*/
}

    

