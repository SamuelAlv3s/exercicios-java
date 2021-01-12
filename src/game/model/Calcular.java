package game.model;

import java.util.Random;

public class Calcular {

    private int dificulade;
    private int valor1;
    private int valor2;
    private int operacao;
    private int resultado;

    public Calcular(int dificuldade){
        this.dificulade = dificuldade;

        Random rand = new Random();
        this.operacao = rand.nextInt(3); // 0 = somar, 1 = subtrair, 2 = multiplicar

        if(dificuldade == 1){
            // Fácil
            this.valor1 = rand.nextInt(10); // 0 à 9
            this.valor2 = rand.nextInt(10); // 0 à 9
        } else if(dificuldade == 2){
            // Médio
            this.valor1 = rand.nextInt(100); // 0 à 99
            this.valor2 = rand.nextInt(100); // 0 à 99
        } else if(dificuldade == 3){
            // Difícil
            this.valor1 = rand.nextInt(1000); // 0 à 999
            this.valor2 = rand.nextInt(1000); // 0 à 999
        } else if(dificuldade == 4){
            // +Difícil
            this.valor1 = rand.nextInt(10000); // 0 à 9999
            this.valor2 = rand.nextInt(10000); // 0 à 9999
        } else{
            // Pró-player
            this.valor1 = rand.nextInt(100000); // 0 à 99999
            this.valor2 = rand.nextInt(100000); // 0 à 99999
        }
    }

    public int getDificulade() {
        return dificulade;
    }

    public int getValor1() {
        return valor1;
    }

    public int getValor2() {
        return valor2;
    }

    public int getOperacao() {
        return operacao;
    }

    public int getResultado() {
        return resultado;
    }

    public String toString(){
        String op;
        if(this.getOperacao() == 0){
            op = "Somar"
        } else if(this.getOperacao() == 1){
            op = "Diminuir";
        } else if(this.getOperacao() == 2){
            op = "Multiplicar";
        } else{
            op = "Operação desconhecida";
        }

        return "Valor 1: " + this.getValor1() +
                "\nValor 2: " + this.getValor2() +
                "\nDificulade: " + this.getDificulade() +
                "\nOperação: " + op;
    }

    public boolean somar(int resposta){
        this.resultado = this.valor1 + this.valor2;

        boolean certo = false;

        if(resposta == this.resultado){
            System.out.println("Resposta Correta!");
            return certo = true;
        } else{
            System.out.println("Resposta Errada!");
        }
        System.out.println(this.getValor1() + " + " + this.getValor2() + " = " + this.getResultado());
        return certo;
    }

    public boolean diminuir(int resposta){
        this.resultado = this.valor1 - this.valor2;

        boolean certo = false;

        if(resposta == this.resultado){
            System.out.println("Resposta Correta!");
            return certo = true;
        } else{
            System.out.println("Resposta Errada!");
        }
        System.out.println(this.getValor1() + " - " + this.getValor2() + " = " + this.getResultado());
        return certo;
    }

    public boolean multiplicar(int resposta){
        this.resultado = this.valor1 * this.valor2;

        boolean certo = false;

        if(resposta == this.resultado){
            System.out.println("Resposta Correta!");
            return certo = true;
        } else{
            System.out.println("Resposta Errada!");
        }
        System.out.println(this.getValor1() + " * " + this.getValor2() + " = " + this.getResultado());
        return certo;
    }
}
