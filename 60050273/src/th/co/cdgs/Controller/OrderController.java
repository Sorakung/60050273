package th.co.cdgs.Controller;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import th.co.cdgs.bean.OrderDto;
@Path("order")
public class OrderController {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOrder() {
		List<OrderDto> list = new ArrayList<>();
		ResultSet rs = null;
		PreparedStatement pst = null;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/workshop","root","p@ssw0rd");
			pst = conn.prepareStatement("SELECT order_Id,"+"customer_id,"
			+"product_id,amount,order_date,order_status FROM order");
			rs = pst.executeQuery();
			OrderDto OrderDto = null;
			while(rs.next()) {
				OrderDto = new OrderDto();
				OrderDto.setOrderid(rs.getLong("order_id"));
				OrderDto.setCustomerid(rs.getLong("customer_id"));
				OrderDto.setProductid(rs.getLong("product_id"));
				OrderDto.setAmount(rs.getLong("amount"));
				OrderDto.setOrderdate(rs.getDate("order_date"));
				OrderDto.setOrderstatus(rs.getString("order_status"));
				list.add(OrderDto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(rs != null) {try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
			if(pst != null) {try {
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
			if(conn != null) {try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
		}
		return Response.ok().entity(list).build();
	}
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createOrder(OrderDto order) {
		ResultSet rs = null;
		PreparedStatement pst = null;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/workshop","root","p@ssw0rd");
			pst = conn.prepareStatement("INSERT INTO order  (customer_id,product_id ,amount,order_date,order_status)"
			+"VALUES (? ,? ,? ,?,?)");
			int index = 1;
			pst.setLong(index++, order.getCustomerid());
			pst.setLong(index++, order.getProductid());
			pst.setLong(index++, order.getAmount());
			pst.setDate(index++, (Date) order.getOrderdate());
			pst.setString(index++, order.getOrderstatus());
			pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(rs != null) {try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
			if(pst != null) {try {
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
			if(conn != null) {try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
		}
		return Response.status(Status.CREATED).entity(order).build();
	}
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateOrder(OrderDto order) {
		ResultSet rs = null;
		PreparedStatement pst = null;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/workshop","root","p@ssw0rd");
			pst = conn.prepareStatement("UPDATE order  SET " + 
					"customer_id  = ? ," + 
					"product_id = ? ," + 
					"amount = ? ," + 
					"order_date= ?  ," + 
					"order_status= ?  ," +
					"WHERE order_Id = ?");
			int index = 1;
			pst.setLong(index++, order.getCustomerid());
			pst.setLong(index++, order.getProductid());
			pst.setLong(index++, order.getAmount());
			pst.setDate(index++, (Date)order.getOrderdate());
			pst.setString(index++, order.getOrderstatus());
			pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(rs != null) {try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
			if(pst != null) {try {
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
			if(conn != null) {try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
		}
		return Response.status(Status.OK).entity(order).build();
	}
	@DELETE
	@Path("{id}")
	
	public Response deleteProduct(@PathParam("id")Long orderid) {
		ResultSet rs = null;
		PreparedStatement pst = null;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/workshop","root","p@ssw0rd");
			pst = conn.prepareStatement("DELETE FROM order WHERE order_Id = ?");
			int index = 1;
			
			pst.setLong(index++, orderid);
			pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(rs != null) {try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
			if(pst != null) {try {
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
			if(conn != null) {try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
		}
		return null ;
	}
}
