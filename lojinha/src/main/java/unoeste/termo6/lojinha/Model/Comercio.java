package unoeste.termo6.lojinha.Model;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


import java.util.ArrayList;
import java.util.List;

public abstract class Comercio {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total")
    private double total;

    @OneToMany(mappedBy = "item")
    private List<Item> itens;

    public Comercio(Long id, double total, List<Item> itens) {
        this.id = id;
        this.total = total;
        this.itens = itens;
    }

    public Comercio(Long id, double total) {
        this(id,total,new ArrayList<>());
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

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }
}
