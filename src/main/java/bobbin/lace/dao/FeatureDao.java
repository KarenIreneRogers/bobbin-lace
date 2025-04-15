package bobbin.lace.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import bobbin.lace.entity.Feature;

public interface FeatureDao extends JpaRepository<Feature, Long> {

	Set<Feature> findByFeatureNameIn(Set<String> featureNames);
}
