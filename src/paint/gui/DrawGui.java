package paint.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import paint.PColor;
import paint.PShape;
import paint.entities.Painted;
import paint.managers.PaintedManager;

public class DrawGui extends javax.swing.JFrame {

	private static final long serialVersionUID = 2L;
	public static Integer user_id;

	private JFrame frame;
	private JPanel panel;
	private JPanel drawPanel;
	private JPanel ctrlPanel;
	private JButton exitBtn;
	private JButton lineBtn;
	private JButton rectBtn;
	private JButton circleBtn;
	private ButtonGroup colorRGrp;
	private JRadioButton blackRBtn;
	private JRadioButton blueRBtn;
	private JRadioButton greenRBtn;
	private JRadioButton redRBtn;

	PaintedManager pMng = PaintedManager.getInstance();
	Painted[] userShapes;

	private PShape shape = PShape.LINE;
	private PColor color = PColor.BLACK;
	private Integer startX, startY;
	private Integer endX, endY;

	public DrawGui() {
		initialize();
	}

	private void initialize() {

		frame = new JFrame();
		panel = new JPanel();
		drawPanel = new JPanel();
		ctrlPanel = new JPanel();
		lineBtn = new JButton();
		circleBtn = new JButton();
		rectBtn = new JButton();
		colorRGrp = new ButtonGroup();
		blackRBtn = new JRadioButton();
		redRBtn = new JRadioButton();
		greenRBtn = new JRadioButton();
		blueRBtn = new JRadioButton();
		exitBtn = new JButton();

		frame.setTitle("Draw");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(new FlowLayout());
	}

	private void showInterface() {

		/**
		 * Control Panel
		 */

		ctrlPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		ctrlPanel.setPreferredSize(new Dimension(100, 500));
		ctrlPanel.setLayout(new GridLayout(8, 0));

		/**
		 * Shape Buttons
		 */

		lineBtn.setFont(new java.awt.Font("Tahoma", 0, 12));
		lineBtn.setText("Line");
		lineBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				lineBtnActionPerformed(evt);
			}
		});
		ctrlPanel.add(lineBtn);

		circleBtn.setFont(new java.awt.Font("Tahoma", 0, 12));
		circleBtn.setText("Circle");
		circleBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				circleBtnActionPerformed(evt);
			}
		});
		ctrlPanel.add(circleBtn);

		rectBtn.setFont(new java.awt.Font("Tahoma", 0, 12));
		rectBtn.setText("Rectangle");
		rectBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				rectBtnActionPerformed(evt);
			}
		});
		ctrlPanel.add(rectBtn);

		/**
		 * Color Buttons
		 */

		colorRGrp.add(blackRBtn);
		blackRBtn.setFont(new java.awt.Font("Tahoma", 0, 12));
		blackRBtn.setSelected(true);
		blackRBtn.setText("Black");
		blackRBtn.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				blackRBtnItemStateChanged(evt);
			}
		});

		colorRGrp.add(redRBtn);
		redRBtn.setFont(new java.awt.Font("Tahoma", 0, 12));
		redRBtn.setText("Red");
		redRBtn.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				redRBtnItemStateChanged(evt);
			}
		});

		colorRGrp.add(greenRBtn);
		greenRBtn.setFont(new java.awt.Font("Tahoma", 0, 12));
		greenRBtn.setText("Green");
		greenRBtn.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				greenRBtnItemStateChanged(evt);
			}
		});

		colorRGrp.add(blueRBtn);
		blueRBtn.setFont(new java.awt.Font("Tahoma", 0, 12));
		blueRBtn.setText("Blue");
		blueRBtn.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				blueRBtnItemStateChanged(evt);
			}
		});

		ctrlPanel.add(blackRBtn);
		ctrlPanel.add(redRBtn);
		ctrlPanel.add(greenRBtn);
		ctrlPanel.add(blueRBtn);

		/**
		 * Exit Button
		 */

		exitBtn.setFont(new java.awt.Font("Tahoma", 0, 12));
		exitBtn.setText("Exit");
		exitBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				exitBtnActionPerformed(evt);
			}
		});
		ctrlPanel.add(exitBtn);

		/**
		 * Draw Panel
		 */

		drawPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		drawPanel.setPreferredSize(new Dimension(500, 500));
		drawPanel.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				drawPanelMousePressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				drawPanelMouseReleased(evt);
			}
		});

		drawPanel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent evt) {
				drawPanelMouseDragged(evt);
			}
		});

		drawPanel.setLayout(new GridLayout(1, 1));

		userShapes = pMng.loadAll(user_id);

		Surface surface = new Surface(userShapes);
		surface.setPreferredSize(new Dimension(500, 500));
		drawPanel.removeAll();
		drawPanel.add(surface);
		drawPanel.repaint();
		drawPanel.revalidate();

		/**
		 * MainFrame
		 */

		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.ipadx = 500;
		gbc.ipady = 500;
		panel.add(drawPanel);

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.ipadx = 100;
		gbc.ipady = 500;
		panel.add(ctrlPanel);

		frame.add(panel);
		frame.pack();
		frame.setVisible(true);

	}

	private void lineBtnActionPerformed(java.awt.event.ActionEvent evt) {
		shape = PShape.LINE;
	}

	private void circleBtnActionPerformed(java.awt.event.ActionEvent evt) {
		shape = PShape.CIRCLE;
	}

	private void rectBtnActionPerformed(java.awt.event.ActionEvent evt) {
		shape = PShape.RECT;
	}

	private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {
		SignInUp.main(null);
		frame.dispose();
	}

	private void blackRBtnItemStateChanged(java.awt.event.ItemEvent evt) {
		if (evt.getStateChange() == ItemEvent.SELECTED) {
			color = PColor.BLACK;
		}
	}

	private void redRBtnItemStateChanged(java.awt.event.ItemEvent evt) {
		if (evt.getStateChange() == ItemEvent.SELECTED) {
			color = PColor.RED;
		}
	}

	private void greenRBtnItemStateChanged(java.awt.event.ItemEvent evt) {
		if (evt.getStateChange() == ItemEvent.SELECTED) {
			color = PColor.GREEN;
		}
	}

	private void blueRBtnItemStateChanged(java.awt.event.ItemEvent evt) {
		if (evt.getStateChange() == ItemEvent.SELECTED) {
			color = PColor.BLUE;
		}
	}

	private void drawPanelMousePressed(java.awt.event.MouseEvent evt) {
		startX = evt.getX();
		startY = evt.getY();
	}

	private void drawPanelMouseDragged(java.awt.event.MouseEvent evt) {
		int dx = evt.getX();
		int dy = evt.getY();

		Painted[] livePainting = new Painted[userShapes.length + 1];
		for (int i = 0; i < userShapes.length; i++) {
			livePainting[i] = userShapes[i];
		}
		livePainting[userShapes.length] = new Painted(color, shape, startX, startY, dx, dy, user_id);

		Surface s = new Surface(livePainting);
		drawPanel.removeAll();
		s.setPreferredSize(new Dimension(500, 500));
		drawPanel.add(s);
		drawPanel.repaint();
		drawPanel.revalidate();
	}

	private void drawPanelMouseReleased(java.awt.event.MouseEvent evt) {
		endX = evt.getX();
		endY = evt.getY();

		if (startX != startY && endX != endY) {
			Painted p = new Painted();
			p.setColor(color);
			p.setShape(shape);
			p.setStartX(startX);
			p.setStartY(startY);
			p.setEndX(endX);
			p.setEndY(endY);
			p.setUserId(user_id);
			pMng.save(p);
		}

		userShapes = pMng.loadAll(user_id);

		Surface s = new Surface(userShapes);
		s.setPreferredSize(new Dimension(500, 500));
		drawPanel.removeAll();
		drawPanel.add(s);
		drawPanel.repaint();
		drawPanel.revalidate();

	}

	public static void main(String args[]) {

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				DrawGui window = new DrawGui();
				window.showInterface();
			}
		});
	}

}

