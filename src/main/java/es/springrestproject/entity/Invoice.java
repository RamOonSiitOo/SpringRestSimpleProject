package es.springrestproject.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "invoices")
public class Invoice implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty
	private String description;
	private String observation;

	//@NotNull
	@Column(name = "create_at")
	@Temporal(TemporalType.DATE) // Conversion to Date time of DB to App and viceversa
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date createAt;

	//@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	private Client client;
	
	//@NotNull
	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="invoice_id") //Foreign Key
	private List<ItemInvoice> itemInvoiceList;

	public Invoice() {
		this.itemInvoiceList = new ArrayList<ItemInvoice>();
	}
	
	@PrePersist //Creation automatically with no attended handle
	public void prePersist() {
		this.createAt = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<ItemInvoice> getItemInvoiceList() {
		return itemInvoiceList;
	}

	public void setItemInvoiceList(List<ItemInvoice> itemInvoiceList) {
		this.itemInvoiceList = itemInvoiceList;
	}

	public void addInvoiceItem(ItemInvoice item) {
		this.itemInvoiceList.add(item);
	}

	public Double getTotal() {
		Double total = 0.0;
		for(ItemInvoice item : this.itemInvoiceList) {
			total+=item.getSubtotal();
		}
		return total;
	}

	// Serializable Own property
	private static final long serialVersionUID = 1L;
}
