package bobbin.lace.controller.model;

import bobbin.lace.entity.Image;
import bobbin.lace.entity.Style;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ImageData {
	private Long imageId;
	private String imageName;
	private String imageLocation;
	private Style style;
	

	public ImageData(Image image) {
		this.imageId = image.getImageId();
		this.imageName = image.getImageName();
		this.imageLocation = image.getImageLocation();
		this.style = image.getStyle();
		
	}


	public  Image toImage() {
		Image image = new Image();
		
		image.setImageId(imageId);
		image.setImageName(imageName);
		image.setImageLocation(imageLocation);
		image.setStyle(style);
		
		return image;
	}
	
}
 