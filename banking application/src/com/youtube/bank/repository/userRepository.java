package com.youtube.bank.repository;
import com.youtube.bank.entity.Transaction;
import com.youtube.bank.entity.user;
import java.util.HashSet;
import java.util.Set;
import java.util.List; // ✅ import List
import java.util.Map;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.stream.Collectors; // ✅ import Collectors

public class userRepository {

    private static Set<user> users = new HashSet<>();
    private static List<Transaction> transcations=new ArrayList<>();
    Map<String,Boolean> chequeBookRequest=new HashMap<>();

    static {
        user user1 = new user("admin", "admin", "1234567", "admin", 0.0);
        user user2 = new user("user2", "user2", "1234789", "user", 1000.0);
        user user3 = new user("user3", "user3", "1234852", "user", 2000.0);
        user user4 = new user("user4", "user4", "1234832", "user", 2000.0);

        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
    }
 public void approveChequeBookRequest(String userId) {
    	if(chequeBookRequest.containsKey(userId))
    	{
    		chequeBookRequest.put(userId,true);
    	}
    }
    public List<String> getUserIdForCheckBookRequest()
    {
    	List<String> userIds=new ArrayList<>();
    	for(Map.Entry<String,Boolean> entry:chequeBookRequest.entrySet()) {
    		if(!entry.getValue()) {
    			userIds.add(entry.getKey());
    		}
    	}
    	return userIds;
    	
    }
public Map<String,Boolean> getAllChequeBookRequest() {
    	return chequeBookRequest;
    }
    public void raiseChequeBookRequest(String userId) {
    	chequeBookRequest.put(userId, false);
    }
public void printTransaction(String userId) {
    	List<Transaction> filteredTransactions=transcations.stream().filter(transaction -> transaction.getTransactionPerformedBy().equals(userId)).collect(Collectors.toList());
    	System.out.println("Date\t\t UserId\t Amount\t Type\t Initial Balance\t Final Balance");
    	System.out.println("---------------------------------------------------------------------------");
    	for(Transaction t:filteredTransactions) {
    		System.out.println(t.getTransactionDate()+"\t"+t.getTransactionUserId()+"\t"+t.getTransactionAmount()+"\t"+t.getTransactionType()+"\t\t"+t.getInitialBalance()+"\t\t"+t.getFinalBalance());
    	}
    	System.out.println("---------------------------------------------------------------------------");
    }

public boolean transferAmount(String userId,String payeeUserId,Double amount) {
	
	boolean isDebit=debit(userId,amount,payeeUserId);
	boolean isCredit=credit(payeeUserId,amount,userId);
	
	return isDebit && isCredit;
    	
    }
private boolean debit(String userId,Double amount,String payeeUserId)
{
	user User=getUser(userId);
	
	Double accountBalance=User.getAccountBalance();
	
	users.remove(User);
	
	Double finalBalance=accountBalance-amount;
	User.setAccountBalance(finalBalance);
	
	Transaction transaction=new Transaction(
			 LocalDate.now(),
			 payeeUserId,
			 amount,
			 "Debit",
			 accountBalance,
			 finalBalance,
			 userId
			);
	
	System.out.println(transaction);
	transcations.add(transaction);
	return users.add(User);
}
    
private boolean credit(String payeeUserId,Double amount,String userId)
{
	user User=getUser(payeeUserId);
	
	Double accountBalance=User.getAccountBalance();
	
	users.remove(User);
	
	Double finalBalance=accountBalance+amount;
	User.setAccountBalance(finalBalance);
	Transaction transaction=new Transaction(
			 LocalDate.now(),
			 userId,
			 amount,
			 "Credit",
			 accountBalance,
			 finalBalance,
			 payeeUserId
			
			);
	System.out.println(transaction);

	transcations.add(transaction);
	return users.add(User);
}
    public user getUser(String userId)
    {
    	List<user> result=users.stream().filter(User->User.getUsername().equals(userId)).collect(Collectors.toList());
    	if(!result.isEmpty()) {
    		return result.get(0);
    	}
    	return null;
    }
    public Double checkBankBalance(String userId)
    {
    	List<user> result=users.stream().filter(User->User.getUsername().equals(userId)).collect(Collectors.toList());
    	
    	if(!result.isEmpty())
    	{
    		return result.get(0).getAccountBalance();
    	}
    	else
    	{
    		return null;
    	}
    }

    public void printUsers() {
        System.out.println(users);
    }

    public user login(String username, String password) {
        List<user> finalList = users.stream()
            .filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password))
            .collect(Collectors.toList());

        if (!finalList.isEmpty()) {
            return finalList.get(0);
        } else {
            return null;
        }
    }
    
public boolean addNewCustomer(String username,String password,String contact) {
    user User=new user(username,password,contact,"user",500.0);
    return users.add(User);
    }
}
