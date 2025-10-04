package unoeste.termo6.lojinha.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unoeste.termo6.lojinha.Model.possiveisExclusoes.ItemCompra;

public interface ItemCompraDao extends JpaRepository<ItemCompra, Long> {

}
