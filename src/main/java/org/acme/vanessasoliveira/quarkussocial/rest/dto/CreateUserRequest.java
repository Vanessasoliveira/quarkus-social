package org.acme.vanessasoliveira.quarkussocial.rest.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data//plugin lombok para diminuir o código evitando os getters and setters. A anotation @Data substitui a anotation @Getter e @Setter

public class CreateUserRequest {
    @NotBlank(message = "O nome é obrigatório")
    private String name;
    @NotNull(message = " Idade é obrigatório")
    private Integer age;



}
