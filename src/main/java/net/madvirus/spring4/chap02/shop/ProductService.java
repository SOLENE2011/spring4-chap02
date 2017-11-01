package net.madvirus.spring4.chap02.shop;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import net.madvirus.spring4.chap02.search.SearchClientFactory;
import net.madvirus.spring4.chap02.search.SearchDocument;

@Component //: 요소.부품의 뜻
// base-package 속성에 지정한 패키지 및 그 하위 패키지에 위치한 클래스 중에서
// @Component 적용된 클래스를 검색해서 스프링 bean으로 등록한다.
// 그리고 의존객체 전달을 위해선 @Autowired 와 @Resource 이용해 자동 연결  
public class ProductService {

	private SearchClientFactory searchClientFactory;

	// 스캔을 통해 빈으로 등록될 경우 자동 의존 설정이 필요함.
	@Resource(name = "productSearchClientFactory")
	// name과 같은 알맞은 bean 객체를 할당한다.
	// @Autowired와 다르게 타입이 아닌 이름을 기준으로.
	// 이름이 없으면 필드 이름이나 프로퍼티 이름을 사용해서 찾음
	
	public void setSearchClientFactory(SearchClientFactory searchClientFactory) {
		this.searchClientFactory = searchClientFactory;
	}

	public void createProduct(ProductInfo pi) {
		searchClientFactory.create().addDocument(toSearchDocument(pi));
	}

	private SearchDocument toSearchDocument(ProductInfo pi) {
		return new SearchDocument();
	}
}
