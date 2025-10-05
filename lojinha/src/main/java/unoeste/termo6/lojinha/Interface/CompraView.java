package unoeste.termo6.lojinha.Interface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unoeste.termo6.lojinha.Control.CompraCtrl;
import unoeste.termo6.lojinha.Control.VendaCtrl;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("comercio/venda")
public class CompraView {

    @Autowired
    CompraCtrl compraCtrl;

    @PostMapping
    public ResponseEntity<Object> gravar(Map<String, Object> dados){
        return compraCtrl.gravar(dados);
    }

    @PutMapping
    public ResponseEntity<Object> alterar(Map<String, Object> dados){
        return compraCtrl.alterar(dados);
    }
}
