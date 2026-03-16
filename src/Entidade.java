public abstract class Entidade {
    protected String nome;
    protected int vida;
    protected int escudo;

    public Entidade(String nome, int vida, int escudo){
        this.nome = nome;
        this.vida = vida;
        this.escudo = escudo;
    }
    public void receberDano(int dano){
        escudo -= dano;
        if (escudo < 0){
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
}