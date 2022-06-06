package mobilelele.example.mobilelele.service;

import mobilelele.example.mobilelele.model.entity.OfferEntity;
import mobilelele.example.mobilelele.model.view.OfferDetailsView;
import mobilelele.example.mobilelele.model.view.OfferSummeryView;

import java.util.List;


public interface OfferService {

    void initializeOffers();

    List<OfferSummeryView> getAllOffers();

    OfferDetailsView findById(Long id);
}
