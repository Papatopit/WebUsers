package testCase;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.userService.controller.UserController;
import org.userService.entity.User;
import org.userService.service.UserServiceWebClient;

import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.modelmapper.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.userService.entity.Role.USER;

@WebMvcTest(UserController.class)
@ExtendWith(SpringExtension.class)
public class PublicControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserServiceWebClient userServiceWebClient;
    @Autowired
    private User user;

    @TestConfiguration
    static class TestConfig {
    }

    @Test
    public void testGetUserList() throws Exception {
        when(userServiceWebClient.getAllUsers()).thenReturn(Collections.singletonList(user));
        mockMvc.perform(get("/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect((ResultMatcher) jsonPath("$", hasSize(1)))
                .andExpect((ResultMatcher) jsonPath("$").isArray())
                .andExpect((ResultMatcher) jsonPath("$").isNotEmpty());
    }
    @Test
    public void testCreateOrder() throws Exception {
        when(userServiceWebClient.saveUser(user)).thenReturn(user);
        mockMvc.perform(
                        post("/create")
                                .content(objectMapper.writeValueAsString(user))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect((ResultMatcher) jsonPath("$.firstName", is("andrew")))
                .andExpect((ResultMatcher) jsonPath("$.lastName", is("Vasilyev")))
                .andExpect((ResultMatcher) jsonPath("$.id", is(10)))
                .andExpect((ResultMatcher) jsonPath("$").isNotEmpty());
    }


    @Test
    public void testDeleteOrder() throws Exception {
        User user = new User
                (1L, "andrew", "Vasilyev","vasilyev@mail.ru", "23.04.1990",USER,"pass");
        when(userServiceWebClient.deleteUserById(user.getId())).thenReturn(true);
        mockMvc.perform(delete("/delete/" + user.getId()))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
