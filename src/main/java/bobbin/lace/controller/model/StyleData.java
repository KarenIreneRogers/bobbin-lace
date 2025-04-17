package bobbin.lace.controller.model;

import java.util.HashSet;
import java.util.Set;

import bobbin.lace.entity.Feature;
import bobbin.lace.entity.Style;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class StyleData {
	
	private Long styleId;
	private String styleName;
	private String countryOfOrigin;
	private int oldestRecordedDate;
	private int typicalNumberOfBobbins;
	private String pillowStyle;
//	private Set<ImageData> images = new HashSet<>();
	private Set<FeatureData> features = new HashSet<>();
	
	
	public StyleData(Style style) {
		this.styleId = style.getStyleId();
		this.styleName = style.getStyleName();
		this.countryOfOrigin = style.getCountryOfOrigin();
		this.oldestRecordedDate = style.getOldestRecordedDate();
		this.typicalNumberOfBobbins = style.getTypicalNumberOfBobbins();
		this.pillowStyle = style.getPillowStyle();
		
//		for (Image image: style.getImages()) {
//			this.images.add(new ImageData(image));
//		}
		
		for (Feature feature : style.getFeatures()) {
	//		this.features.add(new FeatureData(feature));
			this.features.add(new FeatureData(feature));
		}
	}
	
	public Style toStyle() {
		Style style = new Style();
		
		style.setStyleId(styleId);
		style.setStyleName(styleName);
		style.setCountryOfOrigin(countryOfOrigin);
		style.setOldestRecordedDate(oldestRecordedDate);
		style.setTypicalNumberOfBobbins(typicalNumberOfBobbins);
		style.setPillowStyle(pillowStyle);
		
	//	for(ImageData imageData : images) {
	//		style.getImages().add(imageData.toImage());
	//	}
		

		for(FeatureData featureData : features) {
			style.getFeatures().add(featureData.toFeature());
		}
//		for(String featureData : features) {
//			style.getFeatures().add(featureData);
//		}
		return style;
	}
	
	

} 
