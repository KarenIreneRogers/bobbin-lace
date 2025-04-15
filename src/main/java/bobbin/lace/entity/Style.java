package bobbin.lace.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Style {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long styleId;
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private String styleName;
	@EqualsAndHashCode.Exclude	
	@ToString.Exclude
	private String countryOfOrigin;
	@EqualsAndHashCode.Exclude	
	@ToString.Exclude
	private int oldestRecordedDate;
	@EqualsAndHashCode.Exclude	
	@ToString.Exclude
	private int typicalNumberOfBobbins;
	@EqualsAndHashCode.Exclude	
	@ToString.Exclude
	private String pillowStyle;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "style",cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Image> images = new HashSet<>();
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(
		name = "style_feature",
		joinColumns = @JoinColumn(name = "style_id"),
		inverseJoinColumns = @JoinColumn(name = "feature_id")
	)
	private Set<Feature> features = new HashSet<>();
	
	
	
}
