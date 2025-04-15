package bobbin.lace.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import bobbin.lace.entity.Image;

public interface ImageDao extends JpaRepository<Image, Long> {

}
