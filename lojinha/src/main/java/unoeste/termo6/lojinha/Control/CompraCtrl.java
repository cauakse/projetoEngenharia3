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
public class CompraCtrl extends ComercioCtrl {

    @Autowired
    CompraDao compraDao;

    @Autowired
    ProdutoDao produtoDao;

    @Override
    protected ResponseEntity<Object> finalizadoComSucesso(Comercio comercio, String frase) {
        Map<String, Object> resposta = new HashMap<>();
        resposta.put("status", true);
        resposta.put("mensagem", "Compra " + frase + " com sucesso");
        resposta.put("comercio", (Compra) comercio);
        return ResponseEntity.ok(resposta);
    }

    @Override
    protected Comercio gravarPrincipal(Comercio comercio) {
        Compra compra = (Compra) comercio;
        return compraDao.save(compra);
    }

    @Override
    protected Comercio pegaAtributo(Map<String, Object> dados, Long id, double total, ArrayList<Item> itens) {
        try {
            Map<String, Object> fornec = (Map<String, Object>) dados.get("fornecedor");
            String cnpj = fornec.get("cnpj").toString();
            String nome = fornec.get("nome").toString();
            String telefone = fornec.get("telefone").toString();
            String email = fornec.get("email").toString();

            Fornecedor fornecedor = new Fornecedor(cnpj, nome, telefone, email);

            // Cria a compra com os itens
            Compra compra = new Compra(id, total, itens, fornecedor);




            return compra;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    protected boolean verificarEstoque(ArrayList<Item> itens, Comercio cliente) {
        //verifica se algum item tinha estoque zero e agora vai receber, notifica os observadores
        List<Produto> produtos = produtoDao.getAll();
        for (Item item : itens) {
            int i;
            for (i = 0; i < produtos.size() && !produtos.get(i).getId().equals(item.getProduto().getId()); i++) ;
            if (i < produtos.size()) {
                Produto prod = produtos.get(i);
                if (prod.getQuantidade() == 0) {
                    prod.notificarObservers();
                    prod.removeObservers();
                    produtoDao.save(prod);
                }
            }
        }
        return true;
    }

    @Override
    protected boolean atualizarEstoque(List<Item> itens, int multi) {
        boolean flag = true;
        int multValor = 1 * multi;
        for (int i = 0; i < itens.size() && flag; i++) {
            Produto prod = produtoDao.getReferenceById(itens.get(i).getProduto().getId());
            prod.setQuantidade(prod.getQuantidade() + itens.get(i).getQuantidade() * multValor);
            if (prod.getQuantidade() >= 0)
                produtoDao.save(prod);
            else
                flag = false;
        }
        return flag;
    }
}
