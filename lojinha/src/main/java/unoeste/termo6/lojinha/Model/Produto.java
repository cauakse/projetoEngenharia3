package unoeste.termo6.lojinha.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "produto")
@Entity
public class Produto implements Sujeito{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome", nullable = false, length = 200)
    private String nome;
    @Column(name = "peso", nullable = false)
    private Double peso;
    @Column(name = "preco", nullable = false)
    private Double preco;
    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @ManyToMany
    @JoinTable(
            name = "cliente_produto_inscricao",
            joinColumns = @JoinColumn(name = "produto_id"),
            inverseJoinColumns = @JoinColumn(name = "cliente_cpf")
    )
    private List<Cliente> clientes = new ArrayList<>();

    @Override
    public void registrarObserver(Observer o) {
        if (o instanceof Cliente) {
            clientes.add((Cliente) o);
        }
    }

    @Override
    public void removerObserver(Observer o) {
        clientes.remove(o);
    }

    @Override
    public void removeObservers() {
        clientes.clear();
    }

    @Override
    public void notificarObservers() {
        String mensagem = "O produto " + this.nome + " está de volta ao estoque!";
        for (Cliente cliente : clientes) {
            cliente.atualizar(mensagem);
        }
    }

    public Produto() {
    }

    public Produto(Long id, String nome, Double peso, Double preco, Integer quantidade) {
        this.id = id;
        this.nome = nome;
        this.peso = peso;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
