package tacos;

import java.util.Date;  
import java.util.List;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@Entity
public class Taco {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private Date createdAt;
	
	@NotNull
	@Size(min= 5, message="Nazwa musi składać się z przynajmniej pięciu znaków.")
	private String name;
	
	@ManyToMany(targetEntity=Ingredient.class)
	@Size(min= 1, message="Musisz wybrać co najmniej jeden składnik.")
	private List<Ingredient> ingredients;
	
	@PrePersist
	void createdAt() {
		this.createdAt = new Date();
	}
}
