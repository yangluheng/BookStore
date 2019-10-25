package com.xy.web.servlet;

import com.xy.domain.User;
import com.xy.exception.UserException;
import com.xy.service.UserService;
import org.apache.commons.beanutils.BeanUtils;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String checkcode_client=request.getParameter("checkcode");
        String checkcode_session= (String) request.getSession().getAttribute("checkcode_session");
            if (!checkcode_client.equals(checkcode_session)){
            request.setAttribute("checkcode_err","验证码不一样");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
            return;
        }
        User user=new User();
        try {
            BeanUtils.populate(user,request.getParameterMap());
            user.setActiveCode(UUID.randomUUID().toString());
            user.setRole("普通用户");
            user.setRegistTime(new Date());
            System.out.println(user);

            UserService userService=new UserService();
            System.out.println("注册");
            userService.register(user);

            request.getRequestDispatcher("/registersuccess.jsp").forward(request,response);
        } catch (UserException e) {
            e.printStackTrace();
            request.setAttribute("register_err",e.getMessage());
            request.getRequestDispatcher("/register.jsp").forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("参数转模型失败***");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
