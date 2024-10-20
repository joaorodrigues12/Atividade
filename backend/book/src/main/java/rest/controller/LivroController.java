package rest.controller;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.entity.Livro;
import model.repository.LivroRepository;

import java.util.List;

@Path("/livros")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LivroController {

    private final LivroRepository livroRepository;

    public LivroController(LivroRepository livroRepository) {

        this.livroRepository = livroRepository;
    }

    @GET
    public List<Livro> listarTodos() {
        return livroRepository.listAll();
    }

    @Transactional
    @POST
    public Response salvar(Livro livro) {
        livroRepository.persist(livro);
        return Response.ok(livro).build();
    }

    @Transactional
    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") Long id, Livro livro) {

        Livro livroExistente = livroRepository.findById(id);

        if (livroExistente == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        livroExistente.setTitulo(livro.getTitulo());
        livroExistente.setAutor(livro.getAutor());
        livroExistente.setDescricao(livro.getDescricao());

        livroRepository.persist(livroExistente);

        return Response.ok(livroExistente).build();
    }


    @Transactional
    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {

        Livro livro = livroRepository.findById(id);

        if (livro == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        livroRepository.delete(livro);

        return Response.noContent().build();  // Retorna 204 No Content
    }

}
