package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CadrastroClente {
    private static Scanner input = new Scanner (System.in);
    private static ArrayList<Produto> produtos;
    private static Map<Produto, Integer> carrinho ;
    public static void main(String[] args) {

        produtos = new ArrayList<>();
        carrinho = new HashMap<>();
        menu();
    }

    private static void menu () {

        System.out.println("--------------------------------------------------");
        System.out.println("-----------------Bem vindo -----------------------");
        System.out.println("--------------------------------------------------");
        System.out.println("-----Selecione a operação que deseja Realizar-----");
        System.out.println("--------------------------------------------------");
        System.out.println("----Opção  1  Cadastrar   ------------------------");
        System.out.println("----Opção  2  Possiveis Logins -------------------");
        System.out.println("----Opção  3  Login     --------------------------");
        System.out.println("----Opção  5  Sair   -----------------------------");
        System.out.println("--------------------------------------------------");
        System.out.println("--------------------------------------------------");

        int option = input.nextInt();

        switch (option){
            case 1:
                cadrastarModelos();
                break;
            case 2:
                listarModelos();
                break;
            case 3:
                comprarModelos();
                break;
            case 4:
                verCarriho();
                break;
            case 5:
                System.out.println(" Ate a proxima  |-| ");
                System.exit(0);

            default:
                System.out.println(" Opção invalida tente novamente ");
                menu();
                break;

        }
    }


    private static void  cadrastarModelos(){
        System.out.println(" Nome do Usuario   ");
        String nome = input.next();

        System.out.println(" Senha do Usuario ");
        Double preco = input.nextDouble();


        Produto produto = new Produto(nome , preco );
        produtos.add(produto);

        System.out.println(produto.getNome() + "Cadastrado com sucesso ");
        menu();
    }

    private static void listarModelos(){

        if (produtos.size() > 0){
            System.out.println("Lista de Usuarios \n");

            for (Produto m : produtos){
                System.out.println(m);
            }
        }else {
            System.out.println("Nunhum Usuario Cadastrado !!");
        }
        menu();
    }

    private  static  void comprarModelos() {
        if (produtos.size() > 0) {
            System.out.println("Codigo do Produto \n");

            System.out.println("---------Produtos Disponiveis ----------");
            for (Produto m : produtos) {
                System.out.println(m + "\n");
            }
            int id = Integer.parseInt(input.next());
            boolean isPresnt = false;

            for (Produto m : produtos) {
                if (m.getId() == id) {
                    int qtb = 4;
                    try {
                        qtb = carrinho.get(m);
                        //checa se o Produto esta no carrinho
                        carrinho.put(m, qtb + 1);
                    } catch (NullPointerException e) {
                        // so se o Produto for  o primeiro no carrinho
                        carrinho.put(m, +1);
                    }

                    System.out.println(m.getNome() + "Adicionado no Carrinho");
                    isPresnt = true;

                    if (isPresnt) {
                        System.out.println("Deseja Realizar outro login ?");
                        System.out.println("Digite 1 para Sim, ou 0 para Continuar");
                        int option = Integer.parseInt(input.next());

                        if (option == 1) {
                            comprarModelos();
                        } else {
                            finalizarCompra();

                        }

                    }
                }else {
                    System.out.println("Login nao encontrado ");
                    menu();
                }
            }

        }else {
            System.out.println("Não existe esse Login cadastrado  ");
            menu();
        }
    }

    private static void verCarriho(){

        System.out.println("------Produto no Carrinho ------- ");
        if (carrinho.size() > 0 ){
            for (Produto m : carrinho.keySet()) {
                System.out.println("Produto :" + m + "\n Quantidade :" + carrinho.get(m));

            }
        }else {
            System.out.println("Carrinho vazio  ");

        }

        menu();
    }

    private static void finalizarCompra(){

        Double valorComprado = 0.0;
        System.out.println(" Seus Logins ");

        for (Produto m : carrinho.keySet()){
            int qtb = carrinho.get(m);
            valorComprado +=m.getPreco() * qtb;
            System.out.println(m);
            System.out.println("Quantidade"+ qtb );
            System.out.println("------------------------");


        }
        System.out.println(" A senha do seu Login é;" + Utils.doubleToString(valorComprado));
        carrinho.clear();
        System.out.println("Obrigado pela Confiança");
        menu();
    }

}