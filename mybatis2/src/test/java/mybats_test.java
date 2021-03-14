
import com.bean.User;
import com.dao.UserDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class mybats_test {
    private InputStream in;
    private SqlSession session;
    private UserDao userDao;


    @Before//测试方法之前执行
    public void init() throws IOException {
        in= Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
        //使用工厂生产SqlSession对象
        SqlSessionFactory factory=builder.build(in);
        //使用SqlSession创建Dao接口的代理对象
        session=factory.openSession();
        //使用代理对象执行方法
        userDao=session.getMapper(UserDao.class);
    }
    @After//测试方法之后
    public void destroy() throws IOException {
     session.close();
     in.close();
    }




    @Test
    public  void testFindAll() throws IOException {
        List<User> users=userDao.findAll();
        for (User user:users){
            System.out.println(user);
        }

    }

    @Test
    public void testSave() throws IOException {
        User user=new User();
        user.setUsername("啊狗");
        user.setBirthday(new Date());
        user.setAddress("武汉");
        user.setSex("女");
        userDao.saveUser(user);
        session.commit();

    }
    @Test
    public void testUpdate() throws IOException {
        User user=new User();
        user.setId(44);
        user.setUsername("啊");
        user.setBirthday(new Date());
        user.setAddress("武汉");
        user.setSex("男");
        userDao.updateUser(user);
        session.commit();
    }


    @Test
    public void testDelete(){
        userDao.deleteUser(44);
        session.commit();
    }

    @Test
    public void testSelectByid(){
        User byid = userDao.findByid(41);
        System.out.println(byid);
    }
    @Test
    public void testSelectByname(){
        List<User> list = userDao.findByname("打%");
        for (User user :list){
            System.out.println(user);
        }
    }


}
