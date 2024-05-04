package jpabookPr.jpashopPr.controller;

import jpabookPr.jpashopPr.domain.Member;
import jpabookPr.jpashopPr.domain.Order;
import jpabookPr.jpashopPr.domain.item.Item;
import jpabookPr.jpashopPr.repository.OrderSearch;
import jpabookPr.jpashopPr.service.ItemService;
import jpabookPr.jpashopPr.service.MemberService;
import jpabookPr.jpashopPr.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("/order")
    public String createForm(Model model) {

        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findItems();

        model.addAttribute("members", members);
        model.addAttribute("items", items);

        return "order/orderForm";
    }

    @PostMapping("/order")
    public String order(@RequestParam("memberId") Long memberId,
                        @RequestParam("itemId") Long itemId,
                        @RequestParam("count") int count) {
        //여기서 RequestParam은 html에서 form태그에서 넘어온 submit방식을 사용
        orderService.order(memberId, itemId, count);
        return "redirect:/orders";
    }

    @GetMapping("/orders")
    public String orderList(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model) {
        List<Order> orders = orderService.findOrders(orderSearch);
        model.addAttribute("orders", orders);

        return "order/orderList";
    }

    @PostMapping("/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable("orderId") Long orderId) {
        orderService.cancelOrder((orderId));
        return "redirect:/orders";
    }
}
