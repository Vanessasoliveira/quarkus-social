package org.acme.vanessasoliveira.quarkussocial.domain.model.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.acme.vanessasoliveira.quarkussocial.domain.model.User;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped //vai criar uma instância do repositório para utilizar onde quiser como um singleton
public class UserRepository implements PanacheRepository<User> {
}
