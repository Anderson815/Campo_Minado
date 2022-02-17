/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.campominado.config;

/**
 *
 * @author ander
 */
public class ConfigBD {
    
    private String url;
    private String usuario;
    private String senha;

    public ConfigBD(){
        this.usuario = "root";
        this.senha = "";
        
        this.url = "jdbc:mysql://127.0.0.1:3306/campo_minado?createDatabaseIfNotExist=true";      
    }
    
    public String getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }

    public String getUrl() {
        return url;
    }
    
    
}
