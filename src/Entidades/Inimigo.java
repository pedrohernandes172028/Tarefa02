package Entidades;
import java.util.List;

import Cartas.Carta;
public abstract class Inimigo extends Entidade {
    private Carta[] acoes;
    public Inimigo(String nome, int vida, int escudo, int velocidade){
        super(nome, vida, escudo, velocidade);
        
    }
    public Carta[] getAcoes(){
        return acoes;
    }

    public Entidade realizarAcao(Heroi heroi, List<Inimigo> inimigos, int indice){ /*comeco de uma estratégia dos inimigos */
        Entidade afetada;
        if (acoes[indice].getMomento() == 0){   /*afeta o heroi */
            afetada = heroi;   
        }else if (acoes[indice].getMomento() == 1){ /* carta de buff */
            afetada = inimigos.get(0);     /*afeta o inimigo que estiver com mais vida*/
            for (int i = 1; i < inimigos.size(); i++){
                if (inimigos.get(i - 1).getVida() < inimigos.get(i).getVida()){
                    afetada = inimigos.get(i);
                }
            }
        }else{  /*carta de defesa */
            afetada = inimigos.get(0);     /*afeta o inimigo que estiver com menos vida*/
            for (int i = 1; i < inimigos.size(); i++){
                if (inimigos.get(i - 1).getVida() > inimigos.get(i - 1).getVida()){
                    afetada = inimigos.get(i - 1);
                }
            }
        }
        acoes[indice].usar(afetada);
        return afetada;
        
    }

    public abstract String anuncio();
}