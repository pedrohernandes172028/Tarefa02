public class CartaDano extends Carta {

    public CartaDano(String nome, String descricao, int custo){
        super(nome, descricao, custo);
    }
    public int usar(Entidade personagem){  /*vai ser sempre um inimigo */
        personagem.receberDano(custo * 5);
        return custo;
    }
}