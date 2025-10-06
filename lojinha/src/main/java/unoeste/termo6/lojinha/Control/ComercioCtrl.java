package unoeste.termo6.lojinha.Control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import unoeste.termo6.lojinha.Model.*;
import unoeste.termo6.lojinha.Model.Observer;
import unoeste.termo6.lojinha.Repository.ItemDao;

import java.util.*;

@Service
public abstract class ComercioCtrl {

    @Autowired
    ItemDao itemDao;

    public final ResponseEntity<Object> gravar(Map<String, Object> dados) {
        double total = Double.parseDouble(dados.get("total").toString());
        ArrayList<Item> itens = (ArrayList<Item>) dados.get("itens");
        Comercio comercio = pegaAtributo(dados, 0L, total, itens);
        Map<String, Object> resposta = new HashMap<>();
        //verificar se é uma venda e se todos os itens tem estoque, se nao tiver poe o cliente na lista de observadores
        //verificar se é uma compra e se algum item tem estoque negativo, se tiver notifica o cliente que chegou o produto

        if (verificarEstoque(itens,comercio)){

            if (gravarPrincipal(comercio)) {
                int i;
                for (i = 0; i < comercio.getItens().size() && itemDao.save(comercio.getItens().get(i)) != null; i++) ;
                if (i == comercio.getItens().size()){
                    if(atualizarEstoque(comercio.getItens(),1))
                        return finalizadoComSucesso(comercio, "gravada");
                    else {
                        resposta.put("mensagem", "Não foi possivel concluir o procedimento, erro ao atualizar estoque dos produtos itens");
                        resposta.put("status", false);
                    }

                }
                else {
                    resposta.put("mensagem", "Não foi possivel concluir o procedimento, erro ao gravar itens");
                    resposta.put("status", false);
                }

            } else {
                resposta.put("mensagem", "Não foi possivel concluir o procedimento, erro ao gravar");
                resposta.put("status", false);
            }
        }
        else{
            resposta.put("mensagem", "Não foi possivel concluir o procedimento, erro no estoque dos produtos itens");
            resposta.put("status", false);
        }
        return ResponseEntity.badRequest().body(resposta);
    }

    protected abstract boolean verificarEstoque(ArrayList<Item> itens,Comercio cliente);


    public final ResponseEntity<Object> alterar(Map<String, Object> dados) {
        Long id = Long.parseLong(dados.get("id").toString());
        double total = Double.parseDouble(dados.get("total").toString());
        ArrayList<Item> itens = (ArrayList<Item>) dados.get("itens");
        Comercio comercio = pegaAtributo(dados, id, total, itens);
        Map<String, Object> resposta = new HashMap<>();
        if (gravarPrincipal(comercio)) { // se tiver ID ele faz update
            int i;
            try {
                if(atualizarEstoque(comercio.getItens(),-1)){
                    for (i = 0; i < comercio.getItens().size(); i++)
                        itemDao.delete(comercio.getItens().get(i));
                    for (i = 0; i < comercio.getItens().size() && itemDao.save(comercio.getItens().get(i)) != null; i++) ;
                    if (i == comercio.getItens().size()) {
                        if (atualizarEstoque(comercio.getItens(),1)) {
                            return finalizadoComSucesso(comercio, "alterada");
                        }
                        else{
                            resposta.put("mensagem", "Não foi possivel concluir o procedimento, erro ao atualizar o estoque dos itens");
                            resposta.put("status", false);
                        }
                    } else {
                        resposta.put("mensagem", "Não foi possivel concluir o procedimento, erro ao alterar itens");
                        resposta.put("status", false);
                    }
                }
                else{
                    resposta.put("mensagem", "Não foi possivel concluir o procedimento, erro ao atualizar o estoque dos itens");
                    resposta.put("status", false);
                }
            } catch (Exception e) {
                resposta.put("mensagem", "Não foi possivel concluir o procedimento, erro ao atualizar o estoque dos itens");
                resposta.put("status", false);
            }
        } else {
            resposta.put("mensagem", "Não foi possivel concluir o procedimento, erro ao alterar");
            resposta.put("status", false);
        }
        return ResponseEntity.badRequest().body(resposta);
    }

    protected abstract boolean atualizarEstoque(List<Item> itens, int multi);

    protected abstract ResponseEntity<Object> finalizadoComSucesso(Comercio comercio,String frase);

    protected abstract boolean gravarPrincipal(Comercio comercio);

    protected abstract Comercio pegaAtributo(Map<String, Object> dados, Long id, double total, ArrayList<Item> itens);

}