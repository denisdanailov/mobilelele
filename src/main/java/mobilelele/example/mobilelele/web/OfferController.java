package mobilelele.example.mobilelele.web;

import mobilelele.example.mobilelele.model.binding.UserRegistrationBindingModel;
import mobilelele.example.mobilelele.model.view.OfferSummeryView;
import mobilelele.example.mobilelele.service.OfferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class OfferController {

    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/offers/all")
    public String getAllOffers(Model model) {

        model.addAttribute("offers", offerService.getAllOffers());


        return "offers";
    }


}
