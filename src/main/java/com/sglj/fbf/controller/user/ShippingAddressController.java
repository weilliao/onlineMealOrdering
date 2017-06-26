package com.sglj.fbf.controller.user;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sglj.fbf.base.ResponseEntity;
import com.sglj.fbf.constant.BusiConstant;
import com.sglj.fbf.entity.user.ShippingAddress;
import com.sglj.fbf.entity.user.User;
import com.sglj.fbf.service.userservice.ShippingAddressService;
import com.sglj.fbf.service.userservice.UserService;

/**
 * @author wei
 * @date 创建时间：2017年2月14日 下午4:08:45
 * @version 1.0
 */
@RestController
@RequestMapping("/address")
public class ShippingAddressController {
	
	@Resource
	private UserService userService;
	@Resource
	private ShippingAddressService shippingService;
	
	/**
	 * 添加收货地址
	 */
	@RequestMapping(value = "/addAdderss", produces = "application/json")
	private ResponseEntity addAdderss(ShippingAddress address, String id,
			HttpServletRequest request, HttpServletResponse response) {
		ResponseEntity rst = new ResponseEntity();
		try {
			User user = userService.getUser(id);
			address.setUser(user);
			shippingService.saveAddress(address);
			System.out.println(address.toString());
			rst.setStatus(BusiConstant.STATUS_SUCCESS);
		} catch (Exception e) {
			rst.setStatus(BusiConstant.STATUS_ERROR);
			rst.setMsg(e.getMessage());
			return rst;
		}
		return rst;
	}
	
	
	
	/**
	 * 获取地址列表
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getAddressList", method = RequestMethod.GET, produces = "application/json")
	private ResponseEntity getAddressList(String id,HttpServletRequest request,HttpServletResponse response) {
		ResponseEntity rst = new ResponseEntity();
		try {
			User user = userService.getUser(id);
			List<ShippingAddress> addressList = shippingService.queryShippingAddress(user);
			rst.setData(addressList);
			rst.setStatus(BusiConstant.STATUS_SUCCESS);
		} catch (Exception e) {
			rst.setStatus(BusiConstant.STATUS_ERROR);
			rst.setMsg(e.getMessage());
			return rst;
		}
		return rst;
	}

	/**
	 * 删除收货地址
	 */
	@RequestMapping(value = "/delAdderss", produces = "application/json")
	private ResponseEntity delAdderss(String id,HttpServletRequest request, HttpServletResponse response) {
		ResponseEntity rst = new ResponseEntity();
		try {
			shippingService.delAddress(id);
			rst.setStatus(BusiConstant.STATUS_SUCCESS);
		} catch (Exception e) {
			rst.setStatus(BusiConstant.STATUS_ERROR);
			rst.setMsg(e.getMessage());
			return rst;
		}
		return rst;
	}
	
	
	/**
	 * 更新收货地址
	 */
	@RequestMapping(value = "/updatAdderss", produces = "application/json")
	private ResponseEntity updateAdderss(ShippingAddress address,
			HttpServletRequest request, HttpServletResponse response) {
		ResponseEntity rst = new ResponseEntity();
		try {
			System.out.println(address.toString());
			ShippingAddress address2 = shippingService.getAddress(address.getId());
			if (StringUtils.isNotBlank(address.getProvince())) {
				address2.setProvince(address.getProvince());
			}
			if (StringUtils.isNotBlank(address.getCity())) {
				address2.setCity(address.getCity());
			}
			if (StringUtils.isNotBlank(address.getAddress())) {
				address2.setAddress(address.getAddress());
			}
			if (StringUtils.isNotBlank(address.getPostcode())) {
				address2.setPostcode(address.getPostcode());
			}
			if (StringUtils.isNotBlank(address.getName())) {
				address2.setName(address.getName());
			}
			if (StringUtils.isNotBlank(address.getPhone())) {
				address2.setPhone(address.getPhone());
			}
			shippingService.updateAddress(address2);
			rst.setStatus(BusiConstant.STATUS_SUCCESS);
		} catch (Exception e) {
			rst.setStatus(BusiConstant.STATUS_ERROR);
			rst.setMsg(e.getMessage());
			return rst;
		}
		return rst;
	}

}
