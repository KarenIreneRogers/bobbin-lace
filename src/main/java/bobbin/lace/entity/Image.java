package bobbin.lace.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
@Entity
@Data
public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long imageId;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private String imageName;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private String imageLocation;
	
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne       //(cascade = CascadeType.ALL)
	@JoinColumn(name = "style_id", nullable = true)
	private Style style;

}
