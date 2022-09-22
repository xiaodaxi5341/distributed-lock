package com.atguigu.distributed.lock;

import com.alibaba.fastjson.JSON;
import com.atguigu.distributed.lock.pojo.Stock;
import com.atguigu.distributed.lock.service.ModelService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
class DistributedLockApplicationTests {

    @Autowired
    private ModelService modelService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() throws Exception {
        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.post("/model/program1").
                contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("message","消息").param("stock", JSON.toJSONString(new Stock())));
        perform.andDo(result -> {
            MockHttpServletResponse response = result.getResponse();
            log.info(result.getResponse().getContentAsString());
        });
    }


}
