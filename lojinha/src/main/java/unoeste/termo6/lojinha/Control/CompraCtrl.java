package unoeste.termo6.lojinha.Control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import unoeste.termo6.lojinha.Model.*;
import unoeste.termo6.lojinha.Repository.CompraDao;
import unoeste.termo6.lojinha.Repository.ProdutoDao;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CompraCtrl extends ComercioCtrl{

    @Autowired
    CompraDao compraDao;

    @Autowired
    ProdutoDao produtoDao;

    @Override
    protected ResponseEntity<Object> finalizadoComSucesso(Comercio comercio, String frase) {
        Map<String, Object> resposta = new HashMap<>();
        resposta.put("status", true);
        resposta.put("mensagem", "Compra " +frase+ " com sucesso");
        resposta.put("comercio",(Compra) comercio);
        return ResponseEntity.ok(resposta);
    }

    @Override
    protected boolean gravarPrincipal(Comercio comercio) {
        Compra compra = (Compra) comercio;
        return compraDao.save(compra) != null;
    }

    @Override
    protected Comercio pegaAtributo(Map<String, Object> dados, Long id, double total, ArrayList<Item> itens) {
        try{
            Map<String, Object> cliente= (Map<String, Object>) dados.get("fornecedor");
            String cnpj= cliente.get("cnpj").toString();
            String nome= cliente.get("nome").toString();
            String telefone= cliente.get("telefone").toString();
            String email= cliente.get("email").toString();
            return new Venda(id,total,itens,new Cliente(cnpj,nome,telefone,email));
        }
        catch (Exception e){
            return null;
        }

    }

    @Override
    protected boolean verificarEstoque(ArrayList<Item> itens, Comercio cliente) {
        //verifica se algum item tem estoque zero e vai receber algo, dai notifica os observadores
        List<Produto> produtos= produtoDao.getAll();
        for (Item item:itens){
            int i;
            for (i = 0; i < produtos.size() && produtos.get(i).getId()!=item.getProduto().getId(); i++);
            Produto prod= produtos.get(i);
            if (prod.getQuantidade()==0){
                prod.notificarObservers();
                prod.removeObservers();
                produtoDao.save(prod);
            }
        }
        return true;
    }

    @Override
    protected boolean atualizarEstoque(List<Item> itens, int multi) {
        boolean flag=true;
        int multValor= 1*multi;
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