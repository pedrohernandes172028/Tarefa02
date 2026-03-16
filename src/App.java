import java.util.Scanner;
import java.util.Collections;
import java.util.Stack;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Digite o nome do seu personagem:");
        Scanner nome1 = new Scanner (System.in);
        String nome = nome1.nextLine();
        Heroi heroi1 = new Heroi(nome, 40, 5);
        Inimigo inimigo1 = new Inimigo("Cobrinha", 20, 5, 10);
        CartaDano espada = new CartaDano("Espada de cobre", "causa 10 de dano ao inimigo / custa 2 energias",2);
        CartaEscudo escudo = new CartaEscudo("Escudo de madeira","concede 5 de escudo ao usuário / custa 1 energia", 1);
        int energia = 3, cartasNaMao = 0;
        List<Carta> maoJogador = new ArrayList<>();

        /*formando as pilhas */
        Stack<Carta> pilhaCompras = new Stack<>();
        Stack<Carta> pilhaDescarte = new Stack<>();
        for (int i = 0; i < 5; i++){    /*coloquei 10 cartas na pilha de compras, 5 de cada tipo*/
            pilhaCompras.push(espada);
            pilhaCompras.push(escudo);
        }
        Collections.shuffle(pilhaCompras);

        loopExterno: while (true) {

            while (cartasNaMao < 5){    /*comprando cartas*/
                if (pilhaCompras.isEmpty()){
                    Collections.shuffle(pilhaDescarte);
                    for (int i = 0; i < 10; i++){
                        pilhaCompras.push(pilhaDescarte.pop());
                    }
                }
                maoJogador.add(pilhaCompras.pop());
                cartasNaMao++;
            }

            energia = 3;
            System.out.println("\n=-=\nStatus:\n" + heroi1.getNome() + " (" + heroi1.getVida() + "/40 de vida) (" + heroi1.getEscudo() + " de escudo)\nvs");
            System.out.printf("%s (%d/20 de vida) (%d de escudo)\n=-=\n\n", inimigo1.getNome(), inimigo1.getVida(), inimigo1.getEscudo());    /*status dos personagens */
            if (heroi1.estarVivo() == false){
                System.out.println(heroi1.getNome() + " foi derrotado!\nVocê perdeu.");
                break;
            }else{
                while (energia > 0){        /*ação do heroi*/
                    System.out.println("=-=\nSeu turno começou!\n=-=\n=-=\nDeck:\n");
                    for (int i = 0; i < cartasNaMao; i++) {
                        System.out.println((i + 1) + " -> " + maoJogador.get(i).getNome() + " / " + maoJogador.get(i).getDescricao());
                    }
                    System.out.println("=-=\n\n=-=\n" + energia + "/3 de Energia disponível\n=-=\n");
                    System.out.println("=-=\nDigite o número de uma carta para usá-la ou digite 6 para passar o seu turno.\n=-=\n");
                    Scanner scanner = new Scanner (System.in);
                    int comando =  scanner.nextInt();
                    if (comando == 6){
                        System.out.println("=-=\n" + heroi1.getNome() + " passou a vez!\n=-=\n");
                        break;
                    }else if (comando > cartasNaMao) {  /*escolheu um espaço de carta que nao existe no momento */
                        System.out.println("\n=-=\nSem nenhuma carta na posição escolhida.\n=-=\n");

                    }else if (maoJogador.get(comando - 1) == espada){
                        if (energia < maoJogador.get(comando - 1).getCusto()){      /*verificando se tem energia suficiente */
                            System.out.println("\n=-=\nSem energia para realizar esta ação.\n=-=\n");
                        }else{
                        energia -= maoJogador.get(comando - 1).usar(inimigo1);
                        cartasNaMao--;
                        pilhaDescarte.push(maoJogador.remove(comando - 1));
                        }

                    }else{
                        energia -= maoJogador.get(comando - 1).usar(heroi1);
                        cartasNaMao--;
                        pilhaDescarte.push(maoJogador.remove(comando - 1));
                    }
                    System.out.println("\n=-=\nStatus:\n" + heroi1.getNome() + " (" + heroi1.getVida() + "/40 de vida) (" + heroi1.getEscudo() + " de escudo)\nvs");
                    System.out.printf("%s (%d/20 de vida) (%d de escudo)\n=-=\n\n", inimigo1.getNome(), inimigo1.getVida(), inimigo1.getEscudo());    /*status dos personagens */
                    if (inimigo1.estarVivo() == false){
                        System.out.println(inimigo1.getNome() + " foi derrotada!\nVocê venceu.");
                        break loopExterno;
                    }
                }

                System.out.println("=-=\nSeu turno acabou.\n\nCobrinha usou mordida venenosa!\n=-=");
                inimigo1.atacar(heroi1); /* Ataque do inimigo */
                heroi1.resetarEscudo();

            }
        }
    }
}