/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.campominado.style;

import java.awt.Component;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author ander
 */
public class TablePersonalizado extends JTable{
    
    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int rowIndex, int columnIndex){
        Component component = super.prepareRenderer(renderer, rowIndex, columnIndex);
        component.setBackground(Color.GRAY);
        component.setForeground(Color.BLACK);
        
        if(this.getValueAt(rowIndex, columnIndex) instanceof String){
            component.setBackground(Color.RED);
            component.setFont(new Font("Arial", 1, 30));
        }else if(this.getValueAt(rowIndex, columnIndex) instanceof Integer){
            component.setBackground(Color.WHITE);
        }
        
        return component;
    }
}
