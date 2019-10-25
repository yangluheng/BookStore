package com.xy.service;
import com.xy.dao.UserDao;
import com.xy.domain.User;
import com.xy.exception.UserException;
import com.xy.utils.SendJMail;

public class UserService {
    /**
     * 创建UserDao
     * @param user
     */
    UserDao userDao=new UserDao();
    public void register(User user) throws UserException {
        try {
            System.out.println("添加用户");
            userDao.addUser(user);
            System.out.println("添加成功");
            String link="http://localhost:8080/bookstore/ActiveServlet?activeCode="+user.getActiveCode();
            String html="<a href=\""+link+"\">\""+user.getUsername()+"用户，"+"\"欢迎您注册网上书城账号，请点击这里激活</a>";
            System.out.println(html);
            SendJMail.sendMail(user.getEmail(),html);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UserException("用户注册失败，用户名重复");
        }
    }
    public void activeUser(String activeCode) throws UserException {
/*        try {
            User user =userDao.findUserByActiveCode(activeCode);
            if (user==null){
                throw new UserException("非法激活，用户不存在");
            }
            if (user!=null&&user.getState()==1){
                throw new UserException("用户已经激活过了***");
            }
            userDao.updateState(activeCode);
        }catch (Exception e){
            throw new UserException("激活失败");
        }*/
        User user =null;
        try {
            user=userDao.findUserByActiveCode(activeCode);
        }catch (Exception e){
            throw new UserException("未激活");
        }
        if (user==null){
            throw new UserException("非法激活，用户不存在");
        }
        if (user!=null&&user.getState()==1){
            throw new UserException("用户已经激活过了***");
        }
        try {
            userDao.updateState(activeCode);
        } catch (Exception e) {
            throw new UserException("激活失败");
        }
    }
    public User login(String username,String password) throws UserException {
/*        try {
            User user = userDao.findUserByUsernameAndPassword(username, password);
            if (user == null) {
                throw new UserException("用户名或者密码不正确");
            }
            if (user.getState()==0){
                throw new UserException("用户未激活，请先登陆邮件进行激活");
            }
            return user;
        }catch (UserException e) {
            e.printStackTrace();
            throw new UserException("登录失败");
        }*/
        User user=null;
        try {
            user= userDao.findUserByUsernameAndPassword(username, password);
        }
        catch (Exception e) {
                e.printStackTrace();
            throw new UserException("用户名或者密码不正确");
        }
        if (user.getState()==0){
            throw new UserException("用户未激活，请先登陆邮件进行激活");
        }
        return user;
    }
    public User findUserById(String id) throws UserException {
        User user=null;
        try {
            user= userDao.findUserById(id);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new UserException("用户不存在");
        }
        return user;
    }
    public void modifyUserInfo(User user){
        try {
            userDao.updateUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
