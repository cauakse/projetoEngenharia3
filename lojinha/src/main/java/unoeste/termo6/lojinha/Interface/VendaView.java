package unoeste.termo6.lojinha.Interface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unoeste.termo6.lojinha.Control.ComercioCtrl;

import java.util.HashMap;

@RestController
@CrossOrigin
@RequestMapping("comercio")
public class VendaView {

    @Autowired
    VendaCtrl vendaCtrl;

    @PostMapping
    public ResponseEntity<Object> gravar(HashMap<Object, String> dados){
        return vendaCtrl.gravar(dados);
    }
}
