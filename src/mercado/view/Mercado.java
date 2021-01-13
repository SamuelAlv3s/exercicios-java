package mercado.view;

import mercado.helper.Utils;
import mercado.model.Produto;

import java.util.*;

public class Mercado {

    private static Scanner teclado = new Scanner(System.in);
    private static List<Produto> produtos;
    private static Map<Produto, Integer> carrinho;

    public static void main(String[] args) {

        produtos = new ArrayList<Produto>();
        carrinho = new HashMap<Produto, Integer>();
        Mercado.menu();
    }

    private static void menu(){
        System.out.println("==============================");
        System.out.println("======== Bem-vindo(a) ========");
        System.out.println("==============================");

        System.out.println("Selecione uma opção abaixo: ");
        System.out.println("1 - Cadastrar produto");
        System.out.println("2 - Listar produtos");
        System.out.println("3 - Comprar produto");
        System.out.println("4 - Visualizar produto");
        System.out.println("5 - Sair do sistema");

        int opcao = 0;

        try{
            opcao = Integer.parseInt(Mercado.teclado.nextLine());
        } catch (InputMismatchException e){
            Mercado.menu();
        } catch (NumberFormatException f){
            Mercado.menu();
        }

        switch (opcao){
            case 1:
                Mercado.cadastrarProduto();
                break;
            case 2:
                Mercado.listarProdutos();
                break;
            case 3:
                Mercado.comprarProduto();
                break;
            case 4:
                Mercado.visualizarCarrinho();
                break;
            case 5:
                System.out.println("Volte sempre! :)");
                Utils.pausar(2);
                System.exit(0);
            default:
                System.out.println("Opção inválida!");
                Utils.pausar(2);
                Mercado.menu();
                break;
        }
    }

        private static void cadastrarProduto(){
            System.out.println("======Cadastro de Produto=====");
            System.out.println("==============================");

            try{
                System.out.println("Informe o nome do produto: ");
                String nome = Mercado.teclado.nextLine();

                System.out.println("Informe o preço do produto: ");
                double preco = Double.parseDouble(Mercado.teclado.nextLine());

                Produto produto = new Produto(nome, preco);

                Mercado.produtos.add(produto);

                System.out.println("O produto " + produto.getNome() + " foi cadastrado com sucesso!");
                Utils.pausar(2);
                Mercado.menu();
            } catch(NumberFormatException e){
                System.out.println("Informe o valor com .(ponto) ao invés da ,(vírgula)");
                Mercado.cadastrarProduto();
            }
        }

        private static void listarProdutos(){
            if(Mercado.produtos.size() > 0){
                System.out.println("======Listagem de Produtos====");
                System.out.println("==============================");

                for(Produto p : Mercado.produtos){
                    System.out.println(p);
                    System.out.println();
                }

            } else{
                System.out.println("Ainda não possui produtos cadastrados");
            }

            Utils.pausar(2);
            Mercado.menu();
        }

        private static void comprarProduto(){
            if(Mercado.produtos.size() > 0){
                System.out.println("Informe o código do produto que deseja comprar: ");
                System.out.println();

                System.out.println("======Produtos Disponíveis====");
                System.out.println("==============================");

                for(Produto p : Mercado.produtos){
                    System.out.println(p);
                    System.out.println("------------------------------");
                }

                int codigo = Integer.parseInt(Mercado.teclado.nextLine());
                boolean tem = false;

                for(Produto p : Mercado.produtos) {
                    if (p.getCodigo() == codigo) {
                        int quant = 0;
                        try {
                            quant = Mercado.carrinho.get(p); // já tem este produto no carrinho, atualiza quantidade
                            Mercado.carrinho.put(p, quant + 1);
                        } catch (NullPointerException e) {
                            Mercado.carrinho.put(p, 1);
                        }

                        System.out.println("O produto " + p.getNome() + " foi adicionado ao carrinho");
                        tem = true;
                    }
                }
                    if(tem){
                        System.out.println("Adicionar outros produtos ao carrinho ?");
                        System.out.println("Informe 1 para sim e 0 para Não");
                        int op = Integer.parseInt(Mercado.teclado.nextLine());

                        if(op == 1){
                            Mercado.comprarProduto();
                        } else{
                            System.out.println("Aguarde enquanto calculamos o seu pedido...");
                            Utils.pausar(2);
                            Mercado.fecharPedido();
                        }
                    } else{
                        System.out.println("Não foi encontrado o produto com o código " + codigo);
                        Utils.pausar(2);
                        Mercado.menu();
                    }

                } else{
                System.out.println("Ainda não existe produtos cadastrados");
                Utils.pausar(2);
                Mercado.menu();
            }
            }

        private static void visualizarCarrinho(){
            if(Mercado.carrinho.size() > 0){
                System.out.println("=====Produtos no Carrinho=====");
                System.out.println("==============================");

                for(Produto p : Mercado.carrinho.keySet()){
                    System.out.println("Produto: " + p + "\nQuantidade: " + Mercado.carrinho.get(p));
                }
            } else{
                System.out.println("O carrinho está vazio");
            }

            Utils.pausar(2);
            Mercado.menu();
        }

        private static void fecharPedido(){
            Double valorTotal = 0.0;
            System.out.println("=====Produtos do carrinho=====");
            System.out.println("==============================");

            for(Produto p : Mercado.carrinho.keySet()){
                int quant = Mercado.carrinho.get(p);
                valorTotal += p.getPreco()*quant;
                System.out.println(p);
                System.out.println("Quantidade: " + quant);
                System.out.println("------------------------------");
            }
            System.out.println("O valor da sua fatura é: " + Utils.doubleparaString(valorTotal));
            Mercado.carrinho.clear();
            System.out.println("Obrigado pela preferência :)");
            Utils.pausar(5);
            Mercado.menu();
        }



}
