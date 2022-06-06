package mobilelele.example.mobilelele.init;

import mobilelele.example.mobilelele.service.BrandService;
import mobilelele.example.mobilelele.service.ModelService;
import mobilelele.example.mobilelele.service.OfferService;
import mobilelele.example.mobilelele.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInit implements CommandLineRunner {

    private final UserService userService;
    private final OfferService offerService;
    private final BrandService brandService;
    private final ModelService modelService;

    public DataInit(UserService userService, OfferService offerService, BrandService brandService, ModelService modelService) {
        this.userService = userService;
        this.offerService = offerService;
        this.brandService = brandService;
        this.modelService = modelService;
    }

    @Override
    public void run(String... args) throws Exception {

        userService.initializeRoles();
        userService.initializeUsers();
        offerService.initializeOffers();
        brandService.initializeBrands();
        modelService.initializerModels();


    }
}
