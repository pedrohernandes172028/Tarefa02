package Efeitos;
import Entidades.Entidade;

public class Forca extends Efeito{
    
    public Forca(Entidade dono, int acumulo){
        super("Força", dono, acumulo, new int[]{0});
    }

    public void acionado(){
        
    }
}