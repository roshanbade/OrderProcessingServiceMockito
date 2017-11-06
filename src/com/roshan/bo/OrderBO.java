package com.roshan.bo;

import com.roshan.bo.exception.BOException;
import com.roshan.dto.Order;

public interface OrderBO {

	boolean placeOrder(Order order) throws BOException;
	boolean cancelOrder(int id) throws BOException;
	boolean deleteOrder(int id) throws BOException;
}
