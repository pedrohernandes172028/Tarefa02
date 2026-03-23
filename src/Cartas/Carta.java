public abstract class Carta {
    protected String nome;
    protected String descricao;
    protected int custo;
    protected int tipo; /*serve para saber se a carta possui um efeito de ataque (0), um efeito de suporte (1), ou carta de buff (2); orienta o comportamento dos inimigos */

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
    
    public abstract void usar(Entidade alvo);
}