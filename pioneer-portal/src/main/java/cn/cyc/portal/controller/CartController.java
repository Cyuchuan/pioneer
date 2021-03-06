package cn.cyc.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.cyc.common.util.StatusResult;
import cn.cyc.portal.pojo.CartItem;
import cn.cyc.portal.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {
	@Autowired
	private CartService cartService;

	@RequestMapping("/add/{itemId}")
	public String addCartItem(@PathVariable Long itemId, @RequestParam(defaultValue = "1") Integer num,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		StatusResult result = cartService.addCartItem(itemId, num, request, response);
		return "redirect:/cart/cart.html";
	}

	@RequestMapping("/cart")
	public String showCart(HttpServletRequest request, HttpServletResponse response, Model model) {
		List<CartItem> list = cartService.getCartItemList(request, response);
		model.addAttribute("cartList", list);
		return "cart";
	}

	@RequestMapping("/update/{itemId}/{num}")
	public String updateCartItem(@PathVariable long itemId, @PathVariable int num, HttpServletRequest request,
			HttpServletResponse response) {
		cartService.updateCartItem(itemId, num, request, response);
		return "redirect:/cart/cart.html";
	}

	@RequestMapping("/delete/{itemId}")
	public String deleteCartItem(@PathVariable long itemId, HttpServletRequest request, HttpServletResponse response) {
		cartService.deleteCartItem(itemId, request, response);
		return "redirect:/cart/cart.html";
	}

}
