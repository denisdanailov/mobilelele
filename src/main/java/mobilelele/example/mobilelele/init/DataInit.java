package mobilelele.example.mobilelele.init;

import mobilelele.example.mobilelele.service.OfferService;
import mobilelele.example.mobilelele.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInit implements CommandLineRunner {

    private final UserService userService;
    private final OfferService offerService;

    public DataInit(UserService userService, OfferService offerService) {
        this.userService = userService;
        this.offerService = offerService;
    }

    @Override
    public void run(String... args) throws Exception {

        userService.initializeRoles();
        userService.initializeUsers();
        offerService.initializeOffers();

    }
}
