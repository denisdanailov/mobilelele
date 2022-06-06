package mobilelele.example.mobilelele.service.impl;

import mobilelele.example.mobilelele.model.entity.BrandEntity;
import mobilelele.example.mobilelele.model.entity.ModelEntity;
import mobilelele.example.mobilelele.model.view.BrandSummeryView;
import mobilelele.example.mobilelele.repository.BrandRepository;
import mobilelele.example.mobilelele.service.BrandService;
import mobilelele.example.mobilelele.service.ModelService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;
    private final ModelService modelService;

    public BrandServiceImpl(BrandRepository brandRepository, ModelMapper modelMapper, ModelService modelService) {
        this.brandRepository = brandRepository;
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
    public List<BrandSummeryView> getAllBrands() {

        return brandRepository
                .findAll()
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    private BrandSummeryView map(BrandEntity brandEntity) {
        BrandSummeryView brandSummeryView = this.modelMapper.map(brandEntity, BrandSummeryView.class);

        brandSummeryView.setBrandName(brandEntity.getName());


        return brandSummeryView;
    }
}
