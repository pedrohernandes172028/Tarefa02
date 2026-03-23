public class Cobrinha extends Inimigo{
    
    public Cobrinha(String nome, int vida, int escudo, int dano){
        super(nome, vida, escudo, dano);
    }
    public String anuncio(){
        return "Cobrinha está se preparando para morder seu herói. Este ataque vai lhe causar 10 de dano!";
    }
}