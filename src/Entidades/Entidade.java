import java.util.ArrayList;
public abstract class Entidade {
    protected String nome;
    protected int vida;
    protected int escudo;
    protected ArrayList<Efeito> efeitos;
    protected int velocidade;

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

    public void aplicarEfeito(Efeito novo){
        int achou = 0;
        for (Efeito i : efeitos){
            if (novo.nome.equals(i.nome)){
                i.acumulo += novo.acumulo;
                achou = 1;
                break;
            }
        }
        if (achou == 0){
            efeitos.add(novo);
        }
    }

}