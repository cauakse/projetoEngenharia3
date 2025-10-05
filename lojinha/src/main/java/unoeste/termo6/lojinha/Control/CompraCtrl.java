package unoeste.termo6.lojinha.Control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import unoeste.termo6.lojinha.Model.*;
import unoeste.termo6.lojinha.Repository.CompraDao;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class CompraCtrl extends ComercioCtrl{

    @Autowired
    CompraDao compraDao;

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
}
