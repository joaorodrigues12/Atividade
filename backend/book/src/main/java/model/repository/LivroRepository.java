package model.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import model.entity.Livro;

@ApplicationScoped
public class LivroRepository implements PanacheRepository<Livro> {


}
