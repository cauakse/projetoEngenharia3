package unoeste.termo6.lojinha.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unoeste.termo6.lojinha.Model.possiveisExclusoes.ItemVenda;

public interface ItemVendaDao extends JpaRepository<ItemVenda, Long> {
}
