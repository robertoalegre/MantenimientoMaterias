package Clases2020;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	
	private static String url="jdbc:mysql://localhost:3306/matriculadb";
	private static String usuario="root";
	private static String password="";
	public static Connection conectar(){
		Connection conexion=null;
		try{
			conexion= DriverManager.getConnection(url, usuario, password);
		}catch(SQLException e){
			System.out.println("OCURRIO UN ERROR AL CONECTARSE CON LA BASE DE DATOS 2021");
		}
	        return conexion;
	}}
