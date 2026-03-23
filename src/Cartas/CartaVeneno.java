public class CartaVeneno extends Carta{
    
    public CartaVeneno(String nome, String descricao, int custo){
        super(nome, descricao, custo, 0);
    }

    public void usar(Entidade alvo){
        alvo.aplicarEfeito(new Veneno(alvo, custo));
    }
}
