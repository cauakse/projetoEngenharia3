package unoeste.termo6.lojinha.Model;

import jakarta.persistence.*;

import java.util.List;

public class Compra {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total")
    private Double total;

    @OneToMany(mappedBy = "itemVenda")
    private List<ItemCompra> itens_compra;

    @ManyToOne
    @JoinColumn(name = "fornecedor_cnpj", nullable = false)
    private Fornecedor fornecedor;

    public Compra(Long id, Double total, List<ItemCompra> itens_compra, Fornecedor fornecedor) {
        this.id = id;
        this.total = total;
        this.itens_compra = itens_compra;
        this.fornecedor = fornecedor;
    }

    public Compra(Long id, Double total, Fornecedor fornecedor) {
        this(id,total,null,fornecedor);
    }

    public Compra() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<ItemCompra> getItens_compra() {
        return itens_compra;
    }

    public void setItens_compra(List<ItemCompra> itens_compra) {
        this.itens_compra = itens_compra;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
}
