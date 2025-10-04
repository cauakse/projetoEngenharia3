package unoeste.termo6.lojinha.Model;

import jakarta.persistence.*;

public class Item {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "comercio_id", nullable = false)
    private Compra compra;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @Column(name = "preco", nullable = false)
    private Double preco;

    public Item(Long id, Produto produto, Compra compra, Integer quantidade, Double preco) {
        this.id = id;
        this.produto = produto;
        this.compra = compra;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public Item(Produto produto, Compra compra, Integer quantidade, Double preco) {

        this(0L,produto,compra,quantidade,preco);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
