package es.springrestproject.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="items_invoice")
public class ItemInvoice implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	//@NotEmpty
	private Integer quantity;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn
	private Product product;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Double getSubtotal() {
		return quantity.doubleValue()*product.getPrice();
	}

	private static final long serialVersionUID = 1L;
	
}
