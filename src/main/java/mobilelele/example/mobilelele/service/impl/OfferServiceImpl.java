package mobilelele.example.mobilelele.service.impl;

import mobilelele.example.mobilelele.model.entity.OfferEntity;
import mobilelele.example.mobilelele.model.entity.enums.EngineEnum;
import mobilelele.example.mobilelele.model.entity.enums.TransmissionEnum;
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

    private OfferSummeryView map(OfferEntity offer) {

        OfferSummeryView summeryView = this.modelMapper.map(offer, OfferSummeryView.class);

//        summeryView.setModel(offer.getModel().getName());
        summeryView.setMileage(offer.getMileage());
        summeryView.setImageUrl(offer.getImageUrl());



        return summeryView;
    }
}
