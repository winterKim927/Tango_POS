package posApp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;

import posApp.dao.OrderDetailDAO;
import posApp.dao.OrderSummaryDAO;
import posApp.domain.OrderDetail;
import posApp.domain.OrderSummary;
import posApp.model.RevenueDetailModel;
import posApp.model.RevenueSummaryModel;

public class RevenuePage extends Page{
	/*-----------일별 내역을 보여줄 달력------------*/
	WhitePanel p_calander; //달력화면을 담을 컨테이너
	
	//제목패널
	WhitePanel p_title;
	JButton bt_prev;
	JLabel la_title;
	JButton bt_next;
	
	//요일패널
	WhitePanel p_day;
	DayCell[] dayCells = new DayCell[7];
	String[] dayTitle = { "Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat" };
	
	//날짜패널
	WhitePanel p_date;
	DateCell[][] dateCells = new DateCell[6][7];
	
	
	/*----------달력을 누르면 보여질 상세내역--------*/
	WhitePanel p_detail;//상세내역들을 담을 컨테이너
	
	//상단 날짜
	WhitePanel p_dTitle;
	JButton bt_prevDay;
	JLabel la_dTitle;
	JButton bt_nextDay;
	
	//상세내역 테이블
	//요약테이블
	WhitePanel p_sumTable;
	JTable sumTable;
	RevenueSummaryModel sumModel;
	JScrollPane sumScroll;
	
	//상세테이블
	WhitePanel p_detailTable;
	JTable detailTable;
	RevenueDetailModel detailModel;
	JScrollPane detailScroll;
	
	JButton bt_return;
	Calendar currentObj = Calendar.getInstance();
	
	public static final int CALENDARPAGE = 0;
	public static final int DETAILPAGE = 1;
	
	OrderSummaryDAO sumDAO = new OrderSummaryDAO();
	OrderDetailDAO detailDAO = new OrderDetailDAO();
	public RevenuePage(PosMain main) {
		super(main);
		p_calander = new WhitePanel();
		p_calander.setPreferredSize(PosMain.CONTENTSIZE);
		//제목패널
		p_title = new WhitePanel();
		bt_prev = new JButton("<");
		la_title = new JLabel("2022년 12월");
		bt_next = new JButton(">");
		
		p_title.add(bt_prev);
		p_title.add(la_title);
		p_title.add(bt_next);
		
		p_title.setPreferredSize(new Dimension(1000, 50));
		Font font = new Font("NANUM", Font.BOLD, 15);
		bt_prev.setFont(font);
		la_title.setFont(new Font("NANUM", Font.BOLD, 20));
		bt_next.setFont(font);
		
		//요일패널
		p_day = new WhitePanel();
		p_day.setLayout(new GridLayout(1, 7));
		p_day.setPreferredSize(new Dimension(1000, 50));
		createDayCell();
		
		//날짜패널
		p_date = new WhitePanel();
		p_date.setLayout(new GridLayout(6,7));
		p_date.setPreferredSize(new Dimension(1000, 480));
		createDateCell();
		
		/*----------달력을 누르면 보여질 상세내역--------*/
		p_detail = new WhitePanel(); //컨테이너
		
		
		//상단
		p_dTitle = new WhitePanel();
		bt_prevDay = new JButton("<");
		la_dTitle = new JLabel("");
		bt_nextDay = new JButton(">");
		
		//중앙
		//요약테이블
		p_sumTable = new WhitePanel();
		sumTable = new JTable(sumModel = new RevenueSummaryModel());
		sumScroll = new JScrollPane(sumTable);
		bt_return = new JButton("뒤로");

		//상세테이블
		p_detailTable = new WhitePanel();
		detailTable = new JTable(detailModel = new RevenueDetailModel());
		detailScroll = new JScrollPane(detailTable);
		
		//디자인
		p_detail.setPreferredSize(PosMain.CONTENTSIZE);
		p_detail.setBackground(Color.white);
		
		p_dTitle.setPreferredSize(new Dimension(1000,50));
		la_dTitle.setFont(new Font("NANUM", Font.BOLD, 20));
		
		Dimension d = new Dimension(450, 420);
		Border sumBorder = BorderFactory.createTitledBorder("일별 요약");
		p_sumTable.setBorder(sumBorder);
		sumTable.setPreferredSize(d);
		sumTable.setRowHeight(20);
		sumScroll.setPreferredSize(d);
		
		Border detailBorder = BorderFactory.createTitledBorder("일별 상세");
		p_detailTable.setBorder(detailBorder);
		detailTable.setPreferredSize(d);
		detailScroll.setPreferredSize(d);
		
		//조립
		p_dTitle.add(bt_prevDay);
		p_dTitle.add(la_dTitle);
		p_dTitle.add(bt_nextDay);
		
		p_sumTable.add(sumScroll);
		p_detailTable.add(detailScroll);
		
		/*----------전체조립--------*/
		p_calander.add(p_title);
		p_calander.add(p_day);
		p_calander.add(p_date);
		
		p_detail.add(p_dTitle);
		p_detail.add(p_sumTable);
		p_detail.add(p_detailTable);
		p_detail.add(bt_return);
		
		add(p_calander);
		add(p_detail);
		
		calculate();
		addBtAction();
		addTableMouseListener();
	}
	public void showHide(int n) {
		if (n == CALENDARPAGE) {
			p_calander.setVisible(true);
			p_detail.setVisible(false);
		} else {
			p_calander.setVisible(false);
			p_detail.setVisible(true);
		}
	}
	
