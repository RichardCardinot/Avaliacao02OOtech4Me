package app;

import java.io.IOException;
import java.util.Scanner;

import classes.Estoque;
import classes.Financeiro;


public class ProgramaDeVendas {
    public static void main(String[] args) throws InterruptedException, IOException {

        Scanner ler = new Scanner(System.in);
        Estoque estoque = new Estoque();
        Financeiro financeiro = new Financeiro(estoque);

        int op;
        do {
            System.out.printf(exibirMenu());
            op = ler.nextInt();

            switch (op) {
                case 1: //Incluir produto
                    estoque.cadastrarProduto(ler);
                    voltarMenu(ler);

                    break;
    
                case 2: //Consultar produto
                    if (estoque.tamanhoEstoque() == 0) {                        
                        System.out.printf("\n##################################\n###### Consulta de Produto #######\n##################################\n\nNão há produtos cadastrados.\n\n##################################\n");
                        voltarMenu(ler);
                        continue;
                    }

                    System.out.printf("\n##################################\n###### Consulta de Produto #######\n##################################\n");
                    int opc = estoque.consultarProduto(ler);

                    switch (opc) {
                        case -1:
                        System.out.printf("Opção inválida!\n\n##################################\n");                        
                            break;

                        case -2:
                        System.out.printf("\nProduto não localizado.\n\n##################################\n");                         
                            break;

                        case -3:
                        System.out.printf("\nCódigo ou descrição inválida!\n\n##################################\n");                       
                            break;
                                                
                        default:
                        System.out.printf(estoque.visualizarProduto(opc) +"\n");
                            break;
                    }    

                    voltarMenu(ler);
                    break;

                case 3: // Listagem de produtos
                    if (estoque.tamanhoEstoque() == 0) {
                        System.out.printf("\n##################################\n###### Listagem de Produtos ######\n##################################\n\nNão há produtos cadastrados.\n\n##################################\n");
                        voltarMenu(ler);
                        continue;         
                    }

                    estoque.listarProdutos();  
                    voltarMenu(ler);               
                    break;

                case 4: // Vendas por período – detalhado
                if (financeiro.quantidadeVendas() == 0) {
                    System.out.printf("\n##################################\n###### Relatório de Vendas #######\n##################################\n\nNão há vendas cadastradas.\n\n##################################\n");
                    voltarMenu(ler);
                    continue;  
                }

                financeiro.relatorioVendas(ler);
                voltarMenu(ler);

                break;

                case 5: // Realizar venda
                    if (estoque.tamanhoEstoque() == 0) {
                        System.out.printf("\n##################################\n######## Área de Vendas ##########\n##################################\n\nNão há produtos cadastrados.\n\n##################################\n");
                        voltarMenu(ler);
                        continue;         
                    }
                    
                    financeiro.realizarVenda(ler);
                    voltarMenu(ler);

                    break;

                case 0: // Sair
                    System.out.printf("\n##################################\n####### SISTEMA FINALIZADO #######\n##################################");
                    ler.close();
                    System.exit(-1);
                    break;

                default:
                    System.out.println("Opção inválida!");
                    break;
                    
            }
        
        } while (op != 0);


    }

    public static String exibirMenu() {
        // Exibe o menu principal.
        final String[] menu = {"\n##################################", "############## MENU ##############", "##################################", 
        "1 – Incluir produto", "2 – Consultar produto", "3 – Listagem de produtos", "4 – Vendas por período – detalhado", 
         "5 – Realizar venda", "0 – Sair", "\nOpcao:"};

        String opcoes = "";
        String pula;
        for (int i = 0; i < menu.length; i++) {
            if(i < menu.length - 1) {
                pula = "\n";
            } else {
                pula = " ";
            }

            opcoes = opcoes +  menu[i] + pula;
        }

        return opcoes;
    }

    private static void voltarMenu(Scanner ler) throws InterruptedException, IOException {
        ler.nextLine();
        System.out.println("\nPressione ENTER para voltar ao menu.");
        ler.nextLine();

        // Limpa toda a tela, deixando novamente apenas o menu
        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            System.out.print("\033[H\033[2J");
        
        System.out.flush();
    }
}
