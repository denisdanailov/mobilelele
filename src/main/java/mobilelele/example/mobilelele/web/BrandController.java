package mobilelele.example.mobilelele.web;

import mobilelele.example.mobilelele.service.BrandService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("brands/all")
    public String getAllBrands(Model model) {

        model.addAttribute("brands", brandService.getAllBrands());

        brandService.getAllBrands().forEach(System.out::println);

        return "brands";
    }
}