	public void addBtAction() {
		bt_prev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adjustYYMM(e);
			}
		});
		
		bt_next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adjustYYMM(e);
			}
		});
		
		bt_return.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showHide(CALENDARPAGE);
			}
		});
		
		bt_prevDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentObj.set(Calendar.DATE, currentObj.get(Calendar.DATE)-1);
				
				setDetailPage(getFullDate());
			}
		});
		
		bt_nextDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentObj.set(Calendar.DATE, currentObj.get(Calendar.DATE)+1);
				
				setDetailPage(getFullDate());
			}
		});
	}
	
	public void adjustYYMM(ActionEvent e) {
		int adjust = 0;
		if (e.getSource() == bt_next) {
			adjust = 1;
		} else if (e.getSource() == bt_prev) {
			adjust = -1;
		}
		int mm = currentObj.get(Calendar.MONTH);
		currentObj.set(Calendar.MONTH, mm + adjust);
		System.out.println(currentObj.get(Calendar.MONTH));
		calculate();
	}
	
	public void createDayCell() {
		for (int i = 0; i < dayCells.length; i++) {
			dayCells[i] = new DayCell(dayTitle[i]);
			p_day.add(dayCells[i]);
		}
	}
	
	public void createDateCell() {
		for (int i = 0; i < dateCells.length; i++) {
			for (int j = 0; j < dateCells[i].length; j++) {
				dateCells[i][j] = new DateCell("","");
				p_date.add(dateCells[i][j]);
			}
		}
	}
	
	public void calculate() {
		printTitle();
		printDate();
	}
	
	public void printTitle() {
		int yy = currentObj.get(Calendar.YEAR);
		int mm = currentObj.get(Calendar.MONTH);
		String str = yy + "년 " + (mm + 1) + "월";
		la_title.setText(str);
	}
	
	public int getStartDayOfWeek() {
		Calendar cal = Calendar.getInstance();
		int yy = currentObj.get(Calendar.YEAR);
		int mm = currentObj.get(Calendar.MONTH);
		cal.set(yy, mm, 1);
		int day = cal.get(Calendar.DAY_OF_WEEK);
		return day;
	}

	public int getLastDateOfMonth() {
		Calendar cal = Calendar.getInstance();
		int yy = currentObj.get(Calendar.YEAR);
		int mm = currentObj.get(Calendar.MONTH);
		cal.set(yy, mm+1, 0);
		int date = cal.get(Calendar.DATE);
		return date;
	}
	
	public void printDate() {
		int startDay = getStartDayOfWeek();
		int lastDay = getLastDateOfMonth();
		int n = 0;
		int t = 0;
		for (int i = 0; i < dateCells.length; i++) {
			for (int j = 0; j < dateCells[i].length; j++) {
				DateCell cell = dateCells[i][j];
				n++;
				if (n >= startDay && t < lastDay) {
					t++;
					if(j%7 == 6) cell.titleColor = Color.blue;
					if(j%7 == 0) cell.titleColor = Color.red;
					cell.title = t + "";
					cell.color = Color.white;
					cell.content = "";
					printContent(cell);
					addCellClickAction(cell);
				} else {
					cell.title = "";
					cell.color = Color.white;
					cell.content = "";
				}
			}
		}
		p_date.repaint();
	}
	
	public void printContent(DateCell cell) {
		int dailyRevenue = 0;
		String fullDate = getFullDate(cell);
		dailyRevenue = sumDAO.selectDailyRevenue(fullDate);
		if(dailyRevenue>0) {
			cell.color = new Color(236,168,231);
			cell.content = dailyRevenue+"원";
		}
	}
	
	public String getFullDate() {
		String yy = Integer.toString(currentObj.get(Calendar.YEAR));
		String mm = Integer.toString(currentObj.get(Calendar.MONTH)+1);
		if(mm.length() == 1) {
			mm = 0+mm;
		}
		String dd = Integer.toString(currentObj.get(Calendar.DATE));
		if(dd.length()==1) {
			dd = 0+dd;
		}
		String fullDate = yy+mm+dd;
		return fullDate;
	}
	
	public String getFullDate(DateCell cell) {
		String yy = Integer.toString(currentObj.get(Calendar.YEAR));
		String mm = Integer.toString(currentObj.get(Calendar.MONTH)+1);
		if(mm.length() == 1) {
			mm = 0+mm;
		}
		String dd = cell.title;
		if(dd.length()==1) {
			dd = 0+dd;
		}
		String fullDate = yy+mm+dd;
		return fullDate;
	}
	
	
	
	public void setDetailPage(String fullDate) {
		//타이틀 날짜 설정
		int yy = currentObj.get(Calendar.YEAR);
		int mm = currentObj.get(Calendar.MONTH);
		int dd = currentObj.get(Calendar.DATE);
		String str = yy+"년 "+(mm+1)+"월 "+dd+"일";
		la_dTitle.setText(str);
		
		//요약테이블 설정
		ArrayList<OrderSummary> sumList = new ArrayList();
		sumList = (ArrayList)sumDAO.selectAll(fullDate);
		sumModel.setList(sumList);
		sumTable.setPreferredSize(new Dimension(450, 20*sumList.size()));
		sumTable.updateUI();
		detailModel.setList(new ArrayList());
		detailTable.updateUI();
	}
	
	public void addCellClickAction(DateCell cell) {
		cell.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				showHide(DETAILPAGE);
				currentObj.set(Calendar.DATE, Integer.parseInt(cell.title));
				setDetailPage(getFullDate());
			}
		});
	}
	
	public void addTableMouseListener() {
		sumTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				ArrayList<OrderDetail> list = new ArrayList();
				int row = sumTable.getSelectedRow();
				int col = 0;
				int sumIdx = (int)sumTable.getValueAt(row, col);
				list = (ArrayList)detailDAO.selectUsingSumIdx(sumIdx);
				if(list.size()>0) {
					detailModel.setList(list);
				}
				p_detailTable.updateUI();
			}
		});
	}
}
