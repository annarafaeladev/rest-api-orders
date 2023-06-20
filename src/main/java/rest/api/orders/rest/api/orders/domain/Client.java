package rest.api.orders.rest.api.orders.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import rest.api.orders.rest.api.orders.dto.ClientCreateDto;
import rest.api.orders.rest.api.orders.dto.ProductCreateDto;

import java.util.UUID;

@Entity(name = "Client")
@Table(name = "clients")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @NotBlank
    @Column(nullable = false, unique = true, length = 14)

    private String cpf;

    @NotBlank
    @Column(nullable = false, length = 50)
    private String name;

    public Client(ClientCreateDto clientCreateDto){
        this.email = clientCreateDto.email();
        this.cpf = clientCreateDto.cpf();
        this.name = clientCreateDto.name();
    }

}
