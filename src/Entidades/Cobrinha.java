package Entidades;
public class Cobrinha extends Inimigo{
    
    public Cobrinha(String nome, int vida, int escudo, int dano, int velocidade){
        super(nome, vida, escudo, velocidade);
        /*Seu deck */
    }
    public String anuncio(){
        return "Cobrinha está se preparando para morder seu herói. Este ataque vai lhe causar 10 de dano!";
    }
}