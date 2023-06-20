package rest.api.orders.rest.api.orders.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import rest.api.orders.rest.api.orders.dto.ProductCreateDto;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Product")
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @ManyToMany(mappedBy = "products")
    private List<Order> orders = new ArrayList<>();

    public Product(ProductCreateDto productDto){
        this.name = productDto.name();
        this.description = productDto.description();
    }

}
