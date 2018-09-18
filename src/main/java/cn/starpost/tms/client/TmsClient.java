/**
 * STARPOST CONFIDENTIAL
 * _____________________
 * <p>
 * [2014] - [2018] StarPost Supply Chain Management Co. (Shenzhen) Ltd.
 * All Rights Reserved.
 * <p>
 * NOTICE: All information contained herein is, and remains the property of
 * StarPost Supply Chain Management Co. (Shenzhen) Ltd. and its suppliers, if
 * any. The intellectual and technical concepts contained herein are proprietary
 * to StarPost Supply Chain Management Co. (Shenzhen) Ltd. and its suppliers and
 * may be covered by China and Foreign Patents, patents in process, and are
 * protected by trade secret or copyright law. Dissemination of this information
 * or reproduction of this material is strictly forbidden unless prior written
 * permission is obtained from StarPost Supply Chain Management Co. (Shenzhen)
 * Ltd.
 */
package cn.starpost.tms.client;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.starpost.tms.client.utils.HttpClientUtil;
import cn.starpost.tms.client.value.api.pp.FindOrderNumberReponse;
import cn.starpost.tms.client.value.api.pp.FindOrderNumberRequest;
import cn.starpost.tms.client.value.api.pp.FindParcelLabelReponse;
import cn.starpost.tms.client.value.TmsClientResponse;
import cn.starpost.tms.client.value.api.pp.EditExpressCodeRequest;
import cn.starpost.tms.client.value.api.pp.FindExpressCodeResponse;
import cn.starpost.tms.client.value.channel.FindChannelRequest;
import cn.starpost.tms.client.value.channel.FindChannelResponse;
import cn.starpost.tms.client.value.charge.FindChargeByOrderIdsRequest;
import cn.starpost.tms.client.value.charge.FindChargeByOrderIdsResponse;
import cn.starpost.tms.client.value.charge.GetOrderChargeBillResponse;
import cn.starpost.tms.client.value.charge.GetOrderTransactionDetailRequest;
import cn.starpost.tms.client.value.charge.GetOrderTransactionDetailResponse;
import cn.starpost.tms.client.value.charge.QueryOrderChargeRequest;
import cn.starpost.tms.client.value.charge.QueryOrderChargeResponse;
import cn.starpost.tms.client.value.customer.FindCustomerRequest;
import cn.starpost.tms.client.value.customer.FindCustomerResponse;
import cn.starpost.tms.client.value.lastmilelabel.GetLastMileLabelRequest;
import cn.starpost.tms.client.value.lastmilelabel.GetLastMileLabelResponse;
import cn.starpost.tms.client.value.model.GetOrderNumberAndChannelCodeRequest;
import cn.starpost.tms.client.value.model.GetOrderNumberAndChannelCodeResponse;
import cn.starpost.tms.client.value.order.BatchOrderConfirmDeliveryRequest;
import cn.starpost.tms.client.value.order.BatchOrderConfirmDeliveryResponse;
import cn.starpost.tms.client.value.order.OrderCancelRequest;
import cn.starpost.tms.client.value.order.OrderCancelResponse;
import cn.starpost.tms.client.value.order.OrderConfirmDeliveryRequest;
import cn.starpost.tms.client.value.order.OrderConfirmDeliveryResponse;
import cn.starpost.tms.client.value.order.OrderStatusRequest;
import cn.starpost.tms.client.value.order.OrderStatusResponse;
import cn.starpost.tms.client.value.pdf.GetCartonLabelPdfRequest;
import cn.starpost.tms.client.value.pdf.GetCartonLabelPdfResponse;
import cn.starpost.tms.client.value.provider.FindServiceProvideRequest;
import cn.starpost.tms.client.value.provider.FindServiceProvideResponse;
import cn.starpost.tms.client.value.sort.FindSortRequest;
import cn.starpost.tms.client.value.sort.FindSortResponse;
import cn.starpost.tms.client.value.staff.FindStaffRequest;
import cn.starpost.tms.client.value.staff.FindStaffResponse;
import cn.starpost.tms.client.value.transaction.ExportBillRequest;
import cn.starpost.tms.client.value.transaction.GetOrderTransactionRequest;
import cn.starpost.tms.client.value.transaction.GetOrderTransactionResponse;
import cn.starpost.tms.client.value.warehouse.FindAllFbaWarehouseRequest;
import cn.starpost.tms.client.value.warehouse.FindAllFbaWarehouseResponse;
import cn.starpost.tms.client.value.warehouse.FindOrderIdResponse;
import cn.starpost.tms.client.value.warehouse.FindOrderPrefixRequest;
import cn.starpost.tms.client.value.warehouse.FindWarehouseAddressByWarehouseCodeRequest;
import cn.starpost.tms.client.value.warehouse.FindWarehouseAddressByWarehouseCodeResponse;
import cn.starpost.tms.client.value.warehouse.FindWarehouseAddressRequest;
import cn.starpost.tms.client.value.warehouse.FindWarehouseAddressResponse;
import cn.starpost.tms.client.value.warehouse.FindWarehouseByIdRequest;
import cn.starpost.tms.client.value.warehouse.FindWarehouseByIdResponse;
import cn.starpost.tms.client.value.warehouse.FindWarehouseRequest;
import cn.starpost.tms.client.value.warehouse.FindWarehouseResponse;
import cn.starpost.tms.order.values.CreateParcelOrderRequest;
import cn.starpost.tms.order.values.CreateParcelOrderResponse;

