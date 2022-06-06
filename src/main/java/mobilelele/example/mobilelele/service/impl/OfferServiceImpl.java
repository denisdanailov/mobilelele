package mobilelele.example.mobilelele.service.impl;

import mobilelele.example.mobilelele.model.entity.BrandEntity;
import mobilelele.example.mobilelele.model.entity.ModelEntity;
import mobilelele.example.mobilelele.model.entity.OfferEntity;
import mobilelele.example.mobilelele.model.entity.enums.EngineEnum;
import mobilelele.example.mobilelele.model.entity.enums.TransmissionEnum;
import mobilelele.example.mobilelele.model.view.OfferDetailsView;
import mobilelele.example.mobilelele.model.view.OfferSummeryView;
import mobilelele.example.mobilelele.repository.OfferRepository;
import mobilelele.example.mobilelele.repository.UserRepository;
import mobilelele.example.mobilelele.service.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;


    public OfferServiceImpl(OfferRepository offerRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.offerRepository = offerRepository;

        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public void initializeOffers() {

        if (offerRepository.count() == 0) {

            OfferEntity offer1 = new OfferEntity();

            offer1
                    .setEngine(EngineEnum.GASOLINE)
                    .setTransmission(TransmissionEnum.MANUAL)
                    .setMileage(22500)
                    .setPrice(13400)
                    .setYear(2009)
                    .setText("Best Car v Pernik")
                    .setSeller(userRepository.findByUsername("admin").orElse(null))
                    .setImageUrl("https://www.volkswagenag.com/content/dam/online-kommunikation/brands/corporate/world/presence/stories/2019/10/die-glorreichen-sieben/Golf-1-front.jpg");

            offerRepository.save(offer1);

            OfferEntity offer2 = new OfferEntity();

            offer2
                    .setEngine(EngineEnum.DIESEL)
                    .setTransmission(TransmissionEnum.AUTOMATIC)
                    .setMileage(2500)
                    .setPrice(1400)
                    .setYear(2019)
                    .setText("Top quality")
                    .setSeller(userRepository.findByUsername("pesho").orElse(null))
                    .setImageUrl("https://www.shiny-dept.de/media/image/26/2b/23/2000_1292_03f3b22b626203ecc68d6acca0bc6254_e5c0bdfa58d0887936f4b9aa42d708a879ffa2d1ea91fa8b7c570ce75aad34b074e80b7fcd83b450eed20be2d4911d4cc1e762e6250dc17e8efc11c9794dc24f.jpg");

            offerRepository.save(offer2);

        }


    }


    @Override
    public List<OfferSummeryView> getAllOffers() {
        return offerRepository
                .findAll()
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    @Override
    public OfferDetailsView findById(Long id) {
        OfferDetailsView offerDetailsView = offerRepository.findById(id).map(this::mapDetailsView).get();

        return offerDetailsView;
    }

    private OfferDetailsView mapDetailsView(OfferEntity offerEntity) {
        OfferDetailsView offerDetailsView = modelMapper.map(offerEntity, OfferDetailsView.class);

//        offerDetailsView.setModel(offerEntity.getModel().getName());
//        offerDetailsView.setBrand(offerEntity.getModel().getBrand().getName());
        offerDetailsView.setMileage(offerEntity.getMileage());
        offerDetailsView.setModified(offerEntity.getModified().toString());
        offerDetailsView.setCreated(offerEntity.getCreated().toString());
        offerDetailsView.setPrice(offerEntity.getPrice());
        offerDetailsView.setEngine(offerEntity.getEngine().toString());
        offerDetailsView.setSellerFullName(offerEntity.getSeller().getFirstName() + " " + offerEntity.getSeller().getLastName());
        offerDetailsView.setImageUrl(offerEntity.getImageUrl());

        return offerDetailsView;
    }


    private OfferSummeryView map(OfferEntity offer) {

        OfferSummeryView summeryView = this.modelMapper.map(offer, OfferSummeryView.class);

//        summeryView.setModel(offer.getModel().getName());
        summeryView.setPrice(offer.getPrice());
        summeryView.setMileage(offer.getMileage());
        summeryView.setImageUrl(offer.getImageUrl());
        summeryView.setEngine(offer.getEngine());
        summeryView.setTransmission(offer.getTransmission());


        return summeryView;
    }
}
