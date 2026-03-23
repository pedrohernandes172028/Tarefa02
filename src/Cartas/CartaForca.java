public class CartaForca extends Carta{
    
    public CartaForca(String nome, String descricao, int custo){
        super(nome, descricao, custo, 2);
    }

    public void usar(Entidade alvo){
        alvo.aplicarEfeito(new Forca(alvo, custo));
    }
}
