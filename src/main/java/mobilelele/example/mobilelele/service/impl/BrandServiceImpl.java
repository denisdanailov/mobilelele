package mobilelele.example.mobilelele.service.impl;

import mobilelele.example.mobilelele.model.entity.BrandEntity;
import mobilelele.example.mobilelele.model.entity.ModelEntity;
import mobilelele.example.mobilelele.model.view.BrandViewModel;
import mobilelele.example.mobilelele.model.view.ModelViewModel;
import mobilelele.example.mobilelele.repository.BrandRepository;
import mobilelele.example.mobilelele.repository.ModelRepository;
import mobilelele.example.mobilelele.service.BrandService;
import mobilelele.example.mobilelele.service.ModelService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;
    private final ModelService modelService;

    public BrandServiceImpl(BrandRepository brandRepository, ModelRepository modelRepository, ModelMapper modelMapper, ModelService modelService) {
        this.brandRepository = brandRepository;
        this.modelRepository = modelRepository;
        this.modelMapper = modelMapper;
        this.modelService = modelService;
    }

    @Override
    public void initializeBrands() {

        if (brandRepository.count() == 0) {

            BrandEntity vwBrand = new BrandEntity();
            BrandEntity fiat = new BrandEntity();
            BrandEntity mercedes = new BrandEntity();

            vwBrand.setName("Vw");
            fiat.setName("Fiat");
            mercedes.setName("Mercedes");

            brandRepository.saveAll(List.of(vwBrand, fiat, mercedes));
        }

    }

    @Override
    public List<BrandViewModel> getAllBrands() {

        List<BrandViewModel> brandViewModelList = new ArrayList<>();
        List<ModelEntity> allModels = modelRepository.findAll();

        allModels.forEach(modelEntity -> {
            BrandEntity brand = modelEntity.getBrand();
            Optional<BrandViewModel> brandViewModelOptional = findyName(brandViewModelList, brand.getName());

            if (!brandViewModelOptional.isPresent()) {
                BrandViewModel newBrandModel = new BrandViewModel();
                modelMapper.map(brand, newBrandModel);
                brandViewModelList.add(newBrandModel);
                brandViewModelOptional = Optional.of(newBrandModel);
            }

            ModelViewModel newModelViewModel = new ModelViewModel();
            modelMapper.map(modelEntity, newModelViewModel);
            brandViewModelOptional.get().addModel(newModelViewModel);
        });

        return brandViewModelList;

    }

    private Optional<BrandViewModel> findyName(List<BrandViewModel> brandViewModelList, String name) {
        return brandViewModelList.stream()
                .filter(brandViewModel -> brandViewModel.getName().equals(name)).findFirst();
    }


}
