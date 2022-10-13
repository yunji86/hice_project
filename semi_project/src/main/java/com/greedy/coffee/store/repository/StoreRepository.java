package com.greedy.coffee.store.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.greedy.coffee.store.entity.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {

	//@EntityGraph(attributePaths = {})
	Page<Store> findByStoTypeAndStoStatus(int stoType, char stoStatus, Pageable pageable);
	
	@Query("SELECT s " +
	          "FROM Store s " +
	         "WHERE s.stoType = :stoType " +
	           "AND s.stoStatus = :stoStatus " +
	           "AND (s.stoName LIKE '%' || :searchValue || '%' " +
          	"OR s.stoAdd LIKE '%' || :searchValue || '%')")
	Page<Store> findBySearchValue(@Param("stoType") int stoType, @Param("stoStatus") char activeStatus, @Param("searchValue") String searchValue, Pageable pageable);

	Store findByStoCodeAndStoTypeAndStoStatus(Long stoCode, int stoType, char stoStatus);

	List<Store> findByStoCodeAndStoStatus(Long stoCode, char stoStatus);




	









	
}
