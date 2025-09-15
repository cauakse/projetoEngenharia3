package unoeste.termo6.lojinha.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unoeste.termo6.lojinha.Model.Venda;

public interface VendaDao extends JpaRepository<Venda, Long> {
}
