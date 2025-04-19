package bobbin.lace.controller.model;

import bobbin.lace.entity.Feature;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FeatureData {
	private Long featureId;
	private String featureName;
	
	public FeatureData(Feature feature) {
		this.featureId = feature.getFeatureId();
		this.featureName = feature.getFeatureName();
	}

	public Feature toFeature() {
		Feature feature = new Feature();
		feature.setFeatureId(featureId);
		feature.setFeatureName(featureName);
		return feature;
	}
}
