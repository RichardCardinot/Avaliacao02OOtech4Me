package classes;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Estoque {
    private List<Produto> estoque = new ArrayList<>();

    public int tamanhoEstoque() {
        return estoque.size();
    }

    public Produto produto (int posicao) {
        return estoque.get(posicao);
    }

    public void cadastrarProduto(Scanner ler) {
        int qtEstoque = 0;
        String nmProduto = "";  
        double vlProduto = 0.00;


        try {
            System.out.printf("\n##################################\n###### Cadastro de produto #######\n##################################");                    
            System.out.printf("\nInforme o código do produto....: ");
            int cdProduto = ler.nextInt();
            boolean utilizado = false;

        for (int i = 0; i < estoque.size() && !utilizado; i++) {
            utilizado = cdProduto == estoque.get(i).getCdProduto();          
        }

        if (!utilizado) {
            ler.nextLine();
            System.out.printf("Informe o nome do produto......: ");
            nmProduto = ler.nextLine();
            System.out.printf("Informe o quantidade do produto: ");
            qtEstoque = ler.nextInt();
            ler.nextLine();
            System.out.printf("Informe o valor do produto.....: R$ ");
            vlProduto = ler.nextDouble(); 

            Produto produto = new Produto(cdProduto, nmProduto, qtEstoque, vlProduto);
            estoque.add(produto);
            System.out.printf("\nProduto cadastrado com sucesso!\n\n##################################\n");
        } else {
            System.out.printf("\nO código %d já se encontra em uso.\n\n##################################\n", cdProduto);
        }
                      
        } catch (InputMismatchException e) {
            System.out.printf("\nQuantidade ou valor inválido!\n\nTente novamente.\n\n##################################\n");
        }
    }


    public int consultarProduto (Scanner ler) {
        String[] consulta = {"1 - Código", "2 - Nome", "Opcão:"};

        String consultar = "";
        String pula;
        int opc = -1;

            for (int i = 0; i < consulta.length; i++) {
                if(i < consulta.length - 1) {
                    pula = "\n";
                } else {
                    pula = " ";
                }

            consultar = consultar + consulta[i] + pula;
        }  

        try {
            System.out.printf(consultar);
            int cd = ler.nextInt();
            if (cd == 1 || cd == 2) {
            opc = cd;  
            }
       
        } catch (InputMismatchException e) {
            return -1; //Opção inválida!
        }

        if (opc == 1) {
            int posicaoCodigo = -2;
            System.out.printf("\n##################################\n###### Consulta por Código #######\n##################################\n");


            try {
                ler.nextLine();
                System.out.printf("Informe o código: ");
                int cdInformado = ler.nextInt();
                boolean achou = false;
        
                for (int i = 0; i < estoque.size() && !achou; i++) {
                    if(cdInformado == estoque.get(i).getCdProduto()) {
                        posicaoCodigo = i;
                        achou = true;
                    } else {
                        posicaoCodigo = -2; //Produto não localizado.

                    }       
                }
            } catch (InputMismatchException e) {
                return -3; //Código ou descrição inválida!
            }
   

            return posicaoCodigo;
        } else if (opc == 2){
            int posicaoNome = -2;
            System.out.printf("\n##################################\n####### Consulta por Nome ########\n##################################\n");

            try {
                ler.nextLine();
                System.out.printf("Informe o nome: ");
                String nome = ler.nextLine();
                boolean achou = false;
        
                for (int i = 0; i < estoque.size() && !achou; i++) {
                    if(nome.equals(estoque.get(i).getNmProduto())) {
                        posicaoNome = i;
                        achou = true;
                    } else {
                        posicaoNome = -2; //Produto não localizado.
                    }       
                }                  
        
                return posicaoNome;                
            } catch (InputMismatchException e) {
                return -3; //Código ou descrição inválida!
            }

        } else {
            return -1; //Opção inválida!
        }
    }

    public String visualizarProduto(int posicaoProduto) {
        return "\n" + estoque.get(posicaoProduto).toString();
    }

    public void listarProdutos() {
        // Emite a listagem de produtos.
        double vlTot = 0.00, vlMaximo = 0.00, vlMinimo = 0.00;

        System.out.printf("\n##################################\n###### Listagem de Produtos ######\n##################################\n");

        for (int i = 0; i < estoque.size(); i++) {
            String ListagemP = estoque.get(i).toString();
            System.out.printf(ListagemP + "\n\n");

            vlTot = vlTot + estoque.get(i).getVlProduto();
            
            if(estoque.get(i).getVlProduto() > vlMaximo){
                vlMaximo = estoque.get(i).getVlProduto();
            }

            if(estoque.get(i).getVlProduto() <= estoque.get(0).getVlProduto()){
                vlMinimo = estoque.get(i).getVlProduto();
            }
        }

        double vlMedio = vlTot / estoque.size();

        System.out.printf("- O valor médio é de.: R$ %.2f.\n- O valor máximo é de: R$ %.2f.\n- O valor mínimo é de: R$ %.2f.\n\n##################################\n", vlMedio, vlMaximo, vlMinimo);
    }

}
