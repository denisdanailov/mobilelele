package mobilelele.example.mobilelele.service;


import mobilelele.example.mobilelele.model.view.ModelViewModel;

import java.util.List;

public interface ModelService {
    void initializerModels();

    List<ModelViewModel> getModels();

}
