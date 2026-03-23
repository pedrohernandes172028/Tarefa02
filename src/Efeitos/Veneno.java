public class Veneno extends Efeito {
    
    public Veneno(Entidade dono, int acumulo){
        super("Veneno", dono, acumulo, new int[]{4});
    }
    public void acionado(){
        dono.receberDano(3);
        acumulo -= 1;
    }

}
