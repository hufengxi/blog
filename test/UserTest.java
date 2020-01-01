import com.app.appName.dao.UsersDao;
import com.app.appName.dto.UnitTestDto1;
import com.app.appName.entity.UsersEntity;
import com.app.appName.service.UsersService;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.test.context.ContextConfiguration;

import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:spring/spring-servlet.xml"})

public class UserTest extends AbstractJUnit4SpringContextTests{

    //注入测试类
    @Resource
    private UsersService usersService;

    @Test
    public void testSave(){
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setPwd("pwd");
        usersService.save(usersEntity);
    }

    @Test
    public void testDelete(){
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setId(1);
        usersService.delete(usersEntity);
    }

    @Test
    public void testUpdate(){
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setId(1);
        usersEntity.setPwd("updatePwd");
        usersService.update(usersEntity);
    }

    @Test
    public void testSaveOrUpdate(){
        UsersEntity usersEntitySave = new UsersEntity();
        usersEntitySave.setPwd("Pwd");
        usersService.saveOrUpdate(usersEntitySave);

        UsersEntity usersEntityUpdate = new UsersEntity();
        usersEntityUpdate.setId(1);
        usersEntityUpdate.setPwd("Pwd");
        usersService.saveOrUpdate(usersEntityUpdate);
    }

    @Test
    public void testSaveAll(){
        List<UsersEntity> usersEntities = new ArrayList<UsersEntity>();

        for(int i = 0; i < 60000; i++){
            UsersEntity usersEntity = new UsersEntity();
            usersEntity.setPwd("Pwd"+i);
            usersEntities.add(usersEntity);
        }
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        Date dateStart = new Date();
        System.out.println(dateFormat.format(dateStart));
        usersService.saveAll(usersEntities);
        Date dateEnd = new Date();
        System.out.println(dateFormat.format(dateEnd));
    }

    @Test
    public void testSaveOrUpdateAll(){
        List<UsersEntity> usersEntities = new ArrayList<UsersEntity>();

        for(int i = 0; i < 600; i++){
            UsersEntity usersEntity = new UsersEntity();
            if(i == 3){
                usersEntity.setId(2002477);
            }
            usersEntity.setPwd("Pwd"+i);
            usersEntities.add(usersEntity);
        }
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        Date dateStart = new Date();
        System.out.println(dateFormat.format(dateStart));
        usersService.saveOrUpdateAll(usersEntities);
        Date dateEnd = new Date();
        System.out.println(dateFormat.format(dateEnd));
    }

    @Test
    public void testGetById(){
        UsersEntity usersEntity = usersService.get(UsersEntity.class, 1000014);
        System.out.println(usersEntity.toString());
    }

    @Test
    public void testGetByHql(){
        UsersEntity usersEntity = usersService.get("from UsersEntity where id = ?", 1000014);
        System.out.println(usersEntity.toString());
    }

    @Test
    public void testGetBySql(){
        UsersEntity usersEntity = usersService.getBySql(UsersEntity.class,"select a.* from users a where a.id = ?", 1000014);
        System.out.println(usersEntity.toString());
    }

    @Test
    public void queryListByHql(){
        List<UsersEntity> usersEntities = usersService.queryListByHql("from UsersEntity a where a.id < ?", 1000114);
        System.out.println(usersEntities.toString());
    }

    @Test
    public void queryListByHqlMap(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id",1000114);

        List<UsersEntity> usersEntities = usersService.queryListByHql("from UsersEntity a where a.id < :id", map);
        System.out.println(usersEntities.toString());
    }

    @Test
    public void queryListBySql(){
        List<UsersEntity> usersEntities = usersService.queryListBySql("select a.* from users a where a.id < ?", 1000114);
        System.out.println(usersEntities.toString());
    }

    @Test
    public void queryListBySqlBean(){
        List<UsersEntity> usersEntities = usersService.queryListBySql(UsersEntity.class, "select a.* from users a where a.id < ?", 1000114);
        System.out.println(usersEntities.toString());
    }

    @Test
    public void queryListBySqlBean1(){
        List<UnitTestDto1> usersEntities = usersService.queryListBySql("select a.name as nameDto, a.tel as telDto " +
                "from users a where a.id < ?", UnitTestDto1.class, 1000114);
        System.out.println(usersEntities.toString());
    }

    @Test
    public void queryListBySqlMap(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id",1000114);
        List<UsersEntity> usersEntities = usersService.queryListBySql(UsersEntity.class, "select a.* from users a where a.id < :id", map);
        System.out.println(usersEntities.toString());
    }

    @Test
    public void updateByHql(){
        int updateNum = usersService.updateByHql("update UsersEntity a set a.name = 'test' where a.id < 1000114");
        System.out.println(updateNum);
    }

}
