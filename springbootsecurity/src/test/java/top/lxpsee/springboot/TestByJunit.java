package top.lxpsee.springboot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lpgxp.springbootlearn.web.CookieController;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
//由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
@WebAppConfiguration
public class TestByJunit {

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        System.out.println("开始测试-----------------");
        //独立安装测试
        mockMvc = MockMvcBuilders.standaloneSetup(new CookieController()).build();
        //集成Web环境测试（此种方式并不会集成真正的web环境，而是通过相应的Mock API进行模拟测试，无须启动服务器）
        //mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @After
    public void after() {
        System.out.println("测试结束-----------------");
    }

    /***
     * 测试添加用户接口
     * @throws Exception
     */
    @Test
    public void testAddUser() throws Exception {
        //构造添加的用户信息
        //UserInfo userInfo = new UserInfo();
        //userInfo.setName("testuser2");
        //userInfo.setAge(29);
        //userInfo.setAddress("北京");
        ObjectMapper mapper = new ObjectMapper();

        //调用接口，传入添加的用户参数
        mockMvc.perform(post("/user/adduser")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(/*userInfo*/null)))
                //判断返回值，是否达到预期，测试示例中的返回值的结构如下{"errcode":0,"errmsg":"OK","p2pdata":null}
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                //使用jsonPath解析返回值，判断具体的内容
                .andExpect(jsonPath("$.errcode", is(0)))
                .andExpect(jsonPath("$.p2pdata", notNullValue()))
                .andExpect(jsonPath("$.p2pdata.id", not(0)))
                .andExpect(jsonPath("$.p2pdata.name", is("testuser2")));
    }

    /***
     * 测试更新用户信息接口
     * @throws Exception
     */
    @Test
    public void testUpdateUser() throws Exception {
        //构造添加的用户信息，更新id为2的用户的用户信息
        //UserInfo userInfo = new UserInfo();
        //userInfo.setId((long)2);
        //userInfo.setName("testuser");
        //userInfo.setAge(26);
        //userInfo.setAddress("南京");
        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(put("/user/updateuser")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(/*userInfo*/null)))
                //判断返回值，是否达到预期，测试示例中的返回值的结构如下
                //{"errcode":0,"errmsg":"OK","p2pdata":null}
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.errcode", is(0)))
                .andExpect(jsonPath("$.p2pdata", notNullValue()))
                .andExpect(jsonPath("$.p2pdata.id", is(2)))
                .andExpect(jsonPath("$.p2pdata.name", is("testuser")))
                .andExpect(jsonPath("$.p2pdata.age", is(26)))
                .andExpect(jsonPath("$.p2pdata.address", is("南京")));
    }

    /***
     * 测试根据用户id获取用户信息接口
     * @throws Exception
     */
    @Test
    public void testGetUser() throws Exception {
        mockMvc.perform(get("/user/getuser?id=2"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.errcode", is(0)))
                .andExpect(jsonPath("$.p2pdata", notNullValue()))
                .andExpect(jsonPath("$.p2pdata.id", is(2)))
                .andExpect(jsonPath("$.p2pdata.name", is("testuser")))
                .andExpect(jsonPath("$.p2pdata.age", is(26)))
                .andExpect(jsonPath("$.p2pdata.address", is("南京")));
    }

    /***
     * 测试获取用户列表接口
     * @throws Exception
     */
    @Test
    public void testGetUsers() throws Exception {
        mockMvc.perform(get("/user/getalluser"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.errcode", is(0)))
                .andExpect(jsonPath("$.p2pdata", notNullValue()));
    }
}