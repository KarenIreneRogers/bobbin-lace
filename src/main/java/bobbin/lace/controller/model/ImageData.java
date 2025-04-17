package bobbin.lace.controller.model;

import java.util.Objects;

import bobbin.lace.entity.Image;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ImageData {
	private Long imageId;
	private String imageName;
	private String imageLocation;
	private StyleData styleData;   				// This is different from dog Breeds which uses StyleData, not just style here.
	

	public ImageData(Image image) {
		this.imageId = image.getImageId();
		this.imageName = image.getImageName();
		this.imageLocation = image.getImageLocation();
//		this.style = image.getStyle();  // In the DogInfo file, this checks for a null value in style before assigning.
		if(Objects.nonNull(image.getStyle())) {
			this.styleData = new StyleData(image.getStyle());
		}
	}


	public  Image toImage() {
		Image image = new Image();
		
		image.setImageId(imageId);
		image.setImageName(imageName);
		image.setImageLocation(imageLocation);
		
		
		return image;
	}
	
}
 