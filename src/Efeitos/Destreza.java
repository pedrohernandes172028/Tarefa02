package Efeitos;
import Entidades.Entidade;

public class Destreza extends Efeito {
    
    public Destreza(String nome, Entidade dono, int acumulo){
        super(nome, dono, acumulo, new int[]{4});
    }

    public void acionado(){
        
    }
}
