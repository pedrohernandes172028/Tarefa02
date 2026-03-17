public class CartaEscudo extends Carta {

    public CartaEscudo(String nome, String descricao, int custo){
        super(nome, descricao, custo);
    }
    public int usar(Entidade personagem){ /*personagem vai ser um Herói, por enquanto */
        personagem.ganharEscudo(custo * 5);
        return custo;
    }
}