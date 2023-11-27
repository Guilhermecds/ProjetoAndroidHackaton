package com.alfaumuarama.projetoandroidhackaton;

public class Rota {
    private String partida;
    private String chegada;
    private String detalhes;
    private String diapartida;

    public Rota(String partida, String chegada, String detalhes, String diapartida) {
        this.partida = partida;
        this.chegada = chegada;
        this.detalhes = detalhes;
        this.diapartida = diapartida;
    }

    public String getPontoPartida() {
        return partida;
    }


    public String getCidadeDestino() {
        return chegada;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public String getDiaPartida() {
        return diapartida;
    }

    public void setPontoPartida(String partida) {
        this.partida = partida;
    }

    public void setCidadeDestino(String chegada) {
        this.chegada = chegada;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    public void setDiaPartida(String diapartida) {
        this.diapartida = diapartida;
    }
}
