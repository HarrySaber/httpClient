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

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.starpost.tms.client.utils.HttpClientUtil;
import cn.starpost.tms.client.value.channel.FindChannelRequest;
import cn.starpost.tms.client.value.channel.FindChannelResponse;
import cn.starpost.tms.client.value.pdf.GetCartonLabelPdfRequest;
import cn.starpost.tms.client.value.pdf.GetCartonLabelPdfResponse;
import cn.starpost.tms.client.value.provider.FindServiceProvideRequest;
import cn.starpost.tms.client.value.provider.FindServiceProvideResponse;
import cn.starpost.tms.client.value.warehouse.FindAllFbaWarehouseRequest;
import cn.starpost.tms.client.value.warehouse.FindAllFbaWarehouseResponse;
import cn.starpost.tms.client.value.warehouse.FindOrderPrefixRequest;
import cn.starpost.tms.client.value.warehouse.FindOrderIdResponse;
import cn.starpost.tms.client.value.warehouse.FindWarehouseAddressByWarehouseCodeRequest;
import cn.starpost.tms.client.value.warehouse.FindWarehouseAddressByWarehouseCodeResponse;
import cn.starpost.tms.client.value.warehouse.FindWarehouseAddressRequest;
import cn.starpost.tms.client.value.warehouse.FindWarehouseAddressResponse;
import cn.starpost.tms.client.value.warehouse.FindWarehouseByIdRequest;
import cn.starpost.tms.client.value.warehouse.FindWarehouseByIdResponse;
import cn.starpost.tms.client.value.warehouse.FindWarehouseRequest;
import cn.starpost.tms.client.value.warehouse.FindWarehouseResponse;

/**
 * @author dongjianpeng
 *
 */
public class TmsClient {
	public static final Logger logger = LoggerFactory.getLogger(TmsClient.class);

	final String baseUrl;

	public TmsClient(String baseUrl) {
		if (!StringUtils.isBlank(baseUrl)) {
			if (baseUrl.endsWith("/")) {
				this.baseUrl = baseUrl.substring(0, baseUrl.length() - 1);
			} else {
				this.baseUrl = baseUrl;
			}
		} else {
			throw new IllegalArgumentException("baseUrl is blank");
		}
	}

	public FindChannelResponse findAllChannel(FindChannelRequest request) {
		try {
			String url = baseUrl + "/api/owner-channel";
			ObjectMapper objectMapper = new ObjectMapper();
			logger.info(">>>>TmsClient>findAllChannel url:{}", url);
			String json = objectMapper.writeValueAsString(request);
			String response = HttpClientUtil.doPost(url, json);
			if (!StringUtils.isBlank(response)) {
				return objectMapper.readValue(response, FindChannelResponse.class);
			} else {
				return FindChannelResponse.failed("response is blank");
			}
		} catch (JsonParseException e) {
			return FindChannelResponse.failed(e.getLocalizedMessage());
		} catch (JsonMappingException e) {
			return FindChannelResponse.failed(e.getLocalizedMessage());
		} catch (Exception e) {
			return FindChannelResponse.failed(e.getLocalizedMessage());
		}
	}

	public FindWarehouseResponse findAllWarehouse(FindWarehouseRequest request) {
		try {
			String url = baseUrl + "/api/owner-warehouse";
			ObjectMapper objectMapper = new ObjectMapper();
			logger.info(">>>>TmsClient>findAllWarehouse url:{}", url);
			String json = objectMapper.writeValueAsString(request);
			String response = HttpClientUtil.doPost(url, json);
			if (!StringUtils.isBlank(response)) {
				return objectMapper.readValue(response, FindWarehouseResponse.class);
			} else {
				return FindWarehouseResponse.failed("response is blank");
			}
		} catch (JsonParseException e) {
			return FindWarehouseResponse.failed(e.getLocalizedMessage());
		} catch (JsonMappingException e) {
			return FindWarehouseResponse.failed(e.getLocalizedMessage());
		} catch (Exception e) {
			return FindWarehouseResponse.failed(e.getLocalizedMessage());
		}
	}

	public FindWarehouseAddressResponse findWarehouseAddressCode(FindWarehouseAddressRequest request) {
		try {
			String url = baseUrl + "/api/warehouse-address";
			ObjectMapper objectMapper = new ObjectMapper();
			logger.info(">>>>TmsClientWarehouseAddress>findWarehouseAddressCode url:{}", url);
			String json = objectMapper.writeValueAsString(request);
			String response = HttpClientUtil.doPost(url, json);
			if (!StringUtils.isBlank(response)) {
				return objectMapper.readValue(response, FindWarehouseAddressResponse.class);
			} else {
				return FindWarehouseAddressResponse.failed("response is blank");
			}
		} catch (JsonParseException e) {
			return FindWarehouseAddressResponse.failed(e.getLocalizedMessage());
		} catch (JsonMappingException e) {
			return FindWarehouseAddressResponse.failed(e.getLocalizedMessage());
		} catch (Exception e) {
			return FindWarehouseAddressResponse.failed(e.getLocalizedMessage());
		}
	}

