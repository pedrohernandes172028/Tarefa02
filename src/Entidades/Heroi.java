public class Heroi extends Entidade{

    public Heroi(String nome, int vida, int escudo, int velocidade){
        super(nome, vida, escudo, velocidade);
    }

    public void resetarEscudo(){
        this.escudo = 0;
    }
}