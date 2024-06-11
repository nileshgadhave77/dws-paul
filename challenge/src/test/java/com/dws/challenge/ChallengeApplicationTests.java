package com.dws.challenge;

import com.dws.challenge.Exception.AmountNotMatchException;
import com.dws.challenge.Service.FundTransferService;
import com.dws.challenge.Service.NotificationService;
import com.dws.challenge.controller.FundTransferController;
import com.dws.challenge.model.dto.request.FundTransfer;
import com.dws.challenge.model.dto.response.FundTransferResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class ChallengeApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FundTransferService fundTransferService;

	@MockBean
	private NotificationService notificationService;

	@MockBean
	private FundTransferController fundTransferController;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void fundTransfereAPI() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
						.post("/api/v1/transfer")
						.content(asJsonString(new FundTransfer("1", "2", new BigDecimal(0.0), "nilesh gadhave", " Loan")))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void testUsernameIsNull() {

		assertThrows(AmountNotMatchException.class, new Executable() {

			@Override
			public void execute() throws Throwable {

				FundTransfer fundTransfer=	new FundTransfer("1", "2", new BigDecimal(-2.0), "nilesh gadhave", " Loan");
				given(fundTransferService.transferFund(fundTransfer)).willThrow(new AmountNotMatchException(""));
				fundTransferService.transferFund(fundTransfer);
			}
		});
	}

	@Test
	public void fundTransfereAPINotification() throws Exception {

		given(notificationService.sendNotification("1", "2", new BigDecimal(0.0))).willReturn(any());
		mockMvc.perform(MockMvcRequestBuilders
						.post("/api/v1/transfer")
						.content(asJsonString(new FundTransfer("1", "2", new BigDecimal(0.0), "nilesh gadhave", " Loan")))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}



	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}


	}
}
