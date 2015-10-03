package calendarP;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.ItemSelectable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import  sun.audio.*;

import java.util.Timer;

public class calendar {
	
	String [] yearArray={"2000","2001","2002","2003","2004","2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015"};
	String [] monthArray={"January","February","March","April","May","June","July","August","September","October","November","December"};
	JComboBox yearCom = new JComboBox(yearArray);
	JComboBox monthCom = new JComboBox(monthArray);
	
	JPanel [] panel= new JPanel[42];
	JPanel panelBot = new JPanel ();
	JLayeredPane panelTop = new JLayeredPane ();
	
	JFrame mainFrame = new JFrame ("Calendar");
	
	JButton [] button = new JButton[42];
	
	JButton[] eventButton = new JButton[42];
	int count = 0;
	int monthCount=0;
	boolean trigger=true;
	
	
	JLabel sunday = new JLabel("Sunday",SwingConstants.CENTER);
	JLabel monday = new JLabel("Monday",SwingConstants.CENTER);
	JLabel tuesday = new JLabel("Tuesday",SwingConstants.CENTER);
	JLabel wednesday = new JLabel("Wednesday",SwingConstants.CENTER);;
	JLabel thursday = new JLabel("Thursday",SwingConstants.CENTER);
	JLabel friday = new JLabel("Friday",SwingConstants.CENTER);
	JLabel saturday = new JLabel("Saturday",SwingConstants.CENTER);
	JLabel year = new JLabel("Year : ");
	JLabel monthSearch = new JLabel("Month : ");
	JLabel hollow = new JLabel("Welcome to Hollow Awesome Calendar System !");
	
