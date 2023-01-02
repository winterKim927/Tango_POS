package posApp;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import util.DBManager;

public class PosMain extends JFrame{
	InfoPanel p_info;
	Page[] pages = new Page[4];
	
	public static final int HOMEPAGE = 0;
	public static final int HALLPAGE = 1;
	public static final int REVENUEPAGE = 2;
	public static final int STOCKPAGE = 3;
	public static final Dimension INFOPANELSIZE = new Dimension(1000, 120);
	public static final Dimension CONTENTSIZE = new Dimension(1000, 680);
	boolean isAdmin = false;
	
	DBManager manager = DBManager.getInstance();
	
	public PosMain(boolean isAdmin) {
		this.isAdmin = isAdmin;
		System.out.println(isAdmin);
		setLayout(null);
		p_info = new InfoPanel(this);
		createPages();
		pages[HOMEPAGE].setVisible(true);
		
		add(p_info, BorderLayout.NORTH);
		
		if(isAdmin) setTitle("[관리자 모드]");
			
		setSize(1015,800);
		setVisible(true);
		setLocationRelativeTo(null);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				powerOff();
			}
		});
	}
	
	public void createPages() {
		pages[0] = new HomePage(this);
		pages[1] = new HallPage(this);
		if (isAdmin) {
			pages[2] = new RevenuePage(this);
			pages[3] = new AdminPage(this);
		} else {
			pages[2] = new Page(null);
			pages[3] = new Page(null);
		}
		for (int i = 0; i < pages.length; i++) {
			add(pages[i]);
		}
	}
	
	public void showHide(int n) {
		for (int i = 0; i < pages.length; i++) {
			if(i == n) {
				pages[i].setVisible(true);
			} else {
				pages[i].setVisible(false);
			}
		}
	}
	
	public Page getPages(int n) {
		return pages[n];
	}
	
	public void powerOff() {
		manager.release(manager.getConnection());
		System.exit(0);
	}
}


