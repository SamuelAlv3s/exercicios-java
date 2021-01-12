package game.view;

import game.model.Calcular;

import java.util.Scanner;

public class Game {

    static Scanner teclado = new Scanner(System.in);
    static int pontos = 0;
    static Calcular calc;

    public static void main(String[] args) {
        Game.jogar();
    }

    public static void jogar(){
        System.out.println("Informe o nível de dificuldade desejado [1, 2, 3, 4]: ");
        int dificuldade = Game.teclado.nextInt();

        Game.calc = new Calcular(dificuldade);

        System.out.println("Informe um resultado para a seguinte operação: ");

        // somar
        if(calc.getOperacao() == 0){
            System.out.println(calc.getValor1() + " + " + calc.getValor2() + " = ");
            int resposta = Game.teclado.nextInt();

            if(calc.somar(resposta)){
                Game.pontos++;
                System.out.println("Pontos: " + Game.pontos);
            }
        }

        // Diminuir
        else if(calc.getOperacao() == 1){
            System.out.println(calc.getValor1() + " - " + calc.getValor2() + " = ");
            int resposta = Game.teclado.nextInt();

            if(calc.diminuir(resposta)){
                Game.pontos++;
                System.out.println("Pontos: " + Game.pontos);
            }
        }

        // Multiplicar
        else if(calc.getOperacao() == 2){
            System.out.println(calc.getValor1() + " * " + calc.getValor2() + " = ");
            int resposta = Game.teclado.nextInt();

            if(calc.multiplicar(resposta)){
                Game.pontos++;
                System.out.println("Pontos: " + Game.pontos);
            }
        }

        else{
            System.out.println("A operação +" + calc.getOperacao() + " não é reconhecida :(");
        }

        System.out.println("Desesja continuar ? [1 = Sim, 0 = Não");
        int continuar = Game.teclado.nextInt();

        if(continuar == 1){
            Game.jogar();
        }else{
            System.out.println("Você fez " + Game.pontos + " pontos");
            System.out.println("Até a próxima :)");
            System.exit(0);
        }

    }
}