/**
 * @author dongjianpeng
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

	public FindChargeByOrderIdsResponse findChargeByOrderIds(FindChargeByOrderIdsRequest request) {
		try {
			String url = baseUrl + "/api/charge";
			ObjectMapper objectMapper = new ObjectMapper();
			logger.info(">>>>TmsClient findChargeByOrderIds>FindChargeByOrderIdsRequest url:{}", url);
			String json = objectMapper.writeValueAsString(request);
			String response = HttpClientUtil.doPost(url, json);
			if (!StringUtils.isBlank(response)) {
				return objectMapper.readValue(response, FindChargeByOrderIdsResponse.class);
			} else {
				return FindChargeByOrderIdsResponse.failed("response is blank");
			}
		} catch (Exception e) {
			return FindChargeByOrderIdsResponse.failed(e.getLocalizedMessage());
		}
	}

	/**
	 * 获取账单Excel
	 */
	public GetOrderChargeBillResponse getOrderChargeBillByTxIds(ExportBillRequest request) {
		try {
			String url = baseUrl + "/api/order-charge-bill";
			ObjectMapper objectMapper = new ObjectMapper();
			logger.info(">>>>TmsClient GetOrderChargeBillByTxIds>GetOrderChargeBillRequest url:{}", url);
			String json = objectMapper.writeValueAsString(request);
			String response = HttpClientUtil.doPost(url, json);
			if (!StringUtils.isBlank(response)) {
				return objectMapper.readValue(response, GetOrderChargeBillResponse.class);
			} else {
				return GetOrderChargeBillResponse.failed("response is blank");
			}
		} catch (Exception e) {
			return GetOrderChargeBillResponse.failed(e.getLocalizedMessage());
		}
	}

	/**
	 * 获取分拣机端口
	 */
	public FindSortResponse findSort(FindSortRequest request) {
		try {
			String url = baseUrl + "/api/findSort";
			ObjectMapper objectMapper = new ObjectMapper();
			logger.info(">>>>TmsClient findSort url:{}", url);
			String json = objectMapper.writeValueAsString(request);
			String response = HttpClientUtil.doPost(url, json);
			if (!StringUtils.isBlank(response)) {
				return objectMapper.readValue(response, FindSortResponse.class);
			} else {
				return new FindSortResponse(0, false, "system error");
			}
		} catch (Exception e) {
			return new FindSortResponse(0, false, "system error");
		}
	}

	public GetOrderNumberAndChannelCodeResponse getOrderNumberAndChannelCode(
			GetOrderNumberAndChannelCodeRequest request) {
		try {
			String url = baseUrl + "/api/getOrderNumberAndChannelCode";
			ObjectMapper objectMapper = new ObjectMapper();
			logger.info(">>>>TmsClient getOrderNumberAndChannelCode url:{}", url);
			String json = objectMapper.writeValueAsString(request);
			String response = HttpClientUtil.doPost(url, json);
			if (!StringUtils.isBlank(response)) {
				return objectMapper.readValue(response, GetOrderNumberAndChannelCodeResponse.class);
			} else {
				return new GetOrderNumberAndChannelCodeResponse(false, null, null, null, 0, 0, "system error");
			}
		} catch (Exception e) {
			return new GetOrderNumberAndChannelCodeResponse(false, null, null, null, 0, 0, "system error");
		}
	}

	/**
	 * 导出交易流水服务扣款
	 *
	 * @param request
	 * @return
	 */
	public GetOrderTransactionResponse getOrderTransactionByTxId(GetOrderTransactionRequest request) {
		try {
			String url = baseUrl + "/api/order-transaction";
			ObjectMapper objectMapper = new ObjectMapper();
			logger.info(">>>>TmsClient getOrderTransactionByTxId url:{}", url);
			String json = objectMapper.writeValueAsString(request);
			String response = HttpClientUtil.doPost(url, json);
			if (!StringUtils.isBlank(response)) {
				return objectMapper.readValue(response, GetOrderTransactionResponse.class);
			} else {
				return GetOrderTransactionResponse.failed("response is blank");
			}
		} catch (Exception e) {
			return GetOrderTransactionResponse.failed(e.getMessage());
		}
	}

	public QueryOrderChargeResponse getOrderCharge(QueryOrderChargeRequest request) {
		try {
			String url = baseUrl + "/api/order-charge-list";
			ObjectMapper objectMapper = new ObjectMapper();
			logger.info(">>>>TmsClient getOrderCharge url:{}", url);
			String json = objectMapper.writeValueAsString(request);
			String response = HttpClientUtil.doPost(url, json);
			logger.info(">>>>TmsClient getOrderCharge response:{}", response);
			if (!StringUtils.isBlank(response)) {
				return objectMapper.readValue(response, QueryOrderChargeResponse.class);
			} else {
				return QueryOrderChargeResponse.failed("response is blank");
			}
		} catch (Exception e) {
			return QueryOrderChargeResponse.failed(e.getMessage());
		}
	}

	public QueryOrderChargeResponse getAllOrderCharge(QueryOrderChargeRequest request) {
		try {
			String url = baseUrl + "/api/order-charge-all-list";
			ObjectMapper objectMapper = new ObjectMapper();
			logger.info(">>>>TmsClient getOrderCharge url:{}", url);
			String json = objectMapper.writeValueAsString(request);
			String response = HttpClientUtil.doPost(url, json);
			logger.info(">>>>TmsClient getOrderCharge response:{}", response);
			if (!StringUtils.isBlank(response)) {
				return objectMapper.readValue(response, QueryOrderChargeResponse.class);
			} else {
				return QueryOrderChargeResponse.failed("response is blank");
			}
		} catch (Exception e) {
			return QueryOrderChargeResponse.failed(e.getMessage());

		}
	}

	/**
	 * 客户确认发货
	 *
	 * @param request
	 * @return
	 * @author renxiangyang
	 */
	public OrderConfirmDeliveryResponse confirmDelivery(OrderConfirmDeliveryRequest request) {
		try {
			String url = baseUrl + "/api/order/confirm-delivery";
			ObjectMapper objectMapper = new ObjectMapper();
			logger.info(">>>>TmsClient confirmDelivery url:{}", url);
			String json = objectMapper.writeValueAsString(request);
			String response = HttpClientUtil.doPost(url, json);
			logger.info(">>>>TmsClient confirmDelivery response:{}", response);
			if (!StringUtils.isBlank(response)) {
				return objectMapper.readValue(response, OrderConfirmDeliveryResponse.class);
			} else {
				return OrderConfirmDeliveryResponse.failed("response is blank", request.getOrderId());
			}
		} catch (Exception e) {
			logger.error("TmsClient confirmDelivery error :", e);
			return OrderConfirmDeliveryResponse.failed(e.getMessage(), request.getOrderId());
		}
	}

	/**
	 * 订单取消
	 *
	 * @param request
	 * @return
	 * @author renxiangyang
	 */
	public OrderCancelResponse cancelOrder(OrderCancelRequest request) {
		try {
			String url = baseUrl + "/api/order-cancel";
			ObjectMapper objectMapper = new ObjectMapper();
			logger.info(">>>>TmsClient cancelOrder url:{}", url);
			String json = objectMapper.writeValueAsString(request);
			String response = HttpClientUtil.doPost(url, json);
			logger.info(">>>>TmsClient cancelOrder response:{}", response);
			if (!StringUtils.isBlank(response)) {
				return objectMapper.readValue(response, OrderCancelResponse.class);
			} else {
				return OrderCancelResponse.failed("response is blank", request.getOrderId(), request.getRefNumber());
			}
		} catch (Exception e) {
			logger.error("TmsClient confirmDelivery error :", e);
			return OrderCancelResponse.failed(e.getMessage(), request.getOrderId(), request.getRefNumber());
		}
	}

	public GetLastMileLabelResponse getLastMileLabel(GetLastMileLabelRequest request) {
		try {
			String url = baseUrl + "/api/download-labelContent";
			ObjectMapper objectMapper = new ObjectMapper();
			logger.info(">>>>TmsClient getLastMileLabel url:{}", url);
			String json = objectMapper.writeValueAsString(request);
			String response = HttpClientUtil.doPost(url, json);
			logger.info(">>>>TmsClient getLastMileLabel response:{}", response);
			if (!StringUtils.isBlank(response)) {
				return objectMapper.readValue(response, GetLastMileLabelResponse.class);
			} else {
				return GetLastMileLabelResponse.failed("response is blank");
			}
		} catch (Exception e) {
			logger.error("TmsClient getLastMileLabel error :", e);
			return GetLastMileLabelResponse.failed(e.getMessage());
		}
	}

	/**
	 * 查询订单状态
	 *
	 * @param request
	 * @return
	 */
	public OrderStatusResponse getOrderStatus(OrderStatusRequest request) {
		try {
			String url = baseUrl + "/api/order-status";
			ObjectMapper objectMapper = new ObjectMapper();
			logger.info(">>>>TmsClient getOrderStatus url:{}", url);
			String json = objectMapper.writeValueAsString(request);
			String response = HttpClientUtil.doPost(url, json);
			logger.info(">>>>TmsClient getOrderStatus response:{}", response);
			if (!StringUtils.isBlank(response)) {
				return objectMapper.readValue(response, OrderStatusResponse.class);
			} else {
				return OrderStatusResponse.failed("response is blank");
			}
		} catch (Exception e) {
			logger.error("TmsClient getOrderStatus error :", e);
			return OrderStatusResponse.failed(e.getMessage());
		}
	}

	public FindStaffResponse getSalesman(FindStaffRequest request) {
		try {
			String url = baseUrl + "/api/query/salesman";
			ObjectMapper objectMapper = new ObjectMapper();
			logger.info(">>>>TmsClient getSalesman url:{}", url);
			String json = objectMapper.writeValueAsString(request);
			String response = HttpClientUtil.doPost(url, json);
			logger.info(">>>>TmsClient getSalesman response:{}", response);
			if (!StringUtils.isBlank(response)) {
				return objectMapper.readValue(response, FindStaffResponse.class);
			} else {
				return FindStaffResponse.faile("response is blank");
			}
		} catch (Exception e) {
			logger.error("TmsClient getSalesman error :", e);
			return FindStaffResponse.faile(e.getMessage());
		}
	}

	public BatchOrderConfirmDeliveryResponse batchConfirmOrder(BatchOrderConfirmDeliveryRequest request) {
		try {
			String url = baseUrl + "/api/order/batch-confirm-delivery";
			ObjectMapper objectMapper = new ObjectMapper();
			logger.info(">>>>TmsClient batchConfirmOrder url:{}", url);
			String json = objectMapper.writeValueAsString(request);
			String response = HttpClientUtil.doPost(url, json);
			logger.info(">>>>TmsClient batchConfirmOrder response:{}", response);
			if (StringUtils.isNotBlank(response)) {
				return objectMapper.readValue(response, new TypeReference<BatchOrderConfirmDeliveryResponse>() {
				});
			} else {
				return new BatchOrderConfirmDeliveryResponse(false, "response is blank", null);
			}
		} catch (Exception e) {
			logger.error("TmsClient batchConfirmOrder error :", e);
			return new BatchOrderConfirmDeliveryResponse(false, e.getMessage(), null);
		}
	}

	public GetOrderTransactionDetailResponse getOrderTransactionDetail(GetOrderTransactionDetailRequest request) {
		try {
			String url = baseUrl + "/api/order-transaction-detail";
			ObjectMapper objectMapper = new ObjectMapper();
			logger.info(">>>>TmsClient getOrderTransactionDetail url:{}", url);
			String json = objectMapper.writeValueAsString(request);
			String response = HttpClientUtil.doPost(url, json);
			logger.info(">>>>TmsClient getOrderTransactionDetail response:{}", response);
			if (!StringUtils.isBlank(response)) {
				return objectMapper.readValue(response, GetOrderTransactionDetailResponse.class);
			} else {
				return GetOrderTransactionDetailResponse.failed("response is blank");
			}
		} catch (Exception e) {
			logger.error("TmsClient getOrderTransactionDetail error :", e);
			return GetOrderTransactionDetailResponse.failed(e.getMessage());
		}
	}

	public FindParcelLabelReponse findParcelLabel(List<String> orderIds) {
		try {
			String url = baseUrl + "/api/pp/find-label";
			ObjectMapper objectMapper = new ObjectMapper();
			logger.info(">>>>TmsClient findPacketPrintLabel url:{}", url);
			String json = objectMapper.writeValueAsString(orderIds);
			String response = HttpClientUtil.doPost(url, json);
			logger.info(">>>>TmsClient findPacketLabel response:{}", response);
			if (StringUtils.isNotBlank(response)) {
				return objectMapper.readValue(response, new TypeReference<FindParcelLabelReponse>() {
				});
			} else {
				return FindParcelLabelReponse.failed("response is blank");
			}
		} catch (Exception e) {
			logger.error("TmsClient findPacketLabel error :", e);
			return FindParcelLabelReponse.failed(e.getMessage());
		}
	}

	public FindOrderNumberReponse findOrderNumber(FindOrderNumberRequest request) {
		try {
			String url = baseUrl + "/api/pp/find-orderNumbers";
			ObjectMapper objectMapper = new ObjectMapper();
			logger.info(">>>>TmsClient findOrderNumber url:{}", url);
			String json = objectMapper.writeValueAsString(request);
			String response = HttpClientUtil.doPost(url, json);
			logger.info(">>>>TmsClient findOrderNumber response:{}", response);
			if (StringUtils.isNotBlank(response)) {
				return objectMapper.readValue(response, new TypeReference<FindOrderNumberReponse>() {
				});
			} else {
				return FindOrderNumberReponse.failed("response is blank");
			}
		} catch (Exception e) {
			logger.error("TmsClient batchConfirmOrder error :", e);
			return FindOrderNumberReponse.failed(e.getMessage());
		}
	}

	public CreateParcelOrderResponse createParcelOrder(CreateParcelOrderRequest request) {
		try {
			String url = baseUrl + "/api/pp/order";
			ObjectMapper objectMapper = new ObjectMapper();
			logger.info(">>>>TmsClient createParcelOrder url:{}", url);
			String json = objectMapper.writeValueAsString(request);
			String response = HttpClientUtil.doPost(url, json);
			logger.info(">>>>TmsClient createParcelOrder response:{}", response);
			if (StringUtils.isNotBlank(response)) {
				return objectMapper.readValue(response, new TypeReference<CreateParcelOrderResponse>() {
				});
			} else {
				return new CreateParcelOrderResponse(null, null, false, "response is blank");
			}
		} catch (Exception e) {
			logger.error("TmsClient createParcelOrder error :", e);
			return new CreateParcelOrderResponse(null, null, false, e.getMessage());
		}
	}

	/**
	 * 获取客户对象
	 * 
	 * @param request
	 * @return
	 */
	public FindCustomerResponse getOrgCustomer(FindCustomerRequest request) {
		try {
			String url = baseUrl + "/api/query/orgCustomer";
			ObjectMapper objectMapper = new ObjectMapper();
			logger.info(">>>>TmsClient getOrgCustomer url:{}", url);
			String json = objectMapper.writeValueAsString(request);
			String response = HttpClientUtil.doPost(url, json);
			logger.info(">>>>TmsClient getOrgCustomer response:{}", response);
			if (!StringUtils.isBlank(response)) {
				return objectMapper.readValue(response, FindCustomerResponse.class);
			} else {
				return FindCustomerResponse.faile("response is blank");
			}
		} catch (Exception e) {
			logger.error("TmsClient getOrgCustomer error :", e);
			return FindCustomerResponse.faile(e.getMessage());
		}
	}

	public FindExpressCodeResponse findExpressCode(List<String> trackingNumbers) {
		try {
			String url = baseUrl + "/api/pp/find-express-code";
			ObjectMapper objectMapper = new ObjectMapper();
			logger.info(">>>>TmsClient findExpressCode url:{}", url);
			String json = objectMapper.writeValueAsString(trackingNumbers);
			String response = HttpClientUtil.doPost(url, json);
			logger.info(">>>>TmsClient findExpressCode response:{}", response);
			if (!StringUtils.isBlank(response)) {
				return objectMapper.readValue(response, FindExpressCodeResponse.class);
			} else {
				return FindExpressCodeResponse.failed("response is blank");
			}
		} catch (Exception e) {
			logger.error("TmsClient findExpressCode error :", e);
			return FindExpressCodeResponse.failed(e.getMessage());
		}
	}

	public TmsClientResponse editExpressCode(EditExpressCodeRequest request) {
		try {
			String url = baseUrl + "/api/pp/edit-express-code";
			ObjectMapper objectMapper = new ObjectMapper();
			logger.info(">>>>TmsClient editExpressCode url:{}", url);
			String json = objectMapper.writeValueAsString(request);
			String response = HttpClientUtil.doPost(url, json);
			logger.info(">>>>TmsClient editExpressCode response:{}", response);
			if (!StringUtils.isBlank(response)) {
				return objectMapper.readValue(response, TmsClientResponse.class);
			} else {
				return TmsClientResponse.connectedFailed("response is blank");
			}
		} catch (Exception e) {
			logger.error("TmsClient editExpressCode error :", e);
			return TmsClientResponse.connectedFailed(e.getMessage());
		}
	}
}
