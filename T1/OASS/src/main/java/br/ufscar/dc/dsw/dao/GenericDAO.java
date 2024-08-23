package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

abstract public class GenericDAO {
    
    public GenericDAO() {
        try {           
        	/* Setup Banco de dados Derby */
        	// Class.forName("org.apache.derby.jdbc.ClientDriver");
            
        	/* Setup Banco de dados MySQL */
        	Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    protected Connection getConnection() throws SQLException {
    	
    	/* Conexão banco de dados Derby */
    	
        /* 
    	 * String url = "jdbc:derby://localhost:1527/Livraria";
    	 */
    	
    	/* Conexão banco de dados MySQL */
        
        
    	String url = "jdbc:mysql://localhost/OASS?serverTimezone=UTC"; //Localhost - Laysson
    	return DriverManager.getConnection(url, "root", "laysson");
        
        /*String url = "jdbc:mysql://localhost:3306/OASS?useTimezone=true&serverTimezone=America/Sao_Paulo"; //Localhost - Padrão
    	
        return DriverManager.getConnection(url, "root", "Da120903");*/
        
    }
}