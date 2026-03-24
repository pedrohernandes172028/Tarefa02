package Entidades;
import Cartas.Carta;
import java.util.ArrayList;

import Efeitos.Efeito;
public abstract class Entidade {
    private String nome;
    private int vida;
    private int escudo;
    private ArrayList<Efeito> efeitos;
    private int velocidade;
    private Carta[] deckCartas;

    public Entidade(String nome, int vida, int escudo, int velocidade){
        this.nome = nome;
        this.vida = vida;
        this.escudo = escudo;
        this.velocidade = velocidade;
    }
    public void receberDano(int dano){
        escudo -= dano;
        if (escudo < 0){    /*evitando negativos */
            vida += escudo;
            escudo = 0;
            if (vida < 0){
                vida = 0;
            }
        }
    }
    public void ganharEscudo(int bonus){
        escudo += bonus;
    }
    public boolean estarVivo(){
        if (vida > 0){
            return true;
        }else{
            return false;
        }
    }

    public String getNome(){
        return this.nome;
    }
    public int getVida(){
        return this.vida;
    }
    public int getEscudo(){ 
        return this.escudo;
    }
    public int getVelocidade(){
        return this.velocidade;
    }

    public Carta[] getDeckCartas(){
        return deckCartas;
    }
    public ArrayList<Efeito> getEfeitos(){
        return efeitos;
    }


    public void aplicarEfeito(Efeito novo){
        int achou = 0;
        for (Efeito i : efeitos){
            if (novo.getNome().equals(i.getNome())){
                i.alterarAcumulo(novo.getAcumulo());
                achou = 1;
                break;
            }
        }
        if (achou == 0){
            efeitos.add(novo);
        }
    }
    public String getStatus(){
        return getNome() + " (" + getVida() + "/40 de vida) (" + getEscudo() + " de escudo)";
    }

}