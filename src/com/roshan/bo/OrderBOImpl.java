package com.roshan.bo;

import java.sql.SQLException;

import com.roshan.bo.exception.BOException;
import com.roshan.dao.OrderDao;
import com.roshan.dto.Order;

public class OrderBOImpl implements OrderBO {

	private OrderDao orderDao;
	
	@Override
	public boolean placeOrder(Order order) throws BOException {
		try {
			int result = orderDao.create(order);
			if(result == 0) {
				return false;
			}
		} catch (SQLException e) {
			throw new BOException(e);
		}
		return true;
	}

	@Override
	public boolean cancelOrder(int id) throws BOException {
		try {
			Order order = orderDao.read(id);
			order.setStatus("cancelled");
			int result = orderDao.update(order);
			if(result == 0) {
				return false;
			}
		} catch (SQLException e) {
			throw new BOException(e);
		}
		
		return true;
	}

	@Override
	public boolean deleteOrder(int id) throws BOException {
		try {
			int result = orderDao.delete(id);
			if(result == 0) {
				return false;
			}
		} catch (SQLException e) {
			throw new BOException(e);
		}
		return true;
	}

	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

}
