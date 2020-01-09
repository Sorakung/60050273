package th.co.cdgs.Controller;
import java.sql.Connection;
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

import th.co.cdgs.bean.ProductDto;
@Path("product")
public class ProductController {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProduct() {
		List<ProductDto> list = new ArrayList<>();
		ResultSet rs = null;
		PreparedStatement pst = null;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/workshop","root","p@ssw0rd");
			pst = conn.prepareStatement("SELECT product_Id,"+"product_name,"
			+"product_desc,price,active FROM product");
			rs = pst.executeQuery();
			ProductDto productDto = null;
			while(rs.next()) {
				productDto = new ProductDto();
				productDto.setProductid(rs.getLong("product_id"));
				productDto.setProductname(rs.getString("product_name"));
				productDto.setProductdesc(rs.getString("product_desc"));
				productDto.setPrice(rs.getLong("price"));
				productDto.setActive(rs.getString("active"));
				list.add(productDto);
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
	public Response createProduct(ProductDto product) {
		ResultSet rs = null;
		PreparedStatement pst = null;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/workshop","root","p@ssw0rd");
			pst = conn.prepareStatement("INSERT INTO product  (product_name ,product_desc,price,active)"
			+"VALUES (? ,? ,? ,?)");
			int index = 1;
			pst.setString(index++, product.getProductname());
			pst.setString(index++, product.getProductdesc());
			pst.setLong(index++, product.getPrice());
			pst.setString(index++, product.getActive());
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
		return Response.status(Status.CREATED).entity(product).build();
	}
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateProduct(ProductDto product) {
		ResultSet rs = null;
		PreparedStatement pst = null;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/workshop","root","p@ssw0rd");
			pst = conn.prepareStatement("UPDATE product  SET " + 
					"product_name  = ? ," + 
					"product_desc = ? ," + 
					"price = ? ," + 
					"active= ?  ," + 
					"WHERE product_Id = ?");
			int index = 1;
			pst.setString(index++, product.getProductname());
			pst.setString(index++, product.getProductdesc());
			pst.setLong(index++, product.getPrice());
			pst.setString(index++, product.getActive());
			pst.setLong(index++, product.getProductid());
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
		return Response.status(Status.OK).entity(product).build();
	}
	@DELETE
	@Path("{id}")
	
	public Response deleteProduct(@PathParam("id")Long productid) {
		ResultSet rs = null;
		PreparedStatement pst = null;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/workshop","root","p@ssw0rd");
			pst = conn.prepareStatement("DELETE FROM customer WHERE customer_Id = ?");
			int index = 1;
			
			pst.setLong(index++, productid);
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
