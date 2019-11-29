package cn.math;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
    //由spring容器自动注入web上下文对象
    @Autowired
    private WebApplicationContext wac;
    //定义一个用于伪造mvc请求环境的对象
    private MockMvc mockMvc;

    //每个测试方法被调用之前都会先被调用
    //用于测试资源的初始化
    @Before
    public  void setUp(){
        mockMvc= MockMvcBuilders.webAppContextSetup(wac).build();
    }

    //删除
    @Test
    public void whenDeleteSuccess() throws Exception {
        String list=mockMvc.perform(delete("/user/4")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println("我删掉了id为："+list+"的家伙");
    }

    //新增
    @Test
    public void whenCreateSuccess() throws Exception {
        String content="[{\"id\":null,\"username\":\"tom1\",\"password\":\"123456\"},{\"id\":null,\"username\":\"tom2\",\"password\":\"123456\"},{\"id\":null,\"username\":\"tom3\",\"password\":\"123456\"}]";
        String list=mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(list);
    }

    //修改
    @Test
    public void whenUpdateSuccess() throws Exception {
        String content="{\"id\":3,\"username\":null,\"password\":\"888888\",\"is_account_non_expired\":\"true\",\"is_account_non_locked\":\"true\",\"is_credentials_non_expired\":\"true\",\"is_enabled\":\"true\"}";
        String list=mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(list);
    }

    //详细查询
    @Test
    public void whenGetInfoSuccess() throws Exception {
        String list=mockMvc.perform(get("/user/3")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println("详情查询："+list);
    }

    //普通查询
    @Test
    public void whenQuerySuccess() throws Exception {
        long birthday=System.currentTimeMillis();
        String param="[{\"id\":1,\"username\":\"tom1\",\"password\":\"123456\"},{\"id\":2,\"username\":\"tom2\",\"password\":\"123456\"},{\"id\":3,\"username\":\"tom3\",\"password\":\"123456\"}]";
        //发起一个Rest请求，查询所有用户
        //期待返回的结果的状态码为：
        String list=mockMvc.perform(get("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(param))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andReturn().getResponse().getContentAsString();
        System.out.println(list);
    }
}
