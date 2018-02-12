package th.co.cdg.train.ejb.managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import th.co.cdg.train.ejb.entity.Account;
import th.co.cdg.train.ejb.entity.Transaction;
import th.co.cdg.train.ejb.session.AccountQueryLocal;

@ManagedBean
public class AccountController {
	
	@EJB
	private AccountQueryLocal accountQueryLocal;
	
	private Account acctForm;
	private Account account;
	private List<Transaction> transList;
	
	public AccountController() {
		acctForm = new Account();
		account = new Account();
		transList = new ArrayList<Transaction>();
	}
	
	public Account getAcctForm() {
		return acctForm;
	}
	public void setAcctForm(Account acctForm) {
		this.acctForm = acctForm;
	}

	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	
	public List<Transaction> getTransList() {
		return transList;
	}
	public void setTransList(List<Transaction> transList) {
		this.transList = transList;
	}

	// ------------- Methods -------------
	public void findAccount(){
		account = accountQueryLocal.findAccount(acctForm.getAcctNo());
		try {
			transList = account.getTransactions();
			System.out.println("");
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("");
	}
	
	public void queryAccountByAcctNo(){
		account = accountQueryLocal.queryAccountByAcctNo(acctForm.getAcctNo());
	}
	
	public void queryAccountByCondition(){
		account = accountQueryLocal.queryAccountByCondition(acctForm).get(0);
	}
}
