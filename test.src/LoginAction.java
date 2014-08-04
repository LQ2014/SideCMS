
import org.apache.log4j.Logger;

import com.side.util.annotation.Deal;


public class LoginAction {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Deal(path="/login.do", type="json", forward="")
	public String login() {
		System.out.println("成功处理请求");
		return null;
	}
	
	@Deal(path="/logout.do", forward="/test.jsp")
	public void logout() {
		
	}
	
}
