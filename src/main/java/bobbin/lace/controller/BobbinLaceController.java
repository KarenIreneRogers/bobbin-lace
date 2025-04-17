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
	
	// Having problems with updating (and probably creating) style when trying to add features.
	// Pet Park used just a string, not a structure, for the amenities within the petParkData constructor.
	// May want to consider that!  It influences how saveStyle works in the Service layer.  Currently not working 4/12 9:00 pm
	// OR: Look at saveDog and extractBreedNames in the DogRescueService.java
	// 4/14/25 Put is now working OK for features added to a style.  When features are modified, the style_feature table only has the new set.
	// Can also add a style with features.
	
	
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
	
	// Retrieve one style works fine 4/14
	
	@GetMapping("/style")
	public List<StyleData> retrieveAllStyles() {
		log.info("Retrieving all styles");
		return bobbinLaceService.retrieveAllStyles();
	}
	// Retrieving all styles works fine - also gives all features.
	
	
	
	// Delete a style works fine - also deletes all rows of style_feature and images associated with the style 4/16/25
	@DeleteMapping("/style/{styleId}")
	public Map<String,String> deleteStyle(@PathVariable Long styleId) {
		log.info("Deleting style Id={}",styleId);
		bobbinLaceService.deleteStyleById(styleId);
		return Map.of("Message","Style with ID=" + styleId + " was deleted successfully");
	}
	
	// Add "delete All Styles - not allowed - see ParkController  Needs to be tested - 4/12 works to not allow deletion but makes mess in log file!!! 
	@DeleteMapping("/style")
	public void deleteAllStyles() {
		log.info("Attempting to delete all styles");
		throw new UnsupportedOperationException("Deleting all styles is not allowed.");
	}
	
	
	
	// Add image ???  Pet Store insertCustomer
	@PostMapping("/style/{styleId}/image")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ImageData insertImage(@PathVariable Long styleId,
			@RequestBody ImageData imageData) {
		log.info("Creating image {} for style with ID={}", imageData, styleId);
		
		return bobbinLaceService.saveImage(styleId, imageData);
		
	}
	
	// Modify image.  This works 4/16/25
	@PutMapping("/style/{styleId}/image/{imageId}")
	public ImageData updateImage(@PathVariable Long styleId,
			@PathVariable Long imageId, @RequestBody ImageData imageData) {
		imageData.setImageId(imageId);
		log.info("Updating image {} for style with ID={}", imageData, styleId);
		return bobbinLaceService.saveImage(styleId, imageData);
	}
	
	// Retrieve image ???  Use retrievePetParkById
	@GetMapping("/style/{styleId}/image/{imageId}")
	public ImageData retrieveImageDataById(@PathVariable Long styleId, @PathVariable Long imageId) {
		log.info("Retrieving image with ID={} and style with ID={}", imageId, styleId);
		
		return bobbinLaceService.retrieveImageById(styleId, imageId);
	}
	// Retrieve image using different URL 4/17/25
	@GetMapping("/image/{imageId}")
	public ImageData retrieveImageByIdIgnoreStyle(@PathVariable Long imageId) {
		log.info("Retrieving image with ID={}, ignoring style", imageId);
		
		return bobbinLaceService.retrieveImageWithJustImageId(imageId);
		
	}
	// Retrieve all images for a style ????  Haven't found a good comparison for this.  This has problems (5:45 4/4) ???  MIght be fixed.  Check carefully
	@GetMapping("/style/{styleId}/image")
	public List<ImageData> retrieveAllImagesForAStyle(@PathVariable Long styleId) {
		log.info("Retrieving all images associated with style ID={}", styleId);
		
		return bobbinLaceService.retrieveAllImagesForAStyle(styleId);	
	}
	
	// Delete image (without deleting style or features works fine 4/16/25
	
	@DeleteMapping("/style/{styleId}/image/{imageId}")
	public Map<String, String> deleteOneImage(@PathVariable Long styleId, @PathVariable Long imageId) {
		log.info("Deleting image with ID={}, associated with style ID={}", imageId, styleId);

		bobbinLaceService.deleteImageById(styleId, imageId);

		return Map.of("Message", "Deleted image with ID="+imageId + " associated with style with ID="+ styleId);
	}
	
}
