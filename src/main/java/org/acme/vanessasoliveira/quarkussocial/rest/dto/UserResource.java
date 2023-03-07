package org.acme.vanessasoliveira.quarkussocial.rest.dto;


import io.quarkus.hibernate.orm.panache.PanacheQuery;
import org.acme.vanessasoliveira.quarkussocial.domain.model.User;
import org.acme.vanessasoliveira.quarkussocial.domain.model.repository.UserRepository;


import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON) //consumir um objeto json
@Produces(MediaType.APPLICATION_JSON) //produzir um objeto json
public class UserResource {

    private UserRepository repository;
    private Validator validator;
@Inject
    public UserResource(UserRepository repository, Validator validator){

        this.repository = repository;
        this.validator = validator;
    }
    @POST
    @Transactional //sempre que for fazer alguma alteração no bd, se for listar não precisa
    public Response createUser(CreateUserRequest userRequest){

        Set<ConstraintViolation<CreateUserRequest>> violations = validator.validate(userRequest);

        if(!violations.isEmpty()){

            return ResponseError.createFromValidation(violations).withStatusCode(ResponseError.UNPROCESSABLE_ENTITY_STATUS);
        }


        User user = new User();
        user.setAge(userRequest.getAge());
        user.setName(userRequest.getName());

       repository.persist(user);//faz a persistência (salva no bd)

        //user.delete("delete from User where age < 18");

        //Desse modo retornará o código de status 201 ficando mais específico
        return  Response
                .status(Response.Status.CREATED.getStatusCode())
                .entity(user)
                .build();
    }

    @GET
    public Response listAllUsers(){
        PanacheQuery<User> query = repository.findAll();
        return Response.ok(query.list()).build();

    }

    @DELETE
    @Path("{id}")
    @Transactional
    public  Response deleteUser(@PathParam("id") Long id){
        User user = repository.findById(id);

        if (user!= null){
            repository.delete(user);
            return  Response.noContent().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();


    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response updateUser(@PathParam("id") Long id, CreateUserRequest userData){
        User user = repository.findById(id);
        if(user!=null){
            user.setName(userData.getName());
            user.setAge(userData.getAge());
            return  Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    }
