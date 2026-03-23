public abstract class Efeito {
    protected String nome;
    protected Entidade dono;
    protected int acumulo;
    protected int[] momento; /* momentos em que o Efeito é acionado */

    public Efeito(String nome, Entidade dono, int acumulo, int[] momento){
        this.nome = nome;
        this.dono = dono;
        this.acumulo = acumulo;
        this.momento = momento;
    }
    public String getString(){
        return dono + " possui " + acumulo + " de " + nome + "!";
    }
    public abstract void acionado();
}
