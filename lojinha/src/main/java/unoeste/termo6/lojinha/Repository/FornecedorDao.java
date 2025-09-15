package unoeste.termo6.lojinha.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unoeste.termo6.lojinha.Model.Fornecedor;

public interface FornecedorDao extends JpaRepository<Fornecedor,String> {
}
