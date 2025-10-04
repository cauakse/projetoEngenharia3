package unoeste.termo6.lojinha.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "venda")
public class Venda extends Comercio{


    @ManyToOne
    @JoinColumn(name = "cliente_cpf", nullable = false)
    private Cliente cliente;

    public Venda(Long id, double total, List<Item> itens, Cliente cliente) {
        super(id,total,itens);
        this.cliente = cliente;
    }

    public Venda(Long id, double total, Cliente cliente) {
        this(id,total,new ArrayList<>(),cliente);
    }

    public Venda() {
        this(0L,0,new ArrayList<>(),new Cliente());
    }


    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
