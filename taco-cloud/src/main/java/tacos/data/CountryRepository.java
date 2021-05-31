package tacos.data;

import org.springframework.data.repository.CrudRepository;
import tacos.Country;

public interface CountryRepository extends CrudRepository<Country, Integer> {

}
