package net.madvirus.spring4.chap02.shop;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import net.madvirus.spring4.chap02.search.SearchClientFactory;
import net.madvirus.spring4.chap02.search.SearchDocument;

@Component
public class ProductService {

	private SearchClientFactory searchClientFactory;

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
