package br.com.vipmania.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.vipmania.model.Category;
import br.com.vipmania.model.OrderItem;
import br.com.vipmania.model.Product;
import br.com.vipmania.model.SizeItem;

@Repository
@Transactional
public class ProductDAO {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private QuantityBySizeDAO quantityBySizeDao;
	
	private int itensByPage = 8;
	
	
	public void save(Product product) {
		if(product.isNew()) {
			manager.persist(product);
		}
		else {
//			quantityBySizeDao.clear(product);
			
			manager.merge(product);
		}
	}


	public List<Product> list() {
		return manager.createQuery("select p from Product p order by p.name", Product.class).getResultList();
	}


	public Product get(Long id) {
		Product product = manager.find(Product.class, id);
		
		return product;
	}

	public List<Product> listByCategory(Category category) {
		String hql  = "select p from Product p where p.category = :category order by p.name";
		
		TypedQuery<Product> query = manager.createQuery(hql, Product.class);
		
		query.setParameter("category", category);
		
		return query.getResultList();
	}

	public List<Product> listValidsByCategory(Category category) {
		String hql = "select p "
				+ "from "
				+ "	Product p "
				+ "where "
				+ "	p.category = :category "
				+ "	and p.validFrom <= sysdate() "
				+ "	and ("
				+ "		p.validTo is null "
				+ "		or p.validTo >= sysdate()"
				+ "	) "
				+ "and exists ( "
				+ "	from "
				+ "		QuantityBySize qbs "
				+ "	where "
				+ "		qbs.product = p "
				+ "		and qbs.quantity > 0 "
				+ ")"
				+ "order by p.name";
		
		TypedQuery<Product> query = manager.createQuery(hql, Product.class).setParameter("category", category);

		return query.getResultList();
	}

	public List<Product> listValidsByCategoryPaginated(Category category, Long page) {
		String hql = "select p "
				+ "from Product p "
				+ "where "
					+ "p.category = :category "
					+ "and p.validFrom <= sysdate() "
					+ "and (p.validTo is null or p.validTo >= sysdate()) "
					+ "and exists ( "
					+ "	from "
					+ "		QuantityBySize qbs "
					+ "	where "
					+ "		qbs.product = p "
					+ "		and qbs.quantity > 0 "
					+ ")"
				+ "order by p.name";
		
		TypedQuery<Product> query = manager.createQuery(hql, Product.class);
		
		query.setParameter("category", category);
		
		page = (page == null ? 1l : page);
		int firstRow = (int) ((page - 1) * itensByPage);
		
		query.setFirstResult(firstRow);
		query.setMaxResults(itensByPage);
		
		return query.getResultList();
	}
	
	public int getPageCount(Category category) {
		String hql = "select count(p) "
				+ "from Product p "
				+ "where "
					+ "p.category = :category "
					+ "and p.validFrom <= sysdate() "
					+ "and (p.validTo is null or p.validTo >= sysdate()) "
					+ "and exists ( "
					+ "	from "
					+ "		QuantityBySize qbs "
					+ "	where "
					+ "		qbs.product = p "
					+ "		and qbs.quantity > 0 "
					+ ")";
		
		TypedQuery<Long> query = manager.createQuery(hql, Long.class);
		
		query.setParameter("category", category);
		
		return (int) Math.ceil(query.getSingleResult().intValue() / (double) itensByPage);
	}
	
	public int getPageCount(String word) {
		String hql = "select count(p) "
				+ "from Product p "
				+ "where "
					+ "(p.name like :word or p.description like :word) "
					+ "and p.validFrom <= sysdate() "
					+ "and (p.validTo is null or p.validTo >= sysdate()) ";
		
		TypedQuery<Long> query = manager.createQuery(hql, Long.class);
		
		word = (word == null ? "" : word.replaceAll("\\s", "%"));
		
		query.setParameter("word", "%" + word + "%");
		
		return (int) Math.ceil(query.getSingleResult().intValue() / (double) itensByPage);
	}

	public BigDecimal sumProductValueByCategory(String category) {
		TypedQuery<BigDecimal> query = manager.createQuery("select sum(p.value) from Product p where p.category = :category", BigDecimal.class).setParameter("category", category);
		
		return query.getSingleResult();
	}


	public List<Product> search(String word) {
		word = (word == null ? "" : word.replaceAll("\\s", "%"));
		
		String sql = "select p "
					+ "from Product p "
					+ "where "
						+ "p.name like :word or p.description like :word "
						+ "and p.validFrom <= sysdate() "
						+ "and (p.validTo is null or p.validTo >= sysdate()) "
					+ "order by p.name";
		
		TypedQuery<Product> query = manager.createQuery(sql, Product.class).setParameter("word", "%" + word + "%");
		
		return query.getResultList();
	}


	public List<Product> search(String word, Long page) {
		word = (word == null ? "" : word.replaceAll("\\s", "%"));
		
		String sql = "select p "
					+ "from Product p "
					+ "where "
						+ "(p.name like :word or p.description like :word) "
						+ "and p.validFrom <= sysdate() "
						+ "and (p.validTo is null or p.validTo >= sysdate()) "
					+ "order by p.name";	
		
		TypedQuery<Product> query = manager.createQuery(sql, Product.class).setParameter("word", "%" + word + "%");
		
		page = (page == null ? 1l : page);
		int firstRow = (int) ((page - 1) * itensByPage);
		
		query.setFirstResult(firstRow);
		query.setMaxResults(itensByPage);
		
		return query.getResultList();
	}


	public List<Product> listRelated(Product product) {
		String hql = "select p "
				+ "from Product p "
				+ "where "
					+ "p.category = :category "
					+ "and p.validFrom <= sysdate() "
					+ "and (p.validTo is null or p.validTo >= sysdate()) "
					+ "and p.id != :id "
				+ "order by p.name";
		
		TypedQuery<Product> query = manager.createQuery(hql, Product.class);
		
		query.setParameter("category", product.getCategory())
			 .setParameter("id", product.getId());
		
		query.setFirstResult(0);
		query.setMaxResults(itensByPage / 2);
		
		return query.getResultList();
	}


	public List<Product> listMostRecents() {
		String hql = "select p "
				+ "from Product p "
				+ "where "
					+ "p.validFrom <= sysdate() "
					+ "and (p.validTo is null or p.validTo >= sysdate()) "
					+ "and exists ( "
					+ "	from "
					+ "		QuantityBySize qbs "
					+ "	where "
					+ "		qbs.product = p "
					+ "		and qbs.quantity > 0 "
					+ ")"
				+ "order by p.validFrom desc";
		
		TypedQuery<Product> query = manager.createQuery(hql, Product.class);
		
		query.setFirstResult(0);
		query.setMaxResults(itensByPage);
		
		return query.getResultList();
	}


	public void reduce(Product product, Long quantity, SizeItem sizeItem) {
		
	}


	public void reduce(OrderItem item) {
		reduce(item.getProduct(), item.getQuantity(), item.getSizeItem());
	}
	
}