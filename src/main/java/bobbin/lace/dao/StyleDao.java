package bobbin.lace.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import bobbin.lace.entity.Style;

public interface StyleDao extends JpaRepository<Style, Long> {

}
