package ar.com.broker.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import ar.com.broker.dto.StocksDTO;
import ar.com.broker.dto.StocksPackDTO;
import ar.com.broker.dto.StocksPackResponseDTO;
import ar.com.broker.exception.EntityNotFoundException;
import ar.com.broker.exception.StocksCuantityException;
import ar.com.broker.service.impl.StocksServiceImpl;

/**
 * 
 * @author martinvacas
 *
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = StocksController.class)
@WebMvcTest(StocksController.class)
public class StocksControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@Autowired 
	private ObjectMapper objectMapper;

	@MockBean
	private StocksServiceImpl stockService;

	private StocksPackDTO packDto;
	
	private JacksonTester <StocksPackDTO> jsonTester;
	
	@Before
    public void setup() {
        JacksonTester.initFields(this, objectMapper);
        packDto = new StocksPackDTO();
    }
	
	@Test
	public void getStocksWhenReqShouldReturnStocksInfo() throws Exception {
		StocksDTO dto = new StocksDTO("EBAY", "E-Bay", Float.valueOf("100"));
		given(this.stockService.getStocksInfo("EBAY")).willReturn(dto);
		this.mvc.perform(get("/api/stocks/EBAY").accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(content().json("{'name':'E-Bay','ticker':'EBAY', 'price':100.0}"));
	}
	
	@Test
	public void getStocksWhenTickerNotFoundShouldReturnNotFound() throws Exception {
		given(this.stockService.getStocksInfo("UNEXIST_TICKER")).willThrow(new EntityNotFoundException("UNEXIST_TICKER"));
		this.mvc.perform(get("/api/stocks/UNEXIST_TICKER"))
					.andExpect(status().isNotFound());
	}
	
	@Test
	public void buyStocksPackWhenReqShouldReturnListStocksBuyed() throws Exception {
		String packStocksDTOJson = jsonTester.write(packDto).getJson();
		StocksPackResponseDTO packDtoResponse = new StocksPackResponseDTO();
		Map<String, Integer> stocksBuyed = new HashMap<String,Integer>();
		packDtoResponse.setStocksBuyed(stocksBuyed);
		given(this.stockService.buyStoksPack(any(StocksPackDTO.class))).willReturn(new StocksPackResponseDTO());
		this.mvc.perform(post("/api/stocks").content(packStocksDTOJson).contentType(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(status().isOk());
	}
	
	@Test
	public void buyStocksPacksWhenReqShouldReturnStocksCuantityException() throws Exception {
		String packStocksDTOJson = jsonTester.write(packDto).getJson();
		given(this.stockService.buyStoksPack(any(StocksPackDTO.class))).willThrow(new StocksCuantityException());
		this.mvc.perform(post("/api/stocks").content(packStocksDTOJson).contentType(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(status().is5xxServerError());
	}

}
