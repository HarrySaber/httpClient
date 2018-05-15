/**
 * STARPOST CONFIDENTIAL
 * _____________________
 * 
 * [2014] - [2018] StarPost Supply Chain Management Co. (Shenzhen) Ltd. 
 * All Rights Reserved.
 * 
 * NOTICE: All information contained herein is, and remains the property of
 * StarPost Supply Chain Management Co. (Shenzhen) Ltd. and its suppliers, if
 * any. The intellectual and technical concepts contained herein are proprietary
 * to StarPost Supply Chain Management Co. (Shenzhen) Ltd. and its suppliers and
 * may be covered by China and Foreign Patents, patents in process, and are
 * protected by trade secret or copyright law. Dissemination of this information
 * or reproduction of this material is strictly forbidden unless prior written
 * permission is obtained from StarPost Supply Chain Management Co. (Shenzhen)
 * Ltd.
 *
 */
package cn.starpost.tms.client;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.starpost.tms.client.value.channel.FindChannelRequest;
import cn.starpost.tms.client.value.channel.FindChannelResponse;
import cn.starpost.tms.client.value.warehouse.FindWarehouseRequest;
import cn.starpost.tms.client.value.warehouse.FindWarehouseResponse;

/**
 * @author dongjianpeng
 *
 */
public class ApiTest {
	final static String baseUrl = "http://localhost:9003";

	@Test
	public void testChannel() {
		TmsClient tmsClient = new TmsClient(baseUrl);
		ObjectMapper mapper = new ObjectMapper();
//		FindChannelResponse findAllChannel = tmsClient
//				.findAllChannel(new FindChannelRequest("b47f5f3a-c2b4-483d-94f4-9dd0b508700d"));
//		System.out.println(mapper.valueToTree(findAllChannel));
//		FindWarehouseResponse findAllWarehouse = tmsClient
//				.findAllWarehouse(new FindWarehouseRequest("b47f5f3a-c2b4-483d-94f4-9dd0b508700d"));
//		System.out.println(mapper.valueToTree(findAllWarehouse));
	}
}
