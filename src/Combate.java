
    import java.util.Scanner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Entidades.Entidade;
    import Entidades.Heroi;
    import Entidades.Inimigo;

    public class Combate {  /*vai controlar ordem de turnos, realização de ações de inimigos e heroi, notificar efeitos, controlar decks de cartas */
        private List<Inimigo> ordenado;
        private List<Inimigo> inimigos;
        private Heroi heroi;   /*posição de quem vai ser o turno em relação a ordem */

        public Combate(Heroi heroi, List<Inimigo> inimigos){
            this.heroi = heroi;
            this.inimigos = inimigos;
            this.ordenado = ordenar(inimigos);
        }

        public boolean realizarTurno(){
            verificarEfeitosGeral(1);
    
            Scanner comandoScanner = new Scanner (System.in);
            System.out.println(heroi.getStatus());
            for (int l = 0; l < inimigos.size(); l++){
                System.out.println(inimigos.get(l).getStatus());
            }
            for (int l = 1; l < inimigos.size(); l++){
                System.out.println(inimigos.get(l).anuncio());
            }
            for (int j = 0; j < ordenado.size(); j ++){
                if (ordenado.get(j).getVelocidade() < heroi.getVelocidade()){
                    int energia = 3;
                    System.out.println("=-=\nSeu turno começou.\n=-=\n");
                    if (heroi.estarVivo() == false){    
                        System.out.println(heroi.getNome() + " foi derrotado!\nVocê perdeu.");
                        break;
                    }else{
                        while (energia > 0){        /*ação do heroi*/
                            System.out.println("=-=\nDeck:\n");   /*mostrando, no terminal, as cartas na mao do jogador */
                            for (int i = 0; i < heroi.getnCartasNaMao(); i++) {
                                System.out.println((i + 1) + " -> " + heroi.getCartasNaMao().get(i).getNome() + " / " + heroi.getCartasNaMao().get(i).getDescricao());
                            }
                            System.out.println("=-=\n\n=-=\n" + energia + "/3 de Energia disponível\n=-=\n");   /*energias disponíveis */
                            System.out.println("=-=\nDigite o número de uma carta para usá-la ou digite 6 para passar o seu turno.\n=-=\n");    /*instrução*/
                            int comando =  comandoScanner.nextInt();
                            if (comando == 6){  /*passa a vez */
                                System.out.println("\n=-=\n" + heroi.getNome() + " passou a vez!\n=-=\n");
                                break;
                            }else if (comando > heroi.getnCartasNaMao()) {  /*escolheu um espaço de carta que nao existe no momento */
                                System.out.println("\n=-=\nSem nenhuma carta na posição escolhida.\n=-=\n");

                            }else if (heroi.getCartasNaMao().get(comando - 1).getCusto() > energia){   /*sem energia para realizar essa ação */
                                System.out.println("\n=-=\nSem energia para realizar esta ação.\n=-=\n");
                            }else {
                                /*criar texto no terminal com as posicoes de inimigos em inimigos para o jogador escolher */
                                comandoScanner = new Scanner (System.in);
                                int alvo =  comandoScanner.nextInt();
                                heroi.getCartasNaMao().get(comando - 1).usar(inimigos.get(alvo));

                                verificarEfeitosRealizarAcao(heroi.getCartasNaMao().get(comando - 1).getMomento(), heroi, inimigos.get(alvo));
                                if (heroi.getCartasNaMao().get(comando - 1).getMomento() == 0){ /*causou dano, por enquanto é o unico tipo que causa dano durante ação*/
                                    if (verificarMorteGeral(alvo, heroi.getCartasNaMao().get(comando - 1).getMomento())){
                                        /*mostrar no terminal que o jogador venceu */
                                        comandoScanner.close();
                                        return false;   /*nao sei porque está dando esse erro do Scanner */
                                    }
                                }
                                heroi.cartaUsada(comando);

                            System.out.println(heroi.getStatus());
                            for (int l = 0; l < inimigos.size(); l++){
                                System.out.println(inimigos.get(l).getStatus());
                            }/*status dos personagens a cada escolha de carta*/
                        }
                    }
                }

            }else{
                Random aleatorio = new Random();
                int indice = aleatorio.nextInt(ordenado.get(j).getAcoes().length);
                Entidade alvo = ordenado.get(j).realizarAcao(heroi, inimigos, indice);
                verificarEfeitosRealizarAcao(indice, ordenado.get(j), alvo);
            }
                heroi.resetarEscudo();
            }
            verificarEfeitosGeral(2);
            comandoScanner.close();
            return true;
        }


        private ArrayList<Inimigo> ordenar(List<Inimigo> inimigos){   /*ideia do bubble sort*/
            Inimigo guarda;
            ArrayList<Inimigo> desordenado = new ArrayList<>(inimigos);
            for (int i = desordenado.size(); i > 0; i--){
                for (int j = 0; j < i - 1; j++){
                    if (desordenado.get(j).getVelocidade() < desordenado.get(j + 1).getVelocidade()){
                        guarda = desordenado.get(j);
                        desordenado.set(j, desordenado.get(j + 1));
                        desordenado.set(j + 1, guarda);
                    }
                }
            }
            return desordenado;
        }
        private void verificarEfeitosGeral(int momento){
            for (int i = 0; i < ordenado.size(); i ++){    /*percorrendo todas as entidades */
                for (int j = 0; j < ordenado.get(i).getEfeitos().size(); j ++){    /*percorrendo todos os efeitos de cada entidade*/
                    for (int k = 0; k < ordenado.get(i).getEfeitos().get(j).getMomentos().length;){    /*percorrendo todos os momentos em que um efeito é ativado */
                        if (ordenado.get(i).getEfeitos().get(j).getMomentos()[k] == momento){
                            ordenado.get(i).getEfeitos().get(j).acionado();
                        }
                    }
                }
            }
                for (int j = 0; j < heroi.getEfeitos().size(); j ++){    /*percorrendo todos os efeitos de cada entidade*/
                    for (int k = 0; k < heroi.getEfeitos().get(j).getMomentos().length;){    /*percorrendo todos os momentos em que um efeito é ativado */
                        if (heroi.getEfeitos().get(j).getMomentos()[k] == momento){
                            heroi.getEfeitos().get(j).acionado();
                        }
                    }
                }
        }
        private void verificarEfeitosRealizarAcao(int momento, Entidade realizou, Entidade recebeu){

        }

        private boolean verificarMorteGeral(int alvo, int tipo){
            if (inimigos.get(alvo).estarVivo() == false){
                System.out.println(inimigos.get(alvo).getNome() + " foi derrotado!");
                for (int i = 0; i < ordenado.size(); i++){  /*remover inimigo de inimigos e de ordenado */
                    if (inimigos.get(alvo) == ordenado.get(i)){
                        ordenado.remove(i);
                    }
                }
                inimigos.remove(alvo);
                
            }
            if (inimigos.isEmpty()){    /*acabou o jogo */
                /*prinf falando que o jogador venceu */
                return true;
            }
            return false;
        }


    }
