package com.roshan.bo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import java.sql.SQLException;

import com.roshan.bo.exception.BOException;
import com.roshan.dao.OrderDao;
import com.roshan.dto.Order;

public class OrderBOImplTest {

	@Mock
	OrderDao orderDao;
	private OrderBOImpl bo;
	
	@Before
	public	void setup(){
		MockitoAnnotations.initMocks(this);
		bo = new OrderBOImpl();
		bo.setOrderDao(orderDao);
	}
	
	@Test
	public void placeOrder_shouldCreateOrder() throws SQLException, BOException{
		
		Order order = new Order();
		when(orderDao.create(order)).thenReturn(new Integer(1));
	
		boolean result = bo.placeOrder(order);
		assertTrue(result);
		verify(orderDao).create(order);
	}

	@Test
	public void placeOrder_shouldNotCreateOrder() throws SQLException, BOException{
		Order order = new Order();
		when(orderDao.create(order)).thenReturn(new Integer(0));
	
		boolean result = bo.placeOrder(order);
		assertFalse(result);
		verify(orderDao).create(order);
	}

	@Test(expected = BOException.class)
	public void placeOrder_shouldThrowBOException() throws SQLException, BOException{
		Order order = new Order();
		when(orderDao.create(order)).thenThrow(SQLException.class);
		boolean result = bo.placeOrder(order);
	}
}
