public class CartaEscudo extends Carta {

    public CartaEscudo(String nome, String descricao, int custo){
        super(nome, descricao, custo);
    }
    public int usar(Entidade personagem){ /*sempre será um Herói */
        personagem.ganharEscudo(custo * 5);
        return custo;
    }
}