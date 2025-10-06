package unoeste.termo6.lojinha.Interface;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unoeste.termo6.lojinha.Control.CompraCtrl;
import unoeste.termo6.lojinha.Control.VendaCtrl;
import unoeste.termo6.lojinha.Model.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("comercio/compra")
public class CompraView {

    @Autowired
    CompraCtrl compraCtrl;

    @PostMapping
    public ResponseEntity<Object> gravar(@RequestBody  Map<String, Object> dados){
        List<Item> itens = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> dadosItens = (List<Map<String, Object>>) dados.get("itens");
        for (Map<String, Object> map : dadosItens) {
            Item item = mapper.convertValue(map, Item.class);
            itens.add(item);
        }
        dados.put("itens", itens);
        return compraCtrl.gravar(dados);
    }

    @PutMapping
    public ResponseEntity<Object> alterar(Map<String, Object> dados){
        return compraCtrl.alterar(dados);
    }
}
