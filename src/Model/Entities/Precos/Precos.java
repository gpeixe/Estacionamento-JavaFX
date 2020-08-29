package Model.Entities.Precos;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

public class Precos extends RecursiveTreeObject<Precos> {
    private Double preco30min;
    private Double preco1hr;
    private Double precoDemaisHoras;
    private Double precoPerNoite;
    private Double precoMensalista;

    public Precos(Double preco30min, Double preco1hr, Double precoDemaisHoras, Double precoPerNoite, Double precoMensalista) {
        this.preco30min = preco30min;
        this.preco1hr = preco1hr;
        this.precoDemaisHoras = precoDemaisHoras;
        this.precoPerNoite = precoPerNoite;
        this.precoMensalista = precoMensalista;
    }

    public Double getPreco30min() {
        return preco30min;
    }

    public void setPreco30min(Double preco30min) {
        this.preco30min = preco30min;
    }

    public Double getPreco1hr() {
        return preco1hr;
    }

    public void setPreco1hr(Double preco1hr) {
        this.preco1hr = preco1hr;
    }

    public Double getPrecoDemaisHoras() {
        return precoDemaisHoras;
    }

    public void setPrecoDemaisHoras(Double precoDemaisHoras) {
        this.precoDemaisHoras = precoDemaisHoras;
    }

    public Double getPrecoPerNoite() {
        return precoPerNoite;
    }

    public void setPrecoPerNoite(Double precoPerNoite) {
        this.precoPerNoite = precoPerNoite;
    }

    public Double getPrecoMensalista() {
        return precoMensalista;
    }

    public void setPrecoMensalista(Double precoMensalista) {
        this.precoMensalista = precoMensalista;
    }
}
