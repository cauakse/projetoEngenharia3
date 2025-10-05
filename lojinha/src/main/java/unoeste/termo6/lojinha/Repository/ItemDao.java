package unoeste.termo6.lojinha.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unoeste.termo6.lojinha.Model.Item;

public interface ItemDao extends JpaRepository<Item, Long> {
}
