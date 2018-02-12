package th.co.cdg.train.ejb.session;

import java.util.List;

import javax.ejb.Local;

import th.co.cdg.train.ejb.entity.Account;

@Local
public interface AccountQueryLocal {
	public Account findAccount(String acctNo);
	public Account queryAccountByAcctNo(String acctNo);
	public List<Account> queryAccountByCondition(Account account);
}
