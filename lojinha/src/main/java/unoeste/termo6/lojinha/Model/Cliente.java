package unoeste.termo6.lojinha.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "cliente")
@Entity
public class Cliente implements Observer{
    @Id
    @Column(name = "cpf")
    private String cpf;

    @Column(name = "nome")
    private String nome;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "email")
    private String email;

    @ManyToMany(mappedBy = "clientes")
    private List<Produto> produtoObservados = new ArrayList<>();

    @Override
    public void atualizar(String mensagem) {
        System.out.println("Notificação para o cliente " + this.nome + " (" + this.email + "): " + mensagem);
    }

    public Cliente(String cpf, String nome, String telefone, String email) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public Cliente() {
        this("","","","");
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
