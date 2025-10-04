package unoeste.termo6.lojinha.Model;

import jakarta.persistence.*;
import unoeste.termo6.lojinha.Model.possiveisExclusoes.ItemVenda;

import java.util.List;

@Table(name = "venda")
public class Venda {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total")
    private double total;

    @OneToMany(mappedBy = "item")
    private List<ItemVenda> itens;

    @ManyToOne
    @JoinColumn(name = "cliente_cpf", nullable = false)
    private Cliente cliente;

    public Venda(Long id, double total, List<ItemVenda> itens, Cliente cliente) {
        this.id = id;
        this.total = total;
        this.itens = itens;
        this.cliente = cliente;
    }

    public Venda(Long id, double total, Cliente cliente) {
        this(id,total,null,cliente);
    }

    public Venda() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    public void setItens(List<ItemVenda> itens) {
        this.itens = itens;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
