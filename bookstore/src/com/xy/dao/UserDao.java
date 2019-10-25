package com.xy.dao;

import com.xy.domain.User;
import com.xy.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDao {
    /**
     * 添加一个用户
     * @param user
     */
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    public void addUser(User user) throws Exception {
/*        QueryRunner queryRunner=new QueryRunner(C3P0Utils.getDataSource());
        String sql="INSERT INTO user";
        sql+="values(username,PASSWORD,gender,email,telephone,introduce,activeCode,state,role,registTime)";
        sql+="(?,?,?,?,?,?,?,?,?,?)";
        List<Object> list=new ArrayList<>();
        list.add(user.getUsername());
        list.add(user.getPassword());
        list.add(user.getGender());
        list.add(user.getEmail());
        list.add(user.getTelephone());
        list.add(user.getIntroduce());
        list.add(user.getActiveCode());
        list.add(user.getState());
        list.add(user.getRole());
        list.add(user.getRegistTime());
        queryRunner.update(sql,list.toArray());*/
        String sql="INSERT INTO user values(null,?,?,?,?,?,?,?,?,?,?)";
        template.update(sql,user.getUsername(),user.getPassword(),user.getGender(),user.getEmail(),user.getTelephone(),user.getIntroduce(),user.getActiveCode(),user.getState(),user.getRole(),user.getRegistTime());
    }

    public User findUserByActiveCode(String activeCode){
        String sql="SELECT *FROM user WHERE activeCode=?";
        return  template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),activeCode);
    }
    public void updateState(String activeCode){
        String sql="UPDATE user SET state = 1 WHERE activeCode=?";
        template.update(sql,activeCode);
    }
    public User findUserByUsernameAndPassword(String username,String password){
        String sql="SELECT *FROM user WHERE username=? AND password=?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),username,password);
    }
    public User findUserById(String id){
        String sql="SELECT *FROM user WHERE id=?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),id);
    }
    public void updateUser(User user){
        String sql="UPDATE user SET password=?,gender=?,telephone=? WHERE id=?";
        template.update(sql,user.getPassword(),user.getGender(),user.getTelephone(),user.getId());
    }
}
