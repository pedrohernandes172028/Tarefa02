import java.util.Scanner;
import java.util.Collections;
import java.util.Stack;
import java.util.ArrayList;
import java.util.List;
import Cartas.*;
import Efeitos.*;
import Entidades.*;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Digite o nome do seu personagem:");
        Scanner comandoScanner = new Scanner (System.in);
        String nome = comandoScanner.nextLine(); 
        Heroi heroi1 = new Heroi(nome, 40, 5,10);  /*declarando heroi, inimigo e cartas */
        Inimigo inimigo1 = new Cobrinha("Cobrinha", 20, 5, 10, 8);
        Inimigo inimigo2 = new Cobrinha("Cobrinha", 20, 5, 10, 8);
        Inimigo inimigo3 = new Cobrinha("Cobrinha", 20, 5, 10, 8);
        CartaDano espada = new CartaDano("Espada de cobre", "causa 10 de dano ao inimigo / custa 2 energias",2);
        CartaEscudo escudo = new CartaEscudo("Escudo de madeira","concede 5 de escudo ao usuário / custa 1 energia", 1);
        CartaForca bomba = new CartaForca("Esteróide", "concede +1 de força / custa 1 energia", 1);
        CartaVeneno dardo = new CartaVeneno("dardo envenenado", "conde +1 de veneno / custa 1 energia", 1);
        Combate ordem = new Combate(new Entidade[]{heroi1, inimigo1, inimigo2, inimigo3});
        int energia = 3, cartasNaMao = 0;
        List<Carta> maoJogador = new ArrayList<>(); /*mao do jogador */

        /*formando as pilhas */
        Stack<Carta> pilhaCompras = new Stack<>();
        Stack<Carta> pilhaDescarte = new Stack<>();
        for (int i = 0; i < 2; i++){    /*coloquei 8 cartas na pilha de compras, 2 de cada tipo*/
            pilhaCompras.push(espada);
            pilhaCompras.push(escudo);
            pilhaCompras.push(bomba);
            pilhaCompras.push(dardo);
        }
        Collections.shuffle(pilhaCompras);  /*embaralhando */

        loopExterno: while (true) {

            while (cartasNaMao < 5){    /*comprando cartas*/
                if (pilhaCompras.isEmpty()){
                    Collections.shuffle(pilhaDescarte);
                    for (int i = 0; i < 10; i++){
                        pilhaCompras.push(pilhaDescarte.pop()); /*tira as cartas da pilha de descarte e coloca na de compras */
                    }
                }
                maoJogador.add(pilhaCompras.pop());
                cartasNaMao++;
            }

            energia = 3;
            for (int j = 0; j < ordem.getTamanho(); j ++){
                if (ordem.getOrdem()[j] instanceof Heroi){
                    System.out.println("\n=-=\nStatus:\n" + heroi1.getNome() + " (" + heroi1.getVida() + "/40 de vida) (" + heroi1.getEscudo() + " de escudo)\nvs");
                    System.out.printf("%s (%d/20 de vida) (%d de escudo)\n=-=\n\n", inimigo1.getNome(), inimigo1.getVida(), inimigo1.getEscudo());    /*status dos personagens */
                    System.out.println("=-=\n" + inimigo1.anuncio() + "\n=-=\n"); /*anunciando o ataque */
                    System.out.println("=-=\nSeu turno começou.\n=-=\n");
                    if (heroi1.estarVivo() == false){    
                        System.out.println(heroi1.getNome() + " foi derrotado!\nVocê perdeu.");
                        break;
                    }else{
                        while (energia > 0){        /*ação do heroi*/
                            System.out.println("=-=\nDeck:\n");   /*mostrando, no terminal, as cartas na mao do jogador */
                            for (int i = 0; i < cartasNaMao; i++) {
                                System.out.println((i + 1) + " -> " + maoJogador.get(i).getNome() + " / " + maoJogador.get(i).getDescricao());
                            }
                            System.out.println("=-=\n\n=-=\n" + energia + "/3 de Energia disponível\n=-=\n");   /*energias disponíveis */
                            System.out.println("=-=\nDigite o número de uma carta para usá-la ou digite 6 para passar o seu turno.\n=-=\n");    /*instrução*/
                            comandoScanner = new Scanner (System.in);
                            int comando =  comandoScanner.nextInt();
                            if (comando == 6){  /*passa a vez */
                                System.out.println("\n=-=\n" + heroi1.getNome() + " passou a vez!\n=-=\n");
                                break;
                            }else if (comando > cartasNaMao) {  /*escolheu um espaço de carta que nao existe no momento */
                                System.out.println("\n=-=\nSem nenhuma carta na posição escolhida.\n=-=\n");

                            }else if (maoJogador.get(comando - 1) == espada){   /*escolheu espada */
                                if (energia < maoJogador.get(comando - 1).getCusto()){      /*verificando se tem energia suficiente */
                                    System.out.println("\n=-=\nSem energia para realizar esta ação.\n=-=\n");
                                }else{
                                maoJogador.get(comando - 1).usar(inimigo1);  /*usando a carta e gastando a energia */
                                energia -= maoJogador.get(comando - 1).getCusto();
                                cartasNaMao--;
                                pilhaDescarte.push(maoJogador.remove(comando - 1)); /*descartando a carta usada */
                                }

                            }else{
                                maoJogador.get(comando - 1).usar(heroi1);
                                energia -= maoJogador.get(comando - 1).getCusto();
                                cartasNaMao--;
                                pilhaDescarte.push(maoJogador.remove(comando - 1));
                            }
                            System.out.println("\n=-=\nStatus:\n" + heroi1.getNome() + " (" + heroi1.getVida() + "/40 de vida) (" + heroi1.getEscudo() + " de escudo)\nvs");
                            System.out.printf("%s (%d/20 de vida) (%d de escudo)\n=-=\n\n", inimigo1.getNome(), inimigo1.getVida(), inimigo1.getEscudo());    /*status dos personagens a cada escolha de carta*/
                            if (inimigo1.estarVivo() == false){
                                System.out.println(inimigo1.getNome() + " foi derrotada!\nVocê venceu.");
                                break loopExterno;
                            }
                        }

                        System.out.println("=-=\nSeu turno acabou.\n=-=\n\n=-=\nCobrinha usou mordida venenosa!\n=-=");
                }

            }else{
                inimigo1.realizarAcao(new Entidade[]{heroi1, inimigo1, inimigo2, inimigo3});
            }

                heroi1.resetarEscudo();
            }
        }
        comandoScanner.close();
    }
}