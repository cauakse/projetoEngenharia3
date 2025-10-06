package unoeste.termo6.lojinha.Control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import unoeste.termo6.lojinha.Model.*;
import unoeste.termo6.lojinha.Repository.ProdutoDao;
import unoeste.termo6.lojinha.Repository.VendaDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VendaCtrl extends ComercioCtrl {

    @Autowired
    VendaDao vendaDao;

    @Autowired
    ProdutoDao produtoDao;

    @Override
    protected ResponseEntity<Object> finalizadoComSucesso(Comercio comercio, String frase) {
        Map<String, Object> resposta = new HashMap<>();
        resposta.put("status", true);
        resposta.put("mensagem", "Venda " +frase+ " com sucesso");
        resposta.put("comercio",(Venda) comercio);
        return ResponseEntity.ok(resposta);
    }

    @Override
    protected boolean gravarPrincipal(Comercio comercio) {
        Venda venda = (Venda) comercio;
        return vendaDao.save(venda) != null;
    }

    @Override
    protected Comercio pegaAtributo(Map<String, Object> dados, Long id, double total, ArrayList<Item> itens) {
        try{
            Map<String, Object> cliente= (Map<String, Object>) dados.get("cliente");
            String cpf= cliente.get("cpf").toString();
            String nome= cliente.get("nome").toString();
            String telefone= cliente.get("telefone").toString();
            String email= cliente.get("email").toString();
            return new Venda(id,total,itens,new Cliente(cpf,nome,telefone,email));
        }
        catch (Exception e){
            return null;
        }

    }

    @Override
    protected boolean verificarEstoque(ArrayList<Item> itens,Comercio cliente) {
        List<Produto> produtos= produtoDao.getAll();
        boolean flag=true;
        for (Item item:itens){
            int i;
            for (i = 0; i < produtos.size() && produtos.get(i).getId()!=item.getProduto().getId(); i++);
            Produto prod= produtos.get(i);
            if(prod.getQuantidade()<item.getQuantidade()){
                prod.registrarObserver(((Venda) cliente).getCliente());
                produtoDao.save(prod);
                flag=false;
            }
        }
        return flag;
    }

    @Override
    protected boolean atualizarEstoque(List<Item> itens, int multi) {
        boolean flag=true;
        int multValor= -1*multi;
        for (int i = 0; i < itens.size() && flag; i++) {
            Produto prod = produtoDao.getReferenceById(itens.get(i).getProduto().getId());
            prod.setQuantidade(prod.getQuantidade()+itens.get(i).getQuantidade()*multValor);
            if(prod.getQuantidade()>=0)
                produtoDao.save(prod);
            else
                flag=false;
        }
        return flag;
    }
}