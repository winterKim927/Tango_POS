package posApp.domain;

import java.sql.Date;

public class OrderSummary {
	private int order_summary_idx;
	private int payment_number;
	private int totalprice;
	private Date paydate;
	
	public int getOrder_summary_idx() {
		return order_summary_idx;
	}
	public void setOrder_summary_idx(int order_summary_idx) {
		this.order_summary_idx = order_summary_idx;
	}
	public int getPayment_number() {
		return payment_number;
	}
	public void setPayment_number(int payment_number) {
		this.payment_number = payment_number;
	}
	public int getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}
	public Date getPaydate() {
		return paydate;
	}
	public void setPaydate(Date paydate) {
		this.paydate = paydate;
	}
	
}
