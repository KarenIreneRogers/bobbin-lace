package bobbin.lace.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import bobbin.lace.controller.model.FeatureData;
import bobbin.lace.controller.model.ImageData;
import bobbin.lace.controller.model.StyleData;
import bobbin.lace.service.BobbinLaceService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/bobbin_lace")
@Slf4j
class BobbinLaceController {
	
	@Autowired
	private BobbinLaceService bobbinLaceService;
	
	@PostMapping("/style")
	public StyleData createStyle(@RequestBody StyleData styleData) {
		log.info("Creating bobbin lace style {}", styleData);
		return bobbinLaceService.saveStyle(styleData);
		
	}
	
	@PutMapping("/style/{styleId}")
	public StyleData updateStyle(@PathVariable Long styleId, @RequestBody StyleData styleData) {
		styleData.setStyleId(styleId);
		log.info("Updating style {}",styleData);		
		return bobbinLaceService.saveStyle(styleData);
	}
	
	@GetMapping("/style/{styleId}")
	public StyleData retrieveOneStyle(@PathVariable Long styleId) {
		log.info("Retrieving style with ID={}",styleId);
		return bobbinLaceService.retrieveStyleById(styleId);
	}
	
	@GetMapping("/style")
	public List<StyleData> retrieveAllStyles() {
		log.info("Retrieving all styles");
		return bobbinLaceService.retrieveAllStyles();
	}

	@DeleteMapping("/style/{styleId}")
	public Map<String,String> deleteStyle(@PathVariable Long styleId) {
		log.info("Deleting style Id={}",styleId);
		bobbinLaceService.deleteStyleById(styleId);
		return Map.of("Message","Style with ID=" + styleId + " was deleted successfully");
	}
	
	@DeleteMapping("/style")
	public void deleteAllStyles() {
		log.info("Attempting to delete all styles");
		throw new UnsupportedOperationException("Deleting all styles is not allowed.");
	}
	
	@PostMapping("/style/{styleId}/image")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ImageData insertImage(@PathVariable Long styleId,
			@RequestBody ImageData imageData) {
		log.info("Creating image {} for style with ID={}", imageData, styleId);
		
		return bobbinLaceService.saveImage(styleId, imageData);
		
	}
	
	@PutMapping("/style/{styleId}/image/{imageId}")
	public ImageData updateImage(@PathVariable Long styleId,
			@PathVariable Long imageId, @RequestBody ImageData imageData) {
		imageData.setImageId(imageId);
		log.info("Updating image {} for style with ID={}", imageData, styleId);
		return bobbinLaceService.saveImage(styleId, imageData);
	}
	
	@GetMapping("/style/{styleId}/image/{imageId}")
	public ImageData retrieveImageDataById(@PathVariable Long styleId, @PathVariable Long imageId) {
		log.info("Retrieving image with ID={} and style with ID={}", imageId, styleId);
		
		return bobbinLaceService.retrieveImageById(styleId, imageId);
	}

	@GetMapping("/image/{imageId}")
	public ImageData retrieveImageByIdIgnoreStyle(@PathVariable Long imageId) {
		log.info("Retrieving image with ID={}, ignoring style", imageId);
		
		return bobbinLaceService.retrieveImageWithJustImageId(imageId);
	}

	@GetMapping("/style/{styleId}/image")
	public List<ImageData> retrieveAllImagesForAStyle(@PathVariable Long styleId) {
		log.info("Retrieving all images associated with style ID={}", styleId);
		
		return bobbinLaceService.retrieveAllImagesForAStyle(styleId);	
	}
	
	@DeleteMapping("/style/{styleId}/image/{imageId}")
	public Map<String, String> deleteOneImage(@PathVariable Long styleId, @PathVariable Long imageId) {
		log.info("Deleting image with ID={}, associated with style ID={}", imageId, styleId);

		bobbinLaceService.deleteImageById(styleId, imageId);

		return Map.of("Message", "Deleted image with ID="+imageId + " associated with style with ID="+ styleId);
	}
	
	@GetMapping("/feature")
	public List<FeatureData> retrieveAllFeatures() {
		return bobbinLaceService.retrieveAllFeatures();
	}
	
	@GetMapping("/style_feature")
	public List<StyleData> retrieveOnlyStylesWhichHaveFeatures() {
		log.info("Retrieving all styles which have features");
		return bobbinLaceService.retrieveAllStylesWhichHaveFeatures();
	}
}
