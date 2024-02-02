package com.example.jpanext.shop.service;

import com.example.jpanext.shop.entity.OrderItem;
import com.example.jpanext.shop.entity.Customer;
import com.example.jpanext.shop.entity.Item;
import com.example.jpanext.shop.entity.Order;
import com.example.jpanext.shop.repo.CustomerRepository;
import com.example.jpanext.shop.repo.ItemRepository;
import com.example.jpanext.shop.repo.OrderItemRepository;
import com.example.jpanext.shop.repo.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShopService {
    private final CustomerRepository customerRepository;
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    // 나를 호출한 메서드가 트랜잭션이면 그 일부로 실행되고,
    // 아니라면 내가 직접 트랜잭션을 만든다.
    @Transactional(propagation = Propagation.REQUIRED)
    public void createOrder() {
        // 고객 정보 회수 : 1번 구매자 가져오기
        Customer customer = customerRepository
                .findById(1L).orElseThrow();
        // 고객의 새 주문 생성 : 새로운 주문을 만들어서 1번 구매자 정볼 넣어주기
        Order newOrder = orderRepository.save(Order.builder()
                .customer(customer)
                .build());
        // 구매할 물품 회수 : 2번 아이템을 찾을 건데 없으면 던질 거임
        Item item = itemRepository
                .findById(2L)
                .orElseThrow();
        // 주문정보에 물품 추가
        orderItemRepository.save(OrderItem.builder()
                .order(newOrder) // 아까 그 새로운 주문
                .item(item) // 아까 그 찾은 2번 아이템
                .count(10) // 10개 구매하겠다.
                .build());
        if (!(item.getStock() < 10)) { // 재고가 10개 이상이면 성공
            item.setStock(item.getStock() - 10);
            itemRepository.save(item);
        } else {
            throw new IllegalStateException();
        }
    }
}



