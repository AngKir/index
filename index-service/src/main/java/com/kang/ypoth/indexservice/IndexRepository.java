package com.kang.ypoth.indexservice;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

//import com.kang.ypoth.indexservice.Index;


@Repository

public interface IndexRepository extends CrudRepository<Index,Long>{
	
	Index findBySocialSecNum(@Param("socialSecNum") String socialSecNum);
	
	
	
	 
	//responseentity findall
	//public Index findByIdentityNum(@PathVariable("identityNum") String identityNum);
}
