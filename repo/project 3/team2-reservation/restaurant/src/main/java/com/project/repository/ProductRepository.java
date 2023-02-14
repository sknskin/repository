package com.project.repository;

import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

	
	// Administrator statistics (Recent Daily Sales)
//	@Query(value="SELECT * FROM tbl_order ", 
//		   nativeQuery = true)
//	List<E> findSalesByRecentDaily();
	
	// Administrator statistics (Monthly Sales Statistics)
//	@Query(value="SELECT * FROM tbl_order ",
//		   nativeQuery = true)
//	List<E> findSalesByMonthly();

	@Query(value="SELECT * FROM TBL_PRODUCT ",
		   nativeQuery = true)
	List<ProductEntity> findAllproduct();


	// Administrator statistics (Popular Top 10 Menu)
	@Query(value = "SELECT * FROM tbl_product ", 
		   nativeQuery = true)
	List<ProductEntity> findProductByRank();
			
	// TEST CODE
	// Administrator statistics (Popular Top 10 Menu) # JPQL TEST	
	@Query("SELECT new map ( pe.prodId as productId, pe.prodName as productName, sum(ode.orderCount * ode.orderDetailPrice) as salesTotal ) " +
		   "FROM ProductEntity pe " +
		   "INNER JOIN pe.orderProductDetails ode " +
		   "GROUP BY pe.prodId, pe.prodName " +
		   "ORDER BY salesTotal")
	List<HashMap<String, Object>> findProductStatistics();
	
	// Admin Menu Category
	@Query(value = "SELECT DISTINCT pe.prodCategory FROM ProductEntity pe")
	List<String> findAllCategories();

	// Admin Menu Modify
	@Modifying
	@Transactional
//	@Query(value = "UPDATE ProductEntity pe SET pe.prodName = :p.prodName WHERE pe.prodId = :p.prodId ")
//	void updateMenu(@Param("p")ProductEntity pe);
	@Query(value = "UPDATE TBL_PRODUCT SET prod_name=:#{ #pe.prodName }, prod_category=:#{ #pe.prodCategory }, prod_price=:#{ #pe.prodPrice }, prod_desc=:#{ #pe.prodDesc }, prod_ingredients=:#{ #pe.prodIngredients }, saved_file_name=:#{ #pe.savedFileName }, user_file_name=:#{ #pe.userFileName } WHERE prod_id=:#{ #pe.prodId }",
		   nativeQuery = true)
	void updateMenu(@Param("pe")ProductEntity pe);


	////////////////////////////////////////////////////////
	// 전체 태그(카테고리) 데이터베이스 접근해서 데이터 불러오기
	List<ProductEntity> findByProdCategory(String prodCategory);

	ProductEntity findByProdId(int prodId);

	ProductEntity findByProdCategory(ProductEntity prodCategory);
	
	@Query(value = "SELECT p from ProductEntity p WHERE p.prodCategory = :category AND p.prodId > :prodId ORDER BY p.prodId ASC")
	List<ProductEntity> findNextByCategoryAndProductId(@Param("category") String category, @Param("prodId")int prodId);

	@Query(value = "SELECT p from ProductEntity p WHERE p.prodCategory = :category AND p.prodId < :prodId ORDER BY p.prodId DESC")
	List<ProductEntity> findPrevByCategoryAndProductId(@Param("category") String category, @Param("prodId")int prodId);

	@Query(value = "SELECT p from ProductEntity p WHERE p.prodId > :prodId ORDER BY p.prodId ASC")
	List<ProductEntity> findNextByProductId(@Param("prodId")int prodId);
	
	@Query(value = "SELECT p from ProductEntity p WHERE p.prodId < :prodId ORDER BY p.prodId DESC")
	List<ProductEntity> findPrevByProductId(@Param("prodId")int prodId);

}
