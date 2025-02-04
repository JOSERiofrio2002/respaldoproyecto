package com.example.controller.dao.services;

import com.example.controller.dao.TokenDao;
import com.example.models.Token;

public class TokenService {
	private TokenDao objDao;
	
	public TokenService() {
		objDao = new TokenDao();
	}
	
	public Token findbyToken (String tkn) throws Exception{
		return objDao.findbyTokn(tkn);
	}
}
