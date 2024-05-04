package jpabookPr.jpashopPr.service;

import jpabookPr.jpashopPr.domain.item.Item;
import jpabookPr.jpashopPr.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional  //등록하기 기능이므로 readOnly를 false로 바꿔야한다.
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
    //TDD는 전 강의와 반복되는 내용이므로 생략

}
