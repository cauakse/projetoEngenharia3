package unoeste.termo6.lojinha.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "Compra")
@Entity
public class Compra extends Comercio{


    @ManyToOne
    @JoinColumn(name = "fornecedor_cnpj", nullable = false)
    private Fornecedor fornecedor;

    public Compra(Long id, double total, List<Item> itens, Fornecedor fornecedor) {
        super(id,total,itens);
        this.fornecedor = fornecedor;
    }

    public Compra(Long id, double total, Fornecedor fornecedor) {
        this(id,total,new ArrayList<>(),fornecedor);
    }

    public Compra() {
        this(0L,0,new ArrayList<>(),new Fornecedor());
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
}
