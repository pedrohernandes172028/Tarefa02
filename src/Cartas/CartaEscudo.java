package Cartas;
import Entidades.Entidade;

public class CartaEscudo extends Carta {

    public CartaEscudo(String nome, String descricao, int custo){
        super(nome, descricao, custo, 1);
    }
    public void usar(Entidade alvo){ /*personagem vai ser um Herói, por enquanto */
        alvo.ganharEscudo(getCusto() * 5);
    }
}