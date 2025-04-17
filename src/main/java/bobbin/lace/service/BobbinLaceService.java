package bobbin.lace.service;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bobbin.lace.controller.model.FeatureData;
import bobbin.lace.controller.model.ImageData;
import bobbin.lace.controller.model.StyleData;
import bobbin.lace.dao.FeatureDao;
import bobbin.lace.dao.ImageDao;
import bobbin.lace.dao.StyleDao;
import bobbin.lace.entity.Feature;
import bobbin.lace.entity.Image;
import bobbin.lace.entity.Style;

@Service
public class BobbinLaceService {
	
	@Autowired
	private StyleDao styleDao;
	
	@Autowired
	private FeatureDao featureDao;
	
	@Autowired
	private ImageDao imageDao;

	@Transactional(readOnly = false)
	public StyleData saveStyle(StyleData styleData) {
//		Set<Feature> features = featureDao.findAllByFeatureIn(styleData.getFeatures());
//		Set<Feature> features = featureDao.findAllByFeatureIn(styleData.getFeatures());
		
		Set<String> featureNames = extractFeatureNames(styleData);
		Set<Feature> features = featureDao.findByFeatureNameIn(featureNames);
//		Set<Feature> features = styleData.getFeatures();
		
		Long styleId = styleData.getStyleId();
		Style style = findOrCreateStyle(styleId);
		
		style = copyStyleFields(styleData, style);
		
		style.setFeatures(features);
		
		
//		for(Feature feature : features) {
//			feature.getStyles().add(style);
//			style.getFeatures().add(feature);
//		}
		
		return new StyleData(styleDao.save(style));
	}

	private Set<String> extractFeatureNames(StyleData styleData) {

		Set<String> featureNames = new HashSet<>();
		
		for ( FeatureData feature :   styleData.getFeatures()) {
			featureNames.add(feature.getFeatureName());
		}
		return featureNames;
	}

	private Style findOrCreateStyle(Long styleId) {
		Style style;
		
		if(Objects.isNull(styleId)) {
			style = new Style();
		} else {
			style = findStyleById(styleId);
		}
		return style;
	}

	private Style findStyleById(Long styleId) {
		return styleDao.findById(styleId)
				.orElseThrow( () -> new NoSuchElementException
						("Style with ID="+styleId + " was not found."));
	}

	private Style copyStyleFields(StyleData styleData, Style style) {
		style.setStyleId(styleData.getStyleId());
		style.setStyleName(styleData.getStyleName());
		style.setCountryOfOrigin(styleData.getCountryOfOrigin());
		style.setOldestRecordedDate(styleData.getOldestRecordedDate());
		style.setPillowStyle(styleData.getPillowStyle());
		style.setTypicalNumberOfBobbins(styleData.getTypicalNumberOfBobbins());
		return style;
	}
	
	
	
	@Transactional(readOnly = true)
	public StyleData retrieveStyleById(Long styleId) {
		Style style = findStyleById(styleId);
		
		return new StyleData(style);
	}

	public List<StyleData> retrieveAllStyles() {
		return styleDao.findAll().stream()
				.sorted((sty1,sty2) -> sty1.getStyleName().compareTo(sty2.getStyleName()))
				.map(StyleData:: new).toList();	
	}

	@Transactional(readOnly = false)
	public void deleteStyleById(Long styleId) {
		Style style = findStyleById(styleId);
		styleDao.delete(style);
	}

	
	//  ***************  Styles above, Images below *************** //
	
	
	@Transactional(readOnly = false)
	public ImageData saveImage(Long styleId, ImageData imageData) {
	
		Style style = findStyleById(styleId);	
		Long imageId = imageData.getImageId();
		Image image = findOrCreateImage(styleId, imageId);

		image = copyImageFields(image, imageData);
		style.getImages().add(image);
		image.setStyle(style);
		
		return new ImageData( imageDao.save(image));
	}

	private Image findOrCreateImage(Long styleId, Long imageId) {
		Image image;
		
		if(Objects.isNull(imageId)) {
			image = new Image();
		}  else {
			image = findImageById(styleId, imageId);
		}
		return image;
	}

	private Image findImageById(Long styleId, Long imageId) {
		// Note that findImageById returns an Optional.  If the Optional is empty a NoSuchElementException is thrown.
		
		Image image = imageDao.findById(imageId)
				.orElseThrow( () -> new NoSuchElementException("Image with ID=" + imageId + " was not found."));
		
		if(image.getStyle().getStyleId() == styleId) {
			return image;
		}  else {
			throw new IllegalArgumentException("Image with ID=" + imageId + " is not associated with the style with ID=" + styleId);
		}
	}

	private Image copyImageFields(Image image, ImageData imageData) {
//		image.setImageId(imageData.getImageId());
		image.setImageName(imageData.getImageName());
		image.setImageLocation(imageData.getImageLocation());
		return image;
	}

	public ImageData retrieveImageById(Long styleId, Long imageId) {
		findStyleById(styleId);
		Image image = findImageById(styleId, imageId);  // This is a bit different from petPark due to inclusion of styleId  Check this!!!!
		
		if(image.getStyle().getStyleId() != styleId) {  
			throw new IllegalStateException("Image with ID=" + imageId + " "
					+ "is not associated with style with ID=" + styleId);
		}
		
		return new ImageData(image);
	}

	public List<ImageData> retrieveAllImagesForAStyle(Long styleId) { // Problems here (5:45, 4/4) ??? Might be fixed.  Check carefully!!!
		Style style = findStyleById(styleId);

		List<ImageData> response = new LinkedList<>();
		for(Image image : style.getImages()) {
			response.add(new ImageData(image));
		}
		
		return response;
	}

	public void deleteImageById(Long styleId, Long imageId) {
		Image image = findImageById(styleId, imageId);
		
		imageDao.delete(image);
	}

}
