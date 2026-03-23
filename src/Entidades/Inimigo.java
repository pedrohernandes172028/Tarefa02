import java.util.Random;
public abstract class Inimigo extends Entidade {
    private Carta[] acoes;
    public Inimigo(String nome, int vida, int escudo, int velocidade){
        super(nome, vida, escudo, velocidade);
    }

    public void realizarAcao(Entidade[] entidades){
        Random aleatorio = new Random();
        int indice = aleatorio.nextInt(acoes.length);
        Entidade afetada;
        if (acoes[indice].tipo == 0){   /*afeta o heroi */
            afetada = entidades[0];   /* Entidade[0] sempre é o heroi1 */
        }else if (acoes[indice].tipo == 1){ /* carta de buff */
            afetada = entidades[1];     /*afeta o inimigo que estiver com mais vida*/
            for (int i = 2; i < entidades.length; i++){
                if (entidades[i - 1].getVida() < entidades[i].getVida()){
                    afetada = entidades[i];
                }
            }
        }else{  /*carta de defesa */
            afetada = entidades[1];     /*afeta o inimigo que estiver com menos vida*/
            for (int i = 2; i < entidades.length; i++){
                if (entidades[i - 1].getVida() > entidades[i].getVida()){
                    afetada = entidades[i];
                }
            }
        }
        acoes[indice].usar(afetada);
    }
    public abstract String anuncio();
}