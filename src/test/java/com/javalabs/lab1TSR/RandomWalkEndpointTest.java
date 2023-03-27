package com.javalabs.lab1TSR;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RandomWalkEndpointTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void walkShouldReturnDefaultMessage() throws Exception{
        this.mockMvc.perform(get("/walk?"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void walkShouldReturnMessage() throws Exception{
        String number = "9";
        this.mockMvc.perform(get("/walk?num=" + number))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void walkShouldReturn400() throws Exception{
        String number = "11";
        this.mockMvc.perform(get("/walk?num=" + number))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void walkShouldReturn500() throws Exception{
        String num = "NaN";
        this.mockMvc.perform(get("/walk?num=" + num))
                .andDo(print())
                .andExpect(status().isInternalServerError());

    }

}
