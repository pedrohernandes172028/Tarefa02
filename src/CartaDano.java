public class CartaDano extends Carta {

    public CartaDano(String nome, String descricao, int custo){
        super(nome, descricao, custo);
    }
    public int usar(Entidade personagem){  /*personagem vai ser um inimigo, por enquanto */
        personagem.receberDano(custo * 5);
        return custo;
    }
}