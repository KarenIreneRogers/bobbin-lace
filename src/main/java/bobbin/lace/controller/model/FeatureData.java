package bobbin.lace.controller.model;

import bobbin.lace.entity.Feature;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FeatureData {
	private Long featureId;
	private String featureName;
	
//	private Set<StyleData> styles = new HashSet<>();
	
	public FeatureData(Feature feature) {
		this.featureId = feature.getFeatureId();
		this.featureName = feature.getFeatureName();
		
//		for(Style style : feature.getStyles()) {
//			this.styles.add(new StyleData(style));
//		}
	}

	public Feature toFeature() {
		Feature feature = new Feature();
		
		feature.setFeatureId(featureId);
		feature.setFeatureName(featureName);
		
//		for(StyleData styleData : styles) {
//			feature.getStyles().add(styleData.toStyle()) ;
//		}
		return feature;
	}

}
