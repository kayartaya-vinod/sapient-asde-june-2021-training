package com.sapient.asde.batch5.vehicledataservice.controller.pod_e.pjmb1589;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.asde.batch5.vehicledataservice.service.ServiceException;
import com.sapient.asde.batch5.vehicledataservice.service.pod_e.pjmb1589.AccessoriesService;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@AutoConfigureMockMvc
public class AccessoriesControllerTest {
        @Autowired
        MockMvc mockMvc;

        @Autowired
        private ObjectMapper om;

        @MockBean
        private RestTemplate template;

        @MockBean
        AccessoriesService service;
        @Value("${authVerifyUrl}")
        String authVerifyUrl;

        String url = "/api/data/upload/json";

        private String token;
        private String errorToken;
        private String userId;
        private String errorId;
        private String dealer;

        String fileName = "sample.json";
        MockMultipartFile file1 = new MockMultipartFile("jsonfile", fileName, MediaType.TEXT_PLAIN_VALUE,
                        "Hello, World!".getBytes());
        MockMultipartFile file2 = new MockMultipartFile("jsonfile", fileName, MediaType.TEXT_PLAIN_VALUE,
                        "Hello, World!".getBytes());

        @BeforeEach
        void setup() throws ServiceException, RestClientException, JsonProcessingException, IOException,
                        ParseException {
                token = "token";
                userId = "user1";
                errorId = "errorId";
                errorToken = "errorToken";
                dealer = "dealer";

                String url = String.format("%s/?jwt=%s", authVerifyUrl, token);

                Map<String, Object> claims = new HashMap<>();
                claims.put("firstName", "Name");
                claims.put("userType", "customer");
                claims.put("exp", "324323");
                claims.put("userId", userId);
                claims.put("iat", "234234234");
                claims.put("email", "email");

                Mockito.when(template.getForObject(url, String.class)).thenReturn(om.writeValueAsString(claims));

                url = String.format("%s/?jwt=%s", authVerifyUrl, errorToken);
                claims.put("userId", errorId);

                Mockito.when(template.getForObject(url, String.class)).thenReturn(om.writeValueAsString(null));

                url = String.format("%s/?jwt=%s", authVerifyUrl, dealer);
                claims.put("userType", dealer);
                claims.put("userId", "user");
                Mockito.when(service.store(file1, "user", file1.getOriginalFilename())).thenReturn(true);
                Mockito.when(service.store(file2, "user", file1.getOriginalFilename())).thenReturn(false);

                Mockito.when(template.getForObject(url, String.class)).thenReturn(om.writeValueAsString(claims));

        }

        @Test
        void testNotADealer() throws Exception {

                String fileName = "sample-file-mock.json";
                MockMultipartFile sampleFile = new MockMultipartFile("jsonfile", fileName, "text/plain",
                                "This is the file content".getBytes());

                MockMultipartHttpServletRequestBuilder multipartRequest = MockMvcRequestBuilders
                                .multipart("/api/data/upload/json");
                MvcResult result = mockMvc
                                .perform(multipartRequest.file(sampleFile).header("Authorization", "JWT " + token))
                                .andExpect(status().isBadRequest()).andReturn();
                assertEquals("Permession Denied : For Uploading user has to be dealer",
                                result.getResponse().getContentAsString());
        }

        @Test
        void testNotAJson() throws Exception {

                String fileName = "sample-file-mock.txt";
                MockMultipartFile sampleFile = new MockMultipartFile("jsonfile", fileName, "text/plain",
                                "This is the file content".getBytes());

                MockMultipartHttpServletRequestBuilder multipartRequest = MockMvcRequestBuilders
                                .multipart("/api/data/upload/json");
                MvcResult result = mockMvc
                                .perform(multipartRequest.file(sampleFile).header("Authorization", "JWT " + token))
                                .andExpect(status().isBadRequest()).andReturn();
                assertEquals("sample-file-mock.txt: Error: this is not a JSON file! ",
                                result.getResponse().getContentAsString());
        }

        @Test
        void testFileWithoutExtension() throws Exception {

                String fileName = "sample-file-mock";
                MockMultipartFile sampleFile = new MockMultipartFile("jsonfile", fileName, "text/plain",
                                "This is the file content".getBytes());

                MockMultipartHttpServletRequestBuilder multipartRequest = MockMvcRequestBuilders
                                .multipart("/api/data/upload/json");
                MvcResult result = mockMvc
                                .perform(multipartRequest.file(sampleFile).header("Authorization", "JWT " + token))
                                .andExpect(status().isBadRequest()).andReturn();
                assertEquals("sample-file-mock: Error: this is not a JSON file! ",
                                result.getResponse().getContentAsString());
        }

        @Test
        void testUnauthorized() throws Exception {

                String fileName = "sample-file-mock.json";
                MockMultipartFile sampleFile = new MockMultipartFile("jsonfile", fileName, "text/plain",
                                "This is the file content".getBytes());

                MockMultipartHttpServletRequestBuilder multipartRequest = MockMvcRequestBuilders
                                .multipart("/api/data/upload/json");
                MvcResult result = mockMvc
                                .perform(multipartRequest.file(sampleFile).header("Authorization", "JWT " + errorToken))
                                .andExpect(status().isUnauthorized()).andReturn();
                assertEquals("Authorization header may be missing or invalid try.",
                                result.getResponse().getContentAsString());
        }

        @Test
        void testFileUploadPass() throws Exception {
                MockMultipartHttpServletRequestBuilder multipartRequest = MockMvcRequestBuilders
                                .multipart("/api/data/upload/json");
                MvcResult result = mockMvc
                                .perform(multipartRequest.file(file1).header("Authorization", "JWT " + dealer))
                                .andExpect(status().isOk()).andReturn();
                assertEquals("File uploaded successfully", result.getResponse().getContentAsString());
        }

        @Test
        void testFileUploadFail() throws Exception {
                MockMultipartHttpServletRequestBuilder multipartRequest = MockMvcRequestBuilders
                                .multipart("/api/data/upload/json");
                MvcResult result = mockMvc
                                .perform(multipartRequest.file(file2).header("Authorization", "JWT " + dealer))
                                .andExpect(status().isBadRequest()).andReturn();
                assertEquals("sample.json Upload failed necessory key not present",
                                result.getResponse().getContentAsString());
        }
}