	public calendar() throws IOException, InterruptedException
	{
		yearCom.setBackground(Color.YELLOW);
		monthCom.setBackground(Color.YELLOW);
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		
		ActionListener refresh = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				 int monthCount =0;
				 String getMonth = (String)monthCom.getSelectedItem();
				 String getYear=(String)yearCom.getSelectedItem();
				 
				 int casted = Integer.parseInt(getYear);
				 if(getMonth.equals("January"))
				 {
					 monthCount=0;
				 }
				 if(getMonth.equals("February"))
				 {
					 monthCount=1;
				 }
				 if(getMonth.equals("March"))
				 {
					 monthCount=2;
				 }
				 if(getMonth.equals("April"))
				 {
					 monthCount=3;
				 }
				 if(getMonth.equals("May"))
				 {
					 monthCount=4;
				 }
				 if(getMonth.equals("June"))
				 {
					 monthCount=5;
				 }
				 if(getMonth.equals("July"))
				 {
					 monthCount=6;
				 }
				 if(getMonth.equals("August"))
				 {
					 monthCount=7;
				 }
				 if(getMonth.equals("September"))
				 {
					 monthCount=8;
				 }
				 if(getMonth.equals("October"))
				 {
					 monthCount=9;
				 }
				 if(getMonth.equals("November"))
				 {
					 monthCount=10;
				 }
				 if(getMonth.equals("December"))
				 {
					 monthCount=11;
				 }
				 
				 Calendar cal=Calendar.getInstance();
				 cal.set(Calendar.DATE,25);
				 cal.set(Calendar.DAY_OF_MONTH, 1);
				 cal.set(Calendar.MONTH,monthCount);
				 cal.set(Calendar.YEAR,casted);

				 DateFormat sdf=new SimpleDateFormat("EEEE");
				 updateFrame(sdf.format(cal.getTime()));
			}
			
		};
		
		ActionListener editDelete = new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				final JButton dummy = (JButton) e.getSource();
				String edit = dummy.getText();
				
				final JFrame editEvent = new JFrame ("Edit / Delete Event");
				JButton submitEdited = new JButton("Edit");
				JPanel submitPanel = new JPanel();
				
				submitPanel.setMaximumSize(new Dimension(50,50));
				submitPanel.add(submitEdited);
				editEvent.setSize(350, 350);
				editEvent.setLayout(new GridLayout(2,1));
				final JTextField editField = new JTextField(edit);
				editEvent.setVisible(true);
				editEvent.add(editField);
				editEvent.add(submitPanel);
				
				submitEdited.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						
						if(editField.getText().equals(""))
						{
							dummy.getParent().remove(dummy);
							for(int i =0 ;i<panel.length;i++)
							{
								panel[i].revalidate();
								panel[i].repaint();
							}
	
						}
						else
						{
							dummy.setText(editField.getText());
						}
						editEvent.dispose();
						alertMethod();
						
					}
					
				});
				
				
			}
			
		};
		
		
		ActionListener event = new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				
				// TODO Auto-generated method stub
				final JButton dummy = (JButton) e.getSource();
				if(dummy.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Invalid Date", "Invalid Date", JOptionPane.ERROR_MESSAGE); 
				}
				else
				{
					final JPanel panelDummy = (JPanel) dummy.getParent();
					final JFrame addEvent = new JFrame("Event");
					JPanel pane1 = new JPanel();
					JButton submit = new JButton("Submit");
					JPanel pane2 = new JPanel();
					JLabel description = new JLabel("Description : ",SwingConstants.CENTER);
					JLabel Time = new JLabel("Time : ",SwingConstants.CENTER);
					final JTextField jtf1 = new JTextField();
					final JTextField jtf2 = new JTextField();

					pane1.setLayout(new GridLayout(2,2));
					pane2.setLayout(new GridLayout(1,1));
					pane2.setMaximumSize(new Dimension(100,100));
					addEvent.setSize(350, 350);
					addEvent.setLayout(new BoxLayout(addEvent.getContentPane(),BoxLayout.PAGE_AXIS));
					addEvent.setVisible(true);
					
					pane1.add(description);
					pane1.add(jtf1);
					pane1.add(Time);
					pane1.add(jtf2);
					
					pane2.add(submit);
					submit.addActionListener(new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							
							eventButton[count].setText(jtf1.getText()+" "+jtf2.getText());
							panelDummy.add(eventButton[count]);
							panelDummy.revalidate();
							addEvent.dispose();
							alertMethod();
						}
						
					});
					
					addEvent.add(pane1);
					addEvent.add(pane2);
					
					count ++;
				}
				
				
			}

		};
		
		
		for(int i=0;i<panel.length;i++)
		{
			panel[i]= new JPanel();
			panel[i].setLayout(new GridLayout(3,1));
			button[i]= new JButton("");
			button[i].setBackground(Color.WHITE);
			button[i].addActionListener(event);
			panel[i].setBorder(border);
			panel[i].add(button[i]);
			eventButton[i] = new JButton();
			eventButton[i].setBackground(Color.YELLOW);
			eventButton[i].addActionListener(editDelete);
		}
		
		for(int h =6;h<=36;h++ )
		{
			button[h].setText(""+(h-5));
		}
		sunday.setBorder(border);
		monday.setBorder(border);
		tuesday.setBorder(border);
		wednesday.setBorder(border);
		thursday.setBorder(border);
		friday.setBorder(border);
		saturday.setBorder(border);

		BufferedImage month=ImageIO.read(new File("C:\\month.jpg"));
		JLabel monthTop=new JLabel(new ImageIcon(month));
		
		
		year.setForeground(Color.ORANGE);
		year.setFont(new Font(year.getFont().getName(), year.getFont().getStyle(), 25));
		
		monthSearch.setForeground(Color.ORANGE);
		monthSearch.setFont(new Font(monthSearch.getFont().getName(), monthSearch.getFont().getStyle(), 25));

		
		hollow.setForeground(Color.ORANGE);
		hollow.setFont(new Font(hollow.getFont().getName(), hollow.getFont().getStyle(), 25));
		
		
		
		panelTop.setMaximumSize(new Dimension(1500,200));
		
		
		panelBot.setLayout(new GridLayout(7,7,0,0));
		panelBot.setMaximumSize(new Dimension(1500,500));
		
		panelBot.add(sunday);
		panelBot.add(monday);
		panelBot.add(tuesday);
		panelBot.add(wednesday);
		panelBot.add(thursday);
		panelBot.add(friday);
		panelBot.add(saturday);
		
		for(int i=0;i<panel.length;i++)
		{
			panelBot.add(panel[i]);
			
		}	
		
		panelTop.add(monthTop,new Integer(1));
		panelTop.add(year,new Integer(2));
		panelTop.add(monthSearch,new Integer(2));
		panelTop.add(yearCom,new Integer(2));
		panelTop.add(monthCom,new Integer(2));
		panelTop.add(hollow,new Integer(2));
		monthTop.setBounds(0, 0, 1500, 200);
		year.setBounds(100, 75, 100, 100);
	    monthSearch.setBounds(700, 75, 100, 100);
	    yearCom.setBounds(300,100,100,50);
	    monthCom.setBounds(900,100,100,50);
	    hollow.setBounds(350,0,1000,100);

		
		
		mainFrame.setSize(1500, 700);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLayout(new BoxLayout(mainFrame.getContentPane(),BoxLayout.PAGE_AXIS));
		mainFrame.setVisible(true);
		mainFrame.add(panelTop);
		mainFrame.add(panelBot);
		

		
		
		
		yearCom.addActionListener(refresh);
		monthCom.addActionListener(refresh);
		
		Thread refreshS=new Thread();
		
		while(trigger)
		{
		   Thread.sleep(1000);
		   trigger=alertMethod();
		}
	}

	protected void updateFrame(String format) {
		// TODO Auto-generated method stub
		for(int i=0;i<panel.length;i++)
		{
			if(panel[i].getComponentCount()==2)
			{
				panel[i].remove(1);
				panel[i].revalidate();
				panel[i].repaint();
			}
			if(panel[i].getComponentCount()==3)
			{
				panel[i].remove(1);
				panel[i].remove(2);
				panel[i].revalidate();
				panel[i].repaint();
			}
			
		}
		if(format.equals("Monday"))
		{
			button[0].setText("");
			for(int h =1;h<=31;h++ )
			{
				button[h].setText(""+(h));
			}
			for(int x=32;x<=41;x++)
			{
				button[x].setText("");
			}
		}
		if(format.equals("Tuesday"))
		{
			button[0].setText("");
			button[1].setText("");
				for(int h =2;h<=32;h++ )
				{
					button[h].setText(""+(h-1));
				}
				for(int x=33;x<=41;x++)
				{
					button[x].setText("");
				}
		}
		if(format.equals("Wednesday"))
		{
			button[0].setText("");
			button[1].setText("");
			button[2].setText("");
			for(int h =3;h<=33;h++ )
			{
				button[h].setText(""+(h-2));
			}
			for(int x=34;x<=41;x++)
			{
				button[x].setText("");
			}
		}
		if(format.equals("Thursday"))
		{
			button[0].setText("");
			button[1].setText("");
			button[2].setText("");
			button[3].setText("");
			for(int h =4;h<=34;h++ )
			{
				button[h].setText(""+(h-3));
			}
			for(int x=35;x<=41;x++)
			{
				button[x].setText("");
			}
		}
		if(format.equals("Friday"))
		{
			button[0].setText("");
			button[1].setText("");
			button[2].setText("");
			button[3].setText("");
			button[4].setText("");
			for(int h =5;h<=35;h++ )
			{
				button[h].setText(""+(h-4));
			}
			for(int x=36;x<=41;x++)
			{
				button[x].setText("");
			}
		}
		if(format.equals("Saturday"))
		{
			button[0].setText("");
			button[1].setText("");
			button[2].setText("");
			button[3].setText("");
			button[4].setText("");
			button[5].setText("");
			for(int h =6;h<=36;h++ )
			{
				button[h].setText(""+(h-5));
			}
			for(int x=37;x<=41;x++)
			{
				button[x].setText("");
			}
		}
		if(format.equals("Sunday"))
		{
			for(int h =0;h<=30;h++ )
			{
				button[h].setText(""+(h+1));
			}
			for(int x=31;x<=41;x++)
			{
				button[x].setText("");
			}
		}
		
		
	}
	
	protected boolean alertMethod()
	{
      	int temp=0;
      	int hours=0;
      	int hours1=0;
      	int mins=0;
      	int mins1=0;
		int buttonDate=0;
		int yearCount=0;
		Timer eventTimer = new Timer();
		TimerTask task = new event();
		Date time = new Date();
		String getMonth = (String)monthCom.getSelectedItem();
		String getYear = (String)yearCom.getSelectedItem();
		int casted = Integer.parseInt(getYear);
		casted=casted-1900;
		for(int i=0;i<panel.length;i++)
		{
			if(getMonth.equals("January"))
			 {
				 monthCount=0;
			 }
			 if(getMonth.equals("February"))
			 {
				 monthCount=1;
			 }
			 if(getMonth.equals("March"))
			 {
				 monthCount=2;
			 }
			 if(getMonth.equals("April"))
			 {
				 monthCount=3;
			 }
			 if(getMonth.equals("May"))
			 {
				 monthCount=4;
			 }
			 if(getMonth.equals("June"))
			 {
				 monthCount=5;
			 }
			 if(getMonth.equals("July"))
			 {
				 monthCount=6;
			 }
			 if(getMonth.equals("August"))
			 {
				 monthCount=7;
			 }
			 if(getMonth.equals("September"))
			 {
				 monthCount=8;
			 }
			 if(getMonth.equals("October"))
			 {
				 monthCount=9;
			 }
			 if(getMonth.equals("November"))
			 {
				 monthCount=10;
			 }
			 if(getMonth.equals("December"))
			 {
				 monthCount=11;
			 }
			 for(int h=1;h<=31;h++)
			 {
				 if(button[i].getText().equals(""+h) && panel[i].getComponentCount()!=1)
					{
					       buttonDate=h;
					       JButton eventCreated;
					       eventCreated = (JButton) panel[i].getComponent(1);
					       String userInput=eventCreated.getText();
					       int length=userInput.length();
					       String hourCut = userInput.substring(length-7, length-5);
					       hours = Integer.parseInt(hourCut);
					       String minsCut = userInput.substring(length-4, length-2);
					       mins = Integer.parseInt(minsCut);
					       
					       if(panel[i].getComponentCount()==3)
					       {
					    	   JButton eventCreated1;
						       eventCreated1 = (JButton) panel[i].getComponent(2);
						       String userInput1=eventCreated1.getText();
						       int length1=userInput1.length();
						       String hourCut1 = userInput1.substring(length1-7, length1-5);
						       hours1 = Integer.parseInt(hourCut1);
						       String minsCut1 = userInput1.substring(length1-4, length1-2);
						       mins1 = Integer.parseInt(minsCut1);
						       System.out.println(hours1 + " "+ mins1);
					       }
					       
					}
				 
				 if((monthCount==time.getMonth() && casted==time.getYear() && buttonDate == time.getDate() &&   temp==0)
						 && (time.getHours()==hours || time.getHours()==hours1)
					    && (time.getMinutes()==mins || time.getMinutes()==mins1))
						 
					{
					        trigger=false;
							eventTimer.schedule(task, time);
							temp=1;
					}
			 }

		}
		return trigger;
	}

}



