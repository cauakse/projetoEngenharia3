package unoeste.termo6.lojinha.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import unoeste.termo6.lojinha.Model.Produto;

import java.util.List;

public interface ProdutoDao extends JpaRepository<Produto,Long> {

    @Query("select p from Produto p")
    List<Produto> getAll();

}
