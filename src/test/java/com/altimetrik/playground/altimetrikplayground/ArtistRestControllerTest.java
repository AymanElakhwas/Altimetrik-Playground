package com.altimetrik.playground.altimetrikplayground;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ArtistRestControllerTest.class, secure = false)
@ComponentScan(basePackages = "com.altimetrik.playground.altimetrikplayground")
public class ArtistRestControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testGetArtistService() throws Exception {

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/get-artist/Adele/Spain");
		mockMvc.perform(requestBuilder).andExpect(status().isOk()).andExpect(jsonPath("$.name", is("Adele")))
				.andExpect(jsonPath("$.topTrack", is("Hello")));
	}
}
