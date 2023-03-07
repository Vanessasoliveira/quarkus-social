package org.acme.vanessasoliveira.quarkussocial.domain.model;



import lombok.Data;

import javax.persistence.*;

@Entity //uma entidade do banco de dados
@Table(name="users")//seria opcional se o nome da tabela fosse igual, mas no bd é users e não User
@Data //utilizar com classes simples
public class User {

    @Id // se não incluir, não é possível subir a aplicação.
    @GeneratedValue(strategy = GenerationType.IDENTITY) //delega ao bd a geração do valor do ID
    private Long id;

    @Column(name = "name") //é opcional, exceto quanto o nome da variável é diferente do bd
    private String name;
    @Column(name = "age")
    private Integer age;


}
