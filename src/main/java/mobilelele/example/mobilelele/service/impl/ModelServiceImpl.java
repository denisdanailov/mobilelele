package mobilelele.example.mobilelele.service.impl;

import mobilelele.example.mobilelele.model.entity.BrandEntity;
import mobilelele.example.mobilelele.model.entity.ModelEntity;
import mobilelele.example.mobilelele.model.entity.enums.CategoryEnum;
import mobilelele.example.mobilelele.repository.BrandRepository;
import mobilelele.example.mobilelele.repository.ModelRepository;
import mobilelele.example.mobilelele.service.ModelService;
import org.springframework.stereotype.Service;

@Service
public class ModelServiceImpl implements ModelService {

    private final ModelRepository modelRepository;
    private final BrandRepository brandRepository;


    public ModelServiceImpl(ModelRepository modelRepository, BrandRepository brandRepository) {
        this.modelRepository = modelRepository;
        this.brandRepository = brandRepository;
    }

    @Override
    public void initializerModels() {
        if (modelRepository.count() == 0 && brandRepository.count() != 0) {

            String vwBrand = "Vw";
            BrandEntity vw = brandRepository.findByName(vwBrand).orElse(null);

            ModelEntity golf = new ModelEntity();
            golf.setName("golf")
                    .setBrand(vw)
                    .setCategory(CategoryEnum.CAR)
                    .setStartYear(1993)
                    .setEndYear(2004)
                    .setImageUrl("https://cdn.shopify.com/s/files/1/1355/1401/products/vw-golf-logo-decal-sticker.jpg?v=1570919830");

            modelRepository.save(golf);
        }

    }

}
