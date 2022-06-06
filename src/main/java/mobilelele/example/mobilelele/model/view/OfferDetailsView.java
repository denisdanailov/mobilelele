package mobilelele.example.mobilelele.model.view;

public class OfferDetailsView {

    private Long id;
    private String model;
    private String brand;
    private int price;
    private int mileage;
    private String engine;
    private String transmission;
    private String created;
    private String modified;
    private String sellerFullName;
    private String imageUrl;

    public Long getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public OfferDetailsView setModel(String model) {
        this.model = model;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public OfferDetailsView setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public OfferDetailsView setId(Long id) {
        this.id = id;
        return this;
    }

    public int getMileage() {
        return mileage;
    }

    public OfferDetailsView setMileage(int mileage) {
        this.mileage = mileage;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public OfferDetailsView setPrice(int price) {
        this.price = price;
        return this;
    }

    public String getEngine() {
        return engine;
    }

    public OfferDetailsView setEngine(String engine) {
        this.engine = engine;
        return this;
    }

    public String getTransmission() {
        return transmission;
    }

    public OfferDetailsView setTransmission(String transmission) {
        this.transmission = transmission;
        return this;
    }

    public String getCreated() {
        return created;
    }

    public OfferDetailsView setCreated(String created) {
        this.created = created;
        return this;
    }

    public String getModified() {
        return modified;
    }

    public OfferDetailsView setModified(String modified) {
        this.modified = modified;
        return this;
    }

    public String getSellerFullName() {
        return sellerFullName;
    }

    public OfferDetailsView setSellerFullName(String sellerFullName) {
        this.sellerFullName = sellerFullName;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OfferDetailsView setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}


