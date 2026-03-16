public class Heroi extends Entidade{

    public Heroi(String nome, int vida, int escudo){
        super(nome, vida, escudo);
    }

    public void resetarEscudo(){
        this.escudo = 0;
    }
}