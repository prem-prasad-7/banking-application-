package com.youtube.bank.service;
import com.youtube.bank.entity.user;
import com.youtube.bank.repository.userRepository; 
import java.util.Map;// ✅ Add import
import java.util.List;

public class userservice {

    private userRepository UserRepository = new userRepository();

    public void printUsers() {
        UserRepository.printUsers(); // ✅ This method should exist in userRepository
    }
    public user login(String username,String password)
    {
    	return UserRepository.login(username,password);
    }
    public boolean addNewCustomer(String username,String password,String contact) {
    	return UserRepository.addNewCustomer(username,password,contact);
    }
    public Double checkBankBalance(String userId)
    {
    	return UserRepository.checkBankBalance(userId);
    }
    public user getUser(String userId) {
    	return UserRepository.getUser(userId);
    }
    public boolean transferAmount(String userId,String payeeUserId,Double amount) {
    	return UserRepository.transferAmount(userId,payeeUserId,amount);
    }
    public void printTransaction(String userId) {
    	UserRepository.printTransaction(userId);
    }
    public void raiseChequeBookRequest(String userId) {
    	UserRepository.raiseChequeBookRequest(userId);
    }
    public Map<String,Boolean> getAllChequeBookRequest() {
    	return UserRepository.getAllChequeBookRequest();
    }
    public List<String> getUserIdForCheckBookRequest()
    {
    	return UserRepository.getUserIdForCheckBookRequest();
    }
 public void approveChequeBookRequest(String userId) {
	UserRepository.approveChequeBookRequest(userId);
    }
}
