package Cartas;
import Entidades.Entidade;

public abstract class Carta {
    private String nome;
    private String descricao;
    private int custo;
    private int tipo; /*serve para saber se a carta possui um efeito de dano (0), um efeito de escudo (1), etc*/

    public Carta(String nome, String descricao, int custo, int tipo){
        this.nome = nome;
        this.descricao = descricao;
        this.custo = custo;
        this.tipo = tipo;
    }

    public String getNome(){
        return this.nome;
    }
    public String getDescricao(){
        return this.descricao;
    }
    public int getCusto(){
        return this.custo;
    }
    public int getMomento(){
        return this.tipo;
    }
    public abstract void usar(Entidade alvo);
}