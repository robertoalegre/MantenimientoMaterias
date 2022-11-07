package Clases2020;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;

import com.mysql.jdbc.PreparedStatement;
import javax.swing.JLabel;

public class FrameMaterias extends JFrame {
	
	/**
	 * 
	 */

	private static  final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtidMateria;
	private JTextField txtNombreMateria;
	

	/**
	 * Launch the application.
	 */
	Connection conexion=null;
	PreparedStatement preparedStatement=null;
	ResultSet resultSet=null;
	private void limpiarCuadroDeTexto(){
		txtidMateria.setText(null);
		txtNombreMateria.setText(null);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameMaterias frame = new FrameMaterias();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrameMaterias() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new 
				TitledBorder(UIManager.getBorder("TitledBorder.border"), "Mantenimiento de materias",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(255, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		//CREO EL BOTON MODIFICAR
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e1){
				conexion=Conexion.conectar();
				try{
					preparedStatement=(PreparedStatement) conexion.prepareStatement("update materias set nombre_materia= ? where id_materia= ?");
					preparedStatement.setString(1, txtNombreMateria.getText());
					preparedStatement.setString(2, txtidMateria.getText());
					
					int resultado=preparedStatement.executeUpdate();
					if(resultado>0){
						JOptionPane.showMessageDialog(null, "REGISTRO MODIFICADO CORRECTAMENTE");
						
						limpiarCuadroDeTexto();
						conexion.close();
						
						
					}else{
						JOptionPane.showMessageDialog(null, "NO SE PUDO MODIFICAR EL REGISTRO");
					}
				}catch(SQLException e){
					JOptionPane.showMessageDialog(null, "OCURRIO UN ERROR CON EL ACCESO A LA BASE DE DATOS");
				}
			}
		});
		btnModificar.setBounds(108, 208, 89, 23);
		contentPane.add(btnModificar);
		
		//CREO EL BOTON SALIR
		JButton btnSalir = new JButton("Salir");
		
		btnSalir.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e1){
				System.exit(0);
			}
			
		});
		btnSalir.setBounds(319, 208, 89, 23);
		contentPane.add(btnSalir);
		
		//CREO EL BOTON BORRAR
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e1) { 
				conexion=Conexion.conectar();
				try{
					preparedStatement=(PreparedStatement) conexion.prepareStatement("delete from materias where id_materia= ?");
					
					preparedStatement.setString(1, txtidMateria.getText());
					int resultado= preparedStatement.executeUpdate();
					
					if(resultado>0){
						JOptionPane.showMessageDialog(null, "REGISTRO ELIMINADO CORRECTAMENTE");
						
						limpiarCuadroDeTexto();
						conexion.close();
					
					}else{
						JOptionPane.showMessageDialog(null, "NO SE PUDO ELIMINAR EL REGISTRO");
					}
				}catch(SQLException e){
					JOptionPane.showMessageDialog(null, "OCURRIO UN ERROR CON EL ACCESO A LA BASE DE DATOS");
				}
			}
		});
		btnBorrar.setBounds(220, 208, 89, 23);
		contentPane.add(btnBorrar);
		
		//CREO EL BOTON AGREGAR
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e1){
				conexion=Conexion.conectar();
				try{
					preparedStatement=(PreparedStatement) conexion.prepareStatement("insert into materias (id_materia,nombre_materia) values(?,?)");
					preparedStatement.setString(1, txtidMateria.getText());
					preparedStatement.setString(2, txtNombreMateria.getText());
					
					int resultado= preparedStatement.executeUpdate();
					if(resultado>0){
						JOptionPane.showMessageDialog(null, "REGISTRO AGREGADO CORRECTAMENTE");
						
						limpiarCuadroDeTexto();
						conexion.close();
					}else{
						JOptionPane.showMessageDialog(null, "NO SE PUDO AGREGAR EL REGISTRO");
					}
					
				}catch(SQLException e){
					JOptionPane.showMessageDialog(null, "OCURRIO UN ERROR CON AL ACCESO A LA BASE DE DATOS");
				}
			}
		});
		btnAgregar.setBounds(10, 208, 89, 23);
		contentPane.add(btnAgregar);
		
		//CREO EL JLABEL ID MATERIA
		JLabel lblIdDeLa = new JLabel("Id de la Materia");
		lblIdDeLa.setBounds(10, 56, 100, 14);
		lblIdDeLa.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(lblIdDeLa);
		
		//CREO EL JTEXTFIELD ID MATERIA
		txtidMateria = new JTextField();
		txtidMateria.setBounds(146, 53, 86, 20);
		contentPane.add(txtidMateria);
		txtidMateria.setColumns(10);
		
		//CREO EL JLABEL NOMBRE DE MATERIA
		JLabel lblNombreDeLa = new JLabel("Nombre de la Materia");
		lblNombreDeLa.setBounds(10, 129, 131, 14);
		lblNombreDeLa.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(lblNombreDeLa);
		
		//CREO EL JTEXTFIELD NOMBRE DE MATERIA
		txtNombreMateria = new JTextField();
		txtNombreMateria.setBounds(146, 126, 86, 20);
		contentPane.add(txtNombreMateria);
		txtNombreMateria.setColumns(10);
		
		
		//CREO EL BOTON BUSCAR
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e1) { 
				conexion=Conexion.conectar();
				try{
					preparedStatement=(PreparedStatement) conexion.prepareStatement("Select nombre_materia from materias where id_materia= ?");
				    preparedStatement.setString(1, txtidMateria.getText());
				    resultSet= preparedStatement.executeQuery();
				    
				    if(resultSet.next()){
				    	txtNombreMateria.setText(resultSet.getString("nombre_materia"));
				    	JOptionPane.showMessageDialog(null, "REGISTRO ENCONTRADO");
				    	limpiarCuadroDeTexto();
				
				
				    }else{
				    	JOptionPane.showMessageDialog(null, "CODIGO DE MATERIA NO ENCONTRADO");
				    }
				    conexion.close();
				}catch(SQLException e){
					JOptionPane.showMessageDialog(null, "OCURRIO UN ERROR CON EL ACCESO A LA BASE DE DATOS");
				}
			}
			
		});
		btnBuscar.setBounds(280, 88, 89, 23);
		contentPane.add(btnBuscar);
	
		
	
	
		
		
	
	
	
	
	
	
	
	
	
	}
}
