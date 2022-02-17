/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.campominado.bd;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

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
            
            long inicio = new GregorianCalendar(2022, 0, 1, 0, 0, 0).getTimeInMillis();
            Time duracao = new Time(inicio + partida.getDuracao());
                        
            ps.setDate(1 , data);  
            ps.setTime(2, duracao);
            ps.setString(3, partida.getNivel());

            //roda
            ps.execute();
            ps.close();

            System.out.println("GRAVADO COM SUCESSO");
        }catch(SQLException erro){
            System.out.println(erro.getMessage());
        }
    }
    
    public List<PartidaBeans> getPartidasNivel(String nivel){
        
        List<PartidaBeans> listPartidas = new ArrayList<>();
        
        String query = "select * from partida " +
                        "where nivel = ? " +
                        "order by duracao;";
        
        try{
            PreparedStatement ps = this.cx.prepareStatement(query);
            
            ps.setString(1, nivel);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                
                Calendar data = new GregorianCalendar();
                data.setTimeInMillis(rs.getDate("data").getTime());
                
                long duracao = rs.getTime("duracao").getTime();
                
                PartidaBeans partida = new PartidaBeans(data, duracao, nivel);
                
                listPartidas.add(partida);
            }
            
            rs.close();
            ps.close();
            
        }catch(SQLException erro){
            System.out.println(erro.getMessage());
        }
        
        return listPartidas;
    }
}
