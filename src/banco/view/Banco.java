package banco.view;

import banco.helper.Utils;
import banco.model.Cliente;
import banco.model.Conta;

import java.util.ArrayList;
import java.util.Scanner;

public class Banco {

    static String nome = "NB0ss-Bank";
    static Scanner teclado = new Scanner(System.in);
    static ArrayList<Conta> contas;


    public static void main(String[] args) {
        Banco.contas = new ArrayList<Conta>();
        Banco.menu();
    }

    public static void menu(){
        int opcao = 0;
        System.out.println("----------------------------------");
        System.out.println("----------------ATM---------------");
        System.out.println("-----------"+ Banco.nome + "-------------");
        System.out.println("----------------------------------");

        System.out.println("Selecione uma opçãono menu: ");
        System.out.println("1 - Criar Conta");
        System.out.println("2 - Efetuar Saque");
        System.out.println("3 - Efetuar Depósito");
        System.out.println("4 - Efetuar Transferência");
        System.out.println("5 - Listar Contas");
        System.out.println("6 - Sair do Sistema");

        try{
            opcao = Integer.parseInt(Banco.teclado.nextLine());
        } catch(NumberFormatException e){
            System.out.println("Informe uma opção válida");
            Utils.pausar(1);
            Banco.menu();
        }

        switch(opcao){
            case 1:
                Banco.criarConta();
                break;
            case 2:
                Banco.efetuarSaque();
                break;
            case 3:
                Banco.efetuarDeposito();
                break;
            case 4:
                Banco.efetuarTransferencia();
                break;
            case 5:
                Banco.listarContas();
                break;
            case 6:
                System.out.println("Atpe a próxima");
                Utils.pausar(2);
                System.exit(0);
            default:
                System.out.println("Informe uma op~]ao válida");
                Utils.pausar(2);
                break;
        }
    }

    public static void criarConta(){
        System.out.println("Informe os dados do Cliente: ");

        System.out.println("Nome do Cliente: ");
        String nome = Banco.teclado.nextLine();

        System.out.println("E-mail do Cliente: ");
        String email = Banco.teclado.nextLine();

        System.out.println("CPF do Cliente: ");
        String cpf = Banco.teclado.nextLine();

        System.out.println("Data de nascimento do Cliente: ");
        String dataNascimento = Banco.teclado.nextLine();

        Cliente cliente = new Cliente(nome, email, cpf, Utils.stringParaDate(dataNascimento));

        Conta conta = new Conta(cliente);

        Banco.contas.add(conta);

        System.out.println("Conta criada com sucesso!");
        System.out.println("Dados da conta criada: ");
        System.out.println(conta);
        System.out.println();

        Utils.pausar(4);
        Banco.menu();

    }

    public static void efetuarSaque(){
        System.out.println("Informe o número da conta: ");
        int numero = Banco.teclado.nextInt();

        Conta conta = Banco.buscarContaPorNumero(numero);

        if(conta != null){
            System.out.println("Informe um valor para saque: ");
            Double valor = Banco.teclado.nextDouble();
            conta.sacar(valor);
        } else{
            System.out.println("Não foi encontrada a conta n° " + numero);
        }
        Utils.pausar(3);
        Banco.menu();
    }

    public static void efetuarDeposito(){
        System.out.println("Informe o número da conta: ");
        int numero = Banco.teclado.nextInt();

        Conta conta = Banco.buscarContaPorNumero(numero);

        if(conta != null){
            System.out.println("Informe um valor para depósito: ");
            Double valor = Banco.teclado.nextDouble();
            conta.depositar(valor);
        } else{
            System.out.println("Não foi encontrada a conta n° " + numero);
        }

        Utils.pausar(3);
        Banco.menu();
    }

    public static void efetuarTransferencia(){
        System.out.println("Informe o número da sua conta: ");
        int numero_o = Banco.teclado.nextInt();

        Conta conta_o = Banco.buscarContaPorNumero(numero_o);

        if(conta_o != null){
            System.out.println("Informe o número da conta destino: ");
            int numero_d = Banco.teclado.nextInt();

            Conta conta_d = Banco.buscarContaPorNumero(numero_d);

            if(conta_d != null){
                System.out.println("Informe o valor da transferência: ");
                Double valor = Banco.teclado.nextDouble();

                conta_o.transferir(conta_d, valor);
            } else{
                System.out.println("Não foi encontrada a conta n° " + numero_d);
            }
        } else{
            System.out.println("Não foi encontrada a conta n° " + numero_o);
        }

        Utils.pausar(3);
        Banco.menu();
    }

    public static void listarContas(){
        if(Banco.contas.size() > 0){
            System.out.println("Listagem de Contas");
            for(Conta conta : Banco.contas){
                System.out.println(conta);
                System.out.println();
                Utils.pausar(1);
            }
            System.out.println();
        } else{
            System.out.println("Não existem contas cadastradas ainda.");
            Utils.pausar(3);
            Banco.menu();
        }
    }

    public static Conta buscarContaPorNumero(int numero){
        Conta c = null;

        if(Banco.contas.size() > 0){
            for(Conta conta : Banco.contas){
                if(conta.getNumero() == numero){
                    c = conta;
                }
            }
        }

        return c;
    }
}
