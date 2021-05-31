package tacos.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import tacos.Taco;
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.Order;
import tacos.Country;
import tacos.data.TacoRepository;
import tacos.data.CountryRepository;
import tacos.data.IngredientRepository;

// tag::classShell[]
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {
  
//end::classShell[]

//tag::bothRepoProperties[]
//tag::ingredientRepoProperty[]
  private final IngredientRepository ingredientRepo;
  
//end::ingredientRepoProperty[]
  private TacoRepository designRepo;
  
  private CountryRepository countryRepo;

//end::bothRepoProperties[]
  
  

   

  //tag::bothRepoCtor[]
  
  public DesignTacoController(
        IngredientRepository ingredientRepo, 
        TacoRepository designRepo, CountryRepository countryRepo) {
    this.ingredientRepo = ingredientRepo;
    this.designRepo = designRepo;
    this.countryRepo = countryRepo;
  }

  //end::bothRepoCtor[]
  
  // tag::modelAttributes[]
  @ModelAttribute(name = "order")
  public Order order() {
    return new Order();
  }
  
  @ModelAttribute(name = "design")
  public Taco taco() {
    return new Taco();
  }

  // end::modelAttributes[]
  // tag::showDesignForm[]
  
  @GetMapping
  public String showDesignForm(Model model) {
	Ingredient marchew = new Ingredient();
	marchew.setName("marchew");
	//ingredientRepo.save(marchew);
	
    List<Ingredient> ingredients = new ArrayList<>();
    List<Country> countries = new ArrayList<>();
    countryRepo.findAll().forEach(i -> {countries.add(i); });
    
    System.out.println("before printing");
    for (Country c : countries) {
    	System.out.println("printing...");
    	System.out.println(c.getName());
    }
    
    
    Type[] types = Ingredient.Type.values();
    for (Type type : types) {
      model.addAttribute(type.toString().toLowerCase(), 
          filterByType(ingredients, type));
    }

    //model.addAttribute("design", new Taco());
    return "design";
  }
//end::showDesignForm[]

  //tag::processDesign[]
  @PostMapping
  public String processDesign(
      @Valid  @ModelAttribute Taco design, Errors errors, 
      @ModelAttribute Order order) {

    if (errors.hasErrors()) {
    	FieldError fieldError = errors.getFieldError();
        System.out.println(fieldError.toString());
      return "home";
    }

    Taco saved = designRepo.save(design);
    order.addDesign(saved);

    return "redirect:/orders/current";
  }
  //end::processDesign[]
  
  private List<Ingredient> filterByType(
      List<Ingredient> ingredients, Type type) {
    return ingredients
              .stream()
              .filter(x -> x.getType().equals(type))
              .collect(Collectors.toList());
  }

  /*
//tag::classShell[]
  ...
//end::classShell[]
   */
//tag::classShell[]

}
//end::classShell[]