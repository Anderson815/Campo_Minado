/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.campominado.bd;

import java.util.Calendar;



/**
 *
 * @author ander
 */
public class PartidaBeans {
    
    private Calendar data;
    private long duracao;
    private String nivel;

    public PartidaBeans(){}
    
    public PartidaBeans(Calendar data, long duracao, String nivel) {
        this.data = data;
        this.duracao = duracao;
        this.nivel = nivel;
    }
    
    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public long getDuracao() {
        return duracao;
    }

    public void setDuracao(long duracao) {
        this.duracao = duracao;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
       
}