class Surface extends JPanel {

	private static final long serialVersionUID = 3L;

	private Painted[] p;

	Surface() {

	}

	Surface(Painted[] p) {
		this.p = p;
	}

	private void doDrawing(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

		for (int i = 0; i < p.length; i++) {
			switch (p[i].getColor()) {
			case BLACK:
				g2d.setColor(Color.BLACK);
				break;
			case RED:
				g2d.setColor(Color.RED);
				break;
			case GREEN:
				g2d.setColor(Color.GREEN);
				break;
			case BLUE:
				g2d.setColor(Color.BLUE);
				break;
			}

			int startX = p[i].getStartX();
			int startY = p[i].getStartY();
			int endX = p[i].getEndX();
			int endY = p[i].getEndY();

			switch (p[i].getShape()) {
			case LINE:
				g2d.drawLine(startX, startY, endX, endY);
				break;
			case RECT:
				if (startX > endX && startY > endY)
					g2d.drawRect(endX, endY, startX - endX, startY - endY);
				else if (startX > endX && startY < endY)
					g2d.drawRect(endX, startY, startX - endX, endY - startY);
				else if (startX < endX && startY > endY)
					g2d.drawRect(startX, endY, endX - startX, startY - endY);
				else
					g2d.drawRect(startX, startY, endX - startX, endY - startY);
				break;
			case CIRCLE:
				if (startX > endX && startY > endY)
					g2d.drawOval(endX, endY, startX - endX, startY - endY);
				else if (startX > endX && startY < endY)
					g2d.drawOval(endX, startY, startX - endX, endY - startY);
				else if (startX < endX && startY > endY)
					g2d.drawOval(startX, endY, endX - startX, startY - endY);
				else
					g2d.drawOval(startX, startY, endX - startX, endY - startY);
				break;
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		doDrawing(g);
	}

}
