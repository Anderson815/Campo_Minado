package com.campominado.controller;

import java.util.Random;

public class CampoVirtual {
    private int quantidadeLinhasCampo;
    private int quantidadeColunasCampo;
    private int quantidadeBombas;

    private int[][] campoConteudo;
    private int[][] campoAreaVazio;
    private Bomba[]bombas;

    private Random geradorPosicoes;

    public CampoVirtual(String nivel){
        switch(nivel){
            case "fácil":
                this.quantidadeLinhasCampo = 8;
                this.quantidadeColunasCampo = 10;
                this.quantidadeBombas = 8;
                break;
            case "médio":
                this.quantidadeLinhasCampo = 12;
                this.quantidadeColunasCampo = 14;
                this.quantidadeBombas = 16;
                break;
            case "difícil":
                this.quantidadeLinhasCampo = 16;
                this.quantidadeColunasCampo = 18;
                this.quantidadeBombas = 35;
                break;
            default: 
                this.quantidadeLinhasCampo = 8;
                this.quantidadeColunasCampo = 10;
                this.quantidadeBombas = 8;
                break;
                
        }
        
        this.campoConteudo = new int[this.getQuantidadeLinhasCampo()][this.getQuantidadeColunasCampo()];
        this.campoAreaVazio = new int[this.getQuantidadeLinhasCampo()][this.getQuantidadeColunasCampo()];
        this.bombas = new Bomba[this.quantidadeBombas];
        
        this.geradorPosicoes = new Random();

        this.preencherVazio();
        this.preencherBombas();
        this.preencherDicas();

        this.gerarAreasVazio();
        this.gerarDefinicoesAreasDiferentesVazio();
        
        this.exibirCampoConteudo();
        System.out.println("\n\n\n");
        this.exibirCampoAreaVazio();
    }

    public int getPosicao(int linhaVisualizar, int colunaVisualizar){
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

        //métodos auxiliares do campoAreaVazio
    private void gerarAreasVazio(){
        for(int indiceLinha = 0; indiceLinha < this.getQuantidadeLinhasCampo(); indiceLinha++){
            for(int indiceColuna = 0; indiceColuna < this.getQuantidadeColunasCampo(); indiceColuna++){
                if(this.campoConteudo[indiceLinha][indiceColuna] == 0)
                    this.campoAreaVazio[indiceLinha][indiceColuna] = 0;
                else
                    this.campoAreaVazio[indiceLinha][indiceColuna] = -1;
            }
        }
    }

    private void gerarDefinicoesAreasDiferentesVazio(){

        int area = 1;
        boolean primeiroJaMarcado;
        boolean repetir;

        do{
            primeiroJaMarcado = false;
            repetir = false;

            //normal
            for(int indiceLinha = 0; indiceLinha < this.getQuantidadeLinhasCampo(); indiceLinha++){
                for(int indiceColuna = 0; indiceColuna < this.getQuantidadeColunasCampo(); indiceColuna++){
                    if(this.campoAreaVazio[indiceLinha][indiceColuna] == 0 && !primeiroJaMarcado){
                        this.campoAreaVazio[indiceLinha][indiceColuna] = area;
                        primeiroJaMarcado = true;
                    }else if(this.campoAreaVazio[indiceLinha][indiceColuna] == 0 && primeiroJaMarcado && this.areaProximoVaziaIgual(area, indiceLinha, indiceColuna)){
                        this.campoAreaVazio[indiceLinha][indiceColuna] = area;
                    }
                }
            }

            //inverso normal
            for(int indiceLinha = this.getQuantidadeLinhasCampo() - 1; indiceLinha >= 0; indiceLinha--){
                for(int indiceColuna = this.getQuantidadeColunasCampo() - 1; indiceColuna >= 0; indiceColuna--){
                    if(this.campoAreaVazio[indiceLinha][indiceColuna] == 0 && !primeiroJaMarcado){
                        this.campoAreaVazio[indiceLinha][indiceColuna] = area;
                        primeiroJaMarcado = true;
                    }else if(this.campoAreaVazio[indiceLinha][indiceColuna] == 0 && primeiroJaMarcado && this.areaProximoVaziaIgual(area, indiceLinha, indiceColuna)){
                        this.campoAreaVazio[indiceLinha][indiceColuna] = area;
                    }
                }
            }

            // de cima para baixo da direita para esquerda
            for(int indiceLinha = 0; indiceLinha < this.getQuantidadeLinhasCampo(); indiceLinha++){
                for(int indiceColuna = this.getQuantidadeColunasCampo() - 1; indiceColuna >= 0; indiceColuna--){
                    if(this.campoAreaVazio[indiceLinha][indiceColuna] == 0 && !primeiroJaMarcado){
                        this.campoAreaVazio[indiceLinha][indiceColuna] = area;
                        primeiroJaMarcado = true;
                    }else if(this.campoAreaVazio[indiceLinha][indiceColuna] == 0 && primeiroJaMarcado && this.areaProximoVaziaIgual(area, indiceLinha, indiceColuna)){
                        this.campoAreaVazio[indiceLinha][indiceColuna] = area;
                    }
                }
            }

            //de baixo para cima da esquerda para direita
            for(int indiceLinha = this.getQuantidadeLinhasCampo() - 1; indiceLinha >= 0; indiceLinha--){
                for(int indiceColuna = 0; indiceColuna < this.getQuantidadeColunasCampo(); indiceColuna++){
                    if(this.campoAreaVazio[indiceLinha][indiceColuna] == 0 && !primeiroJaMarcado){
                        this.campoAreaVazio[indiceLinha][indiceColuna] = area;
                        primeiroJaMarcado = true;
                    }else if(this.campoAreaVazio[indiceLinha][indiceColuna] == 0 && primeiroJaMarcado && this.areaProximoVaziaIgual(area, indiceLinha, indiceColuna)){
                        this.campoAreaVazio[indiceLinha][indiceColuna] = area;
                    }else if(this.campoAreaVazio[indiceLinha][indiceColuna] == 0)
                        repetir = true;
                }
            }
            area++;
        }while(repetir);



    }

    private boolean areaProximoVaziaIgual(int area, int linhaVazio, int colunaVazio) {

        int linhaAreaProxima;
        int colunaAreaProxima;

        for(int acrescimoLinha = -1; acrescimoLinha <= 1; acrescimoLinha++){
            for(int acrescimoColuna = -1; acrescimoColuna <= 1; acrescimoColuna++){

                linhaAreaProxima = linhaVazio + acrescimoLinha;
                colunaAreaProxima = colunaVazio + acrescimoColuna;

                if((linhaAreaProxima < 0 || linhaAreaProxima >= this.getQuantidadeLinhasCampo()) || (colunaAreaProxima < 0 || colunaAreaProxima >= this.getQuantidadeColunasCampo()))
                    continue;

                if(this.campoAreaVazio[linhaAreaProxima][colunaAreaProxima] == area) {
                    return true;
                }
            }
        }

        return false;
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
    public void exibirCampoConteudo(){
        for(int i = 0; i < this.campoConteudo.length; i++){
            for(int j = 0; j < this.campoConteudo[i].length; j++){
                System.out.print(this.campoConteudo[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public void exibirCampoAreaVazio(){
        for(int i = 0; i < this.campoConteudo.length; i++){
            for(int j = 0; j < this.campoConteudo[i].length; j++){
                System.out.print(this.campoAreaVazio[i][j] + "\t");
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
