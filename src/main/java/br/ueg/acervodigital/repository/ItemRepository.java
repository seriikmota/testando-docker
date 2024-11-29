package br.ueg.acervodigital.repository;

import br.ueg.acervodigital.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findByNumberCode(String numberCode);

    @Query("select i from Item i where upper(i.name) like upper(concat('%',:name,'%'))")
    List<Item> findByNameContaining(@Param("name") String name);

    @Query("select i from Item i where upper(i.collection) like upper(concat('%',:collection,'%'))")
    List<Item> findByCollectionContaining(@Param("collection") String collection);

    @Query("select i from Item i where upper(i.provenance) like upper(concat('%',:provenance,'%'))")
    List<Item> findByProvenanceContaining(@Param("provenance") String provenance);
}
