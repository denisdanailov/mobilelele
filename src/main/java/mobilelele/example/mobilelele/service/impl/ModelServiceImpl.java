package mobilelele.example.mobilelele.service.impl;

import mobilelele.example.mobilelele.model.entity.BrandEntity;
import mobilelele.example.mobilelele.model.entity.ModelEntity;
import mobilelele.example.mobilelele.model.entity.enums.CategoryEnum;
import mobilelele.example.mobilelele.model.view.ModelViewModel;
import mobilelele.example.mobilelele.repository.BrandRepository;
import mobilelele.example.mobilelele.repository.ModelRepository;
import mobilelele.example.mobilelele.service.ModelService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModelServiceImpl implements ModelService {

    private final ModelRepository modelRepository;
    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;


    public ModelServiceImpl(ModelRepository modelRepository, BrandRepository brandRepository, ModelMapper modelMapper) {
        this.modelRepository = modelRepository;
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
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

    @Override
    public List<ModelViewModel> getModels() {
        return modelRepository
                .findAll()
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    private ModelViewModel map(ModelEntity modelEntity) {
        ModelViewModel modelViewModel = this.modelMapper.map(modelEntity, ModelViewModel.class);

        modelViewModel.setName(modelEntity.getName());


        return modelViewModel;
    }

}



