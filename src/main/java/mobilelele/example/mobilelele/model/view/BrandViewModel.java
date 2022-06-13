package mobilelele.example.mobilelele.model.view;

public class BrandSummeryView {

    private String brandName;

    private String modelName;

    private String categoryName;

    private int startYear;

    private int endYear;

    private String picture;

    public String getBrandName() {
        return brandName;
    }

    public BrandSummeryView setBrandName(String brandName) {
        this.brandName = brandName;
        return this;
    }

    public String getModelName() {
        return modelName;
    }

    public BrandSummeryView setModelName(String modelName) {
        this.modelName = modelName;
        return this;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public BrandSummeryView setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    public int getStartYear() {
        return startYear;
    }

    public BrandSummeryView setStartYear(int startYear) {
        this.startYear = startYear;
        return this;
    }

    public int getEndYear() {
        return endYear;
    }

    public BrandSummeryView setEndYear(int endYear) {
        this.endYear = endYear;
        return this;
    }

    public String getPicture() {
        return picture;
    }

    public BrandSummeryView setPicture(String picture) {
        this.picture = picture;
        return this;
    }

    @Override
    public String toString() {
        return brandName;
    }
}
