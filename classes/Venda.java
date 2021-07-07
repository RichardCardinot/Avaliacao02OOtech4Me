package classes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Venda {
    private Produto produto;
    private int qtVenda;
    private double vlVenda;
    private Calendar dataVenda;
    SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

    public Venda (Calendar dataVenda, Produto produto, int qtVenda, double vlVenda) throws ParseException {
        setDataVenda(dataVenda);
        setProduto(produto);
        setQtVenda(qtVenda);
        setVlVenda(vlVenda);    
    }

    public Calendar getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Calendar dataVenda) {
        this.dataVenda = dataVenda;
    }

    public String dataVendaFormatada() {
        return formatoData.format(dataVenda.getTime());
    }

    public Produto getProduto() {
        return produto;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    public int getQtVenda() {
        return qtVenda;
    }
    public void setQtVenda(int qtVenda) {
        this.qtVenda = qtVenda;
    }

    public void setVlVenda(double vlVenda) {
        this.vlVenda = vlVenda;
    }

    public double getVlVenda() {
        return vlVenda;
    }

    @Override
    public String toString() {
        String dadosVenda =              "\nData da venda.: " + dataVendaFormatada() + "\n";
               dadosVenda = dadosVenda +   "Produto.......: " + getProduto().getNmProduto() + "\n";
               dadosVenda = dadosVenda +   "Quantidade....: " + getQtVenda() + "\n";
               dadosVenda = dadosVenda +   "Valor unitário: R$ " + produto.getVlProduto() + "\n";
               dadosVenda = dadosVenda +   "Valor total...: R$ " + getVlVenda();

        return dadosVenda;
    }
    
}
