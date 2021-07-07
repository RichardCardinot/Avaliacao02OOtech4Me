package classes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Financeiro {
    private List<Venda> vendas = new ArrayList<>();
    private Estoque estoque;
    private SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

    public Financeiro (Estoque estoque) {
        setEstoque(estoque);
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }

    public int quantidadeVendas() {
        return vendas.size();
    }

    public void relatorioVendas (Scanner ler) {
        ler.nextLine();

        try { 
            System.out.printf("\nInforme o período de apuração:\n");
            Calendar dataInicial = Calendar.getInstance();
            Calendar dataFinal = Calendar.getInstance();

            System.out.printf("De(dd/mm/yyyy).: ");
            dataInicial.setTime(formatoData.parse(ler.nextLine()));
            System.out.printf("Até(dd/mm/yyyy): ");
            dataFinal.setTime(formatoData.parse(ler.nextLine()));

            System.out.printf("\n\n##################################\n###### Relatório de Vendas #######\n##################################\n");
            System.out.printf("Período: " + formatoData.format(dataInicial.getTime()) + " a " + formatoData.format(dataFinal.getTime()) + "\n");

            dataInicial.add(Calendar.DAY_OF_MONTH, -1);
            dataFinal.add(Calendar.DAY_OF_MONTH, +1);            

            double acumuladoVendas = 0;
            int totalVendas = 0;
            boolean temVenda = false;
            for (int i = 0; i < vendas.size(); i++) {

                if(dataInicial.before(vendas.get(i).getDataVenda()) && dataFinal.after(vendas.get(i).getDataVenda())) {
                    String vendasPeriodo = vendas.get(i).toString();
                    System.out.printf(vendasPeriodo + "\n");
                    acumuladoVendas = acumuladoVendas + vendas.get(i).getVlVenda();
                    totalVendas = totalVendas + ((i + 1) / (i + 1));
                    temVenda = true;
                    
                } 
            }

            if (!temVenda) {
                System.out.printf("\nNão há vendas no período informado.\n");
                System.out.printf("\n##################################");
            } else {
                double vlMedioVendas = acumuladoVendas / totalVendas;
                System.out.printf("\nValor médio das vendas: R$ %.2f\n##################################", vlMedioVendas);
            }           
            
        } catch (ParseException e) {
            System.out.printf("Formato inválido!\nInforme a data no formato dd/mm/yyyy.");
        }  

    }

    public void realizarVenda(Scanner ler) {

        System.out.printf("\n##################################\n######## Área de Vendas ##########\n##################################\n");
        System.out.println("Selecione o produto:");
        int qtVenda = 0;
        int consulta = estoque.consultarProduto(ler);

        switch (consulta) {
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
            Produto produto = estoque.produto(consulta);
            try {

                System.out.printf("Produto: %s.\n\nInforme a quantidade: ",produto.getNmProduto());
                int qtVendaInformada = ler.nextInt();

                    if (qtVendaInformada > 0 && qtVendaInformada > 0) {
                        qtVenda = qtVendaInformada;
                    } else {
                    System.out.printf("Quantidade inválida!");
                    }
                
                if (qtVenda <= produto.getQtEstoque() ) {
                    ler.nextLine();           

                        try {
                            System.out.printf("Data da venda(dd/mm/yyyy): ");
                            String dtVenda = ler.nextLine();
                            Calendar dataVenda = Calendar.getInstance();
                            dataVenda.setTime(formatoData.parse(dtVenda));

                            double vlVenda = qtVenda * produto.getVlProduto();                
                            Venda venda = new Venda(dataVenda, produto, qtVenda, vlVenda);
                            vendas.add(venda);   
                            produto.reduzirQtProduto(qtVenda);   
                            System.out.printf("\nVenda realizada com sucesso!\n\n- Detalhes:" + venda.toString());

                        } catch (ParseException e) {
                            System.out.printf("Formato inválido!\nInforme a data no formato dd/mm/yyyy.");
                        }
        
        
                } else {
                    System.out.printf("\nVenda não realizada!\nMotivo: Não há estoque suficiente de %s.\n\n##################################", produto.getNmProduto());
                    
                }

            } catch (InputMismatchException e) {
                System.out.printf("Quantidade inválida!");            
            }
 
                break;
        }         
        
    }
    
}
