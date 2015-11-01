package br.com.hightechcursos.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class Conexao {
				
			
				
			//N�o precisa obj para poder estanciar
			public static Connection getConection(){
				Connection con = null;

				try {
					Class.forName("org.postgresql.Driver");//Dorça o carregamento do driver na web
					con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/fundamentosJava","postgres","123");
					System.out.println("Sucesso");

				} catch (SQLException e) {

					e.printStackTrace();
					System.out.println("Erro");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				return con;
			}
}
