package com.sparta.springcore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    //스프링 IoC 컨테이너에서 빈을 수동으로 가져오는 방법
//    @Component
//    public class ProductService {
//        private final ProductRepository productRepository;
//
//        @Autowired
//        public ProductService(ApplicationContext context) {
//            // 1.'빈' 이름으로 가져오기
//            ProductRepository productRepository = (ProductRepository) context.getBean("productRepository");
//            // 2.'빈' 클래스 형식으로 가져오기
//            // ProductRepository productRepository = context.getBean(ProductRepository.class);
//            this.productRepository = productRepository;
//        }


//    }

    public Product createProduct(ProductRequestDto requestDto){
// 요청받은 DTO 로 DB에 저장할 객체 만들기
        Product product = new Product(requestDto);

        productRepository.save(product);

        return product;
    }

    public Product updateProduct(Long id, ProductMypriceRequestDto requestDto)  {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("아이디가 존재하지 않습니다."));



        int myprice = requestDto.getMyprice();
        product.setMyprice(myprice);
        productRepository.save(product);

        return product;
    }

    public List<Product> getProducts(){
        List<Product> products = productRepository.findAll();

        return products;
    }
}