package unoeste.termo6.lojinha.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unoeste.termo6.lojinha.Model.Cliente;

public interface ClienteDao extends JpaRepository<Cliente, String> {
}
