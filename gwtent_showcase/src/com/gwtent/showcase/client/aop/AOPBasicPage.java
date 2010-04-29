package com.gwtent.showcase.client.aop;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RichTextArea;
import com.gwtent.client.template.HTMLTemplate;
import com.gwtent.client.template.HTMLWidget;
import com.gwtent.showcase.client.BaseShowCasePanel;
import com.gwtent.showcase.client.aop.Phone.Receiver;


@HTMLTemplate("AOPBasicPage.html")
public class AOPBasicPage extends BaseShowCasePanel implements Interceptors.Log{

	public AOPBasicPage(String html) {
		super(html);
		
		Interceptors.setLog(this);
		
		for (Number number : Phone.RECEIVERS.keySet()){
			cbNumbers.addItem(Phone.RECEIVERS.get(number).getName(), number.toString());
		}
		
	}

	
	public String[] getResourceFileNames() {
		return new String[]{"AOPBasicPage.java", "Phone.java", "Interceptors.java", "../../aop/guicematcher/TestMatcher.java", "AOPBasicPage.html"};
	}

	public String getCaseName() {
		return "Basic AOP Page";
	}
	
	private Number getCurrentNumber(){
		if (cbNumbers.getSelectedIndex() >= 0)
			return Integer.valueOf(cbNumbers.getValue(cbNumbers.getSelectedIndex()));
		else{
			Window.alert("Please select a number first.");
			return null;
		}
	}
	
	@HTMLWidget
	protected Label lblResult = new Label("Please make a call.");
	
	@HTMLWidget
	protected ListBox cbNumbers = new ListBox(false);
	

	@HTMLWidget
	protected Button btnCall = new Button("Call", new ClickHandler(){

		public void onClick(ClickEvent event) {
			beforeCall();
			try {
				Phone phone = GWT.create(Phone.class);
				showResult(phone.call(getCurrentNumber()));
			} catch (Exception e) {
				println("You phone blocked cause of excpetion " + e);
			}
		}});
	
	
	@HTMLWidget
	protected Button btnCallDirect = new Button("CallDirect", new ClickHandler(){

		public void onClick(ClickEvent event) {
			beforeCall();
			Phone phone = new Phone();
			showResult(phone.call(getCurrentNumber()));
		}});
	
	
	@HTMLWidget
	protected RichTextArea memoAOPInfo_Basic = new RichTextArea();
	
	private void showResult(Receiver r){
		lblResult.setText("The Result " + r.getName());
	}
	
	private void beforeCall(){
		memoAOPInfo_Basic.setHTML("");
	}


	public void println(String str) {
		memoAOPInfo_Basic.setHTML(memoAOPInfo_Basic.getHTML() + str + "<br>");
	}
}