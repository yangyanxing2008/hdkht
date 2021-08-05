package com.ep.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository
@Transactional
public class BaseDao {

	@Autowired
	private SessionFactory sessionFactory;
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	/****
	 * 查询所有
	 * @param sql
	 * @return
	 */
	public List getAllList(String sql) {
		Session session = this.getSession();
		Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List list = query.list();
		return list;
	}
	/*******
	 * 分页查询
	 * @param hql
	 * @param pageSize
	 * @param pageNumber
	 * @return
	 */
	public List getPageList(String hql,Integer pageSize,Integer pageNumber){
		Session sess = this.getSession();
		Query query =sess.createSQLQuery(hql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		query.setMaxResults(pageSize);
		query.setFirstResult((pageNumber-1)*pageSize);
		List list = query.list();
		return list;
	}
	
	
	
	/**
	 * 总数
	 * @param hql
	 * @return
	 */
	public int getAllCount(String hql) {
		Session session = this.getSession();
		Query query = session.createSQLQuery(hql);
		query.setMaxResults(1);
		Number nums = (Number) query.uniqueResult();
		return nums.intValue();
	}

	public int saveDao(Object cpsj) {
        Session sess = this.getSession();
		int nums = (int) sess.save(cpsj);; 
		return nums;
	}

	public int updateDao(Object cpsj) {
        Session sess = this.getSession();
        int nums = 0;
        try {
			sess.update(cpsj);
			nums = 1;
		} catch (Exception e) {
			// TODO: handle exception
			nums = 0;
		}
		
		return nums;
	}
	

	public int delById(String hql) {
		Session sess = this.getSession();
		int results =sess.createSQLQuery(hql).executeUpdate();
		return results;
	}
}
