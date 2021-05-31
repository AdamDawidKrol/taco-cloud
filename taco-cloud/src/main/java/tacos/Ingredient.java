package tacos;

import javax.persistence.Entity; 
import javax.persistence.Id;
import lombok.Data;  
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@RequiredArgsConstructor
//@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
//@NoArgsConstructor(force=true)
@Entity
public class Ingredient {
  
	@Id
	private String id;
	private String name;
	private Type type;
  
  public static enum Type {
    WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
  }
  
  public void setName(String name) {
	  this.id = name;
	  this.name = name;
	  this.type = Type.WRAP;
  }

}