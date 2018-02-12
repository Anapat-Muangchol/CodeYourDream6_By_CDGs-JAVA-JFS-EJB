package th.co.cdg.train.ejb.session;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import th.co.cdg.train.ejb.entity.Account;
import th.co.cdg.train.ejb.entity.Book;

/**
 * Session Bean implementation class AccountQueryManager
 */
@Stateless
@LocalBean
public class AccountQueryManager implements AccountQueryLocal {

	@PersistenceContext(unitName = "trainlab")
	private EntityManager em;

	@Override
	public Account findAccount(String acctNo) {
		Account a = em.find(Account.class, acctNo);
		return a;
	}

	@Override
	public Account queryAccountByAcctNo(String acctNo) {
		final String jpql = "select a from Account a join fetch a.transactions where a.acctNo = :acctNo";
		Query query = em.createQuery(jpql);
		query.setParameter("acctNo", acctNo);
		return (Account) query.getSingleResult();
	}

	@Override
	public List<Account> queryAccountByCondition(Account account) {
		final StringBuilder jpql = new StringBuilder();
		jpql.append("select a from Account a join fetch a.transactions where 1=1");
		
		if(!"".equals(account.getAcctNo())){
			jpql.append(" and a.acctNo = :acctNo");
		}
		if(!"".equals(account.getAcctName())){
			jpql.append(" and a.acctName like :acctName");
		}
		if(account.getBalance() != null){
			jpql.append(" and a.balance = :acctBalance");
		}
		
		Query query = em.createQuery(jpql.toString());
		
		if(!"".equals(account.getAcctNo())){
			query.setParameter("acctNo", account.getAcctNo());
		}
		if(!"".equals(account.getAcctName())){
			query.setParameter("acctName", "%"+account.getAcctName()+"%");
		}
		if(account.getBalance() != null){
			query.setParameter("acctBalance", account.getBalance());
		}

		return (List<Account>) query.getResultList();
	}
	
	
}
