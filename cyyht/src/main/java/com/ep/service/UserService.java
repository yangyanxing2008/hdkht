package com.ep.service;


import java.util.List;

import com.ep.entity.User;



/**
 * 
 * @author yyx
 *
 */
public interface UserService {
	public String login(User user);
	public int save(User user);
	public List getUserByNumber(String accountNumber);
	
}
