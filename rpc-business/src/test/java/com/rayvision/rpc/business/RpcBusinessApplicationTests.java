package com.rayvision.rpc.business;

import com.rayvision.rpc.api.business.RateService;
import com.rayvision.rpc.common.ApiResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RpcBusinessApplication.class)
public class RpcBusinessApplicationTests {
	private static Logger log = LoggerFactory.getLogger(RpcBusinessApplicationTests.class);
	@Autowired
	private RateService rateService;

	@Test
	public void testRateCall() throws Exception{
		ApiResponse apiResponse = rateService.selectById(1);
		log.info("api response result : " + apiResponse);
	}

}
