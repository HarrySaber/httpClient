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

import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Date;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.starpost.tms.client.value.charge.GetOrderChargeBillRequest;
import cn.starpost.tms.client.value.charge.GetOrderChargeBillResponse;

/**
 * @author dongjianpeng
 *
 */
public class ApiTest {
	final static String baseUrl = "http://localhost:9003";

	// @Test
	public void testChannel() {
		try {

			TmsClient tmsClient = new TmsClient(baseUrl);
			ObjectMapper mapper = new ObjectMapper();
			// FindChannelResponse findAllChannel = tmsClient
			// .findAllChannel(new
			// FindChannelRequest("b47f5f3a-c2b4-483d-94f4-9dd0b508700d"));
			// System.out.println(mapper.valueToTree(findAllChannel));
			// FindWarehouseResponse findAllWarehouse = tmsClient
			// .findAllWarehouse(new
			// FindWarehouseRequest("b47f5f3a-c2b4-483d-94f4-9dd0b508700d"));
			// System.out.println(mapper.valueToTree(findAllWarehouse));

			GetOrderChargeBillResponse getOrderChargeBillByTxIds = tmsClient
					.GetOrderChargeBillByTxIds(new GetOrderChargeBillRequest(
							Arrays.asList("384b318a-b7a4-4a16-b6fc-0789da10eda7",
									"8c5fb194-5541-4ce7-b15e-f3156a1ae51c", "40b88c9d-a541-4ad7-96b3-0dd7eeef0ec2"),
							"c31be593-975b-4f15-a17f-98beb5aeeba0", new Date(), new Date()));

			if (getOrderChargeBillByTxIds.isSucceed()) {
				String path = this.getClass().getResource("/").getPath();
				System.out.println(path);
				File file = new File(path + "/bill.xlsx");
				if (!file.exists()) {
					file.createNewFile();
				}
				FileOutputStream fos = new FileOutputStream(file);
				fos.write(getOrderChargeBillByTxIds.getOrderChargeBill());
				fos.flush();
				System.out.println("成功");
			} else {
				System.err.println(getOrderChargeBillByTxIds.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
