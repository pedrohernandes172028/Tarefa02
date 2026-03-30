package Entidades;
import java.util.Scanner;

import Cartas.Carta;

import java.util.List;



public class Heroi extends Entidade{
    private Baralho baralho;
    private int energia;

    public Heroi(String nome, int vida, int escudo, int velocidade){
        super(nome, vida, escudo, velocidade);
        this.baralho = new Baralho();
        this.energia = 0;
    }

    public void resetarEscudo(){
        if (getEscudo() != 0){
            System.out.println(getNome() + " perdeu todo o seu escudo!\n");
            ganharEscudo(-getEscudo()); /*escudo vai ficar igual a 0 */
        }
    }
    public Baralho getBaralho(){
        return baralho;
    }
    public void resetarenergia(){
        energia = 3;
    }
    public Carta cartaUtilizada(int posicao){
        return baralho.getCartasNaMao().get(posicao);
    }

    public boolean realizarAcao(Heroi heroi, List<Inimigo> inimigos){   /*realiza apenas uma ação */
        Scanner comandoScanner;
        System.out.println("=-=\nDeck:\n");   /*mostrando, no terminal, as cartas na mao do jogador */
        for (int i = 0; i < baralho.getnCartasNaMao(); i++) {
            System.out.println((i + 1) + " -> " + baralho.getCartasNaMao().get(i).getNome() + " / " + baralho.getCartasNaMao().get(i).getDescricao());
        }
        System.out.println("=-=\n\n=-=\n" + energia + "/3 de Energia disponível\n=-=\n");   /*energias disponíveis */
        System.out.println("=-=\nDigite o número de uma carta para usá-la ou digite 6 para passar o seu turno.\n=-=\n");    /*instrução*/
        comandoScanner = new Scanner (System.in);
        int comando =  comandoScanner.nextInt();
        comandoScanner.close();
        if (comando == 6){  /*passa a vez */
            System.out.println("\n=-=\n" + heroi.getNome() + " passou a vez!\n=-=\n");
            return false;
        }else if (comando > baralho.getnCartasNaMao()) {  /*escolheu um espaço de carta que nao existe no momento */
            System.out.println("\n=-=\nSem nenhuma carta na posição escolhida.\n=-=\n");
        }else if (baralho.getCartasNaMao().get(comando - 1).getCusto() > energia){
            System.out.println("\n=-=\nSem energia para realizar está ação\n=-=\n");
        }else{
            System.out.println("Como alvo da sua ação, digite 0 para escolher você ou digite um dos números dos inimigos a seguir: \n");
            for (int i = 1; i < inimigos.size() + 1; i++){
                System.out.println(i + " -> " + inimigos.get(i - 1).getNome() + "\n");
            }
            comandoScanner = new Scanner (System.in);
            int alvo =  comandoScanner.nextInt();
            System.out.println(getNome() + " usou " + baralho.getCartasNaMao().get(comando - 1).getNome() + ". ");
            baralho.getCartasNaMao().get(comando - 1).usar(inimigos.get(alvo));
            baralho.cartaUsada(comando);
        }
        if (energia > 0){
            return true;
        }else{
            System.out.println(heroi.getNome() + " está sem energia! Seu turno acabou.");
            return false;
        }
    }

}