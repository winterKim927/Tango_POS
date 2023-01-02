package posApp.domain;
//주문 내역을 담을 DTO
public class OrderDetail {
	private int order_summary_idx;
	private int order_detail_idx;
	private int tableNo;
	private String menuName;
	private int price;
	private int count;
	public int getOrder_summary_idx() {
		return order_summary_idx;
	}
	public void setOrder_summary_idx(int order_summary_idx) {
		this.order_summary_idx = order_summary_idx;
	}
	public int getOrder_detail_idx() {
		return order_detail_idx;
	}
	public void setOrder_detail_idx(int order_detail_idx) {
		this.order_detail_idx = order_detail_idx;
	}
	public int getTableNo() {
		return tableNo;
	}
	public void setTableNo(int tableNo) {
		this.tableNo = tableNo;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
}
