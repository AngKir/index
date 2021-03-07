package com.kang.ypoth.indexservice;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.*;
import org.springframework.hateoas.Link;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;










@RestController
@RequestMapping("/index")

public class IndexWebService {
	

    
	
		
		private  IndexRepository indexRepository;
		private static final Logger LOGGER = LoggerFactory.getLogger(IndexWebService.class);


		@Autowired
		public IndexWebService(IndexRepository indexRepository) {
			super();
			this.indexRepository = indexRepository;
		}

		
		
		/**@GetMapping(produces = { "application/json" })// alakse repository
		public Page<Index> getAllRegisters(Pageable pageable){
			 LOGGER.info("GET /index/");
			 List<Index> index = indexRepository.findAllRegisters();
			// List<Index> indexList = index.getContent().stream().collect(Collectors.toList());
			 
		     return new PageImpl<Index>( index, pageable,  );

			 
		}*/
		
		@GetMapping(produces = { "application/json" })
		public ResponseEntity<CollectionModel<Index>> getAllRegisters(){
			LOGGER.info("Start");
			
			Iterable<Index> allIndex =  this.indexRepository.findAll();
			
			if(allIndex == null) {
	    		LOGGER.debug("No individual retrieved from repository");
				return new ResponseEntity<CollectionModel<Index>>(HttpStatus.NOT_FOUND);

			}
			
			CollectionModel<Index> index = new CollectionModel<Index>(allIndex);
			index.forEach(item->LOGGER.debug(index.toString()));
    		
        	LOGGER.info("Ending");

			return new ResponseEntity<CollectionModel<Index>>(index,HttpStatus.OK);
		}
		
		@GetMapping(value = "/{socialSecNum}",produces = { "application/json" })
		public ResponseEntity<EntityModel<Index>> findBySocialSecNum(@PathVariable("socialSecNum") String socialSecNum) {
			
			LOGGER.info("Start");
	    	LOGGER.debug("Fetching individual with socialSecNum: {}", socialSecNum);
			
			Index index = this.indexRepository.findBySocialSecNum(socialSecNum);
			if(index == null) {
	    		LOGGER.debug("Individual with socialSecNum: {} not found", socialSecNum);
				return new ResponseEntity<EntityModel<Index>>(HttpStatus.NOT_FOUND);
				
			}
			//Link link = linkTo(IndexWebService.class).sla
			EntityModel<Index> indexModel = new EntityModel<Index>(index);

	    	LOGGER.info("Ending");

			return new ResponseEntity<EntityModel<Index>>(indexModel,HttpStatus.OK) ;
		}



		
		/**@GetMapping("/{identityNum}")
		public Index getRegisterByIdentityNum(@PathVariable("identityNum") String identityNum) {
			return this.indexRepository.findByIdentityNum(identityNum);
		}*/
		
	
		
		

	


}
