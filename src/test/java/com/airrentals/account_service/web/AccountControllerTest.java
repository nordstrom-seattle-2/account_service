package com.airrentals.account_service.web;

import com.airrentals.account_service.domain.DomainService;
import com.airrentals.account_service.model.Profile;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AccountController.class, secure = false)
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DomainService domainService;

    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void retrieveDetailsForCourse() throws Exception {
        //setup
        Profile mockProfile = createMockProfile();
        Optional<Profile> profile = Optional.of(mockProfile);
        when(domainService.getMember(any())).thenReturn(profile);

        Map map = new HashMap();
        map.put("firstName", mockProfile.getFirstName());
        map.put("lastName", mockProfile.getLastName());
        map.put("emailAddress", mockProfile.getEmailAddress());
        String expected = objectMapper.writeValueAsString(map);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/accounts/getMember/1").accept(
                MediaType.APPLICATION_JSON);

        //action
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        //assert
        assertThat(result.getResponse().getContentAsString(), is(equalTo(expected)));
    }

    private Profile createMockProfile() {
        return new Profile(1l, "firstName", "lastName", "email", "password");
    }
}
