package mobilelele.example.mobilelele.service;

import mobilelele.example.mobilelele.model.view.BrandSummeryView;

import java.util.List;

public interface BrandService {
    void initializeBrands();

    List<BrandSummeryView> getAllBrands();
}
