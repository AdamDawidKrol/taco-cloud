package tacos;

import java.util.List;
import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class Pizza {
	
	@NotNull
	@Size(min= 5, message="Nazwa musi składać się z przynajmniej pięciu znaków.")
	private String name;
	@Size(min= 1, message="Musisz wybrać co najmniej jeden składnik.")
	private List<String> ingredients;
}