	public FindWarehouseAddressByWarehouseCodeResponse findWarehouseAddressByWarehouseCode(
			FindWarehouseAddressByWarehouseCodeRequest request) {
		try {
			String url = baseUrl + "/api/warehouse-address/code";
			ObjectMapper objectMapper = new ObjectMapper();
			logger.info(">>>>TmsClientWarehouseAddress>findWarehouseAddressByWarehouseCode url:{}", url);
			String json = objectMapper.writeValueAsString(request);
			String response = HttpClientUtil.doPost(url, json);
			if (!StringUtils.isBlank(response)) {
				return objectMapper.readValue(response, FindWarehouseAddressByWarehouseCodeResponse.class);
			} else {
				return FindWarehouseAddressByWarehouseCodeResponse.failed("response is blank");
			}
		} catch (JsonParseException e) {
			return FindWarehouseAddressByWarehouseCodeResponse.failed(e.getLocalizedMessage());
		} catch (JsonMappingException e) {
			return FindWarehouseAddressByWarehouseCodeResponse.failed(e.getLocalizedMessage());
		} catch (Exception e) {
			return FindWarehouseAddressByWarehouseCodeResponse.failed(e.getLocalizedMessage());

		}
	}

	public FindOrderIdResponse findOrderPrefix(FindOrderPrefixRequest request) {
		try {
			String url = baseUrl + "/api/order-prefix";
			ObjectMapper objectMapper = new ObjectMapper();
			logger.info(">>>>TmsClient>findOrderPrefix url:{}", url);
			String json = objectMapper.writeValueAsString(request);
			String response = HttpClientUtil.doPost(url, json);
			if (!StringUtils.isBlank(response)) {
				return objectMapper.readValue(response, FindOrderIdResponse.class);
			} else {
				return FindOrderIdResponse.failed("response is blank");
			}
		} catch (JsonParseException e) {
			return FindOrderIdResponse.failed(e.getLocalizedMessage());
		} catch (JsonMappingException e) {
			return FindOrderIdResponse.failed(e.getLocalizedMessage());
		} catch (Exception e) {
			return FindOrderIdResponse.failed(e.getLocalizedMessage());
		}
	}

	public FindServiceProvideResponse findServiceProvide(FindServiceProvideRequest request) {
		try {
			String url = baseUrl + "/api/provider";
			ObjectMapper objectMapper = new ObjectMapper();
			logger.info(">>>>TmsClient>findProvider url:{}", url);
			String json = objectMapper.writeValueAsString(request);
			String response = HttpClientUtil.doPost(url, json);
			if (!StringUtils.isBlank(response)) {
				return objectMapper.readValue(response, FindServiceProvideResponse.class);
			} else {
				return FindServiceProvideResponse.failed("response is blank");
			}
		} catch (JsonParseException e) {
			return FindServiceProvideResponse.failed(e.getLocalizedMessage());
		} catch (JsonMappingException e) {
			return FindServiceProvideResponse.failed(e.getLocalizedMessage());
		} catch (Exception e) {
			return FindServiceProvideResponse.failed(e.getLocalizedMessage());
		}
	}

	public FindWarehouseByIdResponse findWarehouseByIds(FindWarehouseByIdRequest request) {
		try {
			String url = baseUrl + "/api/warehouse/ids";
			ObjectMapper objectMapper = new ObjectMapper();
			logger.info(">>>>TmsClientWarehouseAddress>findWarehouseById url:{}", url);
			String json = objectMapper.writeValueAsString(request);
			String response = HttpClientUtil.doPost(url, json);
			if (!StringUtils.isBlank(response)) {
				return objectMapper.readValue(response, FindWarehouseByIdResponse.class);
			} else {
				return FindWarehouseByIdResponse.failed("response is blank");
			}
		} catch (JsonParseException e) {
			return FindWarehouseByIdResponse.failed(e.getLocalizedMessage());
		} catch (JsonMappingException e) {
			return FindWarehouseByIdResponse.failed(e.getLocalizedMessage());
		} catch (Exception e) {
			return FindWarehouseByIdResponse.failed(e.getLocalizedMessage());

		}
	}

	public FindAllFbaWarehouseResponse findAllFbaWarehouse(FindAllFbaWarehouseRequest request) {
		try {
			String url = baseUrl + "/api/fba-warehouse";
			ObjectMapper objectMapper = new ObjectMapper();
			logger.info(">>>>TmsClient findAllFbaWarehouse>FindAllFbaWarehouseRequest url:{}", url);
			String json = objectMapper.writeValueAsString(request);
			String response = HttpClientUtil.doPost(url, json);
			if (!StringUtils.isBlank(response)) {
				return objectMapper.readValue(response, FindAllFbaWarehouseResponse.class);
			} else {
				return FindAllFbaWarehouseResponse.failed("response is blank");
			}
		} catch (Exception e) {
			return FindAllFbaWarehouseResponse.failed(e.getLocalizedMessage());

		}
	}

	public GetCartonLabelPdfResponse getCartonLabelPdf(GetCartonLabelPdfRequest request) {
		try {
			String url = baseUrl + "/api/cartonlabel";
			ObjectMapper objectMapper = new ObjectMapper();
			logger.info(">>>>TmsClient getCartonLabelPdf>GetCartonLabelPdfRequest url:{}", url);
			String json = objectMapper.writeValueAsString(request);
			String response = HttpClientUtil.doPost(url, json);
			if (!StringUtils.isBlank(response)) {
				return objectMapper.readValue(response, GetCartonLabelPdfResponse.class);
			} else {
				return GetCartonLabelPdfResponse.failed("response is blank");
			}
		} catch (Exception e) {
			return GetCartonLabelPdfResponse.failed(e.getLocalizedMessage());

		}
	}
}
