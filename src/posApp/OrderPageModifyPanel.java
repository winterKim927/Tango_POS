package posApp;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class OrderPageModifyPanel extends JPanel{
	JButton bt_prev, bt_cancel, bt_cancelAll, bt_commit;
	public OrderPageModifyPanel() {
		bt_prev = new JButton("이전");
		bt_cancel = new JButton("선택취소");
		bt_cancelAll = new JButton("전체취소");
		bt_commit = new JButton("결제");
		
		add(bt_prev);
		add(bt_cancel);
		add(bt_cancelAll);
		add(bt_commit);
	}
}
