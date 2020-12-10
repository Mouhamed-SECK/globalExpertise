/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.UserDao;
import models.Employes;


/**
 *
 * @author ASUS
 */
public class Security {
    
    private UserDao userDao ;

    public Security() {
        userDao = new UserDao();
    }
    
        public Employes logInUser (String login, String Password) {
        return userDao.selectUserByEmailAndPassword(login, Password);
    }
    
    
}
