package com.youtube.bank.main;
import com.youtube.bank.entity.user; 
import com.youtube.bank.service.userservice; // ✅ Corrected import

import java.util.Map;
import java.util.Scanner;
import java.util.List;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    static Main main=new Main();
   static userservice userService = new userservice();
    public static void main(String args[]) {
         // ✅ Class name should be capitalized
        
        while(true)
        {
        	System.out.println("Enter your username:");
            String username = sc.next();
            
            System.out.println("Enter your password:");
            String password = sc.next();
           user User=userService.login(username,password);
           if(User!=null && User.getRole().equals("admin"))
           {
        	   main.initAdmin();
           }
           else if(User!=null && User.getRole().equals("user"))
           {
        	   main.initCustomer(User);
           }
           else
           {
        	   System.out.println("Login failed");
           }
        }
    }
    private void initAdmin()
    {
    	boolean flag=true;
    	String userId="";
    	
    	while(flag) {
    		System.out.println("1. Exit/Logout");
    		System.out.println("2. Create a customer account");
    		System.out.println("3. See all transaction");
    		System.out.println("4. Check bank balance");
    		System.out.println("5. Approval of cheque book request");
    		

        	int selectedOption = sc.nextInt();
        	
        	switch(selectedOption) {
        	case 1:
        		flag=false;
        		System.out.println("You have successfully logged out...");
        		break;
        	
        	case 2:
        		main.addNewCustomer();
        		break;
        		
        	case 3:
        		System.out.println("Enter user id");
        		 userId=sc.next();
        		printTransaction(userId);
        		break;
        	case 4:
        		System.out.println("Enter user id");
        		 userId=sc.next();
        		Double accountBalance=checkBankBalance(userId);
        		System.out.println("Your account balance is: "+accountBalance);
        		
        		break;
        	case 5:
        		List<String> userIds=getUserIdForCheckBookRequest();
        		System.out.println("Please select user id from below..");
        		System.out.println(userIds);
        		
        		userId=sc.next();
        		approveChequeBookRequest(userId);
        		System.out.println("chequebook request is approved..");
        		
        		
        		break;
        	default:
        			System.out.println("Wrong choice");
    	}
    	}
    }
    private void approveChequeBookRequest(String userId) {
    	userService.approveChequeBookRequest(userId);
    }
    private List<String> getUserIdForCheckBookRequest()
    {
    	return userService.getUserIdForCheckBookRequest();
    }
    private void raiseChequeBookRequest(String userId) {
    	userService.raiseChequeBookRequest(userId);
    }
    private void addNewCustomer() {
    	System.out.println("Enter username");
    	String username=sc.next();
    	
    	System.out.println("Enter password");
    	String password=sc.next();
    	
    	System.out.println("Enter contact number");
    	String contact=sc.next();
    	
    	boolean result=userService.addNewCustomer(username, password, contact);
    	
    	if(result==true)
    	{
    		System.out.println("Customer account is created");
    	}
    	else
    	{
    		System.out.println("Customer account creation failed...");
    	}
    }
    
    private void initCustomer(user User)
    {
    	boolean flag=true;
    	while(flag)
    	{
    		System.out.println("1. Exit/Logout");
    		System.out.println("2. Check bank balance");
    		System.out.println("3. Fund transaction");
    		System.out.println("4. see all transaction");
    		System.out.println("5. Raise cheque book request");
    		int selectedOption=sc.nextInt();
    		switch(selectedOption) {
        	case 1:
        		flag=false;
        		System.out.println("You have successfully logged out...");
        		break;
        	case 2:
        		Double balance=main.checkBankBalance(User.getUsername());
        		if(balance!=null) {
        			System.out.println("Your bank balance is "+balance);
        		}
        		else {
        			System.out.println("Check your username"); 
        		}
        		break;
        		
        	case 3:
        		main.fundTransfer(User);
        		break;
        	case 4:
        		main.printTransaction(User.getUsername());
        		break;
        	case 5:
        		String userId=User.getUsername();
        		Map<String,Boolean> map = getAllChequeBookRequest();
        		if(map.containsKey(userId) && map.get(userId)) {
        			System.out.println("You have already raised a request and it is already approved");
        		}
        		else if(map.containsKey(userId) && !map.get(userId)){
        			System.out.println("You have already raised a request and it is pending for approved");
        		}
        		else {
        			raiseChequeBookRequest(userId);
        			System.out.print("Request raised successfully.....");
        		}
        		break;
        	default:
        			System.out.println("Wrong choice");
    	}
    	}
    }
    private void printTransaction(String userId) {
    	userService.printTransaction(userId);
    }
    private Map<String,Boolean> getAllChequeBookRequest() {
    	return userService.getAllChequeBookRequest();
    }
    private void fundTransfer(user userDetails) {
    	
    	System.out.println("Enter payee account user id");
    	String payeeeAccountId=sc.next();
    	
    	user user=getUser(payeeeAccountId);
    	
    	if(user!=null)
    	{
    		System.out.println("Enter amount to transfer");
    		Double amount=sc.nextDouble();
    		
    		Double userAccountBalance=checkBankBalance(userDetails.getUsername());
    		if(userAccountBalance>=amount)
    		{
    			boolean result=userService.transferAmount(userDetails.getUsername(),payeeeAccountId,amount);
    			
    			if(result)
    			{
    				System.out.println("Amount transferred succesfully..");
    			}
    			else
    			{
    				System.out.println("Transfer failed...");
    			}
    		}
    		else {
    			System.out.println("Your balance is insufficient: "+userAccountBalance);
    		}
    	}else {
    		System.out.println("Please enter valid username.....");
    	}
    	
    }
    private user getUser(String userId)
    {
    	return userService.getUser(userId);
    }
    private Double checkBankBalance(String userId)
    {
    	return userService.checkBankBalance(userId);
    }
}
