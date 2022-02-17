/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.campominado.bd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ander
 */
public class EstruturaBanco {
    
    public static void createTablePartida(){
        
        if(!EstruturaBanco.tabelaPartidaExiste()){
        
            Connection conexao = ConnectionFactory.getConnection();

            try{

                Statement stmt = conexao.createStatement();

                String sql = "CREATE TABLE `partida` ("
                        + "`data` DATE NOT NULL,"
                        + "`duracao` TIME NOT NULL,"
                        + "`nivel` VARCHAR(7) NOT NULL"
                        + ")"
                        + "CHARACTER SET utf8;";

                stmt.executeUpdate(sql);

                stmt.close();
                conexao.close();

            }catch(SQLException erro){
                System.out.println(erro.getMessage());
            }
        }
        
    }
    
    private static boolean tabelaPartidaExiste(){
        
        boolean resposta = false;
        
        Connection conexao = ConnectionFactory.getConnection();
        
        
        try{
            
            Statement stmt = conexao.createStatement();

            String sql = "SHOW TABLES;";
            
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                String resultado = rs.getString(1);
                
                if(resultado.equals("partida")){
                    resposta = true;
                    break;
                }
            }
            
            stmt.close();
            conexao.close();
        
        }catch(SQLException erro){
            System.out.println(erro.getMessage());
        }
        
        return resposta;
    }
    
}
