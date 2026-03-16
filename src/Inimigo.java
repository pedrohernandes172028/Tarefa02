public class Inimigo extends Entidade {
    private int dano;
    public Inimigo(String nome, int vida, int escudo, int dano){
        super(nome, vida, escudo);
        this.dano = dano;
    }

    public void atacar(Heroi atacado){
        atacado.receberDano(this.dano);
    }
}