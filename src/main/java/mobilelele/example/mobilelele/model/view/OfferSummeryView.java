package mobilelele.example.mobilelele.model.view;

import mobilelele.example.mobilelele.model.entity.ModelEntity;
import mobilelele.example.mobilelele.model.entity.enums.EngineEnum;
import mobilelele.example.mobilelele.model.entity.enums.TransmissionEnum;

import javax.persistence.ManyToOne;

public class OfferSummeryView {

    private String text;

    private EngineEnum engine;

    private String imageUrl;

    private int mileage;

    private int price;

    private TransmissionEnum transmission;

    private int year;

    private String model;

    public String getText() {
        return text;
    }

    public OfferSummeryView setText(String text) {
        this.text = text;
        return this;
    }

    public EngineEnum getEngine() {
        return engine;
    }

    public OfferSummeryView setEngine(EngineEnum engine) {
        this.engine = engine;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OfferSummeryView setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public int getMileage() {
        return mileage;
    }

    public OfferSummeryView setMileage(int mileage) {
        this.mileage = mileage;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public OfferSummeryView setPrice(int price) {
        this.price = price;
        return this;
    }

    public TransmissionEnum getTransmission() {
        return transmission;
    }

    public OfferSummeryView setTransmission(TransmissionEnum transmission) {
        this.transmission = transmission;
        return this;
    }

    public int getYear() {
        return year;
    }

    public OfferSummeryView setYear(int year) {
        this.year = year;
        return this;
    }

    public String getModel() {
        return model;
    }

    public OfferSummeryView setModel(String model) {
        this.model = model;
        return this;
    }
}
