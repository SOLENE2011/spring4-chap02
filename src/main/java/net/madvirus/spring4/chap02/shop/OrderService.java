package net.madvirus.spring4.chap02.shop;

import javax.inject.Inject;

import net.madvirus.spring4.chap02.erp.ErpClient;
import net.madvirus.spring4.chap02.erp.ErpClientFactory;
import net.madvirus.spring4.chap02.erp.ErpOrderData;
import net.madvirus.spring4.chap02.search.SearchClientFactory;
import net.madvirus.spring4.chap02.search.SearchDocument;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("orderSvc")
public class OrderService {

	private ErpClientFactory erpClientFactory;

	private SearchClientFactory searchClientFactory;


	@Inject
	// 의존 자동 설정
	// 생성자, 메서드, 필드 세 곳에 적용가능 
	public void setErpClientFactory(ErpClientFactory erpClientFactory) {
		this.erpClientFactory = erpClientFactory;
	}

	
	// 생성자, 메서드, 필드 세곳에 적용가능
	// 스프링은 @Autowired 애노테이션을 발견하면 이 
	// @Autowired에 해당하는 스프링 bean 객체를 찾아서 설정.
	// @Autowired(required=false) 이면
	// 일치하는 스프링 빈이 존재하지 않더라도 exception 발생시키지 않는다.
	// 의존 자동 설정
	@Autowired
	
	// 프로퍼티 설정 
	public void setSearchClientFactory(@Qualifier("order") SearchClientFactory searchClientFactory) {
		// bean 설정에서 class가 같은 경우를 대비하여 한정자. @Qualifier("order")
		// 타입 기준
		this.searchClientFactory = searchClientFactory;
	}

	public void order(OrderInfo oi) {
		sendOrderInfoToErp(oi);
		addOrderInfoToSearch(oi);
	}

	private void sendOrderInfoToErp(OrderInfo oi) {
		ErpClient erpClient = erpClientFactory.create();
		erpClient.connect();
		erpClient.sendPurchaseInfo(toErpOrderData(oi));
		erpClient.close();
	}

	private ErpOrderData toErpOrderData(OrderInfo oi) {
		return new ErpOrderData();
	}

	private void addOrderInfoToSearch(OrderInfo oi) {
		searchClientFactory.create().addDocument(toSearchDocument(oi));
	}

	private SearchDocument toSearchDocument(OrderInfo oi) {
		return new SearchDocument();
	}
}
