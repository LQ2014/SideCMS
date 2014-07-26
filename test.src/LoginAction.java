import com.side.util.annotation.Deal;


public class LoginAction {
	
	@Deal(path="/login.do", error="", type="json", forward="")
	public void login() {
		
	}
	
}
