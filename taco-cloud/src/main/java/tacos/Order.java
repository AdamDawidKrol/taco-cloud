package tacos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.PrePersist;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.CreditCardNumber;
import lombok.Data;


@Data
@Entity
@Table(name="Taco_Order")
public class Order implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private Date placedAt;
	
	@NotBlank(message="Podanie imienia i nazwiska jest obowiązkowe")
	private String name;
	
	@NotBlank(message="Podanie ulicy jest obowiązkowe")
	private String street;
	
	@NotBlank(message="Podanie miejscowości jest obowiązkowe")
	private String city;
	
	@NotBlank(message="Podanie województwa jest obowiązkowe")
	private String state;
	
	@NotBlank(message="Podanie kodu pocztowego jest obowiązkowe")
	private String zip;
	
	@CreditCardNumber(message="To nie jest prawidłowy kod karty kredytowej")
	private String ccNumber;
	private String ccExpiration;
	private String ccCVV;
	
	@ManyToMany(targetEntity=Taco.class)
	private List<Taco> tacos = new ArrayList<>();
	
	public void addDesign(Taco design) {
		tacos.add(design);
	}
	
	@PrePersist
	void placedAt() {
		this.placedAt = new Date();
	}
}
