package Efeitos;
import Entidades.Entidade;

public class Veneno extends Efeito {
    
    public Veneno(Entidade dono, int acumulo){
        super("Veneno", dono, acumulo, new int[]{4});
    }
    public void acionado(){
        getDono().receberDano(3);
        alterarAcumulo(-1);
    }
}