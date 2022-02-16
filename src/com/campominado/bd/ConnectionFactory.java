/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.campominado.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ander
 */
public class ConnectionFactory {
    public static Connection getConnection(){
        
        Connection retorno = null;
        
        try{
            // jdbc:mysql://127.0.0.1:3306/?user=root
           
            retorno =  DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/campo_minado", "root", "");
            System.out.println("LOL");
        }
        catch(SQLException erro){
            System.out.println("Deu errado a conexão");
            System.out.println(erro.getMessage());
        }
        
        return retorno;
    }
}
