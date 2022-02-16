/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.campominado.bd;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;

/**
 *
 * @author ander
 */
public class PartidaDAO {
    private Connection cx;
    
    public PartidaDAO(){
        this.cx = ConnectionFactory.getConnection();
    }
    
    public void adicionar(PartidaBeans partida){
        //String DML
        String query = "insert into partida " +
                       "(data, duracao, nivel) " +
                       "values (?, ?, ?);";

        try{
            //configura os campos
            PreparedStatement ps = this.cx.prepareStatement(query);

            Date data = new Date(partida.getData().getTimeInMillis());
            
            long inicio = 10800000;
            Time duracao = new Time(inicio + partida.getDuracao());
                        
            ps.setDate(1 , data);  
            ps.setTime(2, duracao);
            ps.setString(3, partida.getNivel());

            //roda
            ps.execute();
            ps.close();

            System.out.println("GRAVADO COM SUCESSO!!!");
        }catch(SQLException erro){
            //System.out.println(erro.getMessage());
            throw new RuntimeException();
        }
    }
}
