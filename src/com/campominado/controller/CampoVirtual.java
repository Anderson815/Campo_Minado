package com.campominado.controller;

import java.util.Random;

public class CampoVirtual {
    private int quantidadeLinhasCampo;
    private int quantidadeColunasCampo;
    private int quantidadeBombas;

    private int[][] campoConteudo;
    private boolean[][] campoVisualizado;
    private Bomba[]bombas;

    private Random geradorPosicoes;

    public CampoVirtual(){

        this.quantidadeLinhasCampo = 6;
        this.quantidadeColunasCampo = 6;
        this.quantidadeBombas = 6;
        
        this.campoConteudo = new int[this.getQuantidadeLinhasCampo()][this.getQuantidadeColunasCampo()];
        this.campoVisualizado = new boolean[this.getQuantidadeLinhasCampo()][this.getQuantidadeColunasCampo()];
        this.bombas = new Bomba[this.quantidadeBombas];
        
        this.geradorPosicoes = new Random();

        this.preencherVazio();
        this.preencherBombas();
        this.preencherDicas();

        this.mapaConteudoEscondido();
    }

    public int visualizarCasa(int linhaVisualizar, int colunaVisualizar){
        return this.campoConteudo[linhaVisualizar][colunaVisualizar];
    }
    
    
    
    //Métodos auxiliares
    private void preencherVazio(){
        for(int indiceLinha = 0; indiceLinha < this.getQuantidadeLinhasCampo(); indiceLinha++){
            for(int indiceColuna = 0; indiceColuna < this.getQuantidadeColunasCampo(); indiceColuna++){
                this.campoConteudo[indiceLinha][indiceColuna] = 0;
            }
        }
    }

    private void preencherBombas(){

        int linhaBombaGerado;
        int colunaBombaGerado;
        boolean posicaoDisponivel;

        for(int numeroBomba = 0; numeroBomba < this.getQuantidadeBombas(); numeroBomba++){

            do {
                linhaBombaGerado = this.geradorPosicoes.nextInt(this.getQuantidadeLinhasCampo());
                colunaBombaGerado = this.geradorPosicoes.nextInt(this.getQuantidadeColunasCampo());
                posicaoDisponivel = this.posicaoBombaValida(linhaBombaGerado, colunaBombaGerado);

                if (posicaoDisponivel) {
                    this.bombas[numeroBomba] = new Bomba(linhaBombaGerado, colunaBombaGerado);
                    this.campoConteudo[linhaBombaGerado][colunaBombaGerado] = -1;
                }
            }while(!posicaoDisponivel);
        }
    }

    private void preencherDicas(){

        int linhaBomba;
        int colunaBomba;

        int linhaDica;
        int colunaDica;

        for(int numeroBomba = 0; numeroBomba < this.getQuantidadeBombas(); numeroBomba++){
            linhaBomba = this.bombas[numeroBomba].getLinha();
            colunaBomba = this.bombas[numeroBomba].getColuna();

            for(int acrescimoLinha = -1; acrescimoLinha <= 1; acrescimoLinha++){
                for(int acrescimoColuna = -1; acrescimoColuna <= 1; acrescimoColuna++){

                    linhaDica = linhaBomba + acrescimoLinha;
                    colunaDica = colunaBomba + acrescimoColuna;

                    if((linhaDica < 0 || linhaDica >= this.getQuantidadeLinhasCampo()) || (colunaDica < 0 || colunaDica >= this.getQuantidadeColunasCampo()))
                        continue;

                    if(this.campoConteudo[linhaDica][colunaDica] != -1) {
                        this.campoConteudo[linhaDica][colunaDica] = this.campoConteudo[linhaDica][colunaDica] + 1;
                    }
                }
            }
        }
    }

    private void mapaConteudoEscondido(){
        for(int indiceLinha = 0; indiceLinha < this.getQuantidadeLinhasCampo(); indiceLinha++){
            for(int indiceColuna = 0; indiceColuna < this.getQuantidadeColunasCampo(); indiceColuna++){
                this.campoVisualizado[indiceLinha][indiceColuna] = false;
            }
        }
    }

    private boolean posicaoBombaValida(int linhaBombaGerado, int colunaBombaGerado){

        boolean disponivel = true;
        int linhaBomba;
        int colunaBomba;

        for(int numeroBomba = 0; numeroBomba < this.getQuantidadeBombas(); numeroBomba++){

            if(this.bombas[numeroBomba] != null) {
                linhaBomba = this.bombas[numeroBomba].getLinha();
                colunaBomba = this.bombas[numeroBomba].getColuna();

                if (linhaBomba == linhaBombaGerado && colunaBomba == colunaBombaGerado) {
                    disponivel = false;
                    break;
                }
            }
        }

        return disponivel;
    }

    
    
    //temporario
    public void exibirCampo(){
        for(int i = 0; i < this.campoConteudo.length; i++){
            for(int j = 0; j < this.campoConteudo[i].length; j++){
                System.out.print(this.campoConteudo[i][j] + "\t");
            }
            System.out.println();
        }
    }

    //Sets e Gets
    
    public int getQuantidadeBombas() {
        return quantidadeBombas;
    }

    public int getQuantidadeLinhasCampo() {
        return quantidadeLinhasCampo;
    }

    public int getQuantidadeColunasCampo() {
        return quantidadeColunasCampo;
    }

    public Bomba[] getBombas() {
        return bombas;
    }
    
}
