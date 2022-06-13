package mobilelele.example.mobilelele.service;

import mobilelele.example.mobilelele.model.view.BrandViewModel;

import java.util.List;

public interface BrandService {
    void initializeBrands();

    List<BrandViewModel> getAllBrands();
}
