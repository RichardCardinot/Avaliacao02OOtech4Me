package classes;

public class Produto {
    private Estoque estoque;
    private int cdProduto, qtEstoque;
    private String nmProduto;
    private double vlProduto;

    public Produto(int cdProduto,String nmProduto, int qtEstoque, double vlProduto) {
        setCdProduto(cdProduto);
        setNmProduto(nmProduto);
        setQtEstoque(qtEstoque);
        setVlProduto(vlProduto);      
    }

    public Estoque getEstoque() {
        return estoque;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }
    
    public Produto (){
    }

    public int getCdProduto() {
        return cdProduto;
    }
    public void setCdProduto(int cdProduto) {
        this.cdProduto = cdProduto;
    }
    public int getQtEstoque() {
        return qtEstoque;
    }
    public void setQtEstoque(int qtEstoque) {
        this.qtEstoque = qtEstoque;
    }
    public String getNmProduto() {
        return nmProduto;
    }
    public void setNmProduto(String nmProduto) {
        this.nmProduto = nmProduto;
    }
    public double getVlProduto() {
        return vlProduto;
    }
    public void setVlProduto(double vlProduto) {
        this.vlProduto = vlProduto;
    }

    public void reduzirQtProduto(int redutor) {
        this.qtEstoque = this.qtEstoque - redutor;
    }

    @Override
    public String toString() {
        String dadosProduto = "";

        dadosProduto = dadosProduto + "Código....: " + getCdProduto() + "\n";
        dadosProduto = dadosProduto + "Nome......: " + getNmProduto() + "\n";
        dadosProduto = dadosProduto + "Valor.....: R$ " + getVlProduto() + "\n";
        dadosProduto = dadosProduto + "Quantidade: " + getQtEstoque();
        
        return dadosProduto;
    }
  
}